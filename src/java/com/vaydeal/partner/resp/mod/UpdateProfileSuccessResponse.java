/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.resp.mod;

import com.sun.xml.bind.xmlschema.StrictWildcardPlug;
import com.vaydeal.partner.message.ResponseMsg;
import com.vaydeal.partner.req.mod.UpdateProfile;

/**
 * @company techvay
 * @author rifaie
 */
public class UpdateProfileSuccessResponse {

    private final String status;
    private final String accessToken;
    private final UpdateProfile req;

    public UpdateProfileSuccessResponse(String status, String accessToken, UpdateProfile req) {
        this.status = status;
        this.accessToken = accessToken;
        this.req = req;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        String response = "";
        if (status.equals(ResponseMsg.RESP_OK)) {
            response = "<div id=\"msgStatus\" class=\"msg-status error\"> Profile updated </div>";
        } else {
            response = "<div id=\"msgStatus\" class=\"msg-status error\"> Some Error occured! Please try again. </div>";

        }
        StringBuilder sb = new StringBuilder();
        sb.append("<div id=\"tabProfile\" class=\"\"> <!-- Tabs Profile Details -->\n"
                + "                                    <div class=\"col-12\">\n"
                + "                                        <h1>Personal Details</h1>\n"
                + "                                    </div>"
                + "                                    <div id=\"profileErr\">\n" + response
                + "                                    </div>\n"
                + "                                    <div class=\"personal-details form\">\n"
                + "                                        <form onsubmit=\"return updateProfile()\">\n"
                + "                                            <label> Name </label>\n"
                + "                                            <input id=\"pn\" type=\"text\" name=\"name\" value=\"" + req.getName() + "\">\n"
                + "                                            <label> Company </label>\n"
                + "                                            <input id=\"pc\" type=\"text\" name=\"company\" value=\"" + req.getCompany() + "\" readonly>\n"
                + "                                            <label> Designation </label>\n"
                + "                                            <input id=\"pd\" type=\"text\" name=\"designation\" value=\"" + req.getDesignation() + "\">\n"
                + "                                            <label> Address 1 </label>\n"
                + "                                            <input id=\"pa1\" type=\"text\" name=\"address1\" value=\"" + req.getAddress1() + "\">\n"
                + "                                            <label> Address 2 </label>\n"
                + "                                            <input id=\"pa2\" type=\"text\" name=\"address2\" value=\"" + req.getAddress2() + "\">\n"
                + "                                            <label> Place </label>\n"
                + "                                            <input id=\"ppl\" type=\"text\" name=\"place\" value=\"" + req.getPlace() + "\">\n"
                + "                                            <label> Pin </label>\n"
                + "                                            <input id=\"ppn\" type=\"text\" name=\"pin\" value=\"" + req.getPin() + "\">\n"
                + "                                            <label> Email </label>\n"
                + "                                            <input id=\"pe\" type=\"email\" name=\"email\" value=\"" + req.getEmail() + "\" readonly>\n"
                + "                                            <label> Mobile </label>\n"
                + "                                            <input id=\"pm\" type=\"text\" name=\"mobile\" value=\"" + req.getMobile() + "\">\n"
                + "                                            <button  type=\"submit\" class=\"btn btn-bg waves-effect\"> Update </button>\n"
                + "                                        </form>\n"
                + "                                    </div>"
                + "                                    </div>");
        return sb.toString();
    }
}
