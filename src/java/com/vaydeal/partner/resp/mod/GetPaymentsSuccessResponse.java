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
public class GetPaymentsSuccessResponse {
    private final String status;
    private final String accessToken;
    private final String queryStatus;
    private final ArrayList<AffiliatePayments> affiliatePayments;
    private final String tp;
    private final String ap;

    public GetPaymentsSuccessResponse(String status, String accessToken, String tp, String ap) {
        this.status = status;
        this.accessToken = accessToken;
        this.affiliatePayments = new ArrayList<>();
        this.queryStatus = "empty";
        this.tp = tp;
        this.ap = ap;
    }

    public GetPaymentsSuccessResponse(String status, String accessToken, ArrayList<AffiliatePayments> affiliatePayments, String tp, String ap) {
        this.status = status;
        this.accessToken = accessToken;
        this.affiliatePayments = affiliatePayments;
        this.queryStatus = "available";
        this.tp = tp;
        this.ap = ap;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ArrayList<AffiliatePayments> getPayments() {
        return affiliatePayments;
    }
    
    @Override
    public String toString() {
        String response = "{\"status\":\""+status + "\",\"qs\":\""+queryStatus +"\",\"tp\":\""+tp +"\",\"ap\":\""+ap +"\",\"aps\":[ ";
        response = affiliatePayments.stream().map((AffiliatePayments ap) -> ap+",").reduce(response, String::concat);
        response = response.substring(0, response.length()-1);
        response += "]}";
        return response;
    }
}