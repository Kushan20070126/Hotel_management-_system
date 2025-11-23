package util;

import java.sql.Connection;
import java.sql.DriverManager;



public class DBConnection {
    
    private Connection con;
    private  static DBConnection instance;
   
    
        private DBConnection(){
          try{
               
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_db?useSSL=false","root","");
            System.out.println("DataBase Connected !!");
                
                
            }catch(Exception e){
                
                System.err.println("Database Connection Failed !!");
                
            }
        }
        
         public static synchronized DBConnection getInstance(){
             if(instance == null){
                 instance = new DBConnection();
             }
             return  instance;
         }
           
         public Connection getConnection(){
             return  con;
         }
        
         
                   
}
