package dao;

import dao.interfaces.IGuestDAO;
import model.Guest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class GuestDAO implements IGuestDAO{
    
    Connection con = DBConnection.getInstance().getConnection();

    @Override
    public boolean POST_GUEST(Guest guest) {
        try{
            PreparedStatement st  = con.prepareStatement("Insert into guests(name,phone,email,id_number,address) values (?,?,?,?,?)");
            st.setString(1, guest.getName());
            st.setString(2, guest.getPhone());
            st.setString(3, guest.getEmail());
            st.setString(4, guest.getNICORPassport());
            st.setString(5, guest.getAddress());
            
            if(st.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
            
            
        }catch(Exception e){
            
            System.err.println("Somthing went Wrong Adding Guest " + e.getMessage());     
            return false;

        }
    }

    @Override
    public boolean PUT_GUEST(Guest guest) {
        
        try{
            PreparedStatement st  = con.prepareStatement("update guests set name=?,phone=?, email=?,id_number=?, address=? where id=?");
            st.setString(1, guest.getName());
            st.setString(2, guest.getPhone());
            st.setString(3, guest.getEmail());
            st.setString(4, guest.getNICORPassport());
            st.setString(5, guest.getAddress());
            st.setInt(6, guest.getID());
            
             if(st.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
            
        }catch(Exception e){
            System.err.println("Somthing went Wrong Updating Guest " + e.getMessage());    
            return false;
        } 
    }

    @Override
    public boolean DELETE_GUEST(int id) {
        try{
            PreparedStatement st = con.prepareStatement("delete from guests where id=?");
            st.setInt(1, id);
            
            if(st.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
            
        }catch(Exception e){
            System.err.println("Somthing went Wrong Updating Guest " + e.getMessage());    
            return false;
            
        }
    }

    @Override
    public Guest GET_GUEST_ID(int id) {
        
        
        try {
            PreparedStatement st = con.prepareStatement("select * from guests where id=? ");
            st.setInt(1, id);
            
            ResultSet rs = st.executeQuery();
            
            if(rs.next()){
                Guest data = new Guest(rs.getInt("id"), rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getString("id_number"), rs.getString("address"));
                return data;
                
            }
            
        } catch (Exception e) {
            
              System.err.println("Somthing went Wrong fetching db Guest Data " + e.getMessage());    
             return null;
        }
        return null;
    }

    @Override
    public List<Guest> GET_GUESTS() {
        
         List<Guest> data = new ArrayList<>();

        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from guests");
            
            while(rs.next()){
                Guest guests = new Guest(rs.getInt("id"), rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getString("id_number"), rs.getString("address"));
                data.add(guests);
            }
            
        }catch(Exception e){
            System.err.println("Somthing went Wrong fetching  All db Room Data " + e.getMessage());  
            return null;
            
        }
        return  data;
    }   
}
    
    
