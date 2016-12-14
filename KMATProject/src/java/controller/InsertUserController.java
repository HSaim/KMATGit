/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.UserBean;
import model.UserDAO;
import model.EmailUtility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * Connect to KMAT DB to add a new user
 * @author Habiba Saim
 */
@WebServlet(name = "InsertUserController", urlPatterns = {"/InsertUserController"})
public class InsertUserController extends HttpServlet {

   
    

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
        try{
            UserBean user = new UserBean();
            //Get Signup form elements 
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
            user.setMobPhone(request.getParameter("m_phone"));
            user.setHidden(request.getParameter("hidden"));
            
            user = UserDAO.insertUser(user);
            
            //If insertion done successfully and done by existing user
            if (user.isAdded() && user.getHidden().equalsIgnoreCase("adduser")){
                //HttpSession session = request.getSession(true);
                //session.setAttribute("CurrentSessionUser", user);//.getUsername());
                //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/UserHome.jsp");
                //dispatcher.forward(request, response);
                response.setContentType("text/html;charset=UTF-8");
                try {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('One record inserted successfully!');");
                    out.println("location='home';");
                    out.println("</script>");                    
                }
                catch (Exception e){
                    System.out.println("Alert could not be generated. Error: " + e);
                }
      
            }
            //If insertion done successfully and done by sign up form
            else if (user.isAdded() && user.getHidden().equalsIgnoreCase("signup")){
                
                String host;
                String port;
                String kmatUsername;
                String kmatPassword;
                response.setContentType("text/html;charset=UTF-8");
                try {
                    UserDAO.insertUnregisteredUser(user.getUserId());
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Your request has been sent to Knowledge Engineer. You can login to KMAT after receiving the confirmation email at your primary email. ');");
                    out.println("location='Home.jsp';");
                    out.println("</script>"); 
                    
                    //Email sending functionality
                    ServletContext context = getServletContext();
                    host = context.getInitParameter("host");
                    port = context.getInitParameter("port");
                    kmatUsername = context.getInitParameter("user");
                    kmatPassword = context.getInitParameter("pass");
                    String recipientEmail = kmatUsername;
                    String recipientName = "Knowledge Engineer";
                    //String updateAccount = 
                    String subject = "Signup Request";
                    String content = "Dear " + recipientName + 
                            ",\n\nKindly add " + user.getFirstName() + " in KMAT." +
                            "\n\nBest Regards,\n\nKMAT Team" +
                            "\n\n\n_________________________________________________________" +
                            "\nThis is an autogenerated email, kindly do not reply.";
                    EmailUtility.sendEmail(host, port, kmatUsername, kmatPassword, recipientEmail, subject, content);
                    
                }
                catch (Exception e){
                    System.out.println("Alert could not be generated. Error: " + e);
                }
            }
            
            //If duplicate user name is given in the form from an existing user
            else if (!user.isAdded() && user.isDuplicateUser() && user.getHidden().equalsIgnoreCase("adduser")){  
                response.setContentType("text/html;charset=UTF-8");
                try {
                    PrintWriter out = response.getWriter();
                    String userName= user.getUserName();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('User name: " + user.getUserName() +" already exists, choose a new one!');");
                    out.println("location='add-user#user_name';");
                    out.println("</script>");                    
                }
                catch (Exception e){
                    System.out.println("Alert could not be generated. Error: " + e);
                }
            }
            //If duplicate user name is given in the signup form
            else if (!user.isAdded() && user.isDuplicateUser() && user.getHidden().equalsIgnoreCase("signup")){  
                response.setContentType("text/html;charset=UTF-8");
                try {
                    PrintWriter out = response.getWriter();
                    String userName= user.getUserName();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('User name: " + userName +" already exists, choose a new one!');");
                    out.println("location='SignUp.jsp#user_name';");
                    out.println("</script>");                    
                }
                catch (Exception e){
                    System.out.println("Alert could not be generated. Error: " + e);
                }
            }
        }
        catch (Throwable theException){ 	    
       
           theException.printStackTrace(); 
        }
        finally{
            
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

}
