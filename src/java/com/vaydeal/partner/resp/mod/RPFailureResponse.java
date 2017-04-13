/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.resp.mod;

import com.vaydeal.partner.result.RPResult;

/**
 * @company techvay
 * @author rifaie
 */
public class RPFailureResponse {
    private final RPResult rpr;
    private final String error;
    private String token;

    public RPFailureResponse(RPResult rpr, String error) {
        this.rpr = rpr;
        this.error = error;
    }

    public String getToken() {
        return token;
    }
    
    @Override
    public String toString() {
        String json = "";
        String[] errors = error.split("#");
        String parameter = errors[1];
        String resp;
        switch (parameter) {
            case "token":
                String token = rpr.getToken();
                resp = token.substring(token.lastIndexOf(" ") + 1);
                json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                this.token = resp;
                break;
        }
        json = json.substring(0, json.length() - 1);
        return "{" + json + "}";
    }
}

