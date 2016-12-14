package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import maddenCFMInfo.CSVManager;
import maddenCFMInfo.PlayerManager;
import userManagement.UserManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	
	private static GUIManager guiManager = new GUIManager();
	private static UserManager userManager = new UserManager();
	private static PlayerManager playerManager = new PlayerManager();
	private static CSVManager csvManager = new CSVManager();
	private static int liveWeek = 1;
	
	@Override
	public void start(Stage primaryStage) {
		guiManager.setupAndRunGUI(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void updateScene(Stage stage){
		
	}
	
	public GUIManager getGUIManager(){
		return guiManager;
	}
	public UserManager getUserManager(){
		return userManager;
	}
	public CSVManager getCSVManager(){
		return csvManager;
	}
	
	public PlayerManager getPlayerManager(){
		return playerManager;
	}

	public static int getLiveWeek() {
		return liveWeek;
	}

	public static void setLiveWeek(int liveWeek) {
		Main.liveWeek = liveWeek;
	}
}
