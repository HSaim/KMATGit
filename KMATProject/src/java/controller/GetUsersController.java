/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.UserBean;
import model.UserDAO;
import model.EmailUtility;
import model.LoginUserBean;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Habiba Saim
 */
public class GetUsersController extends HttpServlet {
    private String host;
    private String port;
    private String kmatUsername;
    private String kmatPassword;
    String currentUsername;
    LoginUserBean currentUser ;
    
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
        doPost(request, response );
        
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
        
        String action =request.getParameter("action");
        //currentUsername = request.getParameter("currentuser");
        currentUser = (LoginUserBean) request.getSession(false).getAttribute("CurrentSessionUser");
        currentUsername = currentUser.getUsername();
        
        //To retrieve the users in KMAT DB
        if (action.equals("get-all-users")){            
            ArrayList<UserBean> registeredUsers = new ArrayList<UserBean>();
            registeredUsers = UserDAO.getRegisteredUsers(currentUsername);
            
            ArrayList<UserBean> unRegisteredUsers = new ArrayList<UserBean>();
            unRegisteredUsers = UserDAO.geUnregisteredUsers(currentUsername);
            
            try{
                HttpSession session = request.getSession(true);
                if (registeredUsers!=null){                   
                    session.setAttribute("rusers", registeredUsers);                                        
                }
                if (unRegisteredUsers!=null){                   
                   session.setAttribute("uusers", unRegisteredUsers);                                        
                }
                response.sendRedirect("view-users");
            }
            catch(Exception e){
                    e.printStackTrace();
            }
        }
        
        else if(action.equals("get-user")){
            String username;
            UserBean user = new UserBean();
            username = request.getParameter("userName");
            user = UserDAO.getUser(username);
            request.setAttribute("user", user);
            HttpSession session = request.getSession(true);
            session.setAttribute("ret-user", user);
            response.sendRedirect("edit-user");
        }
        else if(action.equals("get-uuser")){
            String username;
            UserBean user = new UserBean();
            username = request.getParameter("userName");
            user = UserDAO.getUser(username);
            request.setAttribute("user", user);
            HttpSession session = request.getSession(true);
            session.setAttribute("ret-user", user);
            response.sendRedirect("register-user");
        }
        
