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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Saim
 */
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

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
        session.invalidate();  
        response.sendRedirect("login.jsp");  
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
            user.setUserName(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            
            user = UserDAO.login(user);
            
            if (user.isValid()){
                HttpSession session = request.getSession(true);
                session.setAttribute("CurrentSessionUser", user);//.getUsername());
                //request.getRequestDispatcher("/WEB-INF/view/UserHome.jsp").forward(request, response);
                //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/UserHome.jsp");
                 //dispatcher.forward(request, response);  
               response.sendRedirect("UserHome");
                           
            }
            else{
                response.sendRedirect("LoginAgain.jsp");
               // request.getRequestDispatcher("LoginAgain.jsp").forward(request, response);
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
