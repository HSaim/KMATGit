/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Saim
 */
@WebServlet(name = "RequestDispatcher", 
        loadOnStartup = 1,
        urlPatterns = {"/home",
                        "/NewProcessLadder",
                        "/view-composition-ladders",
                        "/UserContactUs",
                        "/UserNews",
                        "/AddUser",
                        "/AddResource",
                        "/add-process-ladder",
						"/edit-process-ladder"
                        })
public class RequestDispatcher extends HttpServlet {

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
        
         String userPath = request.getServletPath();
         
           // if user home page is requested
        if (userPath.equals("/home")) {
            userPath = "/UserHome";
        }
        else if (userPath.equals("/view-users")) {
            userPath = "/ViewUsers";
        }
        else if (userPath.equals("/edit-user")) {
            userPath = "/EditUser";
        }
        else if (userPath.equals("/register-user")) {
            userPath = "/RegisterUser";
        }
        else if(userPath.equals("/add-user")){
            userPath = "/AddUser";
        }
        
        else if(userPath.equals("/view-roles")){
            userPath = "/ViewRoles";
        }
		else if (userPath.equals("/view-a-roles-hierarchy")){
			userPath = "/ViewARolesHierarchy";
        }
        else if(userPath.equals("/add-role")){
            userPath = "/AddRole";
        }
		else if (userPath.equals("/edit-roles-hierarchy")){
            userPath = "/EditRolesHierarchy";
        }
		
        else if(userPath.equals("/view-composition-ladders")){
            userPath = "/ViewCompositionLadders";
        }
		else if (userPath.equals("/view-a-composition-ladder")){
            userPath = "/ViewACompositionLadder";
        }
        else if(userPath.equals("/add-composition-ladder")){
            userPath = "/AddCompositionLadder";
        }
		else if (userPath.equals("/edit-composition-ladder")){
            userPath = "/EditCompositionLadder";
        }
                else if(userPath.equals("/view-concept-maps")){
            userPath = "/ViewConceptMaps";
        }
                else if(userPath.equals("/view-a-concept-map")){
            userPath = "/ViewAConceptMap";
        }
        else if(userPath.equals("/add-concept-map")){
            userPath = "/AddConceptMap";
        }
        else if(userPath.equals("/edit-concept-map")){
            userPath = "/EditConceptMap";
        }
        
        else if(userPath.equals("/view-process-ladders")){
            userPath = "/ViewProcessLadders";
        }
		else if (userPath.equals("/view-a-process-ladder")){
            userPath = "/ViewAProcessLadder";
        }
        else if (userPath.equals("/add-process-ladder")){
            userPath = "/AddProcessLadder";
        }
		else if (userPath.equals("/edit-process-ladder")){
            userPath = "/EditProcessLadder";
        }
		
        else if(userPath.equals("/view-tools")){
            userPath = "/ViewTools";
        }
        else if (userPath.equals("/add-tool")){
            userPath = "/AddTool";
        }
        else if(userPath.equals("/view-resources")){
            userPath = "/ViewResources";
        }
        else if (userPath.equals("/add-resource")){
            userPath = "/AddResource";
        }
        else if (userPath.equals("/my-profile")){
            userPath = "/MyProfile";
        }
        else if (userPath.equals("/change-password")){
            userPath = "/ChangePassword";
        }
        
        
        
        
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
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
