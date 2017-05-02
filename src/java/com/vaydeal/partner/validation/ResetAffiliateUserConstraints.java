/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.validation;

import com.vaydeal.partner.db.DB;
import com.vaydeal.partner.db.DBConnect;
import com.vaydeal.partner.db.MongoConnect;
import com.vaydeal.partner.intfc.validation.ResetAffiliateUserValidator;
import com.vaydeal.partner.message.CorrectMsg;
import com.vaydeal.partner.message.ErrMsg;
import com.vaydeal.partner.mongo.mod.AffiliateID;
import com.vaydeal.partner.regx.RegX;
import com.vaydeal.partner.req.mod.ResetAffiliateUser;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @company techvay
 * @author rifaie
 */
public class ResetAffiliateUserConstraints implements ResetAffiliateUserValidator {

    private final ResetAffiliateUser req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public ResetAffiliateUserConstraints(ResetAffiliateUser req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public String validateUserId() throws Exception {
        String valid = ErrMsg.ERR_UID;
        String regX = RegX.REGX_DIGIT;
        String param = req.getUser_id();
        if (validate(param, regX)) {
            ArrayList<String> al = new ArrayList<>();
            dbc.getUserDetails(param, al,req.getAffiliate());
            if (al.size() == 2) {
                if (!param.equals(req.getAffiliate_user_id())) {
                    req.setEmail(al.get(0));
                    req.setName(al.get(1));
                    valid = CorrectMsg.CORRECT_UID;
                } else {
                    valid = ErrMsg.ERR_UID_OWN;
                }
            } else {
                valid = ErrMsg.ERR_UID_NOT_EXISTS;
            }
        }
        return valid;
    }

    @Override
    public String validateAccessToken() throws Exception {
        String at = req.getAt();
        String valid = ErrMsg.ERR_ACCESS_TOKEN;
        AffiliateID aff = mdbc.getAffiliateID(at);
        if (!aff.getUser_id().startsWith(ErrMsg.ERR_MESSAGE)) {
            if (dbc.checkNBAffiliateID(aff.getUser_id())) {
                valid = CorrectMsg.CORRECT_ACCESS_TOKEN;
                req.setAffiliate_user_id(aff.getUser_id());
                req.setUser_type(aff.getUser_type());
                req.setAffiliate(aff.getAffiliate());
            } else {
                valid = ErrMsg.ERR_AT_BLOCKED;
            }
        }
        return valid;
    }

    @Override
    public String validateUserType(String type) throws Exception {
        String valid = ErrMsg.ERR_USER_TYPE;
        String uType = req.getUser_type();
        if (uType.matches("super")) {
            valid = CorrectMsg.CORRECT_USER_TYPE;
        }
        return valid;
    }

    @Override
    public boolean validate(String value, String regX) {
        boolean valid = false;
        if (value.matches(regX)) {
            valid = true;
        }
        return valid;
    }

    @Override
    public void closeConnection() throws SQLException {
        dbc.closeConnection();
        mdbc.closeConnection();
    }
}
