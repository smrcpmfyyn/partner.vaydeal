/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.resp.mod;

import java.util.ArrayList;

/**
 * @company techvay
 * @author rifaie
 */
public class GetFilterSuccessResponse {
    private final String status;
    private final String accessToken;
    private final Filter filter;

    public GetFilterSuccessResponse(String status, String accessToken, Filter filter) {
        this.status = status;
        this.accessToken = accessToken;
        this.filter = filter;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }
    
    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"ap\":[ ";
        response += filter.toString();
        response += "]}";
        return response;
    }
}
