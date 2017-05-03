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
    private int aff_next_status;
    private String aff_nxt_status;

    public AffiliateUser(String aff_uid, String aff_name, String aff_designation, String aff_utype, String aff_status) {
        this.aff_uid = aff_uid;
        this.aff_name = aff_name;
        this.aff_designation = aff_designation;
        this.aff_utype = aff_utype;
        this.aff_status = aff_status;
    }

    @Override
    public String toString() {
        if (aff_status.equals("active")) {
            aff_next_status = 2;
            aff_nxt_status = "Block User";
        } else {
            aff_next_status = 1;
            aff_nxt_status = "Unblock User";
        }
        String str = "<td>" + aff_uid +"</td><td>" + aff_name +"</td><td>" + aff_designation + "</td><td><span class=\"utype "+aff_utype+"\">" + aff_utype +"</span></td><td id=\""+aff_uid+"ST\"> <span class=\"status "+aff_status+"\">"+aff_status+"</span></td><td class=\"tab\">\n" + "<div id="+aff_uid+"BT>"+
"                                                        <button class=\"btn btn-view waves-effect tooltipped\" onclick=\"getActivity("+aff_uid+")\" data-position=\"top\" data-delay=\"50\" data-tooltip=\"View Activities\"><i class=\"ti-eye\"></i> </button>\n" +
"                                                        <button class=\"btn btn-edit waves-effect tooltipped\" onclick=\"resetUser("+aff_uid+")\" data-position=\"top\" data-delay=\"50\" data-tooltip=\"Reset User\"><i class=\"ti-pencil-alt\"></i> </button>\n" + 
"                                                        <button class=\"btn btn-remove waves-effect tooltipped\" onclick=\"changeUserStatus("+aff_uid+", "+aff_next_status+")\" data-position=\"top\" data-delay=\"50\" data-tooltip=\""+aff_nxt_status+"\"><i class=\"ti-trash\"></i> </button></div></td>";
        str = str.replaceAll("null", "No data available");
        return str;
    }

}
