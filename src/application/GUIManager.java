package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

public class GUIManager {

	private static TitledPane loginPage;
	private static TitledPane createNewUserPage;
	private static TabPane adminPage;
	private static TabPane CFMFantasyPage;
	private static Stage primaryStage;

	public void setupAndRunGUI(Stage primaryStage) {

		try {
			this.primaryStage = primaryStage;
			loginPage = FXMLLoader.load(Main.class.getResource("LoginPane.fxml"));
			createNewUserPage = FXMLLoader.load(Main.class.getResource("CreateNewUserPane.fxml"));
			//CFMFantasyPage = FXMLLoader.load(Main.class.getResource("CFMFantasyPane.fxml"));
			adminPage = FXMLLoader.load(Main.class.getResource("AdminPane.fxml"));
			Scene scene = new Scene(loginPage);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void changeToCFMScene() {
		
		Scene scene;
		try {
			scene = new Scene(FXMLLoader.load(Main.class.getResource("CFMFantasyPane.fxml")));
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void changeToLoginScene() {
		Scene scene;
		try {
			scene = new Scene(FXMLLoader.load(Main.class.getResource("LoginPane.fxml")));
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void changeToAdminScene() {
		Scene scene;
		try {
			scene = new Scene(FXMLLoader.load(Main.class.getResource("AdminPane.fxml")));
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void changeToCreateNewUserScene() {
	
		Scene scene;
		try {
			scene = new Scene(FXMLLoader.load(Main.class.getResource("CreateNewUserPane.fxml")));
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateScene(Stage stage) {

	}

}
