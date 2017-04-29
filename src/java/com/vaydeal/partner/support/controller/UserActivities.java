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
    private final String user_id;
    private final String affiliate;
    private final String activity;
    private final String dateTime;
    private final String user_type;
    private String entryStatus;
    private MongoConnect mdbc;

    public UserActivities(String user_id, String affiliate, String activity, String user_type, String entryStatus) throws Exception {
        this.user_id = user_id;
        this.affiliate = affiliate;
        this.activity = activity;
        this.dateTime = ""+System.currentTimeMillis();
        this.user_type = user_type;
        this.entryStatus = entryStatus;
    }

    public void setEntryStatus(String entryStatus) {
        this.entryStatus = entryStatus;
    }
    
    public void addActivity() throws Exception{
        this.mdbc = DB.getMongoConnection();
        mdbc.addActivity(this.toString());
        mdbc.closeConnection();
    }

    @Override
    public String toString() {
        return "{\"user_id\":\"" + user_id + "\", \"affiliate\":\"" + affiliate +  "\", \"activity\":\"" + activity + "\", \"dateTime\":\"" + dateTime + "\", \"user_type\":\"" + user_type + "\", \"entryStatus\":\"" + entryStatus + "\"}";
    }
    
    
}
