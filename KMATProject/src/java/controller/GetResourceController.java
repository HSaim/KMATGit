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
import model.ResourceBean;
import model.ResourceDAO;
import model.EmailUtility;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


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
    static Connection currentCon = null;
    static ResultSet rs = null;  
    static Statement stmt = null;
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
        currentCon = ConnectionManager.getConnection();
        ResultSet rs = null;
        boolean more;
        int uid = 0;
        if (action.equals("get-all-resources")){
        ArrayList<ResourceBean> resources = new ArrayList<ResourceBean>();     
        resources = ResourceDAO.getResources(currentUsername);
            
        try{ 
             HttpSession session = request.getSession(true);
            if (resources!=null){
                
               
                if(resources!=null)
                {
                    session.setAttribute("resources", resources);                    
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
            //ServletContext context = getServletContext();
            DiskFileItemFactory factory1 = new DiskFileItemFactory();
            // Configure a repository (to ensure a secure temp location is used)
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory1.setRepository(repository);

            // Create a new file upload handler
            ServletFileUpload upload1 = new ServletFileUpload(factory1);
            PrintWriter out = response.getWriter();
            String resourcename = request.getParameter("addname");
        //PrintWriter out = response.getWriter();
        out.println("resource controller handling request from POST");
       // out.println(request.getParameter("addname"));
        //out.println(request.getParameter("add-description"));
        File file;
        
        int maxFileSize = 5000*1024;
        int maxMemSize = 5000*1024;
    
        
        ServletContext context = request.getServletContext();
        ResourceBean resource = new ResourceBean();
        //String contentType = request.getContentType();
        String filePath = context.getInitParameter("file-upload");
        String contentType = request.getContentType();
        String ext = "";
        String value2="";
        LoginUserBean currentUser = (LoginUserBean)request.getSession(false).getAttribute("CurrentSessionUser");
        String name = currentUser.getUsername();
       
        int sizeInBytes = 0;
        String radio = request.getParameter("chk");
        String fileType="";
       
        currentCon = ConnectionManager.getConnection();
        try
        {
            stmt=currentCon.createStatement();
            rs = stmt.executeQuery(searchUserID);	        
            more = rs.next();
            if(more){
                uid = rs.getInt("user_id");
                resource.setUserID(uid);
            }
        }
        catch(SQLException ex){
        //todo: Handle Sql Exception
        }
        
        

            host = servletContext.getInitParameter("host");
            port = servletContext.getInitParameter("port");
            kmatUsername = servletContext.getInitParameter("user");
            kmatPassword = servletContext.getInitParameter("pass");
            String recipientEmail = request.getParameter("p_email");
            String recipientName = request.getParameter("firstname");
            String subject = "Resource update";
            String content = "Dear " + recipientName + 
                    ",\n\nYour resource has been updated successfully." + 
                    "\n\nBest Regards,\n\nKMAT Team" +
                    "\n\n\n_________________________________________________________" +
                    "\nThis is an autogenerated email, kindly do not reply.";
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
                      if(name.equals("addname")){
                        
                        resource.setResourceName(value);
                      }
                      else if(name.equals("add-description")){
                          resource.setResourceDiscription(value);
                      }
                      else if(name.equals("chk") && value.equals("file")){
                          
                           resource.setResourceType("file");                           
                         
                              
                          }
                      else if(name.equals("chk") && value.equals("link")){
                          
                           resource.setResourceType("link");
                           fi = (FileItem)i.next();
                           name = fi.getFieldName();
                           value = fi.getString();
                           if(name.equals("add-link")&& !value.equals("")){
                                  resource.setResourceLink(value);
                                  //resource.setFileName("none");
                              }
                         
                              
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
                        resource.setResourcePath(filePath);
                        resource.setFileName(fileName);
                       // resource.setResourceLink("none");
                        
                       // 
                       // out.println(request.getParameter("datafile"));
                        //out.println(request.getParameter("add-link"));
                        ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                        out.println(ext);
                        resource.setResourceFormat(ext);
                        resource.setResourceSize(sizeInBytes);
                        out.println(sizeInBytes);
                        fileType = typeID(ext);
                        resource.setFileType(fileType);
                        out.println(fileType);
                        }
                    
                    
                    }
                resource = ResourceDAO.updateResource(resource);
                
               
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
        }
        /*else if (action.equals("del-resource")){
            String resourcename;
            int deleted;
            resourcename = request.getParameter("resourceName");
            deleted = UserDAO.deleteUser(username);
            response.setContentType("text/html;charset=UTF-8");
            //response.sendRedirect("GetUsersController?action=get-all-users");
            if (deleted !=0){
                try {
                    
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('User " +username+ " has been deleted succesfully.');");
                    out.println("location='GetUsersController?action=get-all-users';");                    
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
                    out.println("alert('User '" +username+ "' could not be deleted.');");
                    out.println("</script>");                    
                }
                catch (Exception e){
                    System.out.println("Alert could not be generated. Error: " + e);
                }
            }
        }*/
        
    }
    public String typeID(String extention){
        String typeOfFile = null;
        if(extention.equals("jpg")||extention.equals("png")||extention.equals("bmp")||extention.equals("gif")
                ||extention.equals("jpeg")||extention.equals("svg")||extention.equals("tiff")){
            typeOfFile = "Image";
            }
        else if(extention.equals("avi")||extention.equals("mpeg")||extention.equals("qt")||extention.equals("mp4")
                ||extention.equals("webm")||extention.equals("flv")||extention.equals("mkv")||extention.equals("vob")
                ||extention.equals("wmv")){
            typeOfFile = "Video";
            }
        else if(extention.equals("css")||extention.equals("doc")||extention.equals("docx")||extention.equals("dotx")
                ||extention.equals("html")||extention.equals("pdf")||extention.equals("potx")||extention.equals("ppsx")
                ||extention.equals("ppt")||extention.equals("pptx")||extention.equals("txt")||extention.equals("sgml")
                ||extention.equals("tsv")||extention.equals("xlam")||extention.equals("xls") ||extention.equals("xlsb") 
                ||extention.equals("xlsx")||extention.equals("xltx")){
            typeOfFile = "Document";
            }
        else{
            typeOfFile = "File Type not Identified.";
        }
        return (typeOfFile);
        }
    
}
