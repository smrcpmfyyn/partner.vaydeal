/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.intfc.validation;

/**
 *
 * @author USER
 */
public interface RequestPromotionValidator extends Validator{
    
    /**
     *
     * @return
     * @throws Exception
     */
    public String validateCompany() throws Exception;
    public String validateWebsite() throws Exception;
    public String validateName() throws Exception;
    public String validateEmail() throws Exception;
    public String validateMobile() throws Exception;
    
}
