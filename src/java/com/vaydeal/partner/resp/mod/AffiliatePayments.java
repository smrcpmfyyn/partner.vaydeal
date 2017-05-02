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
public class AffiliatePayments {
    private final String date;
    private final String refNo;
    private final String amount;
    private final String status;

    public AffiliatePayments(String date, String refNo, String amount, String status) {
        this.date = date;
        this.refNo = refNo;
        this.amount = amount;
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "<td>" + refNo +"</td><td>" + amount +"</td><td>" + date + "</td><td><span class=\"status "+status+"\">" + status +"</span></td>";
    }
}
