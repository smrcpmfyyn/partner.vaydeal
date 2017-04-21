/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.validation;

import com.vaydeal.partner.intfc.validation.Validation;
import com.vaydeal.partner.message.CorrectMsg;
import com.vaydeal.partner.message.ErrMsg;
import com.vaydeal.partner.req.mod.RequestPromotion;

/**
 * @company techvay
 * @author rifaie
 */
public class RequestPromotionValidation implements Validation{
    
    private final RequestPromotion req;
    private String paramValue = "";
    private String paramName = "";

    public RequestPromotionValidation(RequestPromotion ve) {
        this.req = ve;
    }

    @Override
    public void validation() throws Exception {
        RequestPromotionConstraints reqc = new RequestPromotionConstraints(req);
        String valid = "";
        valid += reqc.validateCompany();
        valid += "#"+reqc.validateWebsite();
        valid += "#"+reqc.validateName();
        valid += "#"+reqc.validateEmail();
        valid += "#"+reqc.validateMobile();
        int count = 0;
        for (String str : valid.split("#")) {
            paramName += str.split(" ")[1].toLowerCase() + "#";
            if (!str.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                count++;
                paramValue += str + "#";
            } else {
                paramValue += CorrectMsg.CORRECT_MESSAGE + "#";
            }
        }
        paramName += "reqValidation";
        if (count == 0) {
            paramValue += CorrectMsg.CORRECT_REQUEST_PROMOTION;
        } else {
            paramValue += ErrMsg.ERR_REQUEST_PROMOTION;;
        }
    }
    
    @Override
    public String toString() {
        String[] paramsN = paramName.split("#");
        String[] paramV = paramValue.split("#");
        String json = "";
        for (int i = 0; i < paramsN.length; i++) {
            json += "\"" + paramsN[i] + "\"" + ":" + "\"" + paramV[i] + "\" ,";
        }
        json = json.substring(0, json.length() - 1);
        return "{" + json + "}";
    }
    

}
