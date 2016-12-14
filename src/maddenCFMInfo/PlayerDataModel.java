package maddenCFMInfo;

import java.util.ArrayList;

import javafx.scene.control.cell.PropertyValueFactory;

public class PlayerDataModel {
	private String playerNameCol = ""; 	
	private String teamCol = "";
	private String positionCol = "";
	private int rushYardsCol;
	private int recYardsCol;
	private int passYardsCol ;
	private int rushTDCol ;
	private int recTDCol;
	private int passTDCol;
	private int passIntCol;
	private int fumbleCol;
	private int receptionsCol;
	private int overallCol;
	public PlayerDataModel(String playerNameCol, String teamCol, String positionCol, int rushYardsCol, int recYardsCol,
			int passYardsCol, int rushTDCol, int recTDCol, int passTDCol, int passIntCol, int fumbleCol,
			int receptionsCol, int overallCol) {
		super();
		this.playerNameCol = playerNameCol;
		this.teamCol = teamCol;
		this.positionCol = positionCol;
		this.rushYardsCol = rushYardsCol;
		this.recYardsCol = recYardsCol;
		this.passYardsCol = passYardsCol;
		this.rushTDCol = rushTDCol;
		this.recTDCol = recTDCol;
		this.passTDCol = passTDCol;
		this.passIntCol = passIntCol;
		this.fumbleCol = fumbleCol;
		this.receptionsCol = receptionsCol;
		this.overallCol = overallCol;
	}
	public String getPlayerNameCol() {
		return playerNameCol;
	}
	public void setPlayerNameCol(String playerNameCol) {
		this.playerNameCol = playerNameCol;
	}
	public String getTeamCol() {
		return teamCol;
	}
	public void setTeamCol(String teamCol) {
		this.teamCol = teamCol;
	}
	public String getPositionCol() {
		return positionCol;
	}
	public void setPositionCol(String positionCol) {
		this.positionCol = positionCol;
	}
	public int getRushYardsCol() {
		return rushYardsCol;
	}
	public void setRushYardsCol(int rushYardsCol) {
		this.rushYardsCol = rushYardsCol;
	}
	public int getRecYardsCol() {
		return recYardsCol;
	}
	public void setRecYardsCol(int recYardsCol) {
		this.recYardsCol = recYardsCol;
	}
	public int getPassYardsCol() {
		return passYardsCol;
	}
	public void setPassYardsCol(int passYardsCol) {
		this.passYardsCol = passYardsCol;
	}
	public int getRushTDCol() {
		return rushTDCol;
	}
	public void setRushTDCol(int rushTDCol) {
		this.rushTDCol = rushTDCol;
	}
	public int getRecTDCol() {
		return recTDCol;
	}
	public void setRecTDCol(int recTDCol) {
		this.recTDCol = recTDCol;
	}
	public int getPassTDCol() {
		return passTDCol;
	}
	public void setPassTDCol(int passTDCol) {
		this.passTDCol = passTDCol;
	}
	public int getPassIntCol() {
		return passIntCol;
	}
	public void setPassIntCol(int passIntCol) {
		this.passIntCol = passIntCol;
	}
	public int getFumbleCol() {
		return fumbleCol;
	}
	public void setFumbleCol(int fumbleCol) {
		this.fumbleCol = fumbleCol;
	}
	public int getReceptionsCol() {
		return receptionsCol;
	}
	public void setReceptionsCol(int receptionsCol) {
		this.receptionsCol = receptionsCol;
	}
	public int getOverallCol() {
		return overallCol;
	}
	public void setOverallCol(int overallCol) {
		this.overallCol = overallCol;
	}	

	
	

}
	
	

