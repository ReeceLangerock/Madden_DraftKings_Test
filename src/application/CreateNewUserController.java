package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateNewUserController implements Initializable {

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField1;

	@FXML
	private PasswordField passwordField2;

	@FXML
	private TextField emailField;

	@FXML
	private ComboBox<?> teamComboBox;

	@FXML
	private Button registerButton;

	@FXML
	private Button cancelButton;

	@Override
	public void initialize(URL arg0, ResourceBundle resources) {

		registerButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("register");
				String username = usernameField.getText();
				String password1 = passwordField1.getText();
				String password2 = passwordField2.getText();
				String email = emailField.getText();
				// String team = (String)
				// teamComboBox.getSelectionModel().getSelectedItem();
				String team = "temp";

				ArrayList errors = new Main().getUserManager().addUser(username, password1, password2, team, email);
				if (errors == null){
					new Main().getGUIManager().changeToCFMScene();
				}
			}
		});

		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("login");
				new Main().getGUIManager().changeToLoginScene();
			}
		});

	}
}
