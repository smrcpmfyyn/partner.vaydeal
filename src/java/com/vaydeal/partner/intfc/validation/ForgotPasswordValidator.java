/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.intfc.validation;

/**
 *
 * @author rifaie
 */
public interface ForgotPasswordValidator extends Validator{
    
    /**
     *
     * @return
     * @throws Exception
     */
    public String validateUID() throws Exception;
    public String validateEmail() throws Exception;
}
