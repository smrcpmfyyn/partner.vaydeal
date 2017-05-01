/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.resp.mod;

import java.util.ArrayList;

/**
 * @company techvay
 * @author rifaie
 */
public class GetPaymentsSuccessResponse {

    private final String status;
    private final String accessToken;
    private final String queryStatus;
    private final ArrayList<AffiliatePayments> affiliatePayments;
    private final String tp;
    private final String ap;

    public GetPaymentsSuccessResponse(String status, String accessToken, String tp, String ap) {
        this.status = status;
        this.accessToken = accessToken;
        this.affiliatePayments = new ArrayList<>();
        this.queryStatus = "empty";
        this.tp = tp;
        this.ap = ap;
    }

    public GetPaymentsSuccessResponse(String status, String accessToken, ArrayList<AffiliatePayments> affiliatePayments, String tp, String ap) {
        this.status = status;
        this.accessToken = accessToken;
        this.affiliatePayments = affiliatePayments;
        this.queryStatus = "available";
        this.tp = tp;
        this.ap = ap;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ArrayList<AffiliatePayments> getPayments() {
        return affiliatePayments;
    }

    @Override
    public String toString() {
        String response = "";
        if (queryStatus.equals("empty")) {
            response = "<tr><td colspan='4'>No results</td></tr>";
        } else {
            for (int i = 0; i < affiliatePayments.size(); i++) {
                AffiliatePayments a = affiliatePayments.get(i);
                response += "<tr><td>"+(i+1)+"</td>"+a.toString()+"</tr>";
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"payment row-fluid\">\n"
                + "                        <table class=\"table\">\n"
                + "                            <thead>\n"
                + "                                <tr>\n"
                + "                                    <th>Sl No</th>\n"
                + "                                    <th>Reference No</th>\n"
                + "                                    <th>Amount</th>\n"
                + "                                    <th>Date</th>\n"
                + "                                    <th>Status</th>\n"
                + "                                </tr>\n"
                + "                            </thead>\n"
                + "                            <tbody>\n");
        sb.append(response);
        sb.append("                            </tbody>\n"
                + "                        </table>\n"
                + "                        <button onclick='prev_next("+2+")' type=\"button\" ><<</button>\n"
                + "                        <button onclick=\"prev_next("+2+")\" type=\"button\" >>></button>\n"
                + "                    </div>\n"
                + "\n"
                + "                    <div class=\"pay-total\">\n"
                + "                        <label> Total Paid <span> "+tp+" </span> </label>\n"
                + "                        <label> Active Payment <span> "+ap+" </span> </label>\n"
                + "                    </div>");

        response = response.substring(0, response.length() - 1);
        response += "]}";
        return response;
    }
}
