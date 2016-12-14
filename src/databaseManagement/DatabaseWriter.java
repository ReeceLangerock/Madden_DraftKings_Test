package databaseManagement;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import userManagement.User;
import userManagement.UserCreatedTeam;

public class DatabaseWriter {

	public boolean saveCreatedTeamToDatabase(String username, ArrayList<UserCreatedTeam> teamArrayToSave) {

		String user = "revoadmin";
		String pass = "revo2016";
		String dbDriver = "jdbc:mysql://db4free.net:3306/cfmfantasy";
		try {
			// The newInstance() call is a work around for some
			// broken Java implementations

			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			System.out.println("db Connection Error");
		}

		Connection conn = null;
		java.sql.PreparedStatement preparedStatement = null;

		try {
			conn = conn = DriverManager.getConnection(dbDriver, user, pass);
			System.out.println("db connected");
			String updateTableSQL = "UPDATE Users SET CreatedTeamArray = ? " + " WHERE Name = ?";

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream out;
			try {
				out = new ObjectOutputStream(baos);
				out.writeObject(teamArrayToSave);
				out.close();
			} catch (IOException e) { 
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			preparedStatement = conn.prepareStatement(updateTableSQL);
			byte[] bytes = baos.toByteArray();
			// ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			// ps.setBinaryStream(1, bais, bytes.length);

			Blob teamBlob = new javax.sql.rowset.serial.SerialBlob(bytes);

			preparedStatement = conn.prepareStatement(updateTableSQL);

			preparedStatement.setBlob(2, teamBlob);
			preparedStatement.setString(1, username);

			// execute update SQL stetement
			preparedStatement.executeUpdate();

			System.out.println("Record is updated to DBUSER table!");
			return true;
		} catch (SQLException ex) {
			// handle any errors\
			System.out.println("DB update error");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return false;

	}

	public boolean writeNewUserToDatabase(String username, String email, byte[] salt, byte[] encryptedPassword) {

		// username and password for test mySql database stored on db4free.com
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
			// Do something with the Connection
			String query1 = "SELECT * FROM Users";
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(query1);
			User tempUser = null;
			boolean existingUser = false;
			System.out.println(existingUser);
			while (rs.next()) {
				System.out.println(rs.getString("Name") + " - " + username);
				if (rs.getString("Name").equals(username)) {
					existingUser = true;
					System.out.println(existingUser);
				}
			}
			System.out.println(existingUser);
			if (!existingUser) {
				System.out.println("in if");
				String query2 = " insert into Users (Name, Email, Salt, EncryptedPassword, CreatedTeamArray)"
						+ " values (?, ?, ?, ?, ?)";
				Blob saltBlob = new javax.sql.rowset.serial.SerialBlob(salt);
				Blob passwordBlob = new javax.sql.rowset.serial.SerialBlob(encryptedPassword);
				Blob teamBlob = new javax.sql.rowset.serial.SerialBlob(encryptedPassword);// temporary
				// create the mysql insert preparedstatement
				PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query2);
				preparedStmt.setString(1, username);
				preparedStmt.setString(2, email);
				preparedStmt.setBlob(3, saltBlob);
				preparedStmt.setBlob(4, passwordBlob);
				preparedStmt.setBlob(5, teamBlob);// temporary
				preparedStmt.execute();
				System.out.println("added successfully");
				return true;
			}

		} catch (SQLException ex) {
			// handle any errors\
			System.out.println("catch 2");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return false;
	}

}
