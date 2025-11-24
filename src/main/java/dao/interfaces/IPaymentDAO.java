
package dao.interfaces;

import java.util.List;
import model.Payment;

public interface IPaymentDAO {
    
    boolean POST_PAYMENT(Payment payment);
    boolean PUT_PAYMENT(Payment payment);
    boolean DELETE_PAYMENT(int id);
    Payment GET_Payment_ID(int id);
    List<Payment> GET_PAYMENT_BOOKINGID(int bookingid);
    List<Payment> GET_PAYMENTS();
    
}
