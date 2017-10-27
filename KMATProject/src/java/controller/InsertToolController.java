/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;
import javax.servlet.*; 
import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.output.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import model.ConnectionManager;
import model.LoginUserBean;
import model.ToolBean;
import model.ToolDAO;

/**
 *
 * @author Fahad Akhtar
 */
@WebServlet(name = "InsertToolController", urlPatterns = {"/insert-tool"})
public class InsertToolController extends HttpServlet{
    
    static Connection currentCon = null;
    static ResultSet rs = null;
    static Statement stmt = null;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        PrintWriter out = response.getWriter();
        out.println("Tool Controller Handling Request from GET.");
        
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws SecurityException, IOException{
        PrintWriter out = response.getWriter();
        out.println("Tool Controller Handling Request from POST.");
        DiskFileItemFactory factory1 = new DiskFileItemFactory();
        
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory1.setRepository(repository);
        
        ServletFileUpload upload1 = new ServletFileUpload();
        List Items;
        out = response.getWriter();
        
        //String toolName = 
        
        
        File file;
        int maxFileSize = 5000*1024;
        int maxMemSize = 5000*1024;
        
        ServletContext context = request.getServletContext();
        ToolBean tool = new ToolBean();
        String filePath = context.getInitParameter("file-upload-tools");
        String contentType = request.getContentType();
        String ext = "";
        LoginUserBean currentUser = (LoginUserBean)request.getSession(false).getAttribute("CurrentSessionUser");
        String name = currentUser.getUsername();
        String searchUserID = "select user_id from user_tbl where username='"+ name+"'";
        int uid = 0;
        
        /*int sizeInBytes = 0;
        String fileType="";*/
        currentCon = ConnectionManager.getConnection();
        boolean more;
        try{
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchUserID);
            more = rs.next();
            if(more){
                uid = rs.getInt("user_id");
                tool.setUserId(uid);
            }
            
        }
        catch(Exception ex){
            System.out.println("An Exception Has Occured:"+ex);
        //todo: Handle Sql Exception
        }
        if((contentType.indexOf("multipart/form-data")>=0)){
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(maxMemSize);
            factory.setRepository(new File("c:\\temp"));
            ServletFileUpload upload = new ServletFileUpload();
            upload.setSizeMax(maxFileSize);
            try{
                List fileItems = upload.parseRequest(request);
                Iterator i = fileItems.iterator();
                while (i.hasNext()){
                    FileItem fi = (FileItem)i.next();
                    tool.setToolName(fi.getName());
                    if(!fi.isFormField()){
                        String fileName = fi.getName();
                        if(fileName.lastIndexOf("\\")>=0){
                            file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
                                              
                        }
                        else{
                            file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
                        }
                        fi.write(file);
                        out.println(filePath+fileName);
                        tool.setToolPath(filePath);
                        tool.setToolName(fileName);
                        ext = fileName.substring(fileName.lastIndexOf("."+1,fileName.length()));
                        
                        
                        
                    }
                }
                
            }
            catch(Exception ex) {
                System.out.println(ex);
            }
            
        }
        else{
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>"); 
            out.println("</head>");
            out.println("<body>");
            out.println("<p>No file uploaded</p>"); 
            out.println("</body>");
            out.println("</html>");
        }
        try{
            tool = ToolDAO.insertTool(tool);
            if(tool.isAdded() && tool.getHidden().equalsIgnoreCase("AddTool")){
                response.setContentType("text/html;charset = UTF-8");
                try{
                     out.println("<script type=\"text/javascript\">");
                    out.println("alert('One record inserted successfully!');");
                    out.println("location='home';");
                    out.println("</script>");
                    
                }
                catch (Exception e){
                    System.out.println("Alert could not be generated. Error: " + e);
            
                }
                
            }
            else if (!tool.isAdded() && tool.isDuplicateResource() && tool.getHidden().equalsIgnoreCase("addtool")){  
                response.setContentType("text/html;charset=UTF-8");
                try {
                    //PrintWriter out = response.getWriter();
                    String toolName= tool.getToolName();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Tool name: " + tool.getToolName() +" already exists, choose a new one!');");
                    out.println("location='AddTool';");
                    out.println("</script>");                    
                }
                catch (Exception e){
                    System.out.println("Alert could not be generated. Error: " + e);
                }
            
            }
        
        
        
        
        }
        catch(Throwable theException){
            theException.printStackTrace();
        }
        finally{
            //request.getRequestDispatcher("/AddConceptMap.jsp").forward(request, response);
            String path = request.getContextPath() + "/add-concept-map";
            //response.sendRedirect(path);
            //request.forward();
        }
        
    }
    @Override
    public String getServletInfo() {
        return "Controller for Adding Tools";
    }// </editor-fold>
}
