/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.resp.mod;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @company techvay
 * @author rifaie
 */
public class Activity {

    private String user_id;
    private String affiliate;
    private String activity;
    private String dateTime;
    private String user_type;
    private String entryStatus;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAffiliate() {
        return affiliate;
    }

    public void setAffiliate(String affiliate) {
        this.affiliate = affiliate;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(String entryStatus) {
        this.entryStatus = entryStatus;
    }

    public String affiliateToString() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(dateTime));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        return "<td>" + user_id + "</td><td>" + activity + "</td><td>" + sdf.format(calendar.getTime()) + "</td><td><span class=\"status " + entryStatus + "\">" + entryStatus + "</span></td>";
    }

    @Override
    public String toString() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(dateTime));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        return "{\"user_id\":\"" + user_id + "\", \"affiliate\":\"" + affiliate + "\", \"activity\":\"" + activity + "\",\"dateTime\":\"" + sdf.format(calendar.getTime()) + "\",\"user_type\":\"" + user_type + "\",\"entryStatus\":\"" + entryStatus + "\"}";
    }

}
