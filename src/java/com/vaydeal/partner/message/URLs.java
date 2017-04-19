/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.message;

/**
 * @company techvay
 * @author rifaie
 */
public class URLs {

    private static final String AFFILIATE_PASSWORD_GENERATION = "http://localhost:8080/partner.vaydeal/resetPassword?token=";

    public static String getAFFILIATE_PASSWORD_GENERATION() {
        return AFFILIATE_PASSWORD_GENERATION;
    }

}
