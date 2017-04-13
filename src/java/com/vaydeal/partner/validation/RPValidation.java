/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.validation;

import com.vaydeal.partner.intfc.validation.Validation;
import com.vaydeal.partner.message.CorrectMsg;
import com.vaydeal.partner.message.ErrMsg;
import com.vaydeal.partner.req.mod.ResetPassword;

/**
 * @company techvay
 * @author rifaie
 */
public class RPValidation implements Validation{
    
    private final ResetPassword req;
    private String paramValue = "";
    private String paramName = "";

    public RPValidation(ResetPassword ve) {
        this.req = ve;
    }

    @Override
    public void validation() throws Exception {
        RPConstraints rpc = new RPConstraints(req);
        String valid = "";
        valid += rpc.validateToken();
        int count = 0;
        paramName += valid.split(" ")[1].toLowerCase() + "#";
        if (!valid.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
            paramValue += valid + "#";
            count++;
        } else {
            paramValue = CorrectMsg.CORRECT_MESSAGE + "#";
        }
        paramName += "reqValidation";
        if (count == 0) {
            paramValue += CorrectMsg.CORRECT_RP;
        } else {
            paramValue += ErrMsg.ERR_RP;
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

