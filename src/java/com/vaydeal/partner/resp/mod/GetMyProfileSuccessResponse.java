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
        String response = "";
        if(status.equals(ResponseMsg.RESP_OK)){
            response = "{\"status\":\"" + status + "\", \"ap\":" + ap + "\"}";
        }else{
            response = "{\"status\":\""+status+"\"}";
        }
        return response;
    }
}
