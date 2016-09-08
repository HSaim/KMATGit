<%-- 
    Document   : validate
    Author     : Habiba Saim
--%>
 
<%@ page import ="java.sql.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%
    //try{
    	String user = "root";
    	String pass = "admin";
    	out.println("in validate.jsp" + "<br>");
        String username = request.getParameter("username");
        //out.println("\nUser Name: "+ username);
        //out.println("<br>");
        String pwd = request.getParameter("password");
        //out.println("\nPassword: " + pwd + "<br>");
        Class.forName("com.mysql.jdbc.Driver").newInstance();  // MySQL database connection
        //out.println("\nClass.forName executed" + "<br>");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kmat_db1",  user, pass) ;
       // Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kmat_db" + "user=root&password=century_77");    
        out.println("\nConnected to DB" + "<br>");
        PreparedStatement pst = conn.prepareStatement("Select username, password from login where username=? and password=?");
        pst.setString(1, username);
        pst.setString(2, pwd);
        ResultSet rs = pst.executeQuery();                        
        if(rs.next()) {          
           out.println("Valid login credentials");  
        %>
        <script type="text/javascript">
            //window.location.href = "/WEB-INF/view/UserTestPage.jsp";
            window.location.href = "testpage";
        </script>
        <%
        }
        else
           out.println("Invalid login credentials");            
//    }
//     catch(Exception e){       
//         out.println("Something went wrong !! Please try again");       
//     }      
%>