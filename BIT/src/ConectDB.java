import java.sql.*;

public class ConectDB {
	private static String url = "jdbc:mysql://localhost:3306/BIT?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";    
    private static String username = "root";   
    private static String password = "root";
    private static Connection con;

    public static Connection getConnection() {
        try {
        	con = DriverManager.getConnection(url, username, password);
        	
        } catch (SQLException e) {
			System.out.println("Error --> con la DB");
			e.printStackTrace();
		}
        return con;
    }
}
