/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.controller;

import com.vaydeal.partner.jsn.JSONParser;
import com.vaydeal.partner.message.CorrectMsg;
import com.vaydeal.partner.message.ErrMsg;
import com.vaydeal.partner.message.ValidationMsg;
import com.vaydeal.partner.req.mod.ResetPassword;
import com.vaydeal.partner.resp.mod.RPFailureResponse;
import com.vaydeal.partner.resp.mod.RPSuccessResponse;
import com.vaydeal.partner.result.RPResult;
import com.vaydeal.partner.validation.RPValidation;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rifaie
 */
public class resetPassword extends HttpServlet {

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
            String token = request.getParameter("token");
            token = URLDecoder.decode(token, "UTF-8");
            token = token.replaceAll(" ", "+");
            ResetPassword req = new ResetPassword(token);
            RPValidation reqV = new RPValidation(req);
            reqV.validation();
            RPResult reqR = JSONParser.parseJSONRP(reqV.toString());
            String validSubmission = reqR.getValidationResult();
            if (validSubmission.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                RPSuccessResponse SResp = new RPSuccessResponse(ValidationMsg.VALID, token);
                out.print(SResp.toString());
//                response.sendRedirect("reset-password.html?token=" + rpSResp.getToken());
            } else if (validSubmission.startsWith(ErrMsg.ERR_ERR)) {
                RPFailureResponse FResp = new RPFailureResponse(reqR, validSubmission);
                FResp.toString();
                out.print(FResp.toString());
//                if (rpFResp.getToken().equals("expired")) {
//                    response.sendRedirect("index.html?rp=texp");
//                } else if (rpFResp.getToken().equals("expired")) {
//                    response.sendRedirect("index.html?rp=tu");
//                } else {
//                    response.sendRedirect("index.html?rp=err");
//                }
            } else {
//                response.sendRedirect("index.html?rp=err");
                //exception response
            }
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
//            response.sendRedirect("index.html?rp=err");
            //Exception Response
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
