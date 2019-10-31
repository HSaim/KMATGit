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
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
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
    
    public static String OUTPUT_FOLDER = "";
    public static String ZIP = "";
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
        ServletContext context = request.getServletContext();
        OUTPUT_FOLDER = context.getInitParameter("file-upload-tools");;
        out.println("Tool Controller Handling Request from POST.");
        
        DiskFileItemFactory factory1 = new DiskFileItemFactory();
        
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory1.setRepository(repository);
        
        ServletFileUpload upload1 = new ServletFileUpload();
        List Items;
        out = response.getWriter();
        
        String toolName = request.getParameter("addTool");
        out.println("tool controller handling request from POST");
        
        
        File file;
        int maxFileSize = 5000*102400;
        int maxMemSize = 5000*10240;
        
        
        ToolBean tool = new ToolBean();
        String filePath = context.getInitParameter("file-upload-tools");
        
        String contentType = request.getContentType();
        String mainExt = "";
        String value1 = "";
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
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(maxFileSize);
            int sizeInBytes = 0;
            int toolType = 0;
            try{
                
                
                List fileItems = upload.parseRequest(request);
                Iterator i = fileItems.iterator();
                while (i.hasNext()){
                    FileItem fi = (FileItem)i.next();
                    

                    if (fi.isFormField()) {

                        name = fi.getFieldName();//text1
                        String value = fi.getString();
                      
                     

                        out.println(name + " : " + value);
                        if(name.equals("addTool")){
                        
                            tool.setToolName(value);
                        }
                        else if(name.equals("addToolDescrp")){
                            tool.setToolDescription(value);
                        }
                        else if(name.equals("toolType")){
                            int id = ToolDAO.getToolTypeID(value);
                            tool.setToolType(value);
                                           
                            tool.setToolTypeID(id);                           
                         
                              
                        }
                        else if(name.equals("input")){
                            //String a = request.getParameter("input");
                            //if(a)
                            //boolean inout =  !=null;
                            tool.setInput(true); 
                        }
                          
                      
                        else if(name.equals("output")){
                            boolean inout = request.getParameter("output") !=null;
                            tool.setOutput(true);
                        }
                        
                       
                                           
                                            
                          
                      
                    }
                    if ( !fi.isFormField () ){
                        name = fi.getFieldName();//text1
                        String value = fi.getString();
                        if(name.equals("main")){
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
                            
                            tool.setMain(fileName);

                            mainExt = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                            out.println(mainExt);
                            tool.setMainExt(mainExt);
                            //resource.setResourceSize(sizeInBytes);
                            out.println(sizeInBytes);
                        }
                        else if(name.equals("others")){
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
                            ZIP = OUTPUT_FOLDER+fileName;
                            InsertToolController.unZipIt(ZIP,OUTPUT_FOLDER);
                            //get the zip file content
                            ZipInputStream zis =
                            new ZipInputStream(new FileInputStream(ZIP));
                            //get the zipped file list entry
                            ZipEntry ze = zis.getNextEntry();
                            String arr1[] = ze.getName().split("/");
                            filePath = filePath+arr1[0];
                            zis.closeEntry();
                            zis.close();
                            tool.setToolPath(filePath);
                            
                            
                            
                           

                                                      
                        }
                        // Get the uploaded file parameters
                        
                        
                        
                        }
                    
                    
                }
                
            }
            catch(Exception ex) {
                System.out.println(ex);
            }
            
        }
        else{
            
        }
        try{
            tool = ToolDAO.insertTool(tool);
            if(tool.isAdded() /*&& tool.getHidden().equalsIgnoreCase("AddTool")*/){
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
            else if (!tool.isAdded() && tool.isDuplicateResource() /*&& tool.getHidden().equalsIgnoreCase("AddTool")*/){  
                response.setContentType("text/html;charset=UTF-8");
                try {
                    //PrintWriter out = response.getWriter();
                    toolName= tool.getToolName();
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
            String path = request.getContextPath() + "/add-tool";
            //response.sendRedirect(path);
            //request.forward();
        }
        
    }
    public static void unZipIt(String zipFile, String outputFolder){

     byte[] buffer = new byte[1024*50000];

     try{

    	//create output directory is not exists
    	File folder = new File(OUTPUT_FOLDER);
    	if(!folder.exists()){
    		folder.mkdir();
    	}

    	//get the zip file content
    	ZipInputStream zis =
    		new ZipInputStream(new FileInputStream(zipFile));
    	//get the zipped file list entry
    	ZipEntry ze = zis.getNextEntry();
        

    	while(ze!=null){

    	   String fileName = ze.getName();
           File newFile = new File(outputFolder + File.separator + fileName);

           System.out.println("file unzip : "+ newFile.getAbsoluteFile());

            //create all non exists folders
            //else you will hit FileNotFoundException for compressed folder
            new File(newFile.getParent()).mkdirs();

            FileOutputStream fos = new FileOutputStream(newFile);

            int len;
            while ((len = zis.read(buffer)) > 0) {
       		fos.write(buffer, 0, len);
            }

            fos.close();
            ze = zis.getNextEntry();
    	}

        zis.closeEntry();
    	zis.close();

    	System.out.println("Done");

    }catch(IOException ex){
       ex.printStackTrace();
    }
   }
    @Override
    public String getServletInfo() {
        return "Controller for Adding Tools";
    }// </editor-fold>
}
