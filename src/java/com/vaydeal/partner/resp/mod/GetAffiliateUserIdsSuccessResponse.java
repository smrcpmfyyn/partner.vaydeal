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
public class GetAffiliateUserIdsSuccessResponse {
    private final String status;
    private final String accessToken;
    private final ArrayList<String> affiliateUserIds;

    public GetAffiliateUserIdsSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.affiliateUserIds = new ArrayList<>();
    }

    public GetAffiliateUserIdsSuccessResponse(String status, String accessToken, ArrayList<String> affiliateUserIds) {
        this.status = status;
        this.accessToken = accessToken;
        this.affiliateUserIds = affiliateUserIds;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }
    
    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"auids\":[ ";
        response = affiliateUserIds.stream().map((String au) -> au+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}

