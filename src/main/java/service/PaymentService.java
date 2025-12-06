
package service;

import dao.BookingDAO;
import dao.PaymentDAO;
import dao.interfaces.IBookingDAO;
import dao.interfaces.IPaymentDAO;
import java.sql.Date;
import java.util.List;
import model.Booking;
import model.Payment;

public class PaymentService {
    
    private IPaymentDAO paymentDAO = new PaymentDAO();
    private IBookingDAO bookingDAO = new BookingDAO();
    
    
    public boolean AddPayment(int bookingId, double amount, String method, Date date){
        
        
        Booking book = bookingDAO.GET_BOOKING_ID(bookingId);
        if(book == null){
            
            System.out.println("Booking not found!");
            return false;
            
        }
        
        if(amount <= 0){
            
            System.out.println("Invalid Payment Amount ?");
            return false;
            
        }
        
        if(method == null || method.isEmpty()){
            System.out.println("method cannot be empty !!");
            return false;
            
        }
        
        Payment payment = new Payment(0, bookingId, amount, method, "Completed", date);
        
        if(!paymentDAO.POST_PAYMENT(payment)){
            
            return false;
            
        }
        
        double totalPayment = paymentDAO.GET_PAYMENT_BOOKINGID(bookingId).stream().mapToDouble(Payment::getAmmount).sum();
        
        
        if(totalPayment >= book.getToatalAmmount()){
            
            book.setStatus("Paid");
            bookingDAO.PUT_BOOKING(book);
        }
        
        return true;
    }
    
    public boolean UpdatePayment(int id, double amount, String method, Date date, String status){
        
        Payment payment = paymentDAO.GET_Payment_ID(id);
        
        if(payment == null){
            System.out.println("payment is not found !!");
            return false;
        }
        
        payment.setAmmount(amount);
        payment.setMethod(method);
        payment.setDate(date);
        payment.setStatus(status);
        
        return paymentDAO.PUT_PAYMENT(payment);
        
        
        
    }
    
    public boolean DeletePayament(int id){
        
        Payment payment = paymentDAO.GET_Payment_ID(id);
        
        if(payment == null){
            System.out.println("payment is not found !!");
            return false;
        }
        
        int bookid = payment.getBookID();
        
        if(!paymentDAO.DELETE_PAYMENT(id)){
            return false;
        }
        
        double totalPaid = paymentDAO.GET_PAYMENT_BOOKINGID(bookid).stream().mapToDouble(Payment::getAmmount).sum();
        
        Booking booking = bookingDAO.GET_BOOKING_ID(bookid);
        
        if(totalPaid >= booking.getToatalAmmount()){
            
            booking.setStatus("Paid");
            
        }else if(totalPaid == 0){
            
            booking.setStatus("Pending");
            
        }else{
             booking.setStatus("half payment");
        }
        
        bookingDAO.PUT_BOOKING(booking);
        
        return true;
    }
    
    public Payment GetPayment(int id){
        
        return paymentDAO.GET_Payment_ID(id);
    }
    
    public List<Payment> GetPaymentByBooking(int bookid){
        return  paymentDAO.GET_PAYMENT_BOOKINGID(bookid);
        
    }
    
    public List<Payment> GetAllPayments(){
        return paymentDAO.GET_PAYMENTS();
    }
    
}
