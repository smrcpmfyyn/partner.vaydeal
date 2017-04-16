/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.req.mod;

/**
 *
 * @author USER
 */
public class RequestPromotion {
    private final String company;
    private final String website;
    private final String name;
    private final String email;
    private final String mobile;

    public RequestPromotion(String company, String website, String name, String email, String mobile) {
        this.company = company;
        this.website = website;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public String getCompany() {
        return company;
    }

    public String getWebsite() {
        return website;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }
}
