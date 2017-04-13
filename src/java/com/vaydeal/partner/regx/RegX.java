/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.regx;

/**
 * @company techvay
 * @author rifaie
 */
public class RegX {
    public static final String REGX_TOKEN = "[A-Za-z0-9+/=]+";
    public static final String REGX_B64ENCODE = "^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{4})$";
    public static final String REGX_DIGIT = "\\d+";
}
