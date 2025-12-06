package controller;

import model.ReportData;
import service.ReportService;
import java.sql.Date;
import java.util.Map;

public class ReportController {

    private ReportService reportService = new ReportService();

   
    public double getTotalIncome(Date start, Date end) {
        
        return reportService.getTotalIncome(start, end);
        
    }

    public int getBookingCount(Date start, Date end) {
        
        return reportService.getBookingCount(start, end);
        
    }

    public int getGuestCount(Date start, Date end) {
        
        return reportService.getGuestCount(start, end);
        
    }

    public Map<String, Integer> getRoomTypeReport() {
        
        return reportService.getRoomTypeReport();
        
    }

    public double getOccupancyRate(Date start, Date end) {
        
        return reportService.getOccupancyRate(start, end);
        
    }

    public ReportData generateFullReport(Date start, Date end) {
        
        return reportService.generateFullReport(start, end);
        
    }
    
    public Map<String, Integer> getDailyBookings(){
        
    return reportService.getDailyBookings();
}
    
    public Map<String, Double> getMonthlyIncomeTrend(){
        return reportService.getMonthlyIncomeTrend();
    }
    
    
public Map<String,Integer> getRoomOccupancyReport(){
    return reportService.getRoomOccupancyReport();
}

}
