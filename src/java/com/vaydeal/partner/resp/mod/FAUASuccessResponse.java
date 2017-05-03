/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.resp.mod;

import com.vaydeal.partner.message.ResponseMsg;
import java.util.ArrayList;

/**
 * @company techvay
 * @author rifaie
 */
public class FAUASuccessResponse {

    private final String status;
    private final String accessToken;
    private final ArrayList<Activity> allFAUA;
    private final String queryStatus;
    private final int cp;
    private final int np;
    private final int pp;
    private final int me;
    private final int pn;

    public FAUASuccessResponse(String status, String accessToken, ArrayList<Activity> allFAUA, int cp, int np, int pp, int me, int pn) {
        this.status = status;
        this.accessToken = accessToken;
        this.allFAUA = allFAUA;
        this.queryStatus = "available";
        this.cp = cp;
        this.np = np;
        this.pp = pp;
        this.me = me;
        this.pn = pn;
    }

    public FAUASuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.allFAUA = new ArrayList<>();
        this.queryStatus = "empty";
        this.cp = 0;
        this.np = 0;
        this.pp = 0;
        this.me = 0;
        this.pn = 0;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ArrayList<Activity> getAllFAUA() {
        return allFAUA;
    }

    @Override
    public String toString() {
        String response = "";
        if (status.equals(ResponseMsg.RESP_OK)) {
            if (queryStatus.equals("empty")) {
                response = "<tr><td colspan='4'>No results</td></tr>";
            } else {
                int start = (pn - 1) * me;
                for (int i = 0; i < allFAUA.size(); i++) {
                    Activity a = allFAUA.get(i);
                    response += "<tr><td>" + (start + i + 1) + "</td>" + a.affiliateToString() + "</tr>";
                }
            }
        } else {
            response = "<tr><td colspan='4'>Some Error Occured! Please Try again..</td></tr>";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("                                    <div class=\"user-activity\">\n"
                + "                                        <table class=\"table\">\n"
                + "                                            <thead>\n"
                + "                                                <tr>\n"
                + "                                                    <th>Si No</th>\n"
                + "                                                    <th>User ID</th>\n"
                + "                                                    <th>Activity</th>\n"
                + "                                                    <th>Date and Time</th>\n"
                + "                                                    <th>Activity Status</th>\n"
                + "                                                </tr>\n"
                + "                                            </thead>\n"
                + "                                            <tbody>");
        sb.append(response);
        sb.append("</tbody>\n" +
"                                        </table>");
        String button = "";
        if (np == 0 && pp == 0) {
            button += "                         <label>" + cp + "</label>";
        } else if (np == 0) {
            button += "                        <button onclick=\"prev_next(" + pp + ")\" type=\"button\" ><<</button>\n"
                    + "<label>" + cp + "</label>";
        } else if (np != 0 && pp != 0) {
            button += "                        <button onclick='prev_next(" + pp + ")' type=\"button\" ><<</button>\n"
                    + "                         <label>" + cp + "</label>"
                    + "                        <button onclick=\"prev_next(" + np + ")\" type=\"button\" >>></button>\n";
        } else if (np != 0 && pp == 0) {
            button += "<label>" + cp + "</label>"
                    + "                        <button onclick=\"prev_next(" + np + ")\" type=\"button\" >>></button>\n";
        }
        sb.append(button);
        sb.append("</div>");
        return sb.toString();
    }

}
