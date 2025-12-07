import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;

import util.DBConnection;
import view.Login;


public class App {
    public static void main(String[] args) {
        
//        Connection con = DBConnection.getInstance().getConnection();
           

        Login login = new Login();
        login.show();
        
        
        
        
//       HashPassword("staff123");
        
    }
    
    
// public static String HashPassword(String password){
//    
//     try {
//         
//         MessageDigest mg = MessageDigest.getInstance("SHA-256");
//         byte[] diget = mg.digest(password.getBytes());
//         BigInteger intg = new BigInteger(1,diget);
//         
//         System.out.println("password : " + intg.toString(16));
//         return intg.toString(16);
//         
//     } catch (Exception e) {
//         
//         System.out.println("Password hashing error: " + e.getMessage());
//            return null;
//     }
//    
// }
}



