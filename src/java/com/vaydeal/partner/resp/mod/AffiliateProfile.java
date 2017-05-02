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
    
    public String failiureToString(){
        StringBuilder sb = new StringBuilder();
        String res = "<label> Name </label>\n"
                + "                                            <input id=\"pn\" type=\"text\" name=\"name\" value=\""+"\">\n"
                + "                                            <label> Company </label>\n"
                + "                                            <input id=\"pc\" type=\"text\" name=\"company\" readonly value=\""+"\">\n"
                + "                                            <label> Designation </label>\n"
                + "                                            <input id=\"pd\" type=\"text\" name=\"designation\" value=\""+"\">\n"
                + "                                            <label> Address 1 </label>\n"
                + "                                            <input id=\"pa1\" type=\"text\" name=\"address1\" value=\""+"\">\n"
                + "                                            <label> Address 2 </label>\n"
                + "                                            <input id=\"pa2\" type=\"text\" name=\"address2\" value=\""+"\">\n"
                + "                                            <label> Place </label>\n"
                + "                                            <input id=\"ppl\" type=\"text\" name=\"place\" value=\""+"\">\n"
                + "                                            <label> Pin </label>\n"
                + "                                            <input id=\"ppn\" type=\"text\" name=\"pin\" value=\""+"\">\n"
                + "                                            <label> Email </label>\n"
                + "                                            <input id=\"pe\" type=\"email\" name=\"email\" readonly value=\""+"\">\n"
                + "                                            <label> Mobile </label>\n"
                + "                                            <input id=\"pm\" type=\"text\" name=\"mobile\" value=\""+"\">\n";
        sb.append(res);
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String res = "<label> Name </label>\n"
                + "                                            <input id=\"pn\" type=\"text\" name=\"name\" value=\""+name+"\">\n"
                + "                                            <label> Company </label>\n"
                + "                                            <input id=\"pc\" type=\"text\" name=\"company\" readonly value=\""+company+"\">\n"
                + "                                            <label> Designation </label>\n"
                + "                                            <input id=\"pd\" type=\"text\" name=\"designation\" value=\""+designation+"\">\n"
                + "                                            <label> Address 1 </label>\n"
                + "                                            <input id=\"pa1\" type=\"text\" name=\"address1\" value=\""+address1+"\">\n"
                + "                                            <label> Address 2 </label>\n"
                + "                                            <input id=\"pa2\" type=\"text\" name=\"address2\" value=\""+address2+"\">\n"
                + "                                            <label> Place </label>\n"
                + "                                            <input id=\"ppl\" type=\"text\" name=\"place\" value=\""+place+"\">\n"
                + "                                            <label> Pin </label>\n"
                + "                                            <input id=\"ppn\" type=\"text\" name=\"pin\" value=\""+pin+"\">\n"
                + "                                            <label> Email </label>\n"
                + "                                            <input id=\"pe\" type=\"email\" name=\"email\" readonly value=\""+email+"\">\n"
                + "                                            <label> Mobile </label>\n"
                + "                                            <input id=\"pm\" type=\"text\" name=\"mobile\" value=\""+mobileNo+"\">\n";
        res = res.replaceAll("null", "No data available");
        sb.append(res);
        return sb.toString();
    }

}
