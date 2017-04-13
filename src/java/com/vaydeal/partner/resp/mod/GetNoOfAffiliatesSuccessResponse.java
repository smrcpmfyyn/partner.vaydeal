/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.resp.mod;

/**
 *
 * @author USER
 */
public class GetNoOfAffiliatesSuccessResponse {
    private final String status;
    private final int noOfAffiliates;

    public GetNoOfAffiliatesSuccessResponse(String status) {
        this.status = status;
        noOfAffiliates = 0;
    }

    public GetNoOfAffiliatesSuccessResponse(String status, int noOfAffiliates) {
        this.status = status;
        this.noOfAffiliates = noOfAffiliates;
    }
    
    @Override
    public String toString() {
        return "{\"status\":\""+status + "\",\"na\":\""+noOfAffiliates+"\"}";
    }
}
