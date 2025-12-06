package dao;

import dao.interfaces.IGuestDAO;
import model.Guest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class GuestDAO implements IGuestDAO{
    
   private Connection con = DBConnection.getInstance().getConnection();

    @Override
    public boolean POST_GUEST(Guest guest) {
        try{
            PreparedStatement st  = con.prepareStatement("Insert into guests(name,phone,email,nicORPass,address) values (?,?,?,?,?)");
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
            PreparedStatement st  = con.prepareStatement("update guests set name=?,phone=?, email=?,nicORPass=?, address=? where id=?");
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
                Guest data = new Guest(rs.getInt("id"), rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getString("nicORPass"), rs.getString("address"));
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
                Guest guests = new Guest(rs.getInt("id"), rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getString("nicORPass"), rs.getString("address"));
                data.add(guests);
            }
            
        }catch(Exception e){
            System.err.println("Somthing went Wrong fetching  All db Room Data " + e.getMessage());  
            return null;
            
        }
        return  data;
    }   
    
    @Override
    public int GET_GUEST_COUNT(Date start, Date end) {
    String sql = "SELECT COUNT(*) AS total FROM guests "
               + "JOIN bookings ON guests.id = bookings.guestId "
               + "WHERE checkInDate BETWEEN ? AND ?";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDate(1, start);
        ps.setDate(2, end);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getInt("total");
    } catch (Exception e) {
        System.out.println("Guest Count Error: " + e);
    }
    return 0;
}

}
    
    
