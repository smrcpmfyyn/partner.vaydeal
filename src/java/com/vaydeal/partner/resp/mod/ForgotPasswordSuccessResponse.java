/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.resp.mod;

import com.vaydeal.partner.message.ResponseMsg;

/**
 * @company techvay
 * @author rifaie
 */
public class ForgotPasswordSuccessResponse {

    private final String status;
    private final String accessToken;

    public ForgotPasswordSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        if (status.equals(ResponseMsg.RESP_OK)) {
            return "<div id=\"pwdMsg\" class=\"password-meesage\">\n"
                    + "                    <h3>Please check your registered email to change your password.</h3>\n"
                    + "                </div>";
        }else{
            return "<div id=\"pwdMsg\" class=\"password-meesage\">\n"
                    + "                    <h3>An Error Occured ! Please try again.."
                    + "                </div>";
        }
    }
}
