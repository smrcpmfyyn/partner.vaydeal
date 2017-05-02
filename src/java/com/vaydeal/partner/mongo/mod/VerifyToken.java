/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.mongo.mod;

import com.vaydeal.partner.message.CorrectMsg;
import com.vaydeal.partner.message.ErrMsg;

/**
 * @company techvay
 * @author rifaie
 */
public class VerifyToken {
    private String user_id;
    private String toe;
    private String status;

    public VerifyToken() {
        this.status = "invalid";
        this.user_id = "invalid";
        this.toe = "invalid";
    }

    
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getToe() {
        return toe;
    }

    public void setToe(String toe) {
        this.toe = toe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "VerifyToken{" + "user_id=" + user_id + ", toe=" + toe + ", status=" + status + '}';
    }

    public String getValidation() {
        String valid = ErrMsg.ERR_TOKEN;
        if(status.equals("verified")){
            valid = ErrMsg.ERR_TOKEN_USED;
        } else if (status.equals("invalid")) {
            valid = ErrMsg.ERR_TOKEN;
        }else if(Long.parseLong(toe)<System.currentTimeMillis()){
            valid = ErrMsg.ERR_TOKEN_EXPIRED;
        }else if(status.equals("not changed")&&Long.parseLong(toe)>System.currentTimeMillis()){
            valid = CorrectMsg.CORRECT_TOKEN;
        }
        return valid;
    }
    
    
}
