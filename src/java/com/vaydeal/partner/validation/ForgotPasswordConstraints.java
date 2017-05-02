/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.validation;

import com.vaydeal.partner.db.DB;
import com.vaydeal.partner.db.DBConnect;
import com.vaydeal.partner.intfc.validation.ForgotPasswordValidator;
import com.vaydeal.partner.message.CorrectMsg;
import com.vaydeal.partner.message.ErrMsg;
import com.vaydeal.partner.regx.RegX;
import com.vaydeal.partner.req.mod.ForgotPassword;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @company techvay
 * @author rifaie
 */
public class ForgotPasswordConstraints implements ForgotPasswordValidator {

    private final ForgotPassword req;
    private final DBConnect dbc;

    public ForgotPasswordConstraints(ForgotPassword rp) throws Exception {
        this.req = rp;
        this.dbc = DB.getConnection();
    }

    @Override
    public String validateUID() throws Exception {
        String valid = ErrMsg.ERR_UID;
        String regX = RegX.REGX_DIGIT;
        String param = req.getUid();
        if (validate(param, regX)) {
            if (dbc.checkNBAffiliateID(param)) {
                ArrayList<String> al = dbc.getUserAttributes(param);
                req.setAffiliate(al.get(0));
                req.setUser_type(al.get(1));
                valid = CorrectMsg.CORRECT_UID;
            }else{
                valid = ErrMsg.ERR_UID_BLOCKED;
            }
        }
        return valid;
    }

    @Override
    public String validateEmail() throws Exception {
        String valid = ErrMsg.ERR_EMAIL;
        String regX = RegX.REGX_EMAIL;
        String param = req.getEmail();
        if (validate(param, regX)) {
            if (dbc.checkEmail(param,req.getUid())) {
                valid = CorrectMsg.CORRECT_EMAIL;
            }else{
                valid = ErrMsg.ERR_EMAIL_NOT_EXISTS;
            }
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
