package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import databaseManagement.DatabaseReader;
import databaseManagement.DatabaseWriter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import maddenCFMInfo.PlayerDataModel;
import maddenCFMInfo.PlayerManager;
import maddenCFMInfo.PlayerSelectModel;
import userManagement.User;
import userManagement.UserCreatedTeam;
import userManagement.UserCreatedTeamModel;
import userManagement.UserModel;

public class CFMFantasyPaneController implements Initializable {

	@FXML
	private Tab homeTab;

	// Build Team Tab FXML
	@FXML
	private Button buildTabQBButton;
	@FXML
	private Button buildTabRBButton;
	@FXML
	private Button buildTabWRButton;
	@FXML
	private Button buildTabTEButton;
	@FXML
	private Button FLEX;
	@FXML
	private Button buildTabDSTButton;
	@FXML
	private TextField buildTabPlayerSearchField;
	@FXML
	private TableView<UserCreatedTeamModel> buildTabViewTeamTableView;
	@FXML
	private TableColumn<UserCreatedTeamModel, String> buildTabViewPosCol;
	@FXML
	private TableColumn<UserCreatedTeamModel, String> buildTabViewPlayerNameCol;
	@FXML
	private TableColumn<UserCreatedTeamModel, String> buildTabViewOppCol;
	@FXML
	private TableColumn<UserCreatedTeamModel, Float> buildTabViewFPPGCol;
	@FXML
	private TableColumn<UserCreatedTeamModel, Integer> buildTabViewSalaryCol;
	@FXML
	private Button buildTabClearButton;
	@FXML
	private Button buildTabEnterLineupButton;
	@FXML
	private TableView<PlayerSelectModel> buildTabSelectTeamTableView;
	@FXML
	private TableColumn<PlayerSelectModel, String> buildTabSelectPosCol;
	@FXML
	private TableColumn<PlayerSelectModel, String> buildTabSelectPlayerNameCol;
	@FXML
	private TableColumn<PlayerSelectModel, String> buildTabSelectOPPCol;
	@FXML
	private TableColumn<PlayerSelectModel, Integer> buildTabSelectOPRKCol;
	@FXML
	private TableColumn<PlayerSelectModel, Float> buildTabSelectFPPGCol;
	@FXML
	private TableColumn<PlayerSelectModel, Integer> buildTabSelectSalaryCol;
	@FXML
	private Label RemSalaryLabelBuildTab; // Rem. Salary:
	@FXML
	private Label AvgRemPlayerLabelBuildTab; // Avg. Rem. / Player:

	// View League Tab FXML

	@FXML
	private TableView<UserModel> viewLeagueTabTableview;
	@FXML
	private TableColumn<UserModel, String> viewLeagueTabUserNameCol;
	@FXML
	private TableColumn<UserModel, Float> viewLeagueTabTotalPointsCol;
	@FXML
	private TableColumn<UserModel, Float> viewLeagueTabAvgPointsCol;
	@FXML
	private TableColumn<UserModel, Integer> viewLeagueTabWeeksPlayedCol;
	@FXML
	private TextArea viewLeagueTabHighestScoringTextArea;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// Initialize and manage info for Build Team Tab
		initializeSelectTable();
		initializeLineupTable();
		initializeBuildTabButtons();
		fillTableView();
		labelManager();

