package dao;

import java.sql.*;
import dao.interfaces.IRoomDAO;
import java.util.ArrayList;
import java.util.List;
import model.Room;
import util.DBConnection;

public class RoomDAO implements IRoomDAO {
    
 private Connection con = DBConnection.getInstance().getConnection();
   
    @Override
    public boolean POST_ROOM(Room room) {
        
        try {
                
             PreparedStatement st = con.prepareStatement("INSERT INTO rooms(number, type, price, status) VALUES (?, ?, ?, ?)") ;
                     

            st.setString(1, room.getNumber());
            st.setString(2, room.getType());
            st.setDouble(3, room.getPrice());
            st.setString(4, room.getStatus());

            return st.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Database Error Adding Room: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean PUT_ROOM(Room room) {
       

        try {
            
             PreparedStatement st = con.prepareStatement("UPDATE rooms SET number=?, type=?, price=?, status=? WHERE id=?");

            st.setString(1, room.getNumber());
            st.setString(2, room.getType());
            st.setDouble(3, room.getPrice());
            st.setString(4, room.getStatus());
            st.setInt(5, room.getID());

            return st.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Database Error Updating Room: " + e.getMessage());
            return false;
        }
    }

  
    @Override
    public boolean DELETE_ROOM(int id) {

        try {
            
             PreparedStatement st = con.prepareStatement("DELETE FROM rooms WHERE id=?") ;

            st.setInt(1, id);

            return st.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Database Error Deleting Room: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Room GET_ROOM_ID(int id) {

        try {
            
             PreparedStatement st = con.prepareStatement("SELECT id, number, type, price, status FROM rooms WHERE id=?");

            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Room(
                        rs.getInt("id"),
                        rs.getString("number"),
                        rs.getString("type"),
                        rs.getDouble("price"),
                        rs.getString("status")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Database Error fetching Room by ID: " + e.getMessage());
        }
        return null; 
    }

    
    @Override
    public List<Room> GET_ROOMS() {
        
        List<Room> data = new ArrayList<>();

        try {
             Statement st = con.createStatement(); 
             ResultSet rs = st.executeQuery("SELECT id, number, type, price, status FROM rooms");

            while (rs.next()) {
                Room room = new Room(
                    rs.getInt("id"),
                    rs.getString("number"),
                    rs.getString("type"),
                    rs.getDouble("price"),
                    rs.getString("status")
                );
                data.add(room);
            }

        } catch (SQLException e) {
            System.err.println("Database Error fetching All Rooms: " + e.getMessage());
            return null;
        }

        return data;
    }
    
    @Override
    public int GET_TOTAL_ROOMS() {
        
    String sql = "SELECT COUNT(*) FROM rooms";
    
    try (PreparedStatement ps = con.prepareStatement(sql);
            
         ResultSet rs = ps.executeQuery()) {
        
        if (rs.next()) return rs.getInt(1);
        
    } catch (Exception e) { System.out.println("Room Count Error : " + e.getMessage());
    
    }
    return 0;
}
    
    @Override
    public int GET_OCCUPIED_ROOMS() {
        
    String sql = "SELECT COUNT(*) FROM bookings WHERE status IN ('Checked-in','Confirmed')";
    
    try (PreparedStatement ps = con.prepareStatement(sql);
            
         ResultSet rs = ps.executeQuery()) {
        
        if (rs.next()) return rs.getInt(1);
        
    } catch (Exception e) { System.out.println("Occupied Room Count Error : " + e); }
    
    return 0;
}

    

}