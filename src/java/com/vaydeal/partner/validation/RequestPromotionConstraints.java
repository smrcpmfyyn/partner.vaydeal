/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.validation;

import com.vaydeal.partner.intfc.validation.RequestPromotionValidator;
import com.vaydeal.partner.message.CorrectMsg;
import com.vaydeal.partner.message.ErrMsg;
import com.vaydeal.partner.regx.RegX;
import com.vaydeal.partner.req.mod.RequestPromotion;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

/**
 * @company techvay
 * @author rifaie
 */
public class RequestPromotionConstraints implements RequestPromotionValidator{
    
    private final RequestPromotion req;

    public RequestPromotionConstraints(RequestPromotion rp) throws Exception {
        this.req = rp;
    }

    @Override
    public String validateCompany() throws Exception {
        String valid = ErrMsg.ERR_COMPANY;
        String regX = RegX.REGX_COMPANY;
        String company = req.getCompany();
        if (validate(company, regX)) {
            valid = CorrectMsg.CORRECT_COMPANY;
        }
        return valid;
    }
    
    @Override
    public String validateWebsite() throws Exception {
        String valid = ErrMsg.ERR_WEBSITE;
        String urlString = req.getWebsite();
        try {
            URL url = new URL(urlString);
                valid = CorrectMsg.CORRECT_WEBSITE;
            
        } catch (MalformedURLException ex) {

        }
        return valid;
    }
    
    @Override
    public String validateName() throws Exception {
        String valid = ErrMsg.ERR_NAME;
        String regX = RegX.REGX_STRING_UPPER_AND_LOWER;
        String name = req.getName();
        if (validate(name, regX)) {
            valid = CorrectMsg.CORRECT_NAME;
        }
        return valid;
    }
    
    @Override
    public String validateEmail() throws Exception {
        String valid = ErrMsg.ERR_EMAIL;
        String regX = RegX.REGX_EMAIL;
        String email = req.getEmail();
        if (validate(email, regX)) {
            valid = CorrectMsg.CORRECT_EMAIL;
        }
        return valid;
    }
    
    @Override
    public String validateMobile() throws Exception {
        String valid = ErrMsg.ERR_MOBILE;
        String regX = RegX.REGX_MOBILE;
        String mobile = req.getMobile();
        if (validate(mobile, regX)) {
            valid = CorrectMsg.CORRECT_MOBILE;
        }
        return valid;
    }

    @Override
    public boolean validate(String value, String regX) {
        boolean valid = false;
        if (value.matches(regX)) {
            valid = true;
        }
        return valid;
    }

    @Override
    public void closeConnection() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
