
package dao.interfaces;

import java.util.List;
import model.Booking;

public interface IBookingDAO {
    
    boolean POST_BOOKING(Booking booking);
    boolean PUT_BOOKING(Booking booking);
    boolean DELETE_BOOKING(int id);
    Booking GET_BOOKING_ID(int id);
    List<Booking> GET_BOOKINGES();
    List<Booking> GET_BOOKING_GUESTID(int guestId);
    List<Booking> GET_BOOKING_ROOMID(int roomId);
    
    
}
