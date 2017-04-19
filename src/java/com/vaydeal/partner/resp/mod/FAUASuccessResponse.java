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
public class FAUASuccessResponse {
    private final String status;
    private final String accessToken;
    private ArrayList<Activity> allFAUA;

    public FAUASuccessResponse(String status, String accessToken, ArrayList<Activity> allFAUA) {
        this.status = status;
        this.accessToken = accessToken;
        this.allFAUA = allFAUA;
    }

    public FAUASuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.allFAUA = new ArrayList<>();
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ArrayList<Activity> getAllFAUA() {
        return allFAUA;
    }

    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"activities\":[ ";
        response = allFAUA.stream().map((Activity act) -> act.toString()+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
    
    
}

