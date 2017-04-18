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
public interface UpdateProfileValidator extends LogValidator{
    public String validateName() throws Exception;
    
    public String validateAddress1() throws Exception;
    
    public String validateAddress2() throws Exception; 
    
    public String validatePin() throws Exception; 
    
    public String validateMobile() throws Exception; 
    
    public String validateEmail() throws Exception; 
    
    public String validateCompany() throws Exception; 
    
    public String validateDesignation() throws Exception; 
}