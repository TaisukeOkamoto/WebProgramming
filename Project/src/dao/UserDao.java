package dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

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
			int idData = rs.getInt("id");
			String loginIdData = rs.getString("login_id");
			String nameData = rs.getString("name");
			Date birthDateData = rs.getDate("birth_date");
			String createDateData = rs.getString("create_date");
			String updateDteData = rs.getString("update_date");

			return new User(idData,loginIdData,nameData,birthDateData,createDateData,updateDteData);
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

			String sql = "INSERT INTO user(login_id,password,name,birth_date,create_date,update_date) VALUES(?,?,?,?,?,?)";

			Date now = new Date();
			java.sql.Timestamp sqlNow = new java.sql.Timestamp(now.getTime());

			//ハッシュ生成前にバイト配列に置き換える際のCharset
			Charset charset = StandardCharsets.UTF_8;
			//ハッシュアルゴリズム
			String algorithm = "MD5";

			//ハッシュ生成処理
			byte[] bytes;

			bytes = MessageDigest.getInstance(algorithm).digest(password.getBytes(charset));
			String encryptedPassword = DatatypeConverter.printHexBinary(bytes);

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginId);
			pStmt.setString(2, encryptedPassword);
			pStmt.setString(3, name);
			java.sql.Date sqlBirthDate = new java.sql.Date(birthDate.getTime());
			pStmt.setDate(4, sqlBirthDate);
			pStmt.setTimestamp(5, sqlNow);
			pStmt.setTimestamp(6, sqlNow);
			pStmt.executeUpdate();
		} catch (SQLException | NoSuchAlgorithmException e) {
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

	public Boolean userCheck(String loginId) {
		Connection conn = null;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT login_id FROM user WHERE login_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1,loginId);

			ResultSet rs = pStmt.executeQuery();

			if(!rs.next()) {
				return false;
			}
			String loginData;
			loginData = rs.getString("login_id");

			if(loginData != null) {
				return true;
			}

		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}

	//検索結果を取得
	public List<User> searchUser(String inputId,String inputName,Date birthDateFrom,Date birthDateTo){
		Connection conn = null;
		List<User> userList = new ArrayList<User>();
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM user WHERE login_id = ? OR name like ? OR birth_date BETWEEN ? AND ?";

			java.sql.Date birthDateFromSql = new java.sql.Date(birthDateFrom.getTime());
			java.sql.Date birthDateToSql = new java.sql.Date(birthDateTo.getTime());

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, inputId);
			pStmt.setString(2, "%" +inputName+ "%");
			pStmt.setDate(3, birthDateFromSql);
			pStmt.setDate(4, birthDateToSql);

			ResultSet rs = pStmt.executeQuery();

			if(!rs.next()) {
				return null;
			}

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

	//更新用のユーザ情報を取得
	public User getUpdateUserInfo(String id) {
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
			int idData = rs.getInt("id");
			String loginIdData = rs.getString("login_id");
			String NameData = rs.getString("name");
			Date birthDateData = rs.getDate("birth_date");
			String passwordData = rs.getString("password");

			return new User(idData,loginIdData,NameData,birthDateData,passwordData);

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
	//ユーザ情報更新処理
	public void setUpdateUserInfo(String loginId,String password,String name,Date birthDate) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "UPDATE user SET password = ?,name = ?, birth_date = ?,update_date = ? WHERE login_id = ?";

		    java.sql.Date sqlDate = new java.sql.Date(birthDate.getTime());

			//ハッシュ生成前にバイト配列に置き換える際のCharset
			Charset charset = StandardCharsets.UTF_8;
			//ハッシュアルゴリズム
			String algorithm = "MD5";

			//ハッシュ生成処理
			byte[] bytes;

			bytes = MessageDigest.getInstance(algorithm).digest(password.getBytes(charset));
			String encryptedPassword = DatatypeConverter.printHexBinary(bytes);

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, encryptedPassword);
			pStmt.setString(2, name);
			pStmt.setDate(3,sqlDate);
			Date utilNow = new Date();
			java.sql.Timestamp sqlNow = new java.sql.Timestamp(utilNow.getTime());
			pStmt.setTimestamp(4,sqlNow);
			pStmt.setString(5, loginId);
			pStmt.executeUpdate();
		} catch(SQLException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//ユーザ削除
	public void userDelete(String id) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();

			String sql = "DELETE FROM user WHERE id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);
			pStmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
