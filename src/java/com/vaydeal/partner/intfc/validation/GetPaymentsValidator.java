/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.intfc.validation;

/**
 * @company techvay
 * @author rifaie
 */
public interface GetPaymentsValidator extends LogValidator{
    public String validateQuery() throws Exception;
    
    public String validateOffset() throws Exception; 
}
