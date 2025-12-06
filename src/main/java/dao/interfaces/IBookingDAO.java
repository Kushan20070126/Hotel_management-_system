
package dao.interfaces;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import model.Booking;

public interface IBookingDAO {
    
    boolean POST_BOOKING(Booking booking);
    boolean PUT_BOOKING(Booking booking);
    boolean DELETE_BOOKING(int id);
    Booking GET_BOOKING_ID(int id);
    List<Booking> GET_BOOKINGES();
    List<Booking> GET_BOOKING_GUESTID(int guestId);
    List<Booking> GET_BOOKING_ROOMID(int roomId);
    int GET_BOOKING_COUNT(Date start, Date end);
    Map<String, Integer> GET_ROOM_TYPE_USAGE();
    int GET_TOTAL_ROOMS();
    int GET_OCCUPIED_ROOMS(Date start, Date end);
    Map<String, Integer> GET_DAILY_BOOKING_VOLUME();
    Map<String, Double> GET_MONTHLY_INCOME_TREND();
}
