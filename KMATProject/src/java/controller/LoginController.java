/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.LoginUserBean;
import model.UserBean;
import model.UserDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.EmailUtility;
import model.RandomPasswordGenerator;
import model.UserBean;

/**
 *
 * @author Habiba Saim
 */
public class LoginController extends HttpServlet {

    private String host;
    private String port;
    private String kmatUsername;
    private String kmatPassword;

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
        HttpSession session=request.getSession(); 
        session.removeAttribute("CurrentSessionUser");
        request.logout();
        session.invalidate();  
        //Code to prevent user from accessing any user specific page after logout/session-end -->

        response.setHeader("Cache-Control","no-cache");  //Forces caches to obtain a new copy of the page from the origin server
        response.setHeader("Cache-Control","no-store");  //Directs caches not to store the page under any circumstance
        response.setDateHeader("Expires",-1);            //Causes the proxy cache to see the page as "stale"
        response.setHeader("Pragma","no-cache");         //HTTP 1.0 backward compatibility
        response.sendRedirect("Home.jsp");  
        
        

        //if(session.getAttribute("CurrentSessionUser")==null){

          //  response.sendRedirect("Home.jsp");
       // }  
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
        ArrayList userLevels = new ArrayList();
        //Handles user login request
        if (action.equals("login")){
            try{
                LoginUserBean user = new LoginUserBean();
                user.setUserName(request.getParameter("username"));
                user.setPassword(request.getParameter("password"));

                user = UserDAO.login(user);

                if (user.isValid()){
                    HttpSession session = request.getSession(true);
                    session.setAttribute("CurrentSessionUser", user);//.getUsername());
                    //request.getRequestDispatcher("/WEB-INF/view/UserHome.jsp").forward(request, response);
                    //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/UserHome.jsp");
                     //dispatcher.forward(request, response);  
                    //userLevels = UserDAO.getUserLevels();
                    
                    response.sendRedirect("home");

                }
                else{
                    response.setContentType("text/html;charset=UTF-8");
                    try {
                        PrintWriter out = response.getWriter();
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Invalid username or password');");
                        out.println("location='Home.jsp#name';");
                        out.println("</script>");                    
                    }
                    catch (Exception e){
                        System.out.println("Alert could not be generated. Error: " + e);
                    }
                   // response.sendRedirect("Home.jsp#get-signin");
                    //request.getRequestDispatcher("Home.jsp#get-signin").forward(request, response);
                }
            }
            catch (Throwable theException){ 	    

               theException.printStackTrace(); 
            }
            finally{

            }
        }
        
        //Handles user account recovery request
        else if (action.equals("recover-account")){
            String newPassword = "kmat_user";
            UserBean user = new UserBean();
            user.setUserName(request.getParameter("username"));
            user.setPriEmail(request.getParameter("pri-email"));
            Object data = "You will shortly receive an email.";
            //user.setFirstName(kmatUsername);
            
            newPassword = new String(RandomPasswordGenerator.generatePswd(6, 6, 2, 2, 2));  //A new password is generated randomly
            user = UserDAO.recoverAccount(user, newPassword );
            //Send updated password to the user.
            if (user.isValidUser()){
               
                ServletContext context = getServletContext();
                setEmailHeader(context);
                String recipientEmail = user.getPriEmail();
                String recipientName = user.getFirstName();
                String subject = "Account recovery successful";
                String content = "Dear " + recipientName + 
                    ",\n\nYour password has been reset." +
                    "\nYour new password is: "+ newPassword +
                    "\n\nBest Regards,\n\nKMAT Team" +
                    "\n\n\n_________________________________________________________" +
                    "\nThis is an autogenerated email, kindly do not reply.";
                
                
                try{
                    EmailUtility.sendEmail(host, port, kmatUsername, kmatPassword, recipientEmail, subject,
                    content);                    
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            else{/*Scenario - 1
                //Send email to the user and tell him that your provided credentials were not correct, so meet the administrator
                ServletContext context = getServletContext();
                setEmailHeader(context);
                String recipientEmail = user.getPriEmail();
                String recipientName = user.getFirstName();
                String subject = "Account recovery unsuccessful";
                String content = "Dear " + recipientName + 
                    ",\n\nYour password has been reset." +
                    "\nYour new password is: "+ newPassword +
                    "\n\nBest Regards,\n\nKMAT Team" +
                    "\n\n\n_________________________________________________________" +
                    "\nThis is an autogenerated email, kindly do not reply.";
                
                
                try{
                    EmailUtility.sendEmail(host, port, kmatUsername, kmatPassword, recipientEmail, subject,
                    content); 
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                */
                //Scenario - 2
                data = "User name or primary email is wrong. Try again. Or meet Knowledge Engineer for your account recovery";
                
            }
            
            request.setAttribute("data", data);
            request.getRequestDispatcher("RecoverAccount.jsp").forward(request, response);
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
