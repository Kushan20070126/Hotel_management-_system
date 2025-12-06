package dao;

import dao.interfaces.IBookingDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.Booking;
import util.DBConnection;


public class BookingDAO implements IBookingDAO {

 
    private  Connection con = DBConnection.getInstance().getConnection();


    
    @Override
    public boolean POST_BOOKING(Booking booking) {
        
        try{
            
            PreparedStatement st = con.prepareStatement("INSERT INTO bookings(guest_id, room_id, check_in, check_out, status, total_amount) VALUES (?, ?, ?, ?, ?, ?)") ;
            
            st.setInt(1, booking.getGuestID());
            st.setInt(2, booking.getRoomID());
            st.setDate(3, booking.getCheckInDate());
            st.setDate(4, booking.getCheckOutDate());
            st.setString(5, booking.getStatus());
            
            st.setDouble(6, booking.getToatalAmmount()); 
            
            return st.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Database Error Adding Booking: " + e.getMessage());
            return false;
        }
    }

  
    @Override
    public boolean PUT_BOOKING(Booking booking) {
        
        try {
            PreparedStatement st = con.prepareStatement("UPDATE bookings SET guest_id=?, room_id=?, check_in=?, check_out=?, status=?, total_amount=? WHERE id=?");
            st.setInt(1, booking.getGuestID());
            st.setInt(2, booking.getRoomID());
            st.setDate(3, booking.getCheckInDate());
            st.setDate(4, booking.getCheckOutDate());
            st.setString(5, booking.getStatus());
            
            st.setDouble(6, booking.getToatalAmmount()); 
            st.setInt(7, booking.getID());

            return st.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Database Error Updating Booking: " + e.getMessage());
            return false;
        }
    }

    
    @Override
    public boolean DELETE_BOOKING(int id) {
        
        
        try {
            
            PreparedStatement st = con.prepareStatement("DELETE FROM bookings WHERE id=?");
            st.setInt(1, id);
            
            return st.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Database Error Deleting Booking: " + e.getMessage());
            return false;
        }
    }

  
    @Override
    public Booking GET_BOOKING_ID(int id) {
        
        try {
            
            
            PreparedStatement st = con.prepareStatement("SELECT id, guest_id, room_id, check_in, check_out, status, total_amount FROM bookings WHERE id=?");
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Booking(
                        rs.getInt("id"),
                        rs.getInt("guest_id"),
                        rs.getInt("room_id"),
                        rs.getDate("check_in"),
                        rs.getDate("check_out"),
                        rs.getString("status"),
                        rs.getDouble("total_amount")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Database Error fetching Booking by ID: " + e.getMessage());
        }
        return null; 
    }

    
    @Override
        public List<Booking> GET_BOOKINGES() { 
        List<Booking> data = new ArrayList<>();
        
        
        final String SQL = "SELECT id, guest_id, room_id, check_in, check_out, status, total_amount FROM bookings";
        
        
        try (Statement st = con.createStatement(); 
             ResultSet rs = st.executeQuery(SQL)) { 
            
            while (rs.next()) {
                Booking book = new Booking(
                    rs.getInt("id"),
                    rs.getInt("guest_id"),
                    rs.getInt("room_id"),
                    rs.getDate("check_in"),
                    rs.getDate("check_out"),
                    rs.getString("status"),
                    rs.getDouble("total_amount")
                );
                data.add(book);
            }
        } catch (SQLException e) {
            System.err.println("Database Error fetching All Bookings: " + e.getMessage());
            return null;
        }
        return data;
    }

   
    @Override
    public List<Booking> GET_BOOKING_GUESTID(int guestId) {
        List<Booking> data = new ArrayList<>();
        
        
        try {
            
            PreparedStatement st = con.prepareStatement("SELECT id, guest_id, room_id, check_in, check_out, status, total_amount FROM bookings WHERE guest_id=?");
            st.setInt(1, guestId);
            ResultSet rs = st.executeQuery();
                
                while (rs.next()) {
                    Booking book = new Booking(
                        rs.getInt("id"),
                        rs.getInt("guest_id"),
                        rs.getInt("room_id"),
                        rs.getDate("check_in"),
                        rs.getDate("check_out"),
                        rs.getString("status"),
                        rs.getDouble("total_amount")
                    );
                    data.add(book);
                }
            }
         catch (SQLException e) {
            System.err.println("Database Error fetching Booking by Guest ID: " + e.getMessage());
            return null;
        }
        return data;
    }

  
    @Override
    public List<Booking> GET_BOOKING_ROOMID(int roomId) {
        List<Booking> data = new ArrayList<>();
        
        try {
            
            
            PreparedStatement st = con.prepareStatement("SELECT id, guest_id, room_id, check_in, check_out, status, total_amount FROM bookings WHERE room_id=?");
            st.setInt(1, roomId);
            ResultSet rs = st.executeQuery();
                
                while (rs.next()) {
                    Booking book = new Booking(
                        rs.getInt("id"),
                        rs.getInt("guest_id"),
                        rs.getInt("room_id"),
                        rs.getDate("check_in"),
                        rs.getDate("check_out"),
                        rs.getString("status"),
                        rs.getDouble("total_amount")
                    );
                    data.add(book);
                }
            }
       catch (SQLException e) {
            System.err.println("Database Error fetching Booking by Room ID: " + e.getMessage());
            return null;
        }
        return data;
    }

    @Override
   public int GET_BOOKING_COUNT(Date start, Date end) {
    String sql = "SELECT COUNT(*) AS total FROM bookings WHERE checkInDate BETWEEN ? AND ?";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDate(1, start);
        ps.setDate(2, end);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getInt("total");
    } catch (Exception e) {
        System.out.println("Booking Count Error: " + e);
    }
    return 0;
}
   
