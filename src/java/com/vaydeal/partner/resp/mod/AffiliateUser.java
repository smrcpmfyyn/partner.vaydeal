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
public class AffiliateUser {

    private final String aff_uid;
    private final String aff_name;
    private final String aff_designation;
    private final String aff_utype;
    private final String aff_status;

    public AffiliateUser(String aff_uid, String aff_name, String aff_designation, String aff_utype, String aff_status) {
        this.aff_uid = aff_uid;
        this.aff_name = aff_name;
        this.aff_designation = aff_designation;
        this.aff_utype = aff_utype;
        this.aff_status = aff_status;
    }

    @Override
    public String toString() {
        return "{\"aff_uid\":\"" + aff_uid + "\", \"aff_name\":\"" + aff_name + "\", \"aff_designation\":\"" + aff_designation + "\", \"aff_utype\":\"" + aff_utype + "\", \"aff_status\":\"" + aff_status + "\"}";    
    }
    
    
}
