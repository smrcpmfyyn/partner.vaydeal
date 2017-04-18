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
public class LogSuccessResponse {
    private final String status;
    private final String accessToken;
    private final String user_type;

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public LogSuccessResponse(String status, String accessToken, String user_type) {
        this.status = status;
        this.accessToken = accessToken;
        this.user_type = user_type;
    }

    @Override
    public String toString() {
        return "{\"status\":\"" + status + "\",\"ut\":\"" + user_type +"\"}";
    }
    
}