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
public class ForgotPasswordResult implements Result {

    private String uid;
    private String email;
    private String reqValidation;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReqValidation() {
        return reqValidation;
    }

    public void setReqValidation(String reqValidation) {
        this.reqValidation = reqValidation;
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
        if (uid.startsWith(ErrMsg.ERR_MESSAGE)) {
            error += "uid#";
        } else {
            if (email.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "email#";
            }
        }
        return error.substring(0, error.length() - 1);
    }
}
