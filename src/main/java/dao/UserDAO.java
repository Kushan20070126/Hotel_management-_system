
package dao;

import dao.interfaces.IUserDAO;
import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class UserDAO  implements IUserDAO{
    
    Connection con = DBConnection.getInstance().getConnection();

    @Override
    public boolean POST_USER(User user) {
        try{
            PreparedStatement st = con.prepareStatement("INSERT INTO users(username, password, role) VALUES (?,?,?)");
            st.setString(1, user.getUserName());
            st.setString(2, user.getPassword());
            st.setString(3, user.getRole());
            
            if(st.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
            
        }catch(Exception e){
            System.err.println("Somthing went Wrong Adding User " + e.getMessage());    
            return false;
            
            
        }
    }

    @Override
    public boolean PUT_USER(User user) {
        
        try {
       PreparedStatement st = con.prepareStatement("Update users set username=?,password=? ,role=?  where id=?");
       
       st.setString(1, user.getUserName());
       st.setString(2, user.getPassword());
       st.setString(3, user.getRole());
       st.setInt(4, user.getID());
       
        if(st.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }            
        } catch (Exception e) {
            System.err.println("Somthing went Wrong Updating User " + e.getMessage());    
            return false;
        }
        
    }

    @Override
    public boolean DELETE_USER(int id) {
        try{
             PreparedStatement st = con.prepareStatement("delete from users where id=?");
             st.setInt(1, id);
             
             if(st.executeUpdate() > 0){
                return true;
            }else{
                return false;
            } 
            
        }catch(Exception e){
             System.err.println("Somthing went Wrong Deleting User " + e.getMessage());    
            return false;
            
        }
    }

    @Override
    public User GET_USER_ID(int id) {
        
        try{
        PreparedStatement st = con.prepareStatement("select * from users where id=?");
        
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        
        if(rs.next()){
            User data = new User(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("password"),
            rs.getString("role")
            );
            
            return data;
        }
        
        }catch(Exception e){
             System.err.println("Somthing went Wrong fetching db User Data " + e.getMessage());    
             return null;
            
        }
        return null;
    }

    @Override
    public List<User> GET_USERS() {
        List<User> data = new ArrayList<>();
        
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from users");
            
            while(rs.next()){
                User user = new User(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("password"),
            rs.getString("role")
            );
                data.add(user);
            }
        } catch (Exception e) {
           System.err.println("Somthing went Wrong fetching  All db Users Data " + e.getMessage());  
           return null;
            
        }
        
        return data;
    }

    @Override
    public User GET_LOGIN(String username, String Password) {
        
         User user = null;
        try {
            PreparedStatement st = con.prepareStatement("select * from users where username = ? and password = ?");
            st.setString(1, username);
            st.setString(2, Password);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
               user = new User(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("password"),
            rs.getString("role")
            );
                
            }
        } catch (Exception e) {
            
          System.err.println("Somthing went Wrong fetching db Users Login Data " + e.getMessage());  
           
        }
        return user;
        
    }
 
    
}
