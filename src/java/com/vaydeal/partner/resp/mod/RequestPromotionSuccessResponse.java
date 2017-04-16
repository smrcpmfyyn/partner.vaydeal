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
public class RequestPromotionSuccessResponse {
    private final String status;

    public RequestPromotionSuccessResponse(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "{\"status\":\""+status + "\"}";
    }
}

