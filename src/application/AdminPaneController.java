package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeListener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import maddenCFMInfo.CSVParser;
import maddenCFMInfo.Player;
import maddenCFMInfo.PlayerDataModel;
import maddenCFMInfo.PlayerManager;

public class AdminPaneController implements Initializable {

	@FXML
	private Tab manageCSVTab;
	
	 @FXML
	    private Button advanceWeekButton;

	    @FXML
	    private Button runCalculationButton;

	    @FXML
	    private Button lockTeamLineupButton;

	@FXML
	private ComboBox<Integer> weekSelectorForImportComboBox;

	@FXML
	private ComboBox<String> weekSelectorForStatTypeComboBox;

	@FXML
	private Button importWeeklyStatsButton;

	@FXML
	private Button importPlayerListButton;

	@FXML
	private ComboBox<Integer> weekSelectorForEditComboBox;

	@FXML
	private Button saveEditedChangesButton;

	@FXML
	private ScrollPane manageCSVScrollPane;

	@FXML
	private TableView<PlayerDataModel> manageCSVTableView;

	@FXML
	private TableColumn<PlayerDataModel, String> playerNameCol;

	@FXML
	private TableColumn<PlayerDataModel, String> positionCol;

	@FXML
	private TableColumn<PlayerDataModel, String> teamCol;

	@FXML
	private TableColumn<PlayerDataModel, Integer> overallCol;

	@FXML
	private TableColumn<PlayerDataModel, Integer> passYardsCol;

	@FXML
	private TableColumn<PlayerDataModel, Integer> passTDCol;

	@FXML
	private TableColumn<PlayerDataModel, Integer> passIntCol;

	@FXML
	private TableColumn<PlayerDataModel, Integer> rushYardsCol;

	@FXML
	private TableColumn<PlayerDataModel, Integer> rushTDCol;

	@FXML
	private TableColumn<PlayerDataModel, Integer> recYardsCol;

	@FXML
	private TableColumn<PlayerDataModel, Integer> recTDCol;

	@FXML
	private TableColumn<PlayerDataModel, Integer> receptionsCol;

	@FXML
	private TableColumn<PlayerDataModel, Integer> fumbleCol;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Integer[] weeks = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 };
		weekSelectorForEditComboBox.getItems().addAll(weeks);
		weekSelectorForImportComboBox.getItems().addAll(weeks);
		weekSelectorForStatTypeComboBox.getItems().addAll("Passing", "Rushing", "Receiving", "Defensive");
		initializeComboBoxListeners();
		initializeButtons();
		initialiazeTable();
	}

	public void initializeButtons() {
		importWeeklyStatsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String statCategory = weekSelectorForStatTypeComboBox.getValue();
				int weekForImport = weekSelectorForImportComboBox.getValue();
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV", "*.csv"));
				fileChooser.setTitle("Open CSV File");
				Window node = ((Node) event.getTarget()).getScene().getWindow();
				File file = fileChooser.showOpenDialog(node);

				if (file != null) {
					new CSVParser().readStatCSV(file, statCategory, weekForImport);
					new Main().getPlayerManager().updateMaxPPG();
					new Main().getPlayerManager().savePlayerList();
				
				}
				
				
			}
		});

		importPlayerListButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("loading list");
				new CSVParser().readPlayerListCSV();
				fillTableView(new Main().getLiveWeek());
				new Main().getPlayerManager().savePlayerList();
			}
		});
		
		runCalculationButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {				
				PlayerManager pm = new Main().getPlayerManager();
				pm.updatePlayerInfo();				
				fillTableView(new Main().getLiveWeek());
				new Main().getPlayerManager().savePlayerList();
				
			}
		});
	}

	public void initializeComboBoxListeners() {
		weekSelectorForEditComboBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int weekSelected = weekSelectorForEditComboBox.getValue();
				System.out.println(weekSelected);
				fillTableView(weekSelected);				

			}
		});

		weekSelectorForImportComboBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int weekSelected = weekSelectorForImportComboBox.getValue();
			
			}
		});	
	

	}

	public void initialiazeTable() {
		playerNameCol.setCellValueFactory(new PropertyValueFactory<PlayerDataModel, String>("playerNameCol"));
		positionCol.setCellValueFactory(new PropertyValueFactory<PlayerDataModel, String>("positionCol"));
		teamCol.setCellValueFactory(new PropertyValueFactory<PlayerDataModel, String>("teamCol"));
		overallCol.setCellValueFactory(new PropertyValueFactory<PlayerDataModel, Integer>("overallCol"));
		passYardsCol.setCellValueFactory(new PropertyValueFactory<PlayerDataModel, Integer>("passYardsCol"));
		passTDCol.setCellValueFactory(new PropertyValueFactory<PlayerDataModel, Integer>("passTDCol"));
		passIntCol.setCellValueFactory(new PropertyValueFactory<PlayerDataModel, Integer>("passIntCol"));
		rushYardsCol.setCellValueFactory(new PropertyValueFactory<PlayerDataModel, Integer>("rushYardsCol"));
		rushTDCol.setCellValueFactory(new PropertyValueFactory<PlayerDataModel, Integer>("rushTDCol"));
		recYardsCol.setCellValueFactory(new PropertyValueFactory<PlayerDataModel, Integer>("recYardsCol"));
		recTDCol.setCellValueFactory(new PropertyValueFactory<PlayerDataModel, Integer>("recTDCol"));
		receptionsCol.setCellValueFactory(new PropertyValueFactory<PlayerDataModel, Integer>("receptionsCol"));
		fumbleCol.setCellValueFactory(new PropertyValueFactory<PlayerDataModel, Integer>("fumbleCol"));

	}

	public void fillTableView(int week) {
		manageCSVTableView.getColumns().clear();
		manageCSVTableView.setItems(new Main().getPlayerManager().getObservablePlayerList(week));
		manageCSVTableView.getColumns().addAll(playerNameCol, positionCol, teamCol, overallCol, passYardsCol, passTDCol,
				passIntCol, rushYardsCol, rushTDCol, recYardsCol, recTDCol, receptionsCol, fumbleCol);
			}

}
