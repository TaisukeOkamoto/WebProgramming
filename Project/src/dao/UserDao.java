package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDao {
	public List<User> findAll(){
		Connection conn = null;
		List<User> userList = new ArrayList<User>();
		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * FROM user";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String name = rs.getString("name");
				String birth_date = rs.getString("birth_date");
				String password = rs.getString("password");
				String create_date = rs.getString("create_date");
				User user = new User(id, login_id, name, birth_date, password, create_date);
				userList.add(user);
			}
			} catch(SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				if(conn != null) {
					try {
						conn.close();
					} catch(SQLException e) {
						e.printStackTrace();
						return null;
					}
				}
			}
		return userList;
		}
	}
