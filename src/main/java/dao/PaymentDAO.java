package dao;

import dao.interfaces.IPaymentDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Payment;
import util.DBConnection;

public class PaymentDAO implements IPaymentDAO {

    // Removed the class-level 'Connection con' for better thread safety and resource management.

    @Override
    public boolean POST_PAYMENT(Payment payment) {
        // SQL statement for inserting a new payment record.
        String sql = "INSERT INTO payments(booking_id, amount, method, date, status) VALUES (?, ?, ?, ?, ?)";

        // Use try-with-resources for automatic closure of Connection and PreparedStatement
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, payment.getBookID());
            st.setDouble(2, payment.getAmount()); // Corrected method name (assuming a fix in Payment model)
            st.setString(3, payment.getMethod());
            st.setDate(4, payment.getDate());
            st.setString(5, payment.getStatus());

            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Something went wrong adding Payment: " + e.getMessage());
            // Optionally, log the full stack trace for better debugging: e.printStackTrace();
            return false;
        }
    }

    ---

    @Override
    public boolean PUT_PAYMENT(Payment payment) {
        // Corrected SQL: The original SQL was mismatched with the parameter settings.
        String sql = "UPDATE payments SET booking_id=?, amount=?, method=?, date=?, status=? WHERE id=?";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, payment.getBookID());
            st.setDouble(2, payment.getAmount()); // Corrected method name
            st.setString(3, payment.getMethod());
            st.setDate(4, payment.getDate());
            st.setString(5, payment.getStatus());
            st.setInt(6, payment.getID()); // This should be the WHERE clause parameter

            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Something went wrong updating Payment: " + e.getMessage());
            return false;
        }
    }

    ---

    @Override
    public boolean DELETE_PAYMENT(int id) {
        String sql = "DELETE FROM payments WHERE id=?";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);

            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Something went wrong deleting Payment: " + e.getMessage());
            return false;
        }
    }

    ---

    @Override
    public Payment GET_Payment_ID(int id) {
        String sql = "SELECT id, booking_id, amount, method, status, date FROM payments WHERE id=?";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);

            // Use try-with-resources for ResultSet as well
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Payment(
                        rs.getInt("id"),
                        rs.getInt("booking_id"),
                        rs.getDouble("amount"),
                        rs.getString("method"),
                        rs.getString("status"),
                        rs.getDate("date")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Something went wrong fetching Payment by ID: " + e.getMessage());
        }
        return null;
    }

    ---

    @Override
    public List<Payment> GET_PAYMENTS() {
        List<Payment> data = new ArrayList<>();
        String sql = "SELECT id, booking_id, amount, method, status, date FROM payments";

        // Using PreparedStatement even for simple queries is often safer, but Statement is okay here too.
        try (Connection con = DBConnection.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) { // ResultSet closed automatically

            while (rs.next()) {
                Payment pay = new Payment(
                    rs.getInt("id"),
                    rs.getInt("booking_id"),
                    rs.getDouble("amount"),
                    rs.getString("method"),
                    rs.getString("status"),
                    rs.getDate("date")
                );
                data.add(pay);
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong fetching all Payments: " + e.getMessage());
            return null; // Return null to indicate failure
        }
        return data;
    }

    ---

    @Override
    public List<Payment> GET_PAYMENT_BOOKINGID(int bookingid) {
        List<Payment> data = new ArrayList<>();
        String sql = "SELECT id, booking_id, amount, method, status, date FROM payments WHERE booking_id=?";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, bookingid);

            // Use try-with-resources for ResultSet
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Payment pay = new Payment(
                        rs.getInt("id"),
                        rs.getInt("booking_id"),
                        rs.getDouble("amount"),
                        rs.getString("method"),
                        rs.getString("status"),
                        rs.getDate("date")
                    );
                    data.add(pay);
                }
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong fetching Payments by Booking ID: " + e.getMessage());
            return null;
        }
        return data;
    }
}