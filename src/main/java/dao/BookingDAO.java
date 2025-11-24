
package dao;

import dao.interfaces.IBookingDAO;
import java.util.List;
import model.Booking;
import java.sql.*;
import java.util.ArrayList;

import util.DBConnection;

public class BookingDAO implements IBookingDAO{

    Connection con = DBConnection.getInstance().getConnection();
    @Override
    public boolean POST_BOOKING(Booking booking) {
        try {
            PreparedStatement st = con.prepareStatement("insert into bookings(guest_id, room_id, check_in, check_out, status, total_amount) values (?, ?, ?, ?,?, ?)");
            st.setInt(1, booking.getGuestID());
            st.setInt(2, booking.getRoomID());
            st.setDate(3, booking.getCheckInDate());
            st.setDate(4,booking.getCheckOutDate());
            st.setString(5, booking.getStatus());
            st.setDouble(6, booking.getToatalAmmount());
            
              if(st.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            
            System.err.println("Somthing went Wrong Adding Booking " + e.getMessage());    
            return false;
            
        }
    }

    @Override
    public boolean PUT_BOOKING(Booking booking) {
        try {
    PreparedStatement st = con.prepareStatement("Update bookings set guest_id=?, room_id=?, check_in=?, check_out=?, status=?, total_amount=? where id=?");
            st.setInt(1, booking.getGuestID());
            st.setInt(2, booking.getRoomID());
            st.setDate(3, booking.getCheckInDate());
            st.setDate(4,booking.getCheckOutDate());
            st.setString(5, booking.getStatus());
            st.setDouble(6, booking.getToatalAmmount());
            
            st.setInt(7, booking.getID());

             if(st.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
             
        } catch (Exception e) {
            System.err.println("Somthing went Wrong Updating Booking " + e.getMessage());    
            return false;
        }
    }

    @Override
    public boolean DELETE_BOOKING(int id) {
        try {
            
            
    PreparedStatement st = con.prepareStatement("delete from bookings where id=?");
    st.setInt(1, id);
    
     if(st.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }

        } catch (Exception e) {
             System.err.println("Somthing went Wrong Deleting Booking " + e.getMessage());    
            return false;
        }
    }

    @Override
    public Booking GET_BOOKING_ID(int id) {
        try {
            PreparedStatement st = con.prepareStatement("select * from bookings where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Booking data = new Booking(
                        rs.getInt("id"),
                        rs.getInt("guest_id"),
                        rs.getInt("room_id"),
                        rs.getDate("check_in"),
                        rs.getDate("check_out"),
                        rs.getString("status"),
                        rs.getDouble("total_amount"));
                return data;
                
            }
        } catch (Exception e) {
            System.err.println("Somthing went Wrong fetching db Booking Data " + e.getMessage());    
             return null;
        }
        return null;
    }

    @Override
    public List<Booking> GET_BOOKINGES() {
         List<Booking> data = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from bookings");
            
            while(rs.next()){
                Booking book = new Booking(
                        rs.getInt("id"),
                        rs.getInt("guest_id"),
                        rs.getInt("room_id"),
                        rs.getDate("check_in"),
                        rs.getDate("check_out"),
                        rs.getString("status"),
                        rs.getDouble("total_amount"));
                
                data.add(book);
            }
            
        } catch (Exception e) {
            
           System.err.println("Somthing went Wrong fetching  All db Booking Data " + e.getMessage());  
           return null;
        }
        
        return data;
    }

    @Override
    public List<Booking> GET_BOOKING_GUESTID(int guestId) {
        List<Booking> data = new ArrayList<>();
        try {
            PreparedStatement st = con.prepareStatement("select * from bookings where guest_id=?");
            st.setInt(1, guestId);
            ResultSet rs = st.executeQuery();
            
             while(rs.next()){
                Booking book = new Booking(
                        rs.getInt("id"),
                        rs.getInt("guest_id"),
                        rs.getInt("room_id"),
                        rs.getDate("check_in"),
                        rs.getDate("check_out"),
                        rs.getString("status"),
                        rs.getDouble("total_amount"));
                
                data.add(book);
            }
            
        } catch (Exception e) {
             System.err.println("Somthing went Wrong fetching Get Booking By Guest  " + e.getMessage());  
           return null;
        }
        return data;
    }

    @Override
    public List<Booking> GET_BOOKING_ROOMID(int roomId) {
         List<Booking> data = new ArrayList<>();
        try {
            PreparedStatement st = con.prepareStatement("select * from bookings where room_id=?");
            st.setInt(1, roomId);
            ResultSet rs = st.executeQuery();
            
             while(rs.next()){
                Booking book = new Booking(
                        rs.getInt("id"),
                        rs.getInt("guest_id"),
                        rs.getInt("room_id"),
                        rs.getDate("check_in"),
                        rs.getDate("check_out"),
                        rs.getString("status"),
                        rs.getDouble("total_amount"));
                
                data.add(book);
            }
            
        } catch (Exception e) {
             System.err.println("Somthing went Wrong fetching Get Booking By Room  " + e.getMessage());  
           return null;
        }
        return data;
    }
    
}
