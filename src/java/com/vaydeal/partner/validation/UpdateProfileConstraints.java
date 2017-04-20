/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.validation;

import com.vaydeal.partner.db.DB;
import com.vaydeal.partner.db.DBConnect;
import com.vaydeal.partner.db.MongoConnect;
import com.vaydeal.partner.intfc.validation.UpdateProfileValidator;
import com.vaydeal.partner.message.CorrectMsg;
import com.vaydeal.partner.message.ErrMsg;
import com.vaydeal.partner.mongo.mod.AffiliateID;
import com.vaydeal.partner.regx.RegX;
import com.vaydeal.partner.req.mod.UpdateProfile;
import java.sql.SQLException;

/**
 * @company techvay
 * @author rifaie
 */
public class UpdateProfileConstraints implements UpdateProfileValidator {

    private final UpdateProfile req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public UpdateProfileConstraints(UpdateProfile req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public String validateName() throws Exception {
        String valid = ErrMsg.ERR_NAME;
        String regX = RegX.REGX_STRING_UPPER_AND_LOWER;
        String name = req.getName();
        if (validate(name, regX)) {
            valid = CorrectMsg.CORRECT_NAME;
        }
        return valid;
    }

    @Override
    public String validateAddress1() throws Exception {
        String valid = ErrMsg.ERR_ADDRESS1;
        String regX = RegX.REGX_STRING_UPPER_LOWER_AND_NUMBER;
        String param = req.getAddress1();
        if (validate(param, regX)) {
            valid = CorrectMsg.CORRECT_ADDRESS1;
        }
        return valid;
    }

    @Override
    public String validateAddress2() throws Exception {
        String valid = ErrMsg.ERR_ADDRESS2;
        String regX = RegX.REGX_STRING_UPPER_LOWER_AND_NUMBER;
        String param = req.getAddress2();
        if (validate(param, regX)) {
            valid = CorrectMsg.CORRECT_ADDRESS2;
        }
        return valid;
    }

    @Override
    public String validatePin() throws Exception {
        String valid = ErrMsg.ERR_PIN;
        String regX = RegX.REGX_DIGIT;
        String param = req.getPin();
        if (validate(param, regX)) {
            valid = CorrectMsg.CORRECT_PIN;
        }
        return valid;
    }

    @Override
    public String validateMobile() throws Exception {
        String valid = ErrMsg.ERR_MOBILE;
        String regX = RegX.REGX_MOBILE;
        String param = req.getMobile();
        if (validate(param, regX)) {
            valid = CorrectMsg.CORRECT_MOBILE;
        }
        return valid;
    }

    @Override
    public String validateEmail() throws Exception {
        String valid = ErrMsg.ERR_EMAIL;
        String regX = RegX.REGX_EMAIL;
        String param = req.getEmail();
        if (validate(param, regX)) {
            if (dbc.checkEmail(param,req.getAffiliate_user_id())) {
                valid = CorrectMsg.CORRECT_EMAIL;
            } else {
                valid = ErrMsg.ERR_EMAIL_NOT_EXISTS;
            }
        }
        return valid;
    }

    @Override
    public String validateCompany() throws Exception {
        String valid = ErrMsg.ERR_COMPANY;
        String regX = RegX.REGX_COMPANY;
        String param = req.getCompany();
        if (validate(param, regX)) {
            if (dbc.checkComapany(param, req.getEmail())) {
                valid = CorrectMsg.CORRECT_COMPANY;
            } else {
                valid = ErrMsg.ERR_COMPANY_USER;
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
