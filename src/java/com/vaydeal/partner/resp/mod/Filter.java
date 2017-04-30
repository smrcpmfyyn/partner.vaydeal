/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.resp.mod;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @company techvay
 * @author rifaie
 */
public class Filter {

    private HashMap<String, ArrayList<String>> filters;

    public Filter() {
        this.filters = new HashMap<>();
    }

    public void addFilter(String key, ArrayList<String> values) {
        filters.put(key, values);
    }

    @Override
    public String toString() {
        return "Filter{" + "filters=" + filters + '}';
    }

    public int size() {
        return filters.size();
    }
    
    
}
