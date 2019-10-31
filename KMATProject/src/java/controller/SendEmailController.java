/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.EmailUtility;

/**
 *
 * @author Saim
 */
public class SendEmailController extends HttpServlet {    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    //Parametrs for Email 
    private String host;
    private String port;
    private String kmatUsername;
    private String kmatPassword;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
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
        
        ServletContext context = request.getServletContext();
        HttpSession session = request.getSession(false);
        String message = "Message sent successfully!";
        session.setAttribute("msg", message);
        
        
        String senderName = request.getParameter("name");
        String senderEmail = request.getParameter("email");
        String senderMessage = request.getParameter ("message");
        
        setEmailHeader(context);
        
        String recipientEmail = kmatUsername;
        String recipientName = "KMAT Team";
        String subject = "Contact Us Email Message received from " + request.getParameter("name");
        String content = "Sender Name:\n\t\t " + senderName + "\n\n"
             + "Sender Email Address:\n\t\t " + senderEmail + "\n\n"
             + "Sender Message: \n\t\t" + senderMessage;

        try{
            EmailUtility.sendEmail(host, port, kmatUsername, kmatPassword, recipientEmail, subject,
            content);
            
            response.sendRedirect("Contact.jsp#contactform");
        }
        catch (Exception e){
            e.printStackTrace();
        }
       
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
    
    private void setEmailHeader(ServletContext context){
        //Getting from web.xml
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        kmatUsername = context.getInitParameter("user");
        kmatPassword = context.getInitParameter("pass");
    }

}
