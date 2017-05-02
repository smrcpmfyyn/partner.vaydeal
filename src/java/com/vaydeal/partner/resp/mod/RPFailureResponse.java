/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.resp.mod;

import com.vaydeal.partner.result.RPResult;

/**
 * @company techvay
 * @author rifaie
 */
public class RPFailureResponse {

    private final RPResult rpr;
    private final String error;
    private String token;

    public RPFailureResponse(RPResult rpr, String error) {
        this.rpr = rpr;
        this.error = error;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        String resp;
        StringBuilder sb = new StringBuilder();
        sb.append("<div id=\"rp\" class=\"col-12 reset-pwd\"> <div class=\"reset-error\">\n"
                + "            <i> <img src=\"images/error-image.png\" alt=\"error\"> </i>\n"
                + "            <p class=\"error-bg\"> Error occurred !</p>\n"
                + "            <h2> Token ");
        String token = rpr.getToken();
        resp = token.substring(token.lastIndexOf(" ") + 1);
        if (resp.startsWith("not")) {
            resp = resp.replace("not", "not ");
        }
        sb.append(resp);
        sb.append("</h2>\n" +
"        </div>").append("</div>");
        return sb.toString();
    }
}