   @Override
   public Map<String, Integer> GET_ROOM_TYPE_USAGE() {
       
    Map<String, Integer> report = new HashMap<>();
        String sql =
    "SELECT rooms.type, COUNT(bookings.room_id) AS count " +
    "FROM rooms " +
    "LEFT JOIN bookings ON rooms.id = bookings.room_id " +
    "GROUP BY rooms.type";

    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            report.put(rs.getString("type"), rs.getInt("count"));
            String type = rs.getString("type");
            int count = rs.getInt("count");
            System.out.println(type + " -> " + count);
            report.put(type, count);
        }
    } catch (Exception e) {
        System.out.println("Room Type Report Error: " + e);
    }
    return report;
}
   
   @Override
   public int GET_TOTAL_ROOMS() {
    String sql = "SELECT COUNT(*) AS total FROM rooms";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getInt("total");
    } catch (Exception e) {
        System.out.println("Total Rooms Error: " + e);
    }
    return 0;
}

   @Override
   public int GET_OCCUPIED_ROOMS(Date start, Date end) {
    String sql = "SELECT COUNT(*) AS used FROM bookings "
               + "WHERE checkInDate <= ? AND checkOutDate >= ?";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDate(1, end);
        ps.setDate(2, start);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getInt("used");
    } catch (Exception e) {
        System.out.println("Occupied Rooms Error: " + e);
    }
    return 0;
}
   
   @Override
   public Map<String, Integer> GET_DAILY_BOOKING_VOLUME() {
    Map<String, Integer> report = new LinkedHashMap<>(); 

    String sql = "SELECT DATE(check_in) AS day, COUNT(*) AS total "
               + "FROM bookings "
               + "GROUP BY DATE(check_in) "
               + "ORDER BY day ASC";   

    try (PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            report.put(rs.getString("day"), rs.getInt("total"));
        }

    } catch (Exception e) {
        System.out.println("Booking Daily Report Error: " + e);
    }
    return report;
}
   @Override
   public Map<String, Double> GET_MONTHLY_INCOME_TREND() {

    Map<String, Double> report = new LinkedHashMap<>();

    String sql = "SELECT DATE_FORMAT(check_in, '%Y-%m') AS month, "
               + "SUM(total_amount) AS income "
               + "FROM bookings "
               + "GROUP BY DATE_FORMAT(check_in, '%Y-%m') "
               + "ORDER BY month ASC";

    try (PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while(rs.next()) {
            report.put(rs.getString("month"), rs.getDouble("income"));
        }

    } catch (Exception e) {
        System.out.println("Monthly Income Report Error: " + e);
    }

    return report;
}




}