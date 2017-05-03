/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.resp.mod;

import com.vaydeal.partner.message.ResponseMsg;
import com.vaydeal.partner.message.ValidationMsg;

/**
 * @company techvay
 * @author rifaie
 */
public class RPSuccessResponse {

    private final String status;
    private final String token;

    public RPSuccessResponse(String status, String token) {
        this.status = status;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(status.equals(ValidationMsg.VALID)){
        sb.append("<div class=\"form reset-form\">\n"
                    + "            <label for=\"\"> New Password</label>\n"
                    + "            <input id=\"np\" type=\"password\" name=\"name\">\n"
                    + "            <button id=\"resetBtn\" onclick='reset()' type=\"button\" class=\"btn btn-bg waves-effect\"> Reset </button>\n"
                    + "        </div>");
        }else{
            sb.append("<div id=\"rp\" class=\"col-12 reset-pwd\"> <div class=\"reset-error\">\n"
                + "            <i> <img src=\"images/error-image.png\" alt=\"error\"> </i>\n"
                + "            <p class=\"error-bg\"> Error occurred !</p>\n</div>").append("</div>");
        }
        return sb.toString();
    }
}
