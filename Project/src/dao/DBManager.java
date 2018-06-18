package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	private static String URL = "jdbc:mysql://localhost/sampledb?useUnicode=true&characterEncoding=utf8&serverTimezone=JST";
	private static String USER = "root";
	private static String PASSWORD = "password";

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}

}
