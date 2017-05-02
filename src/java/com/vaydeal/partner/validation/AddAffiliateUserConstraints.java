/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.validation;

import com.vaydeal.partner.db.DB;
import com.vaydeal.partner.db.DBConnect;
import com.vaydeal.partner.db.MongoConnect;
import com.vaydeal.partner.intfc.validation.AddAffiliateUserValidator;
import com.vaydeal.partner.message.CorrectMsg;
import com.vaydeal.partner.message.ErrMsg;
import com.vaydeal.partner.mongo.mod.AffiliateID;
import com.vaydeal.partner.regx.RegX;
import com.vaydeal.partner.req.mod.AddAffiliateUser;
import java.sql.SQLException;

/**
 * @company techvay
 * @author rifaie
 */
public class AddAffiliateUserConstraints implements AddAffiliateUserValidator {

    private final AddAffiliateUser req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public AddAffiliateUserConstraints(AddAffiliateUser req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }
    
    @Override
    public String validateName() throws Exception {
        String valid = ErrMsg.ERR_NAME;
        String regX = RegX.REGX_STRING;
        String name = req.getName();
        if (validate(name, regX)) {
            valid = CorrectMsg.CORRECT_NAME;
        }
        return valid;
    }

    @Override
    public String validateMobile() throws Exception {
        String valid = ErrMsg.ERR_MOBILE;
        String regX = RegX.REGX_MOBILE;
        String mobile = req.getMobile();
        if (validate(mobile, regX)) {
            if (dbc.checkAffiliateUserMobile(mobile) == 0) {
                valid = CorrectMsg.CORRECT_MOBILE;
            } else {
                valid = ErrMsg.ERR_MOBILE_EXISTS;
            }
        }
        return valid;
    }

    @Override
    public String validateEmail() throws Exception {
        String valid = ErrMsg.ERR_EMAIL;
        String regX = RegX.REGX_EMAIL;
        String email = req.getEmail();
        if (validate(email, regX)) {
            if (dbc.checkAffiliateUserEmail(email) == 0) {
                req.setNew_user_id(dbc.getNewAffiliateUserId());
                valid = CorrectMsg.CORRECT_EMAIL;
            } else {
                valid = ErrMsg.ERR_EMAIL_EXISTS;
            }
        }
        return valid;
    }

    @Override
    public String validateDesignation() throws Exception {
        String valid = ErrMsg.ERR_DESIGNATION;
        String regX = RegX.REGX_STRING_UPPER_AND_LOWER;
        String param = req.getDesignation();
        if (validate(param, regX)) {
            valid = CorrectMsg.CORRECT_DESIGNATION;
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
