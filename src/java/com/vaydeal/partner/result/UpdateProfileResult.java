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
public class UpdateProfileResult implements Result {
    private String at;
    private String utype;
    private String name;
    private String add1;
    private String add2;
    private String pin;
    private String mobile;
    private String email;
    private String company;
    private String designation;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdd1() {
        return add1;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }

    public String getAdd2() {
        return add2;
    }

    public void setAdd2(String add2) {
        this.add2 = add2;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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
            error += "utype#";
        } else {
            if (name.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "name#";
            }
            if (add1.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "add1#";
            }
            if (add2.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "add2#";
            }
            if (pin.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "pin#";
            }
            if (mobile.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "mobile#";
            }
            if (email.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "email#";
            }
            if (company.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "company#";
            }
            if (designation.startsWith(ErrMsg.ERR_MESSAGE)) {
                error += "designation#";
            }
        }
        return error.substring(0, error.length() - 1);
    }
}
