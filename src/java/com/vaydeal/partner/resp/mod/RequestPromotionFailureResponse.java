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
        String err = "";
        for (int i = 1; i < errors.length; i++) {
            String parameter = errors[i];
            switch (parameter) {
                case "company":
                    err += "Invalid Company\n";
                    break;
                case "website":
                    err += "Invalid Website\n";
                    break;
                case "name":
                    err += "Invalid Name\n";
                    break;
                case "email":
                    err += "Invalid Email\n";
                    break;
                case "mobile":
                    err += "Invalid Mobile Number";
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<div id=\"indexErr\"><div id=\"msgStatus\" class=\"msg-status error\">");
        sb.append(err);
        sb.append("</div>"+
"         </div>\n" +
"            <div class=\"col-6\"></div>\n" +
"            <div class=\"col-6\" id=\"reqPCon\">"+
"              <div class=\"form register-form\">\n" +
"                <h2>Request Promotion</h2>\n" +
"                <label id=\"rpmsg\"></label>\n" +
"                <form onsubmit=\"return reqPromo()\">\n" +
"                    <label> Company Name</label>\n" +
"                    <input required id='cname' type=\"text\" name=\"name\"  value='"+req.getCompany()+"' >\n" +
"                    <label> Website</label>\n" +
"                    <input type=\"url\" id=\"curl\" required name=\"website\"  value='"+req.getWebsite()+"'>\n" +
"                    <label> Contact Person </label>\n" +
"                    <input type=\"text\" id=\"cper\"  value='"+req.getName()+"' required name=\"confoer\">\n" +
"                    <label> Email </label>\n" +
"                    <input type=\"email\" id=\"cemail\"   value='"+req.getEmail()+"'required name=\"email\">\n" +
"                    <label> Mobile </label>\n" +
"                    <input type=\"text\" name=\"mobile\" id=\"cmob\" pattern=\"[7-9]{1}[0-9]{9}\"   value='"+req.getMobile()+"' required>\n" +
"                    <button type=\"submit\" class=\"btn btn-bg waves-effect\"> Request Promotion </button>\n" +
"                </form>\n" +
"            </div>\n" +
"       </div>\n");
        
        return sb.toString();
    }

}
