/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * UserBean to set and get user name/password
 * @author Habiba Saim
 */
public class UserBean implements java.io.Serializable{
    private String username;
    private String password;
    
    public boolean valid;
    
    public String getPassword() {
       return password;
    }

    public void setPassword(String newPassword) {
       password = newPassword;
    }


    public String getUsername() {
       return username;
    }

    public void setUserName(String newUsername) {
       username = newUsername;
    }

    public boolean isValid() {
       return valid;
    }

    public void setValid(boolean newValid) {
       valid = newValid;
    }	
    
}
