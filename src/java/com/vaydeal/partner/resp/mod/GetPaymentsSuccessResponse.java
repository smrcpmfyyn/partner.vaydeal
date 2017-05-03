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
public class GetPaymentsSuccessResponse {

    private final String status;
    private final String accessToken;
    private final String queryStatus;
    private final ArrayList<AffiliatePayments> affiliatePayments;
    private final String tp;
    private final String ap;
    private final int cp;
    private final int np;
    private final int pp;
    private final int me;
    private final int pn;

    public GetPaymentsSuccessResponse(String status, String accessToken, String tp, String ap) {
        this.status = status;
        this.accessToken = accessToken;
        this.affiliatePayments = new ArrayList<>();
        this.queryStatus = "empty";
        this.tp = tp;
        this.ap = ap;
        this.cp = 0;
        this.np = 0;
        this.pp = 0;
        this.me = 0;
        this.pn = 0;
    }

    public GetPaymentsSuccessResponse(String status, String accessToken, ArrayList<AffiliatePayments> affiliatePayments, String tp, String ap, int cp, int np, int pp, int me, int pn) {
        this.status = status;
        this.accessToken = accessToken;
        this.affiliatePayments = affiliatePayments;
        this.queryStatus = "available";
        this.tp = tp;
        this.ap = ap;
        this.cp = cp;
        this.np = np;
        this.pp = pp;
        this.me = me;
        this.pn = pn;
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
        if (status.equals(ResponseMsg.RESP_OK)) {
            if (queryStatus.equals("empty")) {
                response = "<tr><td colspan='4'>No results</td></tr>";
            } else {
                int start = (pn - 1) * me;
                for (int i = 0; i < affiliatePayments.size(); i++) {
                    AffiliatePayments a = affiliatePayments.get(i);
                    response += "<tr><td>" + (start + i + 1) + "</td>" + a.toString() + "</tr>";
                }
            }
        }else{
            response = "<tr><td colspan='4'>Some Error Occured! Please Try again..</td></tr>";
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
                + "                        </table>\n");
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
        sb.append("                    </div>\n" + "\n" + "                    <div class=\"pay-total\">\n" + "                        <label> Total Paid <span> ").append(tp).append(" </span> </label>\n" + "                        <label> Active Payment <span> ").append(ap).append(" </span> </label>\n"
                + "                    </div>");
        return sb.toString();
    }
}
