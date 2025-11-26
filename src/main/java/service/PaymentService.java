
package service;

import dao.BookingDAO;
import dao.PaymentDOA;
import dao.interfaces.IBookingDAO;
import dao.interfaces.IPaymentDAO;

public class PaymentService {
    
    private IPaymentDAO paymentDAO = new PaymentDOA();
    private IBookingDAO bookingDAO = new BookingDAO();
    
    
    public boolean AddPayment(){
        
    }
    
}
