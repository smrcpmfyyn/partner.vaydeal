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
public class NPSuccessResponse {
    private final String status;

    public NPSuccessResponse(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{\"status\":\""+status + "\"}";
    }
}
