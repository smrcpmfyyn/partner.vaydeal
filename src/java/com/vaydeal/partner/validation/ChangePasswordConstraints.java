/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.validation;

import com.vaydeal.partner.db.DB;
import com.vaydeal.partner.db.DBConnect;
import com.vaydeal.partner.db.MongoConnect;
import com.vaydeal.partner.intfc.validation.ChangePasswordValidator;
import com.vaydeal.partner.message.CorrectMsg;
import com.vaydeal.partner.message.ErrMsg;
import com.vaydeal.partner.mongo.mod.AffiliateID;
import com.vaydeal.partner.regx.RegX;
import com.vaydeal.partner.req.mod.ChangePassword;
import java.sql.SQLException;
import java.util.List;

/**
 * @company techvay
 * @author rifaie
 */
public class ChangePasswordConstraints implements ChangePasswordValidator {

    private final ChangePassword req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public ChangePasswordConstraints(ChangePassword req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public String validateCurrentPassword() throws Exception {
        String valid = ErrMsg.ERR_PASSWORD;
        String regX = RegX.REGX_B64ENCODE;
        String uname = req.getAffiliate_user_id();
        List<String> passDSalt = dbc.getPassDSalt(uname);
        req.setSalt(passDSalt.get(0));
        req.changeCurrentPassword();
        req.changeNewPassword();
        String apassword = passDSalt.get(1);
        String password = req.getCurrentPassword();
        String newPass = req.getNewPassword();
        if (validate(password, regX)) {
            if (apassword.equals(password)) {
                if (validate(newPass, regX)) {
                    valid = CorrectMsg.CORRECT_PASSWORD;
                }else{
                    valid = ErrMsg.ERR_NEW_PASSWORD;
                }
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
        if (uType.matches("super") || uType.matches("sub")) {
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
