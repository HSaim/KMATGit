
package model;

/**
 * LoginUserBean to set and get user details
 * @author Habiba Saim
 */


public class LoginUserBean implements java.io.Serializable {
    private String username;
    private String password;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
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
