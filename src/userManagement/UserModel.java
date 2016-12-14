package userManagement;

import javafx.scene.control.cell.PropertyValueFactory;

public class UserModel {

	private String viewLeagueTabUserNameCol;
	private Float viewLeagueTabTotalPointsCol;
	private Float viewLeagueTabAvgPointsCol;
	private Integer viewLeagueTabWeeksPlayedCol;
	public UserModel(String viewLeagueTabUserNameCol, Float viewLeagueTabTotalPointsCol,
			Float viewLeagueTabAvgPointsCol, Integer viewLeagueTabWeeksPlayedCol) {
		super();
		this.viewLeagueTabUserNameCol = viewLeagueTabUserNameCol;
		this.viewLeagueTabTotalPointsCol = viewLeagueTabTotalPointsCol;
		this.viewLeagueTabAvgPointsCol = viewLeagueTabAvgPointsCol;
		this.viewLeagueTabWeeksPlayedCol = viewLeagueTabWeeksPlayedCol;
	}
	public String getViewLeagueTabUserNameCol() {
		return viewLeagueTabUserNameCol;
	}
	public void setViewLeagueTabUserNameCol(String viewLeagueTabUserNameCol) {
		this.viewLeagueTabUserNameCol = viewLeagueTabUserNameCol;
	}
	public Float getViewLeagueTabTotalPointsCol() {
		return viewLeagueTabTotalPointsCol;
	}
	public void setViewLeagueTabTotalPointsCol(Float viewLeagueTabTotalPointsCol) {
		this.viewLeagueTabTotalPointsCol = viewLeagueTabTotalPointsCol;
	}
	public Float getViewLeagueTabAvgPointsCol() {
		return viewLeagueTabAvgPointsCol;
	}
	public void setViewLeagueTabAvgPointsCol(Float viewLeagueTabAvgPointsCol) {
		this.viewLeagueTabAvgPointsCol = viewLeagueTabAvgPointsCol;
	}
	public Integer getViewLeagueTabWeeksPlayedCol() {
		return viewLeagueTabWeeksPlayedCol;
	}
	public void setViewLeagueTabWeeksPlayedCol(Integer viewLeagueTabWeeksPlayedCol) {
		this.viewLeagueTabWeeksPlayedCol = viewLeagueTabWeeksPlayedCol;
	}
	
	
	
	
}
