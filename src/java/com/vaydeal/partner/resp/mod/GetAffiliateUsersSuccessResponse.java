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
public class GetAffiliateUsersSuccessResponse {
    private final String status;
    private final String accessToken;
    private final ArrayList<AffiliateUser> affiliateUsers;

    public GetAffiliateUsersSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.affiliateUsers = new ArrayList<>();
    }

    public GetAffiliateUsersSuccessResponse(String status, String accessToken, ArrayList<AffiliateUser> affiliateUsers) {
        this.status = status;
        this.accessToken = accessToken;
        this.affiliateUsers = affiliateUsers;
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
        response = affiliateUsers.stream().map((AffiliateUser au) -> au+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}
