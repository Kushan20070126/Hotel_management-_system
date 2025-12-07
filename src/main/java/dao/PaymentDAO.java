package dao;

import dao.interfaces.IPaymentDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Payment;
import util.DBConnection;

public class PaymentDAO implements IPaymentDAO {

    private Connection con = DBConnection.getInstance().getConnection();
    

    @Override
    public boolean POST_PAYMENT(Payment payment) {
        

        try {
             PreparedStatement st = con.prepareStatement("INSERT INTO payments(booking_id, amount, method, date, status) VALUES (?, ?, ?, ?, ?)");

            st.setInt(1, payment.getBookID());
            st.setDouble(2, payment.getAmmount()); 
            st.setString(3, payment.getMethod());
            st.setDate(4, payment.getDate());
            st.setString(5, payment.getStatus());

            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            
            
            System.err.println("Something went wrong adding Payment: " + e.getMessage());
           
            return false;
        }
    }

    

    @Override
    public boolean PUT_PAYMENT(Payment payment) {
       

        try {
            
             PreparedStatement st = con.prepareStatement("UPDATE payments SET booking_id=?, amount=?, method=?, date=?, status=? WHERE id=?");

            st.setInt(1, payment.getBookID());
            st.setDouble(2, payment.getAmmount());
            st.setString(3, payment.getMethod());
            st.setDate(4, payment.getDate());
            st.setString(5, payment.getStatus());
            st.setInt(6, payment.getID()); 

            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
            
            

        } catch (SQLException e) {
            
            
            System.err.println("Something went wrong updating Payment: " + e.getMessage());
            
            
            return false;
        }
    }

    

    @Override
    public boolean DELETE_PAYMENT(int id) {

        try {
             PreparedStatement st = con.prepareStatement("DELETE FROM payments WHERE id=?");

            st.setInt(1, id);

            int rowsAffected = st.executeUpdate();
            
            return rowsAffected > 0;

        } catch (SQLException e) {
            
            
            System.err.println("Something went wrong deleting Payment: " + e.getMessage());
            
            
            return false;
        }
    }

    

    @Override
    public Payment GET_Payment_ID(int id) {

        try{
             PreparedStatement st = con.prepareStatement("SELECT id, booking_id, amount, method, status, date FROM payments WHERE id=?");

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
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

       catch (SQLException e) {
            
            
            System.err.println("Something went wrong fetching Payment by ID: " + e.getMessage());
            
            
        }
        return null;
    }

    

    @Override
    public List<Payment> GET_PAYMENTS() {
        
        List<Payment> data = new ArrayList<>();

        

        try {
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT id, booking_id, amount, method, status, date FROM payments"); 

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
            
            return null; 
            
        }
        return data;
    }

    

    @Override
    public List<Payment> GET_PAYMENT_BOOKINGID(int bookingid) {
        
        
        List<Payment> data = new ArrayList<>();
        

        try {
             PreparedStatement st = con.prepareStatement("SELECT id, booking_id, amount, method, status, date FROM payments WHERE booking_id=?");

            st.setInt(1, bookingid);

           ResultSet rs = st.executeQuery();
                
                
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
        catch (SQLException e) {
            
            System.err.println("Something went wrong fetching Payments by Booking ID: " + e.getMessage());
            return null;
        }
        return data;
    }
    
     @Override
    public double GET_TOTAL_INCOME() {
     String sql = "SELECT SUM(total_amount) AS income FROM bookings";
    
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()) return rs.getDouble("income");
        
    } catch(Exception e) {
        System.out.println("Income Report Error: " + e);
    }
    return 0;

    }
}