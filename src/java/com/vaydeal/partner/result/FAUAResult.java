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
public class FAUAResult implements Result {
    private String at;
    private String utype;
    private String ftruid;
    private String ftrutype;
    private String ftractivity;
    private String ftrentrystatus;
    private String reqValidation;

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public String getFtruid() {
        return ftruid;
    }

    public void setFtruid(String ftruid) {
        this.ftruid = ftruid;
    }

    public String getFtrutype() {
        return ftrutype;
    }

    public void setFtrutype(String ftrutype) {
        this.ftrutype = ftrutype;
    }

    public String getFtractivity() {
        return ftractivity;
    }

    public void setFtractivity(String ftractivity) {
        this.ftractivity = ftractivity;
    }

    public String getFtrentrystatus() {
        return ftrentrystatus;
    }

    public void setFtrentrystatus(String ftrentrystatus) {
        this.ftrentrystatus = ftrentrystatus;
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
        if (at.startsWith(ErrMsg.ERR_MESSAGE)) {
            error += "at#";
        } else if (utype.startsWith(ErrMsg.ERR_MESSAGE)) {
            error += "admintype#";
        } else {
            if(ftruid.startsWith(ErrMsg.ERR_MESSAGE)){
                error += "ftruid#";
            }
            if (ftrutype.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "ftrutype#";
            }
            if (ftractivity.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "ftractivity#";
            }
            if (ftrentrystatus.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "ftrentrystatus#";
            }         
        }
        return error.substring(0, error.length() - 1);
    }
    
    
}


