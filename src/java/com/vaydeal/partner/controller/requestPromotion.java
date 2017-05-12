/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.controller;

import com.vaydeal.partner.jsn.JSONParser;
import com.vaydeal.partner.message.CorrectMsg;
import com.vaydeal.partner.message.ErrMsg;
import com.vaydeal.partner.processreq.ProcessRequestPromotion;
import com.vaydeal.partner.req.mod.RequestPromotion;
import com.vaydeal.partner.resp.mod.RequestPromotionFailureResponse;
import com.vaydeal.partner.resp.mod.RequestPromotionSuccessResponse;
import com.vaydeal.partner.result.RequestPromotionResult;
import com.vaydeal.partner.validation.RequestPromotionValidation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public class requestPromotion extends HttpServlet {

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
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            String company = request.getParameter("cmpn");
            String website = request.getParameter("ws");
            String name = request.getParameter("cp");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mob");
            RequestPromotion req = new RequestPromotion(company, website, name, email, mobile);
            RequestPromotionValidation reqV = new RequestPromotionValidation(req);
            reqV.validation();
            RequestPromotionResult reqR = JSONParser.parseJSONReqPromo(reqV.toString());
            String validSubmission = reqR.getValidationResult();
            if(validSubmission.startsWith(CorrectMsg.CORRECT_MESSAGE)){
                ProcessRequestPromotion process = new ProcessRequestPromotion(req);
                RequestPromotionSuccessResponse npSResp = process.processRequest();
                process.closeConnection();
                out.print(npSResp.toString());
            }else if(validSubmission.startsWith(ErrMsg.ERR_ERR)){
                RequestPromotionFailureResponse FResp = new RequestPromotionFailureResponse(req, reqR, validSubmission);
                out.print(FResp.toString());
            }else{
                // Exception Response
            }
        } catch (Exception ex) {
            //Eception Response
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
