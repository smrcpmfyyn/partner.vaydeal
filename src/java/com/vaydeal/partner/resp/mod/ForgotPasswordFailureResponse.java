/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.resp.mod;

import com.vaydeal.partner.req.mod.ForgotPassword;
import com.vaydeal.partner.result.ForgotPasswordResult;

/**
 * @company techvay
 * @author rifaie
 */
public class ForgotPasswordFailureResponse {

    private final ForgotPassword req;

    private final ForgotPasswordResult reqR;
    private final String error;

    public ForgotPasswordFailureResponse(ForgotPassword req, ForgotPasswordResult reqR, String error) {
        this.req = req;
        this.reqR = reqR;
        this.error = error;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String uid = req.getUid();
        String email = req.getEmail();
        String ur = "";
        String er = "";
        String[] errors = error.split("#");
        String resp;
        for (int i = 1; i < errors.length; i++) {
            String parameter = errors[1];
            switch (parameter) {
                case "uid":
                    resp = reqR.getUid().substring(uid.lastIndexOf(" ") + 1);
                    if (resp.startsWith("not")) {
                        ur = "User ID not exists";
                    } else {
                        ur = "User ID invalid";
                    }
                    break;
                case "email":
                    resp = reqR.getEmail().substring(email.lastIndexOf(" ") + 1);
                    if (resp.startsWith("not")) {
                        ur = "Email not exists";
                    } else {
                        ur = "Email invalid";
                    }
                    break;
            }
        }
        sb.append("<div id=\"rp\" class=\"col-12 reset-pwd\"> \n"
                + "                <div class=\"form reset-form text-left\">\n"
                + "                    <form onsubmit=\"return fpsend()\">\n"
                + "\n"
                + "                        <h2 class=\"text-center\"> Forgot Password </h2>\n"
                + "                        <label for=\"uid\"> User ID</label>\n"
                + "                        <input required pattern=\"[0-9]{7,}\" oninvalid=\"setCustomValidity('Please enter numbers only')\" id=\"uid\" type=\"text\" name=\"uid\" value='" + uid + "'>\n"
                + "                        <label>"+ur+"</label>\n"
                + "                        <label for=\"email\"> Email</label>\n"
                + "                        <input id=\"email\" type=\"email\" required name=\"email\" value='" + email + "'>\n"
                + "                        <label>"+er+"</label>\n"
                + "                        <button id=\"forgotBtn\" type=\"submit\" class=\"btn btn-bg waves-effect\"> Submit </button>\n"
                + "                    </form>\n"
                + "                </div>    \n"
                + "            </div>");
        return sb.toString();
    }

}
