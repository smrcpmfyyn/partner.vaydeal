/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.req.mod;

/**
 * @company techvay
 * @author rifaie
 */
public class ResetPassword {
    private final String token;
    private String admin_id;
    private String toe;

    public ResetPassword(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public String getToe() {
        return toe;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setToe(String toe) {
        this.toe = toe;
    }
}
