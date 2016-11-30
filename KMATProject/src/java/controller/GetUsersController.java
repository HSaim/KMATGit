/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.UserBean;
import model.UserDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        //String username = request.getParameter("userName");
        if (action.equals("get-all-users")){
        ArrayList<UserBean> users = new ArrayList<UserBean>();
        users = UserDAO.getUsers();
        try{
            if (users!=null){
        //PrintWriter out = response.getWriter();
          //      out.println("<script type=\"text/javascript\">");
            //    out.println("alert('Inside doPost of GetUsersController');");
              //  out.println("location='view-users';");
                //out.println("</script>");           
                HttpSession session = request.getSession(true);
                session.setAttribute("users", users);
                response.sendRedirect("view-users");
                //url = "/WEB-INF/view/ViewUsers.jsp";


                //request.getRequestDispatcher(url).forward(request, response);
            }
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
        
        else if (action.equals("update-user")){
            try{
                UserBean user = new UserBean();
                //Get Signup form elements 
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
