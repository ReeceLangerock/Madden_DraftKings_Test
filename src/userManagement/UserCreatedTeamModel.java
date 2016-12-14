package userManagement;

import javafx.scene.control.cell.PropertyValueFactory;
import maddenCFMInfo.Player;

public class UserCreatedTeamModel {

	private String buildTabViewPosCol;
	private String buildTabViewPlayerNameCol;
	private String buildTabViewOppCol;
	private float buildTabViewFPPGCol;
	private int buildTabViewSalaryCol;

	public UserCreatedTeamModel(String buildTabViewPosCol, String buildTabViewPlayerNameCol, String buildTabViewOppCol,
			float buildTabViewFPPGCol, int buildTabViewSalaryCol) {
		super();
		this.buildTabViewPosCol = buildTabViewPosCol;
		this.buildTabViewPlayerNameCol = buildTabViewPlayerNameCol;
		this.buildTabViewOppCol = buildTabViewOppCol;
		this.buildTabViewFPPGCol = buildTabViewFPPGCol;
		this.buildTabViewSalaryCol = buildTabViewSalaryCol;
	}

	public String getBuildTabViewPosCol() {
		return buildTabViewPosCol;
	}

	public void setBuildTabViewPosCol(String buildTabViewPosCol) {
		this.buildTabViewPosCol = buildTabViewPosCol;
	}

	public String getBuildTabViewPlayerNameCol() {
		return buildTabViewPlayerNameCol;
	}

	public void setBuildTabViewPlayerNameCol(String buildTabViewPlayerNameCol) {
		this.buildTabViewPlayerNameCol = buildTabViewPlayerNameCol;
	}

	public String getBuildTabViewOppCol() {
		return buildTabViewOppCol;
	}

	public void setBuildTabViewOppCol(String buildTabViewOppCol) {
		this.buildTabViewOppCol = buildTabViewOppCol;
	}

	public float getBuildTabViewFPPGCol() {
		return buildTabViewFPPGCol;
	}

	public void setBuildTabViewFPPGCol(float buildTabViewFPPGCol) {
		this.buildTabViewFPPGCol = buildTabViewFPPGCol;
	}

	public int getBuildTabViewSalaryCol() {
		return buildTabViewSalaryCol;
	}

	public void setBuildTabViewSalaryCol(int buildTabViewSalaryCol) {
		this.buildTabViewSalaryCol = buildTabViewSalaryCol;
	}

}
