/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.controller;

import com.vaydeal.partner.jsn.JSONParser;
import com.vaydeal.partner.message.CorrectMsg;
import com.vaydeal.partner.message.ErrMsg;
import com.vaydeal.partner.processreq.ProcessFAUA;
import com.vaydeal.partner.req.mod.FAUA;
import com.vaydeal.partner.resp.mod.FAUAFailureResponse;
import com.vaydeal.partner.resp.mod.FAUASuccessResponse;
import com.vaydeal.partner.result.FAUAResult;
import com.vaydeal.partner.support.controller.BlockAffiliateUser;
import com.vaydeal.partner.support.controller.UserActivities;
import com.vaydeal.partner.validation.FAUAValidation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author rifaie
 */
public class filterAffiliateUserActivities extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try (PrintWriter out = response.getWriter()) {
            JSONObject jObj = new JSONObject(request.getParameter("ftr"));
            String maxEntries = request.getParameter("me");
            String pageNo = request.getParameter("pn");
            Cookie ck = Servlets.getCookie(request, "at");
            String at = "";
            if (ck != null) {
                at = ck.getValue();
            }
            FAUA req = new FAUA(at, jObj, maxEntries, pageNo);
            FAUAValidation reqV = new FAUAValidation(req);
            reqV.validation();
            FAUAResult reqR = JSONParser.parseJSONFAUA(reqV.toString());
            String validSubmission = reqR.getValidationResult();
            UserActivities ua = new UserActivities(req.getAffiliate_user_id(), req.getAffiliate(),"filter_user_activity", req.getUser_type(), "valid");
            System.out.println(validSubmission);
            if (validSubmission.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                response.setContentType("text/html");
                ProcessFAUA process = new ProcessFAUA(req);
                FAUASuccessResponse SResp = process.processRequest();
                process.closeConnection();
                ck.setValue(SResp.getAccessToken());
                response.addCookie(ck);
                out.write(SResp.toString());
            } else if (validSubmission.startsWith(ErrMsg.ERR_ERR)) {
                response.setContentType("application/json");
                if (reqR.getAt().startsWith(ErrMsg.ERR_MESSAGE)) {
                    // do nothing
                } else if (reqR.getUtype().startsWith(ErrMsg.ERR_MESSAGE)) {
                    BlockAffiliateUser bau = new BlockAffiliateUser(req.getAffiliate_user_id());
                    bau.block();
                    ua.setEntryStatus("blocked");
                    ua.addActivity();
                }
//                ua.setEntryStatus("invalid");
                FAUAFailureResponse FResp = new FAUAFailureResponse(reqR, validSubmission);
                System.out.println(FResp.toString());
                out.write(FResp.toString());
            } else {
                //exception response
            }
//            ua.addActivity();
            out.flush();
            out.close();
        } catch (Exception ex) {
//            System.out.println(ex.getCause());
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
