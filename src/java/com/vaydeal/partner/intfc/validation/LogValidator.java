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
public interface LogValidator extends Validator{
    public String validateAccessToken() throws Exception;
    public String validateAffiliate(String affiliate) throws Exception;
}
