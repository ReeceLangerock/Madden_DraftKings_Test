package maddenCFMInfo;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class PlayerSelectModel {
	private String buildTabSelectPosCol;
	private String buildTabSelectPlayerNameCol;
	private String buildTabSelectOPPCol;
	private int buildTabSelectOPRKCol;
	private float buildTabSelectFPPGCol;
	private int buildTabSelectSalaryCol;
	Player player;
	
	
	public PlayerSelectModel(String buildTabSelectPosCol, String buildTabSelectPlayerNameCol,
			String buildTabSelectOPPCol, int buildTabSelectOPRKCol, float buildTabSelectFPPGCol,
			int buildTabSelectSalaryCol, Player player) {
		super();
		this.buildTabSelectPosCol = buildTabSelectPosCol;
		this.buildTabSelectPlayerNameCol = buildTabSelectPlayerNameCol;
		this.buildTabSelectOPPCol = buildTabSelectOPPCol;
		this.buildTabSelectOPRKCol = buildTabSelectOPRKCol;
		this.buildTabSelectFPPGCol = buildTabSelectFPPGCol;
		this.buildTabSelectSalaryCol = buildTabSelectSalaryCol;
		this.player = player;
	}
	public String getBuildTabSelectPosCol() {
		return buildTabSelectPosCol;
	}
	public void setBuildTabSelectPosCol(String buildTabSelectPosCol) {
		this.buildTabSelectPosCol = buildTabSelectPosCol;
	}
	public String getBuildTabSelectPlayerNameCol() {
		return buildTabSelectPlayerNameCol;
	}
	public void setBuildTabSelectPlayerNameCol(String buildTabSelectPlayerNameCol) {
		this.buildTabSelectPlayerNameCol = buildTabSelectPlayerNameCol;
	}
	public String getBuildTabSelectOPPCol() {
		return buildTabSelectOPPCol;
	}
	public void setBuildTabSelectOPPCol(String buildTabSelectOPPCol) {
		this.buildTabSelectOPPCol = buildTabSelectOPPCol;
	}
	public int getBuildTabSelectOPRKCol() {
		return buildTabSelectOPRKCol;
	}
	public void setBuildTabSelectOPRKCol(int buildTabSelectOPRKCol) {
		this.buildTabSelectOPRKCol = buildTabSelectOPRKCol;
	}
	public float getBuildTabSelectFPPGCol() {
		return buildTabSelectFPPGCol;
	}
	public void setBuildTabSelectFPPGCol(float buildTabSelectFPPGCol) {
		this.buildTabSelectFPPGCol = buildTabSelectFPPGCol;
	}
	public int getBuildTabSelectSalaryCol() {
		return buildTabSelectSalaryCol;
	}
	public void setBuildTabSelectSalaryCol(int buildTabSelectSalaryCol) {
		this.buildTabSelectSalaryCol = buildTabSelectSalaryCol;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
	
	}
	


	