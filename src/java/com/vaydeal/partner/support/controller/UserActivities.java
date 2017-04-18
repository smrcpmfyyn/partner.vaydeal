/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.support.controller;

import com.vaydeal.partner.db.DB;
import com.vaydeal.partner.db.MongoConnect;

/**
 * @company techvay
 * @author rifaie
 */
public class UserActivities {
    private final String uid;
    private final String uType;
    private final String activity;
    private final String dateTime;
    private final String activityUType;
    private MongoConnect mdbc;
    private String entryStatus;

    public UserActivities(String uid, String uType, String activity, String activityCategory, String entryStatus) throws Exception {
        this.uid = uid;
        this.uType = uType;
        this.activity = activity;
        this.dateTime = ""+System.currentTimeMillis();
        this.activityUType = activityCategory;
        this.entryStatus = entryStatus;
    }

    public void setEntryStatus(String entryStatus) {
        this.entryStatus = entryStatus;
    }
    
    public void addActivity() throws Exception{
        this.mdbc = DB.getMongoConnection();
        mdbc.addActivity(this.toString());
    }

    @Override
    public String toString() {
        return "{\"uid\":\"" + uid + "\", \"uType\":\"" + uType + "\", \"activity\":\"" + activity + "\", \"dateTime\":\"" + dateTime + "\", \"activityUType\":\"" + activityUType + "\", \"entryStatus\":\"" + entryStatus + "\"}";
    }
    
    
}
