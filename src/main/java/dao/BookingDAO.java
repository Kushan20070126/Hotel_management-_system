package dao;

import dao.interfaces.IBookingDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Booking;
import util.DBConnection;

/**
 * Data Access Object for managing Booking persistence.
 * Implements standard CRUD operations and custom retrieval methods.
 */
public class BookingDAO implements IBookingDAO {

    // Connection object is managed by DBConnection singleton.
    // It's retrieved on construction or per-method call, but for simplicity
    // and given the original structure, we'll retrieve it once here.
    // NOTE: In a production environment, it's safer to get the connection
    // inside the try-with-resources block for each method call,
    // or use a proper connection pool.
    private final Connection con = DBConnection.getInstance().getConnection();

    /**
     * Helper method to safely close the connection if it's not managed by a pool
     * or to indicate a best practice when managing resources.
     * Given the singleton connection pattern used, explicit closing isn't used
     * per method, relying on try-with-resources for Statement/ResultSet.
     */

    // --- Create (POST) ---
    @Override
    public boolean POST_BOOKING(Booking booking) {
        final String SQL = "INSERT INTO bookings(guest_id, room_id, check_in, check_out, status, total_amount) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = con.prepareStatement(SQL)) {
            st.setInt(1, booking.getGuestID());
            st.setInt(2, booking.getRoomID());
            st.setDate(3, booking.getCheckInDate());
            st.setDate(4, booking.getCheckOutDate());
            st.setString(5, booking.getStatus());
            // Assuming the model method is corrected to getTotalAmount()
            st.setDouble(6, booking.getTotalAmount()); 
            
            return st.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Database Error Adding Booking: " + e.getMessage());
            return false;
        }
    }

    // --- Update (PUT) ---
    @Override
    public boolean PUT_BOOKING(Booking booking) {
        final String SQL = "UPDATE bookings SET guest_id=?, room_id=?, check_in=?, check_out=?, status=?, total_amount=? WHERE id=?";
        try (PreparedStatement st = con.prepareStatement(SQL)) {
            st.setInt(1, booking.getGuestID());
            st.setInt(2, booking.getRoomID());
            st.setDate(3, booking.getCheckInDate());
            st.setDate(4, booking.getCheckOutDate());
            st.setString(5, booking.getStatus());
            // Assuming the model method is corrected to getTotalAmount()
            st.setDouble(6, booking.getTotalAmount()); 
            st.setInt(7, booking.getID());

            return st.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Database Error Updating Booking: " + e.getMessage());
            return false;
        }
    }

    // --- Delete (DELETE) ---
    @Override
    public boolean DELETE_BOOKING(int id) {
        final String SQL = "DELETE FROM bookings WHERE id=?";
        try (PreparedStatement st = con.prepareStatement(SQL)) {
            st.setInt(1, id);
            
            return st.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Database Error Deleting Booking: " + e.getMessage());
            return false;
        }
    }

    // --- Read by ID (GET) ---
    @Override
    public Booking GET_BOOKING_ID(int id) {
        final String SQL = "SELECT id, guest_id, room_id, check_in, check_out, status, total_amount FROM bookings WHERE id=?";
        try (PreparedStatement st = con.prepareStatement(SQL)) {
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
        return null; // Return null if not found or on error
    }

    // --- Read All (GET) ---
    @Override
    // Corrected method name from GET_BOOKINGES to GET_BOOKINGS
    public List<Booking> GET_BOOKINGS() { 
        List<Booking> data = new ArrayList<>();
        final String SQL = "SELECT id, guest_id, room_id, check_in, check_out, status, total_amount FROM bookings";
        
        // Using Statement here since no dynamic parameters are needed
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

    // --- Read by Guest ID (GET) ---
    @Override
    public List<Booking> GET_BOOKING_GUESTID(int guestId) {
        List<Booking> data = new ArrayList<>();
        final String SQL = "SELECT id, guest_id, room_id, check_in, check_out, status, total_amount FROM bookings WHERE guest_id=?";
        
        try (PreparedStatement st = con.prepareStatement(SQL)) {
            st.setInt(1, guestId);
            try (ResultSet rs = st.executeQuery()) {
                
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
        } catch (SQLException e) {
            System.err.println("Database Error fetching Booking by Guest ID: " + e.getMessage());
            return null;
        }
        return data;
    }

    // --- Read by Room ID (GET) ---
    @Override
    public List<Booking> GET_BOOKING_ROOMID(int roomId) {
        List<Booking> data = new ArrayList<>();
        final String SQL = "SELECT id, guest_id, room_id, check_in, check_out, status, total_amount FROM bookings WHERE room_id=?";
        
        try (PreparedStatement st = con.prepareStatement(SQL)) {
            st.setInt(1, roomId);
            try (ResultSet rs = st.executeQuery()) {
                
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
        } catch (SQLException e) {
            System.err.println("Database Error fetching Booking by Room ID: " + e.getMessage());
            return null;
        }
        return data;
    }
}