package userManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import databaseManagement.DatabaseReader;
import databaseManagement.DatabaseWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import maddenCFMInfo.Player;
import maddenCFMInfo.PlayerSelectModel;

public class UserManager {
	User currentUser = null;
	ArrayList<User> userList = new ArrayList();

	
	public boolean passwordValidation(User userToValidate, String password){
		int userIndex = 0;
		PasswordEncrpytor pwEnc = new PasswordEncrpytor();
		boolean isCorrectPassword = false;
		
		System.out.println(userToValidate.toString());
		try {
			isCorrectPassword = pwEnc.authenticate(password, userToValidate.getEncryptedPassword(), userToValidate.getSalt());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isCorrectPassword;
		
	}

	public ArrayList<String> addUser(String username, String password1, String password2, String team, String email) {
		
		ArrayList<String> errors = new ArrayList();
		PasswordEncrpytor pwEnc = new PasswordEncrpytor();
		byte[] salt = null;
		byte[] encryptedPassword = null;

		if (!password1.equals(password2))
			errors.add("Passwords do not match");		
		if (emailCheck(email) == false)
			errors.add("Invalid email address");
		
		if (errors.isEmpty() == false)
			return errors;
		else {

			try {
				salt = pwEnc.generateSalt();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				encryptedPassword = pwEnc.getEncryptedPassword(password1, salt);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//User newUser = new User(username, email, salt, encryptedPassword);
			boolean userAdded = new DatabaseWriter().writeNewUserToDatabase(username, email, salt, encryptedPassword);
			if(userAdded){
				return null;
			}
			else{
				errors.add("Username already taken");
				return errors;
			}
			

		}

	}


	public boolean emailCheck(String email) {
		
		Pattern ptr = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
		System.out.println(ptr.matcher(email).matches());
		if(ptr.matcher(email).matches())
			return true;
		else
			return false;
	}
	
	public void setUserList(ArrayList users){
		userList = users;
		
	}
	
	public ArrayList getUserList(){
		return userList;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
		

	/*public void saveUserList(){
		
		file = new File(".UserInfo.ser");
		try{
		FileOutputStream fileStream = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fileStream);
		oos.writeObject(userList);
		oos.close();}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean usernameCheck(String username) {
		boolean uniqueName = true;
		for (User u : userList) {
			if (u.getUsername().equals(username))
				uniqueName = false;
				return uniqueName;
		}

		return uniqueName;
	}
	
	public UserManager() {
		try {
			FileInputStream fileStream = new FileInputStream(".UserInfo.ser");
			ObjectInputStream ois = new ObjectInputStream(fileStream);
			userList = (ArrayList<User>) ois.readObject();
			ois.close();
			System.out.println("User Manager Loaded");

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();

		}
	}
	
	*/
}
