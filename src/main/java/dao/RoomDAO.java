package dao;

import java.sql.*;
import dao.interfaces.IRoomDAO;
import java.util.ArrayList;
import java.util.List;
import model.Room;
import util.DBConnection;

public class RoomDAO implements IRoomDAO {

    // Removed the class-level 'private Connection con' to avoid thread-safety issues
    // and rely on retrieving a connection inside each method using try-with-resources.

    // --- Create (POST) ---
    @Override
    public boolean POST_ROOM(Room room) {
        final String SQL = "INSERT INTO rooms(number, type, price, status) VALUES (?, ?, ?, ?)";

        // Use try-with-resources to automatically close Connection and PreparedStatement
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(SQL)) {

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

    // --- Update (PUT) ---
    @Override
    public boolean PUT_ROOM(Room room) {
        final String SQL = "UPDATE rooms SET number=?, type=?, price=?, status=? WHERE id=?";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(SQL)) {

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

    // --- Delete (DELETE) ---
    @Override
    public boolean DELETE_ROOM(int id) {
        final String SQL = "DELETE FROM rooms WHERE id=?";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(SQL)) {

            st.setInt(1, id);

            return st.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Database Error Deleting Room: " + e.getMessage());
            return false;
        }
    }

    // --- Read by ID (GET) ---
    @Override
    public Room GET_ROOM_ID(int id) {
        final String SQL = "SELECT id, number, type, price, status FROM rooms WHERE id=?";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(SQL)) {

            st.setInt(1, id);

            // ResultSet is also managed by try-with-resources
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
        return null; // Return null if not found or on error
    }

    // --- Read All (GET) ---
    @Override
    public List<Room> GET_ROOMS() {
        List<Room> data = new ArrayList<>();
        final String SQL = "SELECT id, number, type, price, status FROM rooms";

        try (Connection con = DBConnection.getInstance().getConnection();
             Statement st = con.createStatement(); // Statement is suitable here as no parameters are needed
             ResultSet rs = st.executeQuery(SQL)) {

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
}