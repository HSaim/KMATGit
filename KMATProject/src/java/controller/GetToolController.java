/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.sql.*;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.LoginUserBean;
import model.UserBean;
import model.UserDAO;
import model.ConnectionManager;
import model.ToolBean;
import model.ToolDAO;
import model.EmailUtility;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 *
 * @author fahad
 */
public class GetToolController extends HttpServlet{
     private String host;
    private String port;
    private String kmatUsername;
    private String kmatPassword;
    String currentUsername;
    static Connection currentCon = null;
    static ResultSet rs = null;  
    static Statement stmt = null;
    
    LoginUserBean  currentUser;
     /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException{
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
    throws ServletException, IOException{
        String action = request.getParameter("action");
        currentUser = (LoginUserBean) request.getSession(false).getAttribute("CurrentSessionUser");
        currentUsername = currentUser.getUsername();
        String searchUserId = "select user_id from user_tbl where username = '"+currentUsername+"'";
        
        currentCon = ConnectionManager.getConnection();
        ResultSet rs = null;
        boolean more;
        int uid = 0;
        if(action.equals("get-all-tools")){
            ArrayList<ToolBean> tools = new ArrayList<ToolBean>();
            tools = ToolDAO.getTools(currentUsername);
            try{
                HttpSession session = request.getSession(true);
                if(tools != null){
                    session.setAttribute("tools", tools);
                    response.sendRedirect("view-tools");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
        }
        else if(action.equals("get-tool")){
            String toolName;
            ToolBean tool = new ToolBean();
            toolName = request.getParameter("toolName");
            tool = ToolDAO.getTool(toolName);
            request.setAttribute("tool", tool);
            HttpSession session = request.getSession(true);
            session.setAttribute("ret-tool", tool);
            response.sendRedirect("edit-tool");
            
            
        }
        else if(action.equals("update-tool")){
            DiskFileItemFactory factory1 = new DiskFileItemFactory();
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory1.setRepository(repository);
            ServletFileUpload upload1 = new ServletFileUpload(factory1);
            PrintWriter out = response.getWriter();
            String toolname = request.getParameter("toolName");
            out.println("Tool Controller handling request from POSt");
            File file;
            
            
            int maxFileSize = 5000*1024;
            int maxMemSize = 5000*1024;
            
            ServletContext context = request.getServletContext();
            ToolBean tool = new ToolBean();
            String filePath = context.getInitParameter("file-upload-tools");
            String contentType = request.getContentType();
            String mainExt="";
            String value2 = "";
            LoginUserBean currentUser = (LoginUserBean)request.getSession(false).getAttribute("CurrentSessionUser");
            String name = currentUser.getUsername();
            String tooltype = request.getParameter("toolType");
            String searchToolTypeID = "select tool_type_id from tool_type_tbl where tool_type_name = '"+tooltype+"'";
            int sizeInBytes = 0;
            int toolType = 0;
           
            
            currentCon = ConnectionManager.getConnection();
            try{
                stmt = currentCon.createStatement();
                rs = stmt.executeQuery(searchUserId);
                more = rs.next();
                if(more){
                    uid = rs.getInt("user_id");
                    tool.setUserId(uid);
                }
                rs = stmt.executeQuery(searchToolTypeID);
                more = rs.next();
                if(more){
                    toolType = rs.getInt("tool_type_id");
                }
            }
            catch(Exception ex){
                out.println(ex);
                
            }
            host = servletContext.getInitParameter("host");
            port = servletContext.getInitParameter("port");
            kmatUsername = servletContext.getInitParameter("user");
            kmatPassword = servletContext.getInitParameter("pass");
            if ((contentType.indexOf("multipart/form-data") >= 0)){
                DiskFileItemFactory factory = new DiskFileItemFactory();
                // maximum size that will be stored in memory
                factory.setSizeThreshold(maxMemSize);
                // Location to save data that is larger than maxMemSize.
                factory.setRepository(new File("c:\\temp"));
                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);
                // maximum file size to be uploaded.
                upload.setSizeMax( maxFileSize );
                try{
                    // Parse the request to get file items.
                List fileItems = upload.parseRequest(request);
                // Process the uploaded file items
                Iterator i = fileItems.iterator();
                out.println("<html>");
                out.println("<head>");
               // out.println("<title>JSP File upload</title>");
                out.println("</head>");
                out.println("<body>");
                
                while ( i.hasNext () ){
                    FileItem fi = (FileItem)i.next();
                    

                    if (fi.isFormField()) {

                      name = fi.getFieldName();//text1
                      String value = fi.getString();
                      
                     

                      out.println(name + " : " + value);
                      if(name.equals("addtool")){
                        
                        tool.setToolName(value);
                      }
                      else if(name.equals("addToolDescrp")){
                          tool.setToolDescription(value);
                      }
                      else if(name.equals("toolType")){
                          
                           tool.setToolTypeID(toolType);                           
                         
                              
                          }
                      else if(name.equals("input")){
                          boolean inout = request.getParameter("input") !=null;
                          tool.setInput(inout); 
                      }
                          
                      
                      else if(name.equals("output")){
                          boolean inout = request.getParameter("output") !=null;
                          tool.setOutput(inout);
                      }
                      else if(name.equals("toolType")){
                          
                      }
                       
                                           
                                            
                          
                      //}
                    }
                    if ( !fi.isFormField () ){
                        
                        // Get the uploaded file parameters
                        String fieldName = fi.getFieldName();
                        String fileName = fi.getName();
                        boolean isInMemory = fi.isInMemory();
                        sizeInBytes = (int)fi.getSize();
                        // Write the file
                        if( fileName.lastIndexOf("\\") >= 0 ){
                            file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
                            }
                        else{
                            file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                            }
                        fi.write( file ) ;
                        out.println("Uploaded Filename: " + filePath + fileName);
                        tool.setToolPath(filePath);
                        tool.setMain(fileName);
                       // resource.setResourceLink("none");
                        
                       // 
                       // out.println(request.getParameter("datafile"));
                        //out.println(request.getParameter("add-link"));
                        mainExt = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                        out.println(mainExt);
                        tool.setMainExt(mainExt);
                        //resource.setResourceSize(sizeInBytes);
                        out.println(sizeInBytes);
                        
                        
                        }
                    
                    
                    }
                tool = ToolDAO.updateTool(tool);
                
                    
                }
                catch(Exception ex) {
                    System.out.println(ex);
                    }

            }    
                
           
             
            
            
        }
        else if (action.equals("del-tool")){
            String toolname;
            int deleted;
            toolname = request.getParameter("toolName");
            deleted = ToolDAO.deleteTool(toolname);
            response.setContentType("text/html;charset=UTF-8");
            //response.sendRedirect("GetUsersController?action=get-all-users");
            if (deleted !=0){
                try {
                    
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('tool " +toolname+ " has been deleted succesfully.');");
                    out.println("location='GetToolController?action=get-all-tools';");                    
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
                    out.println("alert('Tool '" +toolname+ "' could not be deleted.');");
                    out.println("</script>");                    
                }
                catch (Exception e){
                    System.out.println("Alert could not be generated. Error: " + e);
                }
            }
        }
        
    }
    
    
    
    
}
