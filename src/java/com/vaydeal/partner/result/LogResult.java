/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.result;

import com.vaydeal.partner.intfc.vres.Result;
import com.vaydeal.partner.message.CorrectMsg;
import com.vaydeal.partner.message.ErrMsg;
import com.vaydeal.partner.message.ValidationMsg;

/**
 * @company techvay
 * @author rifaie
 */
public class LogResult implements Result {

    private String uname;
    private String password;
    private String reqValidation;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReqValidation() {
        return reqValidation;
    }

    public void setReqValidation(String logValidation) {
        this.reqValidation = logValidation;
    }

    @Override
    public String getValidationResult() {
        String result;
        if (isRequestValid()) {
            result = ValidationMsg.VALID;
        } else {
            result = getAllErrors();
        }
        return result;
    }

    @Override
    public boolean isRequestValid() {
        boolean flag = false;
        if (reqValidation.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
            flag = true;
        }
        return flag;
    }

    @Override
    public String getAllErrors() {
        String error = ErrMsg.ERR_ERR + "#";
        if (uname.startsWith(ErrMsg.ERR_MESSAGE)) {
            error += "uname#";
        } else if (password.startsWith(ErrMsg.ERR_MESSAGE)) {
            error += "password#";
        }
        return error.substring(0, error.length() - 1);
    }

}
