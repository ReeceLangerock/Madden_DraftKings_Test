package application;

import java.net.URL;
import java.util.ResourceBundle;

import databaseManagement.DatabaseReader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import maddenCFMInfo.CSVParser;
import userManagement.User;

public class LoginController implements Initializable {

	@FXML
	private TitledPane loginPane;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Button loginButton;

	@FXML
	private Label usernameLabel;

	@FXML
	private Label passwordLabel;

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button signUpButton;

	@FXML
	private Button forgotPasswordButton;

	@FXML
	private Label incorrectPasswordLabel;

	@Override
	public void initialize(URL arg0, ResourceBundle resources) {

		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String username = usernameField.getText();
				String password = passwordField.getText();
				User signInAttempt = new DatabaseReader().readUserFromDatabase(username);
				
				if(signInAttempt == null){
					usernameField.clear();
					passwordField.clear();
					incorrectPasswordLabel.setText("User Name does not Exist!");
		
				}
				
				boolean goodLogin = new Main().getUserManager().passwordValidation(signInAttempt, password);
				if (goodLogin) {
					if(username.equals("admin")){
						new Main().getUserManager().setCurrentUser(signInAttempt);
						new Main().getGUIManager().changeToAdminScene();}
					else{
					new Main().getUserManager().setCurrentUser(signInAttempt);
					new Main().getGUIManager().changeToCFMScene();
				}
				
				} else if (!goodLogin) {
					passwordField.clear();
					incorrectPasswordLabel.setText("Incorrect Password!");

				}
			}
		});

		signUpButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				new Main().getGUIManager().changeToCreateNewUserScene();

			}
		});
		
		forgotPasswordButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// will eventually send recovery email to user email on file
				//new DatabaseReader().readUserFromDatabase();

			}
		});

	}

}
