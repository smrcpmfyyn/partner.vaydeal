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
public class AffiliateProfile {

    private final String name;
    private final String address1;
    private final String address2;
    private final String place;
    private final String pin;
    private final String mobileNo;
    private final String email;
    private final String company;
    private final String designation;

    public AffiliateProfile(String name, String address1, String address2, String place, String pin, String mobileNo, String email, String company, String designation) {
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.place = place;
        this.pin = pin;
        this.mobileNo = mobileNo;
        this.email = email;
        this.company = company;
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public String failiureToString() {
        StringBuilder sb = new StringBuilder();
        String res = "<label onclick = location.reload(true);> Please reload </label>\n";
        sb.append(res);
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String res = "<label> Name </label>\n"
                + "                                            <input id=\"pn\" type=\"text\" name=\"name\" value=\"" + name + "\" required>\n"
                + "                                            <label> Company </label>\n"
                + "                                            <input id=\"pc\" type=\"text\" name=\"company\" readonly value=\"" + company + "\" required>\n"
                + "                                            <label> Designation </label>\n"
                + "                                            <input id=\"pd\" type=\"text\" name=\"designation\" value=\"" + designation + "\" required>\n"
                + "                                            <label> Address 1 </label>\n"
                + "                                            <input id=\"pa1\" type=\"text\" name=\"address1\" value=\"" + address1 + "\" required>\n"
                + "                                            <label> Address 2 </label>\n"
                + "                                            <input id=\"pa2\" type=\"text\" name=\"address2\" value=\"" + address2 + "\" required>\n"
                + "                                            <label> Place </label>\n"
                + "                                            <input id=\"ppl\" type=\"text\" name=\"place\" value=\"" + place + "\" required>\n"
                + "                                            <label> Pin </label>\n"
                + "                                            <input id=\"ppn\" type=\"text\" pattern=[0-9]{6} name=\"pin\" value=\"" + pin + "\" required>\n"
                + "                                            <label> Email </label>\n"
                + "                                            <input id=\"pe\" type=\"email\" name=\"email\" readonly value=\"" + email + "\" required>\n"
                + "                                            <label> Mobile </label>\n"
                + "                                            <input id=\"pm\" type=\"text\" name=\"mobile\" value=\"" + mobileNo + "\" required>\n";
        res = res.replaceAll("null", "");
        sb.append(res);
        return sb.toString();
    }

}