        else if (action.equals("update-user")){
            //Email sending functionality
            ServletContext context = getServletContext();
            host = context.getInitParameter("host");
            port = context.getInitParameter("port");
            kmatUsername = context.getInitParameter("user");
            kmatPassword = context.getInitParameter("pass");
            String recipientEmail = request.getParameter("p_email");
            String recipientName = request.getParameter("firstname");
            String subject = "Account update";
            String content = "Dear " + recipientName + 
                    ",\n\nYour account has been updated successfully." + 
                    "\n\nBest Regards,\n\nKMAT Team" +
                    "\n\n\n_________________________________________________________" +
                    "\nThis is an autogenerated email, kindly do not reply.";
            try{
                UserBean user = new UserBean();
                //Get update form elements 
                String user_id = request.getParameter("userId");
                user.setUserId(( Integer.parseInt(user_id)));
                user.setUserName(request.getParameter("user_name"));
                user.setPassword(request.getParameter("password"));
                user.setFirstName(request.getParameter("firstname"));
                user.setLastName(request.getParameter("lastname"));
                user.setPriEmail(request.getParameter("p_email"));
                user.setSecEmail(request.getParameter("s_email"));
                user.setPosAddress(request.getParameter("postal_address"));
                user.setPerAddress(request.getParameter("per_address"));
                user.setWorkPhone(request.getParameter("w_phone"));
                user.setMobPhone(request.getParameter("h_phone"));
                user.setHomePhone (request.getParameter("h_phone"));
                //user.setHidden(request.getParameter("hidden"));

                user = UserDAO.updateUser(user);
                /*
                if (!user.isDuplicateUser()){
                    response.setContentType("text/html;charset=UTF-8");
                try {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('User updates successfully!');");
                    out.println("location='GetUsersController?action=get-all-users';");
                    out.println("</script>");                    
                }
                catch (Exception e){
                    System.out.println("Alert could not be generated. Error: " + e);
                }
                response.sendRedirect("GetUsersController?action=get-all-users");
                }
                else{
                     response.setContentType("text/html;charset=UTF-8");
                try {
                    PrintWriter out = response.getWriter();
                    String userName= user.getUserName();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('User name: " + user.getUserName() +" already exists, choose a new one!');");
                    out.println("location='edit-user';");
                    out.println("</script>");                    
                }
                catch (Exception e){
                    System.out.println("Alert could not be generated. Error: " + e);
                }
                    
                }*/
                EmailUtility.sendEmail(host, port, kmatUsername, kmatPassword, recipientEmail, subject,
                    content);
                response.setContentType("text/html;charset=UTF-8");
                try {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('User " +user.getUserName() + " updated successfully!');");
                    out.println("location='GetUsersController?action=get-all-users';");
                    out.println("</script>");                    
                }
                catch (Exception e){
                    System.out.println("Alert could not be generated. Error: " + e);
                }
                //response.sendRedirect("GetUsersController?action=get-all-users");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        
        else if (action.equals("register-user")){
            //Email sending functionality
            ServletContext context = getServletContext();
            host = context.getInitParameter("host");
            port = context.getInitParameter("port");
            kmatUsername = context.getInitParameter("user");
            kmatPassword = context.getInitParameter("pass");
            String recipientEmail = request.getParameter("p_email");
            String recipientName = request.getParameter("firstname");
            //String updateAccount = 
            String subject = "User Registration";
            String content = "Dear " + recipientName + 
                    ",\n\nWelcome to KMAT" + 
                    "\n\nBest Regards,\n\nKMAT Team" +
                    "\n\n\n_________________________________________________________" +
                    "\nThis is an autogenerated email, kindly do not reply.";
            try{
                UserBean user = new UserBean();
                //Get registeration form elements 
                //user.setUserId(request.getParameter("userId"));
                String user_id = request.getParameter("userId");
                user.setUserId(( Integer.parseInt(user_id)));
                user.setUserName(request.getParameter("user_name"));
                user.setPassword(request.getParameter("password"));
                user.setFirstName(request.getParameter("firstname"));
                user.setLastName(request.getParameter("lastname"));
                user.setPriEmail(request.getParameter("p_email"));
                user.setSecEmail(request.getParameter("s_email"));
                user.setPosAddress(request.getParameter("postal_address"));
                user.setPerAddress(request.getParameter("per_address"));
                user.setWorkPhone(request.getParameter("w_phone"));
                user.setHomePhone (request.getParameter("h_phone"));
                
                user = UserDAO.registerUser(user);
                
                EmailUtility.sendEmail(host, port, kmatUsername, kmatPassword, recipientEmail, subject,
                    content);
                response.setContentType("text/html;charset=UTF-8");
                try {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('User " +user.getUserName() + " registered successfully!');");
                    out.println("location='GetUsersController?action=get-all-users';");
                    out.println("</script>");                    
                }
                catch (Exception e){
                    System.out.println("Alert could not be generated. Error: " + e);
                }
                //response.sendRedirect("GetUsersController?action=get-all-users");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else if (action.equals("del-user")){
            String username;
            int deleted;
            username = request.getParameter("userName");
            deleted = UserDAO.deleteUser(username);
            response.setContentType("text/html;charset=UTF-8");
            //response.sendRedirect("GetUsersController?action=get-all-users");
            if (deleted !=0){
                try {
                    
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('User " +username+ " has been deleted succesfully.');");
                    out.println("location='GetUsersController?action=get-all-users';");                    
                    out.println("</script>");                    
                }
                catch (Exception e){
                    System.out.println("Alert could not be generated. Error: " + e);
                }
            }
            else{
                try {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    
                    out.println("location='GetUsersController?action=get-all-users';");
                    out.println("alert('User '" +username+ "' could not be deleted.');");
                    out.println("</script>");                    
                }
                catch (Exception e){
                    System.out.println("Alert could not be generated. Error: " + e);
                }
            }
        }
        //Delete unregsitered user
        else if (action.equals("del-uuser")){
            String username;
            String useremail;
            int deleted;
            ServletContext context = getServletContext();
            host = context.getInitParameter("host");
            port = context.getInitParameter("port");
            kmatUsername = context.getInitParameter("user");
            kmatPassword = context.getInitParameter("pass");
            String recipientEmail = request.getParameter("userEmail");
            String recipientName = request.getParameter("userFName");
            String subject = "Registration Denied";
            String content = "Dear " + recipientName + 
                    ",\n\nWE are sorry to inform you that you did not get registered to KMAT" + 
                    "\n\nBest Regards,\n\nKMAT Team" +
                    "\n\n\n_________________________________________________________" +
                    "\nThis is an autogenerated email, kindly do not reply.";
            username = request.getParameter("userName");
            useremail = request.getParameter("userEmail");
            deleted = UserDAO.deleteUnregisteredUser(username);
            response.setContentType("text/html;charset=UTF-8");
            //response.sendRedirect("GetUsersController?action=get-all-users");
            if (deleted !=0){
                try {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('User " +username+ " has been deleted succesfully.');");
                    out.println("location='GetUsersController?action=get-all-users';");                    
                    out.println("</script>");  
                    EmailUtility.sendEmail(host, port, kmatUsername, kmatPassword, recipientEmail, subject,
                    content);
                }
                catch (Exception e){
                    System.out.println("Alert could not be generated. Error: " + e);
                }
            }
            else{
                try {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");                    
                    out.println("location='GetUsersController?action=get-all-users';");
                    out.println("alert('User '" +username+ "' could not be deleted.');");
                    out.println("</script>");                    
                }
                catch (Exception e){
                    System.out.println("Alert could not be generated. Error: " + e);
                }
            }
        }
        
        else if (action.equals("my-profile")){
            
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
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        kmatUsername = context.getInitParameter("user");
        kmatPassword = context.getInitParameter("pass");
    }

}
