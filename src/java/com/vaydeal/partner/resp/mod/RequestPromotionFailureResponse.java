/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.resp.mod;

import com.vaydeal.partner.req.mod.RequestPromotion;
import com.vaydeal.partner.result.RequestPromotionResult;

/**
 * @company techvay
 * @author rifaie
 */
public class RequestPromotionFailureResponse {

    private final RequestPromotion req;

    private final RequestPromotionResult reqR;
    private final String error;

    public RequestPromotionFailureResponse(RequestPromotion req,RequestPromotionResult reqR, String error) {
        this.req = req;
        this.reqR = reqR;
        this.error = error;
    }

    @Override
    public String toString() {
        String[] errors = error.split("#");
        String resp;
        String company = "";
        String website = "";
        String name = "";
        String email = "";
        String mobile = "";
        for (int i = 1; i < errors.length; i++) {
            String parameter = errors[1];
            switch (parameter) {
                case "company":
                    company = "Invalid Company";
                    break;
                case "website":
                    website = "Invalid Website";
                    break;
                case "name":
                    name = "Invalid Name";
                    break;
                case "email":
                    email = "Invalid Email";
                    break;
                case "mobile":
                    mobile = "Invalid Mobile Number";
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"form register-form\">\n" +
"                <h2>Request Promotion</h2>\n" +
"                <label id=\"rpmsg\"></label>\n" +
"                <form onsubmit=\"return reqPromo()\">\n" +
"                    <label> Company Name</label>\n" +
"                    <input required id='cname' type=\"text\" name=\"name\"  value='"+req.getCompany()+"' >\n" +
"                    <label>'"+company+"'</label>\n" +
"                    <label> Website</label>\n" +
"                    <input type=\"url\" id=\"curl\" required name=\"website\"  value='"+req.getWebsite()+"'>\n" +
"                    <label>'"+website+"'</label>\n" +
"                    <label> Contact Person </label>\n" +
"                    <input type=\"text\" id=\"cper\"  value='"+req.getName()+"' required name=\"confoer\">\n" +
"                    <label>'"+name+"'</label>\n" +
"                    <label> Email </label>\n" +
"                    <input type=\"email\" id=\"cemail\"   value='"+req.getEmail()+"'required name=\"email\">\n" +
"                    <label>'"+email+"'</label>\n" +
"                    <label> Mobile </label>\n" +
"                    <input type=\"text\" name=\"mobile\" id=\"cmob\" pattern=\"[7-9]{1}[0-9]{9}\"   value='"+req.getMobile()+"' required>\n" +
"                    <label>'"+mobile+"'</label>\n" +
"                    <button type=\"submit\" class=\"btn btn-bg waves-effect\"> Request Promotion </button>\n" +
"                </form>\n" +
"            </div>\n" +
"       ");
        
        return sb.toString();
    }

}
