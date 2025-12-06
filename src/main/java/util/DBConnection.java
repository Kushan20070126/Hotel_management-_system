package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;



public class DBConnection {
    
    private Connection con;
    private  static DBConnection instance;
   
    
        private DBConnection(){
            createDB();
            createTable();
            connnectDB(); 
        }
        private void connnectDB(){
            try{
              
               
            Class.forName("com.mysql.cj.jdbc.Driver");
            createDB();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_db?useSSL=false","root","");
            System.out.println("DataBase Connected !!");
                
                
            }catch(Exception e){
                
                System.err.println("Database Connection Failed !!");
                
                JOptionPane.showMessageDialog(null, "Error : Database Connection Failed ->  !! " + e.getMessage());
                
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
         
         
         private void createDB(){
             try {
                 Class.forName("com.mysql.cj.jdbc.Driver");
                 Connection tmpCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/?useSSL=false","root","");
                 
                 Statement st = tmpCon.createStatement();
                 st.execute("CREATE DATABASE IF NOT EXISTS hotel_db");
                 System.out.println("Database Automaticlly created  or Checked!!");

                 tmpCon.close();
             } catch (Exception e) {
                 System.err.println("Database Creation Failed!" + e.toString());
             }
             
             
         }
         
         private void createTable(){
             
             try {
                 Class.forName("com.mysql.cj.jdbc.Driver");
                 Connection tmpCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_db?useSSL=false","root","");
                 
                 Statement st = tmpCon.createStatement();
                 
                 st.execute("""
            CREATE TABLE IF NOT EXISTS users (
                id INT AUTO_INCREMENT PRIMARY KEY,
                username VARCHAR(100) ,
                password VARCHAR(255),
                role VARCHAR(50) 
            )
        """);

        st.execute("""
            CREATE TABLE IF NOT EXISTS guests (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(20) ,
                phone VARCHAR(10),
                email VARCHAR(30),
                nicORPass VARCHAR(30),
                address VARCHAR(200)
            )
        """);

        st.execute("""
            CREATE TABLE IF NOT EXISTS rooms (
                id INT AUTO_INCREMENT PRIMARY KEY,
                number VARCHAR(50) ,
                type VARCHAR(20),
                price DOUBLE ,
                status VARCHAR(50)
            )
        """);

        st.execute("""
            CREATE TABLE IF NOT EXISTS bookings (
                id INT AUTO_INCREMENT PRIMARY KEY,
                room_id INT ,
                guest_id INT ,
                check_in DATE ,
                check_out DATE ,
                status VARCHAR(50),
                total_amount DOUBLE ,
                FOREIGN KEY (room_id) REFERENCES rooms(id),
                FOREIGN KEY (guest_id) REFERENCES guests(id)
            )
        """);

        
        st.execute("""
            CREATE TABLE IF NOT EXISTS payments (
                id INT AUTO_INCREMENT PRIMARY KEY,
                booking_id INT ,
                amount DOUBLE ,
                method VARCHAR(100),
                status VARCHAR(50),
                date DATE,
                FOREIGN KEY (booking_id) REFERENCES bookings(id)
            )
        """);
        System.out.println("All tables created or verified successfully!");
        tmpCon.close();
             } catch (Exception e) {
                 
                 System.err.println("Table creation error: " + e.toString());
             }
             
         }
         
         
        
         
                   
}
