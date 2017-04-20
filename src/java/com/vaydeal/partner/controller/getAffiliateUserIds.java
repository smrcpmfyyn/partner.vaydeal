/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.controller;

import com.vaydeal.partner.jsn.JSONParser;
import com.vaydeal.partner.message.CorrectMsg;
import com.vaydeal.partner.message.ErrMsg;
import com.vaydeal.partner.processreq.ProcessGetAffiliateUserIds;
import com.vaydeal.partner.req.mod.GetAffiliateUserIds;
import com.vaydeal.partner.resp.mod.GetAffiliateUserIdsFailureResponse;
import com.vaydeal.partner.resp.mod.GetAffiliateUserIdsSuccessResponse;
import com.vaydeal.partner.result.GetAffiliateUserIdsResult;
import com.vaydeal.partner.support.controller.BlockAffiliateUser;
import com.vaydeal.partner.support.controller.UserActivities;
import com.vaydeal.partner.validation.GetAffiliateUserIdsValidation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rifaie
 */
public class getAffiliateUserIds extends HttpServlet {

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
            Cookie ck = Servlets.getCookie(request, "at");
            String at = "";
            if (ck != null) {
                at = ck.getValue();
            }
            GetAffiliateUserIds req = new GetAffiliateUserIds(at);
            GetAffiliateUserIdsValidation reqV = new GetAffiliateUserIdsValidation(req);
            reqV.validation();
            GetAffiliateUserIdsResult reqR = JSONParser.parseJSONGAUsI(reqV.toString());
            String validSubmission = reqR.getValidationResult();
            UserActivities ua = new UserActivities(req.getAffiliate_user_id(), req.getAffiliate(),"get_payments", req.getUser_type(), "valid");
            if (validSubmission.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                ProcessGetAffiliateUserIds process = new ProcessGetAffiliateUserIds(req);
                GetAffiliateUserIdsSuccessResponse SResp = process.processRequest();
                process.closeConnection();
                ck.setValue(SResp.getAccessToken());
                response.addCookie(ck);
                out.write(SResp.toString());
            } else if (validSubmission.startsWith(ErrMsg.ERR_ERR)) {
                if (reqR.getAt().startsWith(ErrMsg.ERR_MESSAGE)) {
                    // do nothing
                } else if (reqR.getUtype().startsWith(ErrMsg.ERR_MESSAGE)) {
                    BlockAffiliateUser bau = new BlockAffiliateUser(req.getAffiliate_user_id());
                    bau.block();
                    ua.setEntryStatus("blocked");
                }
                ua.setEntryStatus("invalid");
                GetAffiliateUserIdsFailureResponse FResp = new GetAffiliateUserIdsFailureResponse(reqR, validSubmission);
                out.write(FResp.toString());
            } else {
                //exception response
            }
            ua.addActivity();
            out.flush();
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(getPayments.class.getName()).log(Level.SEVERE, null, ex);
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
