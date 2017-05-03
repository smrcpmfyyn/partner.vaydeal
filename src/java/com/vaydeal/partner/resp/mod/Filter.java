/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.resp.mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
        Set<String> keys = filters.keySet();
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            String checkBox = "";
            String cbVal = "";
            switch (key) {
                case "Activities":
                    sb.append("<div class=\"col-4\">\n"
                            + "                    <div class=\"sm-select\">\n"
                            + "                        <h2> " + key + " </h2>");
                    checkBox = "<input onclick=\"addActivity(this);\" type=\"checkbox\" value=\"" ;
                    break;
                case "Activity Status":
                    sb.append("<div class=\"col-4\">                                    \n"
                            + "                    <div class=\"\">\n"
                            + "                        <h2>" + key + "</h2>");
                    checkBox = "<input onclick=\"addEntryStatus(this);\" type=\"checkbox\" value=\"" ;
                    break;
                case "User Type":
                    sb.append("<div class=\"col-4\">                                    \n"
                            + "                    <div class=\"\">\n"
                            + "                        <h2>" + key + "</h2>");
                    checkBox = "<input onclick=\"addUtype(this);\" type=\"checkbox\" value=\"" ;
                    break;
            }
            for (String string : filters.get(key)) {
                cbVal = string;
                sb.append(checkBox);
                sb.append(cbVal);
                sb.append("\" />");
                sb.append(cbVal);
                sb.append(" <br />");
                cbVal = "";
            }
            sb.append("</div>\n"
                    + "                </div>");

        }
        sb.append("<div class=\"col-12\">\n"
                + "                    <button class=\"btn btn-bg\" onclick=\"setFilter()\"> Apply </button>\n"
                + "                </div>");
        return sb.toString();
    }

    public int size() {
        return filters.size();
    }

}
