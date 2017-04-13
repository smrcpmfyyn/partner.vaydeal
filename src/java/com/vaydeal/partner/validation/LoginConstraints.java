/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.validation;

import com.vaydeal.partner.db.DB;
import com.vaydeal.partner.db.DBConnect;
import com.vaydeal.partner.intfc.validation.LoginValidator;
import com.vaydeal.partner.message.CorrectMsg;
import com.vaydeal.partner.message.ErrMsg;
import com.vaydeal.partner.regx.RegX;
import com.vaydeal.partner.req.mod.Login;
import java.sql.SQLException;
import java.util.List;

/**
 * @company techvay
 * @author rifaie
 */
public final class LoginConstraints implements LoginValidator {

    private final Login req;
    private final DBConnect dbc;

    /**
     *
     * @param log
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public LoginConstraints(Login log) throws ClassNotFoundException, SQLException {
        this.req = log;
        dbc = DB.getConnection();
    }

    @Override
    public String validateUserName() throws SQLException {
        String valid = ErrMsg.ERR_UNAME;
        String regX = RegX.REGX_DIGIT;
        String uname = req.getuName();
        if (validate(uname, regX)) {
            if (dbc.checkUID(uname) == 1) {
                if (dbc.checkNBUID(uname) == 1) {
                    valid = CorrectMsg.CORRECT_UNAME;
                } else {
                    valid = ErrMsg.ERR_UNAME_BLOCKED;
                }
            } else {
                valid = ErrMsg.ERR_UNAME_NOT_EXISTS;
            }
        }
        return valid;
    }

    @Override
    public String validatePassword() throws Exception {
        String valid = ErrMsg.ERR_PASSWORD;
        String regX = RegX.REGX_B64ENCODE;
        String uname = req.getuName();
        List<String> passDSalt = dbc.getPassDSalt(uname);
        req.setSalt(passDSalt.get(0));
        req.changePassword();
        req.setAff_user_id(passDSalt.get(2));
        String apassword = passDSalt.get(1);
        String password = req.getPassword();
        if (validate(password, regX)) {
            if (apassword.equals(password)) {
                valid = CorrectMsg.CORRECT_PASSWORD;
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
        dbc.closeConnection();
    }

}
