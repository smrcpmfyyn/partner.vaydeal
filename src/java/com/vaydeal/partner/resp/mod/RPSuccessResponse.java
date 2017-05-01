/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.resp.mod;

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
        sb.append("<div class=\"form reset-form\">\n"
                + "            <label for=\"\"> New Password</label>\n"
                + "            <input id=\"np\" type=\"password\" name=\"name\">\n"
//                + "            <label id=''> New Password</label>\n"
                + "            <button id=\"resetBtn\" onclick='reset()' type=\"button\" class=\"btn btn-bg waves-effect\"> Reset </button>\n"
                + "        </div>");
        return sb.toString();
//        return "{\"status\":\"" + status + "\",\"token\":\"" + token + "\"}";

    }
}
