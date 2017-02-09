/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import static controller.InsertResourceController.stmt;
import model.ResourceBean;
import model.ResourceDAO;
import model.EmailUtility;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.LoginUserBean;
import model.UserBean;
import model.UserDAO;
import model.ConnectionManager;


/**
 *
 * @author Fahad Akhtar
 */
public class GetResourceController extends HttpServlet{
    private String host;
    private String port;
    private String kmatUsername;
    private String kmatPassword;
    String currentUsername;
    //ServletContext context = request.getServletContext();
    //LoginUserBean currentUser = (LoginUserBean)request.getSession(false).getAttribute("CurrentSessionUser");
    LoginUserBean currentUser;// = (LoginUserBean)request.getSession(false).getAttribute("CurrentSessionUser");
    //String name = currentUser.getUsername();
   // String searchUserID = "select user_id from user_tbl where username='"+ name+"'";
    
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
        doPost(request,response);
         
     
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
        String action = request.getParameter("action");
        currentUser = (LoginUserBean) request.getSession(false).getAttribute("CurrentSessionUser");
        currentUsername = currentUser.getUsername();
        String searchUserID = "select user_id from user_tbl where username='"+currentUsername+"'";
        Connection currentCon = ConnectionManager.getConnection();
        ResultSet rs = null;
        boolean more;
        String uid = null;
        
        if (action.equals("get-all-resources")){
        ArrayList<ResourceBean> resource = new ArrayList<ResourceBean>();
       
        ArrayList<UserBean> registeredUsers = new ArrayList<UserBean>();
        resource = ResourceDAO.getResources(currentUsername);
            //registeredUsers = UserDAO.getRegisteredUsers(currentUsername);
        try{ 
            if (resource!=null){
        //PrintWriter out = response.getWriter();
          //      out.println("<script type=\"text/javascript\">");
            //    out.println("alert('Inside doPost of GetUsersController');");
              //  out.println("location='view-users';");
                //out.println("</script>");           
                HttpSession session = request.getSession(true);
                if(resource!=null)
                {
                    session.setAttribute("resource", resource);                    
                }
                response.sendRedirect("view-resources");
                //url = "/WEB-INF/view/ViewUsers.jsp";


                //request.getRequestDispatcher(url).forward(request, response);
            }
        }
        catch(Exception e){
                e.printStackTrace();
        }
        }
        else if(action.equals("get-resource")){
            String resourcename;
            ResourceBean resource = new ResourceBean();
            resourcename = request.getParameter("resourceName");
            resource = ResourceDAO.getResource(resourcename);
            request.setAttribute("resource", resource);
            HttpSession session = request.getSession(true);
            session.setAttribute("ret-resource", resource);
            response.sendRedirect("edit-resource");
        }
        else if (action.equals("update-resource")){
            //Email sending functionality
            ServletContext context = getServletContext();
            host = context.getInitParameter("host");
            port = context.getInitParameter("port");
            kmatUsername = context.getInitParameter("user");
            kmatPassword = context.getInitParameter("pass");
            String recipientEmail = request.getParameter("p_email");
            String recipientName = request.getParameter("firstname");
            String subject = "Resource update";
            String content = "Dear " + recipientName + 
                    ",\n\nYour resource has been updated successfully." + 
                    "\n\nBest Regards,\n\nKMAT Team" +
                    "\n\n\n_________________________________________________________" +
                    "\nThis is an autogenerated email, kindly do not reply.";
            try{
                ResourceBean resource = new ResourceBean();
                stmt=currentCon.createStatement();
                rs = stmt.executeQuery(searchUserID);	        
                more = rs.next();
                if(more){
                    uid = rs.getString("user_id");                  
                }
                //resource.setResourceID();
                
                
                /*//Get Signup form elements 
                user.setUserId(request.getParameter("userId"));
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
                //user.setHidden(request.getParameter("hidden"));

                //user = UserDAO.updateUser(user);
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
                  //  out.println("alert('User " +user.getUserName() + " updated successfully!');");
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
        
    }
    
}
