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
public class GetAffiliateUsersSuccessResponse {

    private final String status;
    private final String accessToken;
    private final ArrayList<AffiliateUser> affiliateUsers;

    public GetAffiliateUsersSuccessResponse(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
        this.affiliateUsers = new ArrayList<>();
    }

    public GetAffiliateUsersSuccessResponse(String status, String accessToken, ArrayList<AffiliateUser> affiliateUsers) {
        this.status = status;
        this.accessToken = accessToken;
        this.affiliateUsers = affiliateUsers;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        String response = "";
        if (status.equals(ResponseMsg.RESP_OK)) {
            for (int i = 0; i < affiliateUsers.size(); i++) {
                AffiliateUser a = affiliateUsers.get(i);
                response += "<tr><td>" + (i + 1) + "</td>" + a.toString() + "</tr>";
            }
        } else {
            response = "<tr><td colspan='4'>Some Error Occured! Please Try again..</td></tr>";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<div id=\"userErr\"></div>\n"
                + "                                    <div class=\"User-details\">\n"
                + "                                        <table class=\"table\">\n"
                + "                                            <thead>\n"
                + "                                                <tr>\n"
                + "                                                    <th>Si No</th>\n"
                + "                                                    <th>User ID </th>\n"
                + "                                                    <th>Name</th>\n"
                + "                                                    <th>Designation</th>\n"
                + "                                                    <th>User Type</th>\n"
                + "                                                    <th>Status</th>\n"
                + "                                                    <th>Operations</th>\n"
                + "                                                </tr>\n"
                + "                                            </thead>\n"
                + "                                            <tbody>");
        sb.append(response);
        sb.append("</tbody>\n"
                + "                                        </table>\n"
                + "                                    </div>");
        return sb.toString();
    }
}
