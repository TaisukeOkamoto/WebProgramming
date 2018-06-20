package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.User;

public class UserDao {

	//ログイン情報を取得
	public User findByLoginInfo(String loginId,String password) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginId);
			pStmt.setString(2, password);
			ResultSet rs = pStmt.executeQuery();

			if(!rs.next()) {
				return null;
			}
			String loginIdData = rs.getString("login_id");
			String nameData = rs.getString("name");
			return new User(loginIdData,nameData);
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

	}

	//ユーザ全情報を取得
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
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				Date birth_date = rs.getDate("birth_date");
				String password = rs.getString("password");
				String create_date = rs.getString("create_date");
				String update_date = rs.getString("update_date");
				User user = new User(id, loginId, name, birth_date, password, create_date, update_date);
				userList.add(user);
			}

		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}

	//ユーザ詳細情報を取得
	public User findByUserDetail(String id) {
		Connection conn = null;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * FROM user WHERE id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);
			ResultSet rs = pStmt.executeQuery();
			if(!rs.next()) {
				return null;
			}
			String loginIdData = rs.getString("login_id");
			String nameData = rs.getString("name");
			Date birthDateData = rs.getDate("birth_date");
			String createDateData = rs.getString("create_date");
			String updateDteData = rs.getString("update_date");

			return new User(loginIdData,nameData,birthDateData,createDateData,updateDteData);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public void userRegistration(String loginId,String password,String name,Date birthDate) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ss HH:mm:ss");
			String now = sdf.format(new Date());

			java.sql.Date sqlNow = new java.sql.Date(now.getTime());

			String sql = "INSERT INTO user(login_id,password,name,birth_date,create_date,update_date) VALUES(?,?,?,?,?,?)";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginId);
			pStmt.setString(2, password);
			pStmt.setString(3, name);
			java.sql.Date sqlBirthDate = new java.sql.Date(birthDate.getTime());
			pStmt.setDate(4, sqlBirthDate);
			pStmt.setDate(5, sqlNow);
			pStmt.setDate(6, sqlNow);
			int rs = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
					return;
				}
			}
		}
	}

}
