
package controller;

import java.sql.Date;
import java.util.List;
import model.Payment;
import service.PaymentService;

public class PaymentController {
    
    
    private PaymentService payamentservice = new PaymentService();
    
    public boolean addpayment(int bookingId, double amount, String method, Date date){
        
        return payamentservice.AddPayment(bookingId, amount, method, date);
    }
    
    public boolean updatepayment(int id, double amount, String method, Date date, String status){
        
        return payamentservice.UpdatePayment(id, amount, method, date, status);
    }
    
    public boolean deletepayment(int id){
        
        return payamentservice.DeletePayament(id);
    }
    
    public Payment getpayment(int id){
        
        return payamentservice.GetPayment(id);
    }
    
    public List<Payment> getpaymentforbooking(int bookingId){
        
        return payamentservice.GetPaymentByBooking(bookingId);
    }
    
    public List<Payment> getallpayament(){
        
        return payamentservice.GetAllPayments();
    }
}
