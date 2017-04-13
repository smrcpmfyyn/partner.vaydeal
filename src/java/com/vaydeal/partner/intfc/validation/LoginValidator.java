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
public interface LoginValidator extends Validator{
    
    /**
     *
     * @return
     * @throws java.lang.Exception
     */
    public String validateUserName() throws Exception;
    
    /**
     *
     * @return
     * @throws Exception
     */
    public String validatePassword() throws Exception;
}
