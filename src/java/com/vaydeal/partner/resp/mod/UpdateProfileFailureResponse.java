/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.resp.mod;

import com.vaydeal.partner.message.ResponseMsg;
import com.vaydeal.partner.result.UpdateProfileResult;

/**
 * @company techvay
 * @author rifaie
 */
public class UpdateProfileFailureResponse {

    private final UpdateProfileResult reqR;
    private final String error;

    public UpdateProfileFailureResponse(UpdateProfileResult reqR, String error) {
        this.reqR = reqR;
        this.error = error;
    }

    @Override
    public String toString() {
        String json = "\"status\":\""+ResponseMsg.RESP_NOT_OK + "\",";
        String[] errors = error.split("#");
        String resp;
        String param;
        for (int i = 1; i < errors.length; i++) {
            String parameter = errors[1];
            switch (parameter) {
                case "at":
                    param = reqR.getAt();
                    resp = param.substring(param.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "utype":
                    param = reqR.getUtype();
                    resp = param.substring(param.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "name":
                    param = reqR.getName();
                    resp = param.substring(param.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "add1":
                    param = reqR.getAdd1();
                    resp = param.substring(param.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "add2":
                    param = reqR.getAdd2();
                    resp = param.substring(param.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "pin":
                    param = reqR.getPin();
                    resp = param.substring(param.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "mobile":
                    param = reqR.getMobile();
                    resp = param.substring(param.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "email":
                    param = reqR.getEmail();
                    resp = param.substring(param.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "company":
                    param = reqR.getCompany();
                    resp = param.substring(param.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "designation":
                    param = reqR.getDesignation();
                    resp = param.substring(param.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
            }
        }
        json = json.substring(0, json.length() - 1);
        return "{" + json + "}";
    }

}


