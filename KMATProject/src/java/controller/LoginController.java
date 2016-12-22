/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.LoginUserBean;
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
        response.sendRedirect("Home.jsp");  
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
                response.sendRedirect("home");
                           
            }
            else{
                response.setContentType("text/html;charset=UTF-8");
                try {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Invalid username or password');");
                    out.println("location='Home.jsp#get-signin';");
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
