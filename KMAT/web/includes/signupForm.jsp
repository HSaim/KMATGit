<%-- 
    Document   : signupForm
    Created on : Aug 11, 2016, 11:28:21 AM
    Author     : Habiba Saim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


    
    <br/><br/>
    <tr></tr>
    <tr>
        <td align="right">
             User Name <span class="required">*</span>                           
        </td>
        <td align="left">
            <input type="text" name="user_name" placeholder="User Name" >
        </td>
    </tr>

    <tr>
        <td align="right">
           Primary Email <span class="required">*</span> 
        </td>
        <td align="left">
            <input type="text" name="p_email" placeholder="Email Address" required>                        
        </td>
    </tr>

    <tr>
        <td align="right">
            Secondary Email
        </td>
        <td align="left">
            <input type="text" name="s_email" placeholder="Email Address">
        </td>
    </tr>

    <tr>
        <td align="right">
            First Name <span class="required">*</span>
        </td>
        <td align="left">
            <input type="text" name="firstname" placeholder="First Name" required>
        </td>
    </tr>

    <tr>
        <td align="right">
            Last Name <span class="required">*</span>
        </td>
        <td align="left">
            <input type="text" name="lastname" placeholder="Last Name" required>
        </td>
    </tr>

    <tr>
        <td align="right">
            Password <span class="required">*</span>
        </td>
        <td align="left">
            <input type="password" name="password" placeholder="Password" required>
        </td>
    </tr> 

    <tr>
        <td align="right">
            Postal Address
        </td>
        <td align="left">
            <input type="text" name="postal_address" placeholder="Address">
        </td>
    </tr>

    <tr>
        <td align="right">
            Permanent Address
        </td>
        <td align="left">
            <input type="text" name="per_address" placeholder="Address">
        </td>
    </tr>

    <tr>
        <td align="right">
            Work Phone
        </td>
        <td align="left">
            <input type="text" name="w_phone" placeholder="Contact Number">
        </td>
    </tr>

    <tr>
        <td align="right">
            Home Phone
        </td>
        <td align="left">
            <input type="text" name="h_phone" placeholder="Contact Number">
        </td>
    </tr>

    <tr>
        <td align="right">
            Mobile Phone
        </td>
        <td align="left">
            <input type="text" name="m_phone" placeholder="Contact Number">
        </td>
    </tr>

    <tr>                       
        <td colspan="2" align="center">
            <input type="submit" value="Add User" />
        </td>
    </tr>

                
