package model;

import java.sql.Date;

public class Payment {
    private int Pid;
    private int bookingID;
    private double ammount;
    private String method;
    private String status;
    private Date date;
    
    public Payment(){
        System.out.println("Default constructor !!");
    }
    
    public Payment(int id, int bid , double amm , String meth ,String sta , Date date ){
        this.Pid = id;
        this.bookingID = bid;
        this.ammount = amm;
        this.method = meth;
        this.status = sta;
        this.date = date;
    }
    
    public boolean isPaid(){
        if(status.equals("Paid")){
            return  true;
        }else{
            return  false;
        }
    }
    
    public void markPaid(){
        status = "Paid";
    }
    
    public int getID(){
        return Pid;
    }
    public int getBookID(){
        return bookingID;
    }
    public double getAmmount(){
        return ammount;
    }
    public String getMethod(){
        return method;
    }
    public String getStatus(){
        return status;
    }
    public Date getDate(){
        return date;
    }
    
    
    public void setID(int id){
        this.Pid = id;
    }
    public void setBookID(int id){
        this.bookingID = id;
    }
    public void setAmmount(double amm){
        this.ammount = amm;
    }
    public void setMethod(String method){
        this.method = method;
    }
    public void setStatus(String sta){
        this.status = sta;
        
    }
    public void setDate(Date date){
        this.date = date;
    }
}
