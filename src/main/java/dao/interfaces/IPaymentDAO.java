
package dao.interfaces;

import java.util.List;
import model.Payment;

public interface IPaymentDAO {
    
    boolean addPayment(Payment payment);
    boolean upadatePayment(Payment payment);
    boolean deletePayment(int id);
    Payment getPaymentByID(int id);
    List<Payment> getPaymentByBooking(int bookingid);
    List<Payment> getAllPaymnet();
    
}
