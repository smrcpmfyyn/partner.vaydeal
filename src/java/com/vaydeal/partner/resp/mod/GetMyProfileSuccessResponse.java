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
public class GetMyProfileSuccessResponse {
    private final String status;
    private final String accessToken;
    private AffiliateProfile ap;

    public GetMyProfileSuccessResponse(String status, String accessToken, AffiliateProfile ap) {
        this.status = status;
        this.accessToken = accessToken;
        this.ap = ap;
    }

    public GetMyProfileSuccessResponse(String status, String accessToken) {
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
            sb.append(
"                                    <div class=\"col-12\">\n" +
"                                        <h1>Personal Details</h1>\n" +
"                                    </div>\n" +
"                                    <div id=\"profileErr\">\n" +
"                                    </div>\n" +
"                                    <div class=\"personal-details form\">\n" +
"                                        <form onsubmit=\"return updateProfile()\">\n" +
                                            ap.toString()+
"                                            <button  type=\"submit\" class=\"btn btn-bg waves-effect\"> Update </button>\n" +
"                                        </form>\n" +
"                                    </div>\n" +
"                                </div>");
        }else{
            sb.append("<div id=\"tabProfile\" class=\"\"> <!-- Tabs Profile Details -->\n" +
"                                    <div class=\"col-12\">\n" +
"                                        <h1>Personal Details</h1>\n" +
"                                    </div>\n" +
"                                    <div id=\"profileErr\">\n" +"<div id=\"msgStatus\" class=\"msg-status error\"> Some Error Occured! please try again</div>"+
"                                    </div>\n" +
"                                    <div class=\"personal-details form\">\n" +
"                                        <form onsubmit=\"return updateProfile()\">\n" +
                                            ap.failiureToString()+
"                                            <button  type=\"submit\" class=\"btn btn-bg waves-effect\"> Update </button>\n" +
"                                        </form>\n" +
"                                    </div>\n" +
"                                ");
        }
        return sb.toString();
    }
}
