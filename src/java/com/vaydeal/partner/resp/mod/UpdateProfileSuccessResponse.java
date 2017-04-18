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
public class UpdateProfileSuccessResponse {
    private final String status;
    private final String accessToken;

    public UpdateProfileSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        return "{\"status\":\""+status + "\"}";
    }
}
