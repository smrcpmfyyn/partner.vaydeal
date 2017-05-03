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
public class NPSuccessResponse {

    private final String status;

    public NPSuccessResponse(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (status.equals(ResponseMsg.RESP_OK)) {
            sb.append(" <div id=\"pwdMsg\" class=\"password-meesage\">\n"
                    + "                    <h3>Password changed successfully. Please <a href=\"index.html\"> login </a></h3>\n"
                    + "                </div>");

        } else {
            sb.append("<div id=\"rp\" class=\"col-12 reset-pwd\"> <div class=\"reset-error\">\n"
                    + "            <i> <img src=\"images/error-image.png\" alt=\"error\"> </i>\n"
                    + "            <p class=\"error-bg\"> Error occurred !</p>\n</div>").append("</div>");
        }
        return sb.toString();
    }
}