		// Initialize and manage info for View Team Tab
		initializeViewLeagueTable();
		initializeViewLeagueTextArea();
		
	}

	// Build Tab Methods
	// -----------------------------------------------------------------------------
	public void initializeBuildTabButtons() {
		buildTabClearButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main m = new Main();
				m.getUserManager().getCurrentUser().getWeeklyTeams().get(m.getLiveWeek()).resetTeam();
				updateViewTeamTableView();

			}
		});

		buildTabEnterLineupButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main m = new Main();
				User currentUser = m.getUserManager().getCurrentUser();
				new DatabaseWriter().saveCreatedTeamToDatabase(currentUser.getUsername(), currentUser.getWeeklyTeams());

			}
		});
	}

	public void labelManager() {
		Main m = new Main();
		m.getUserManager().getCurrentUser().userCreatedTeamUpdate();
		int currentWeek = m.getLiveWeek();
		int availableBudget = m.getUserManager().getCurrentUser().getWeeklyTeams().get(currentWeek)
				.getBudgetRemaining();
		String budgetString = String.format("%,d", availableBudget);
		RemSalaryLabelBuildTab.setText("Rem. Salary: $" + budgetString);

		int openPositions = m.getUserManager().getCurrentUser().getWeeklyTeams().get(currentWeek).getOpenPositions();
		if (openPositions == 0)
			openPositions = 1;
		int avgBudget = availableBudget / openPositions;
		String avgBudgetString = String.format("%,d", avgBudget);
		AvgRemPlayerLabelBuildTab.setText("Avg. Rem. / Player: $" + avgBudgetString);
	}

	public void initializeSelectTable() {
		buildTabSelectPosCol
				.setCellValueFactory((new PropertyValueFactory<PlayerSelectModel, String>("buildTabSelectPosCol")));
		buildTabSelectPlayerNameCol.setCellValueFactory(
				(new PropertyValueFactory<PlayerSelectModel, String>("buildTabSelectPlayerNameCol")));
		buildTabSelectOPPCol
				.setCellValueFactory((new PropertyValueFactory<PlayerSelectModel, String>("buildTabSelectOPPCol")));
		buildTabSelectOPRKCol
				.setCellValueFactory((new PropertyValueFactory<PlayerSelectModel, Integer>("buildTabSelectOPRKCol")));
		buildTabSelectFPPGCol
				.setCellValueFactory((new PropertyValueFactory<PlayerSelectModel, Float>("buildTabSelectFPPGCol")));
		buildTabSelectSalaryCol
				.setCellValueFactory((new PropertyValueFactory<PlayerSelectModel, Integer>("buildTabSelectSalaryCol")));
		buildTabSelectTeamTableView.setRowFactory(tv -> {
			TableRow<PlayerSelectModel> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					PlayerSelectModel rowData = row.getItem();

					System.out.println(rowData.getPlayer().toString());
					new Main().getUserManager().getCurrentUser().addPlayerToWeeklyTeam(rowData.getPlayer());
					labelManager();
					updateViewTeamTableView();
				}
			});
			return row;
		});

	}

	public void initializeLineupTable() {
		System.out.println("Initializing lineup table");

		buildTabViewPosCol
				.setCellValueFactory(new PropertyValueFactory<UserCreatedTeamModel, String>("buildTabViewPosCol"));
		buildTabViewPlayerNameCol.setCellValueFactory(
				new PropertyValueFactory<UserCreatedTeamModel, String>("buildTabViewPlayerNameCol"));
		buildTabViewOppCol
				.setCellValueFactory(new PropertyValueFactory<UserCreatedTeamModel, String>("buildTabViewOppCol"));
		buildTabViewFPPGCol
				.setCellValueFactory(new PropertyValueFactory<UserCreatedTeamModel, Float>("buildTabViewFPPGCol"));
		buildTabViewSalaryCol
				.setCellValueFactory(new PropertyValueFactory<UserCreatedTeamModel, Integer>("buildTabViewSalaryCol"));

		buildTabViewPosCol.setSortable(false);
		buildTabViewPlayerNameCol.setSortable(false);
		buildTabViewOppCol.setSortable(false);
		buildTabViewFPPGCol.setSortable(false);

		fillTableView();

		buildTabViewTeamTableView.setRowFactory(tv -> {
			TableRow<UserCreatedTeamModel> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					System.out.println(row.getIndex());
					new Main().getUserManager().getCurrentUser().deletePlayerFromWeeklyTeam(row.getIndex());
					labelManager();
					updateViewTeamTableView();
				}
			});
			return row;
		});
	}

	public void fillTableView() {
		buildTabSelectTeamTableView.getColumns().clear();
		buildTabSelectTeamTableView
				.setItems(new Main().getPlayerManager().getPlayerSelectModelPlayerList(new Main().getLiveWeek()));
		buildTabSelectTeamTableView.getColumns().addAll(buildTabSelectPosCol, buildTabSelectPlayerNameCol,
				buildTabSelectOPPCol, buildTabSelectOPRKCol, buildTabSelectFPPGCol, buildTabSelectSalaryCol);

		buildTabViewTeamTableView.getColumns().clear();
		buildTabViewTeamTableView.setItems(new Main().getUserManager().getCurrentUser().getWeeklyTeams()
				.get(new Main().getLiveWeek()).getUserCreatedTeamModelList());
		buildTabViewTeamTableView.getColumns().addAll(buildTabViewPosCol, buildTabViewPlayerNameCol, buildTabViewOppCol,
				buildTabViewFPPGCol, buildTabViewSalaryCol);

	}

	public void updateViewTeamTableView() {
		buildTabViewTeamTableView.getColumns().clear();
		buildTabViewTeamTableView.setItems(new Main().getUserManager().getCurrentUser().getWeeklyTeams()
				.get(new Main().getLiveWeek()).getUserCreatedTeamModelList());
		buildTabViewTeamTableView.getColumns().addAll(buildTabViewPosCol, buildTabViewPlayerNameCol, buildTabViewOppCol,
				buildTabViewFPPGCol, buildTabViewSalaryCol);
	}

	// View League Tab Methods
	// -----------------------------------------------------------------------------

	public void initializeViewLeagueTable() {	
		
		viewLeagueTabUserNameCol.setCellValueFactory((new PropertyValueFactory<UserModel, String>("viewLeagueTabUserNameCol")));
		viewLeagueTabTotalPointsCol.setCellValueFactory((new PropertyValueFactory<UserModel, Float>("viewLeagueTabTotalPointsCol")));
		viewLeagueTabAvgPointsCol.setCellValueFactory((new PropertyValueFactory<UserModel, Float>("viewLeagueTabAvgPointsCol")));
		viewLeagueTabWeeksPlayedCol.setCellValueFactory((new PropertyValueFactory<UserModel, Integer>("viewLeagueTabWeeksPlayedCol")));
		fillViewLeagueTable();
		
	}
	
	public void initializeViewLeagueTextArea(){
		float teamPoints, qbPoints, rbPoints, wrPoints, tePoints;
		teamPoints = 0;
		Main m = new Main();
		ArrayList<Object> maxScores = m.getPlayerManager().getMaxScore();
		// arraylist index is ( 1-2-3-4 = qb-hb-wr-te ) ( 4-5-6-7 = qbName, hbName, wrName, teName
		viewLeagueTabHighestScoringTextArea.appendText("Team: " + teamPoints);
		viewLeagueTabHighestScoringTextArea.appendText("\nQuarterback: "+ maxScores.get(4)+ " - "+ maxScores.get(0));
		viewLeagueTabHighestScoringTextArea.appendText("\nRunning Back: "+ maxScores.get(5)+ " - "+ maxScores.get(1));
		viewLeagueTabHighestScoringTextArea.appendText("\nWide Receiver: "+ maxScores.get(6)+ " - "+ maxScores.get(2));
		viewLeagueTabHighestScoringTextArea.appendText("\nTight End: "+ maxScores.get(7)+ " - "+ maxScores.get(3));


		
	}
	
	public void fillViewLeagueTable(){
		viewLeagueTabTableview.getColumns().clear();
		viewLeagueTabTableview.setItems(new DatabaseReader().readAllUsers());		
		viewLeagueTabTableview.getColumns().addAll(viewLeagueTabUserNameCol, viewLeagueTabTotalPointsCol,
				viewLeagueTabAvgPointsCol, viewLeagueTabWeeksPlayedCol);
		
		
		
	}

}
