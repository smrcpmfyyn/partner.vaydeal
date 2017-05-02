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
public class ChangePasswordSuccessResponse {
    private final String status;
    private final String accessToken;

    public ChangePasswordSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(status.equals(ResponseMsg.RESP_OK)){
            sb.append("<div id=\"msgStatus\" class=\"msg-status error\">Password Changed Successfully </div>");
        }else{
            sb.append("<div id=\"msgStatus\" class=\"msg-status error\">Some Error Occured! please try again</div>");  
        }
        return sb.toString();
    }
}
