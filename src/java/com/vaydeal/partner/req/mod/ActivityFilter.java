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
public class ActivityFilter {
    private String uid;
    private String [] uType;
    private String [] activity;
    private String [] entryStatus ;
    
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String[] getuType() {
        return uType;
    }

    public void setuType(String[] uType) {
        this.uType = uType;
    }

    public String[] getActivity() {
        return activity;
    }

    public void setActivity(String[] activity) {
        this.activity = activity;
    }

    public String[] getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(String[] entryStatus) {
        this.entryStatus = entryStatus;
    }
    
}

