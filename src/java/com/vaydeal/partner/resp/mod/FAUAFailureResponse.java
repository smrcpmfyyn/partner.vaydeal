/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.resp.mod;

import com.vaydeal.partner.message.ResponseMsg;
import com.vaydeal.partner.result.FAUAResult;

/**
 * @company techvay
 * @author rifaie
 */
public class FAUAFailureResponse {
    private final FAUAResult reqR;
    private final String error;

    public FAUAFailureResponse(FAUAResult reqR, String error) {
        this.reqR = reqR;
        this.error = error;
    }
    
    @Override
    public String toString() {
        String json = "\"status\":\""+ResponseMsg.RESP_NOT_OK + "\",";
        String[] errors = error.split("#");
        
        String resp;
        for (int i = 1; i < errors.length; i++) {
            String parameter = errors[i];
            switch (parameter) {
                case "at":
                    String at = reqR.getAt();
                    resp = at.substring(at.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "utype":
                    String param = reqR.getUtype();
                    resp = param.substring(param.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "ftruid":
                    String ftruid = reqR.getFtruid();
                    resp = ftruid.substring(ftruid.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "ftrutype":
                    String ftrutype = reqR.getFtrutype();
                    resp = ftrutype.substring(ftrutype.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "ftractivity":
                    String ftractivity = reqR.getFtractivity();
                    resp = ftractivity.substring(ftractivity.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "ftrentrystatus":
                    String ftrentrystatus = reqR.getFtrentrystatus();
                    resp = ftrentrystatus.substring(ftrentrystatus.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "offset":
                    String offset = reqR.getOffset();
                    resp = offset.substring(offset.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
            }
        }
        json = json.substring(0, json.length() - 1);
        return "{" + json + "}";
    }
}

