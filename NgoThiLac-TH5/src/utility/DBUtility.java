package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtility {

	Connection con;
	//mo ket noi voi csdl
	public Connection openConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:Test.db");
			
		} catch (ClassNotFoundException e) {
	//Xu ly ngoai le
			System.out.println(" Lỗi class not found");
			System.out.println(e.getMessage());
	
		} catch (SQLException e) {
			//xu ly ngoai le
			System.out.println(" Lỗi SQL");
			System.out.println(e.getMessage());
		}
		return con;
	}


	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			
			System.out.println(" Lỗi class not found");
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
	}
}
