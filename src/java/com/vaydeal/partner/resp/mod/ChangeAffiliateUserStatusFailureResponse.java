/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.resp.mod;

import com.vaydeal.partner.message.ResponseMsg;
import com.vaydeal.partner.result.ChangeAffiliateUserStatusResult;

/**
 * @company techvay
 * @author rifaie
 */
public class ChangeAffiliateUserStatusFailureResponse {

    private final ChangeAffiliateUserStatusResult reqR;
    private final String error;

    public ChangeAffiliateUserStatusFailureResponse(ChangeAffiliateUserStatusResult reqR, String error) {
        this.reqR = reqR;
        this.error = error;
    }

    @Override
    public String toString() {
        String json = "\"status\":\"" + ResponseMsg.RESP_NOT_OK + "\",";
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
                case "uid":
                    param = reqR.getUid();
                    resp = param.substring(param.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "status":
                    param = reqR.getStatus();
                    resp = param.substring(param.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
            }
        }
        json = json.substring(0, json.length() - 1);
        return "{" + json + "}";
    }

}
