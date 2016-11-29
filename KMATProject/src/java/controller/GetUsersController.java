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
        /*String url;
        url = "/WEB-INF/view/ViewUsers.jsp";
        //response.sendRedirect("view-users");
        ArrayList<UserBean> users = new ArrayList<UserBean>();
        users = UserDAO.getUsers();
        try{
        //if (users!=null){
            PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Inside doPost of GetUsersController');");
                    //out.println("location='Home.jsp#name';");
                    out.println("</script>");           
            HttpSession session = request.getSession(true);
            session.setAttribute("users", users);
            url = "/WEB-INF/view/ViewUsers.jsp";

        
            request.getRequestDispatcher(url).forward(request, response);
        
        //}
        }
        catch(Exception e){
            e.printStackTrace();
        }
        */
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
