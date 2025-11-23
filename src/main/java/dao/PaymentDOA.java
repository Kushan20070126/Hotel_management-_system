
package dao;

import dao.interfaces.IPaymentDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Payment;
import util.DBConnection;

public class PaymentDOA implements IPaymentDAO{
    
    Connection con = DBConnection.getInstance().getConnection();

    @Override
    public boolean addPayment(Payment payment) {
        
        try {
            PreparedStatement st = con.prepareStatement("insert into payments(booking_id, amount, method, date, status) values (?, ?, ?, ?,?)");
            st.setInt(1, payment.getBookID());
            st.setDouble(2, payment.getAmmount());
            st.setString(3, payment.getMethod());
            st.setDate(4, payment.getDate());
            st.setString(5, payment.getStatus());
            
            if(st.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
            
        } catch (Exception e) {
             System.err.println("Somthing went Wrong Adding Payment " + e.getMessage());    
            return false;
        }
    }

    @Override
    public boolean upadatePayment(Payment payment) {
        try {
       PreparedStatement st = con.prepareStatement("Update payments set booking_id=?,type=? ,price=? , status=? where id=?");
            st.setInt(1, payment.getBookID());
            st.setDouble(2, payment.getAmmount());
            st.setString(3, payment.getMethod());
            st.setDate(4, payment.getDate());
            st.setString(5, payment.getStatus());
            st.setInt(6, payment.getID());
            
             if(st.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
            
        } catch (Exception e) {
            
            System.err.println("Somthing went Wrong Updating Payment " + e.getMessage());    
            return false;
        }
    }
    

    @Override
    public boolean deletePayment(int id) {
        
        try {
            PreparedStatement st = con.prepareStatement("delete from payments where id=?");
            st.setInt(1, id);
            
             if(st.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
            

        } catch (Exception e) {
            
            System.err.println("Somthing went Wrong Deleting Payment " + e.getMessage());    
            return false;
        }
    }

    @Override
    public Payment getPaymentByID(int id) {
        
        try {
           PreparedStatement st = con.prepareStatement("select * from payments where id=? ");
           st.setInt(1, id);
           
           ResultSet rs = st.executeQuery();
          
           if(rs.next()){
               Payment data = new Payment(
               rs.getInt("id"),
               rs.getInt("booking_id"),
               rs.getDouble("amount"),
               rs.getString("method"),
               rs.getString("status"),
               rs.getDate("date")
               );
               
               return data;
               
           }
            
        } catch (Exception e) {
            System.err.println("Somthing went Wrong fetching db Payment Data " + e.getMessage());    
             return null;
        }
                     return null;
    }

    @Override
    public List<Payment> getAllPaymnet() {
        List<Payment> data = new ArrayList<>();
        try {
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from payments");
            
            while(rs.next()){
                
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
            
        } catch (Exception e) {
           System.err.println("Somthing went Wrong fetching  All db Payment Data " + e.getMessage());  
           return null;
        }
        
        return data;
    }

    @Override
    public List<Payment> getPaymentByBooking(int bookingid) {
         List<Payment> data = new ArrayList<>();
        try {
             PreparedStatement st = con.prepareStatement("SELECT * FROM payments WHERE booking_id=?");
                st.setInt(1, bookingid);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
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
            
            
        } catch (Exception e) {
            
            System.err.println("Somthing went Wrong fetching Get Payments By Booking  " + e.getMessage());  
           return null;
        }
        
        return data;
            
    }
    
    
    
    
    
    
}
