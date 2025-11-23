import java.sql.Connection;
import util.DBConnection;


public class App {
    public static void main(String[] args) {
        
        Connection con = DBConnection.getInstance().getConnection();
        
    }
}
