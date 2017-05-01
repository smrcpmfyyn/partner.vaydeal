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
public class RequestPromotionSuccessResponse {
    private final String status;

    public RequestPromotionSuccessResponse(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"form register-form\">\n" +
"                <h2>Request Promotion</h2>\n" +
"                <label id=\"rpmsg\">Request submitted successfully</label>\n" +
"                <form onsubmit=\"return reqPromo()\">\n" +
"                    <label> Company Name</label>\n" +
"                    <input required id='cname' type=\"text\" name=\"name\">\n" +
"                    <label></label>\n" +
"                    <label> Website</label>\n" +
"                    <input type=\"url\" id=\"curl\" required name=\"website\">\n" +
"                    <label></label>\n" +
"                    <label> Contact Person </label>\n" +
"                    <input type=\"text\" id=\"cper\" required name=\"confoer\">\n" +
"                    <label></label>\n" +
"                    <label> Email </label>\n" +
"                    <input type=\"email\" id=\"cemail\" required name=\"email\">\n" +
"                    <label></label>\n" +
"                    <label> Mobile </label>\n" +
"                    <input type=\"text\" name=\"mobile\" id=\"cmob\" pattern=\"[7-9]{1}[0-9]{9}\" required>\n" +
"                    <label></label>\n" +
"                    <button type=\"submit\" class=\"btn btn-bg waves-effect\"> Request Promotion </button>\n" +
"                </form>\n" +
"            </div>\n" +
"       ");
        
        return sb.toString();
    }
}

