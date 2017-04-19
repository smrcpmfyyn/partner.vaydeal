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
    public static final String REGX_COMPANY = "[A-Za-z0-9_@#+= ]+"; // needs to be tested
    public static final String REGX_STRING_UPPER_AND_LOWER = "[A-Za-z ]+"; // needs to be tested
    public static final String REGX_STRING_UPPER_LOWER_AND_NUMBER = "[A-Za-z0-9# ]+"; // needs to be tested
    public static final String REGX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String REGX_MOBILE = "\\d{10}";
    public static final String REGX_STRING = "[A-Z ]+"; // needs to be tested
    public static final String REGX_ACTIVITY = "[a-z_]+"; // needs to be tested
}
