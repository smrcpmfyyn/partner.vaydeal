/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.controller;

import com.vaydeal.partner.jsn.JSONParser;
import com.vaydeal.partner.message.CorrectMsg;
import com.vaydeal.partner.message.ErrMsg;
import com.vaydeal.partner.processreq.ProcessNP;
import com.vaydeal.partner.req.mod.NewPassword;
import com.vaydeal.partner.req.mod.ResetPassword;
import com.vaydeal.partner.resp.mod.NPFailureResponse;
import com.vaydeal.partner.resp.mod.NPSuccessResponse;
import com.vaydeal.partner.result.RPResult;
import com.vaydeal.partner.validation.RPValidation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rifaie
 */
public class newPassword extends HttpServlet {

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
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            String token = request.getParameter("token");
            token = token.replaceAll(" ", "+");
            String password = request.getParameter("newpass");
            NewPassword req = new NewPassword(token, password);
            req.changePassword();
            ResetPassword rp = new ResetPassword(token);
            RPValidation reqV = new RPValidation(rp);
            reqV.validation();
            RPResult reqR = JSONParser.parseJSONRP(reqV.toString());
            String validSubmission = reqR.getValidationResult();
            if(validSubmission.startsWith(CorrectMsg.CORRECT_MESSAGE)){
                ProcessNP process = new ProcessNP(req);
                NPSuccessResponse npSResp = process.processRequest();
                process.closeConnection();
                out.print(npSResp);
            }else if(validSubmission.startsWith(ErrMsg.ERR_ERR)){
                NPFailureResponse FResp = new NPFailureResponse(reqR, validSubmission);
                out.print(FResp);
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
