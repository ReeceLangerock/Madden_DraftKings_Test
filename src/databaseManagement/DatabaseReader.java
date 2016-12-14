package databaseManagement;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import maddenCFMInfo.Player;
import maddenCFMInfo.PlayerSelectModel;
import userManagement.User;
import userManagement.UserModel;

public class DatabaseReader {

	public void passwordCheck() {

	}

	public Connection getConnection() {

		String user = "revoadmin";
		String pass = "revo2016";
		String dbDriver = "jdbc:mysql://db4free.net:3306/cfmfantasy";
		try {
			// The newInstance() call is a work around for some
			// broken Java implementations

			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			System.out.println("catch 1");
		}

		Connection conn = null;

		try {
			conn = conn = DriverManager.getConnection(dbDriver, user, pass);
			System.out.println("db connected");
			return conn;
		} catch (SQLException ex) {
			// handle any errors\
			System.out.println("catch 2");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return null;

	}

	public ObservableList<UserModel> readAllUsers() {

		
		Connection conn = getConnection();
		ObservableList<UserModel> temp = FXCollections.observableArrayList();
		try {
			String query = "SELECT * FROM Users";

			// create the mysql insert preparedstatement
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			User tempUser = null;
			while (rs.next()) {			
					String tempUsername = (rs.getString("Name"));
					String tempEmail = rs.getString("Email");

					Blob saltBlob = rs.getBlob("Salt");
					int blobLength = (int) saltBlob.length();
					byte[] tempSalt = saltBlob.getBytes(1, blobLength);
					saltBlob.free();

					Blob passwordBlob = rs.getBlob("EncryptedPassword");
					blobLength = (int) passwordBlob.length();
					byte[] tempPassword = passwordBlob.getBytes(1, blobLength);
					passwordBlob.free();

					Blob teamArrayBlob = rs.getBlob("CreatedTeamArray");
					teamArrayBlob.free();
					ArrayList tempTeamArrayList = new ArrayList();
					tempUser = new User(tempUsername, tempEmail, tempSalt, tempPassword, tempTeamArrayList);
					
					temp.add(tempUser.generateAndReturnUserModel());
				}
			
			return temp;
		}catch (SQLException ex) {
			// handle any errors\
			System.out.println("Read All Users Error");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		

		return null;

	}

	public User readUserFromDatabase(String username) {

	

		Connection conn = getConnection();

		try {
			String query = "SELECT * FROM Users";

			// create the mysql insert preparedstatement
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			User tempUser = null;
			while (rs.next()) {
				System.out.println("searching");
				if (rs.getString("Name").equals(username)) {
					String tempUsername = (rs.getString("Name"));
					String tempEmail = rs.getString("Email");

					Blob saltBlob = rs.getBlob("Salt");
					int blobLength = (int) saltBlob.length();
					byte[] tempSalt = saltBlob.getBytes(1, blobLength);
					saltBlob.free();

					Blob passwordBlob = rs.getBlob("EncryptedPassword");
					blobLength = (int) passwordBlob.length();
					byte[] tempPassword = passwordBlob.getBytes(1, blobLength);
					passwordBlob.free();

					Blob teamArrayBlob = rs.getBlob("CreatedTeamArray");
					teamArrayBlob.free();
					ArrayList tempTeamArrayList = new ArrayList();
					tempUser = new User(tempUsername, tempEmail, tempSalt, tempPassword, tempTeamArrayList);
				}

			}
			st.close();
			return tempUser;

		} catch (SQLException ex) {
			// handle any errors\
			System.out.println("catch 2");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return null;

	}

}
