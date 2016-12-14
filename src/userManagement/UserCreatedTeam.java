package userManagement;

import java.io.Serializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import maddenCFMInfo.Player;
import maddenCFMInfo.PlayerSelectModel;

public class UserCreatedTeam implements Serializable {

	private String userName = "";
	private Player quarterback;
	private Player halfback1;
	private Player halfback2;
	private Player wideReceiver1;
	private Player wideReceiver2;
	private Player wideReceiver3;
	private Player tightEnd;
	private Player flex;
	private Player defense;
	private int openPositions = 8;

	private int userBudget = 50000;
	private int budgetRemaining = 50000;

	private float pointsEarned = 0;

	public void resetTeam() {
		quarterback = null;
		halfback1 = null;
		halfback2 = null;
		wideReceiver1 = null;
		wideReceiver2 = null;
		wideReceiver3 = null;
		tightEnd = null;
		flex = null;
		defense = null;
	}

	public ObservableList<UserCreatedTeamModel> getUserCreatedTeamModelList() {
		System.out.println("creating team model");
		ObservableList<UserCreatedTeamModel> temp = FXCollections.observableArrayList();

		if (quarterback != null)
			temp.add(new UserCreatedTeamModel("QB", quarterback.getName(), "Opp", quarterback.getPointsPerGame(),
					quarterback.getPlayerCost()));
		else
			temp.add(new UserCreatedTeamModel("QB", " - ", "Opp", 0, 0));
		// --------------
		if (halfback1 != null)
			temp.add(new UserCreatedTeamModel("HB", halfback1.getName(), "Opp", halfback1.getPointsPerGame(),
					halfback1.getPlayerCost()));
		else
			temp.add(new UserCreatedTeamModel("HB", " - ", "Opp", 0, 0));
		// ---------------
		if (halfback2 != null)
			temp.add(new UserCreatedTeamModel("HB", halfback2.getName(), "Opp", halfback2.getPointsPerGame(),
					halfback2.getPlayerCost()));
		else
			temp.add(new UserCreatedTeamModel("HB", " - ", "Opp", 0, 0));
		// --------------
		if (wideReceiver1 != null)
			temp.add(new UserCreatedTeamModel("WR", wideReceiver1.getName(), "Opp", wideReceiver1.getPointsPerGame(),
					wideReceiver1.getPlayerCost()));
		else
			temp.add(new UserCreatedTeamModel("WR", " - ", "Opp", 0, 0));
		// --------------
		if (wideReceiver2 != null)
			temp.add(new UserCreatedTeamModel("WR", wideReceiver2.getName(), "Opp", wideReceiver2.getPointsPerGame(),
					wideReceiver2.getPlayerCost()));
		else
			temp.add(new UserCreatedTeamModel("WR", " - ", "Opp", 0, 0));
		// --------------
		if (wideReceiver3 != null)
			temp.add(new UserCreatedTeamModel("WR", wideReceiver3.getName(), "Opp", wideReceiver3.getPointsPerGame(),
					wideReceiver3.getPlayerCost()));
		else
			temp.add(new UserCreatedTeamModel("WR", " - ", "Opp", 0, 0));
		// --------------
		if (tightEnd != null)
			temp.add(new UserCreatedTeamModel("TE", tightEnd.getName(), "Opp", tightEnd.getPointsPerGame(),
					tightEnd.getPlayerCost()));
		else
			temp.add(new UserCreatedTeamModel("TE", " - ", "Opp", 0, 0));

		if (flex != null) {
			String position = flex.getPositionAbbreviation().toUpperCase();

			temp.add(new UserCreatedTeamModel(position, flex.getName(), "Opp", flex.getPointsPerGame(),
					flex.getPlayerCost()));

		} else
			temp.add(new UserCreatedTeamModel("FLEX", " - ", "Opp", 0, 0));

		return temp;

	}

	public void calculatePointsEarned(int week) {
		int tempPoints = 0;
		if (quarterback == null) {
		} else {
			tempPoints += quarterback.getWeeklyPointsEarned(week);
		}
		if (wideReceiver1 == null) {
		} else {
			tempPoints += wideReceiver1.getWeeklyPointsEarned(week);
		}
		if (wideReceiver2 == null) {
		} else {
			tempPoints += wideReceiver2.getWeeklyPointsEarned(week);
		}
		if (wideReceiver3 == null) {
		} else {
			tempPoints += wideReceiver3.getWeeklyPointsEarned(week);
		}
		if (halfback1 == null) {
		} else {
			tempPoints += halfback1.getWeeklyPointsEarned(week);
		}
		if (halfback1 == null) {
		} else {
			tempPoints += halfback1.getWeeklyPointsEarned(week);
		}
		if (tightEnd == null) {
		} else {
			tempPoints += tightEnd.getWeeklyPointsEarned(week);
		}
		if (flex == null) {
		} else {
			tempPoints += flex.getWeeklyPointsEarned(week);
		}
		pointsEarned = tempPoints;
	}

	public void calculateBudgetRemaining() {
		int tempBudget = 0;
		if (quarterback == null) {
		} else {
			tempBudget += quarterback.getPlayerCost();
		}
		if (wideReceiver1 == null) {
		} else {
			tempBudget += wideReceiver1.getPlayerCost();
		}
		if (wideReceiver2 == null) {
		} else {
			tempBudget += wideReceiver2.getPlayerCost();
		}
		if (wideReceiver3 == null) {
		} else {
			tempBudget += wideReceiver3.getPlayerCost();
		}
		if (halfback1 == null) {
		} else {
			tempBudget += halfback1.getPlayerCost();
		}
		if (halfback1 == null) {
		} else {
			tempBudget += halfback1.getPlayerCost();
		}
		if (tightEnd == null) {
		} else {
			tempBudget += tightEnd.getPlayerCost();
		}
		if (flex == null) {
		} else {
			tempBudget += flex.getPlayerCost();
		}
		budgetRemaining = userBudget - tempBudget;

	}

	public void calculateOpenPositions() {
		int temp = 0;
		if (quarterback == null) {
			temp++;
		}
		if (wideReceiver1 == null) {
			temp++;
		}
		if (wideReceiver2 == null) {
			temp++;
		}
		if (wideReceiver3 == null) {
			temp++;
		}
		if (halfback1 == null) {
			temp++;
		}
		if (halfback2 == null) {
			temp++;
		}
		if (tightEnd == null) {
			temp++;
		}
		if (flex == null) {
			temp++;
		}
		openPositions = temp;
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Player getQuarterback() {
		return quarterback;
	}

	public void setQuarterback(Player quarterback) {
		this.quarterback = quarterback;
	}

	public Player getHalfback1() {
		return halfback1;
	}

	public void setHalfback1(Player halfback1) {
		this.halfback1 = halfback1;
	}

	public Player getHalfback2() {
		return halfback2;
	}

	public void setHalfback2(Player halfback2) {
		this.halfback2 = halfback2;
	}

	public Player getWideReceiver1() {
		return wideReceiver1;
	}

	public void setWideReceiver1(Player wideReceiver1) {
		this.wideReceiver1 = wideReceiver1;
	}

	public Player getWideReceiver2() {
		return wideReceiver2;
	}

	public void setWideReceiver2(Player wideReceiver2) {
		this.wideReceiver2 = wideReceiver2;
	}

	public Player getWideReceiver3() {
		return wideReceiver3;
	}

	public void setWideReceiver3(Player wideReceiver3) {
		this.wideReceiver3 = wideReceiver3;
	}

	public Player getFlex() {
		return flex;
	}

	public void setFlex(Player flex) {
		this.flex = flex;
	}

	public Player getDefense() {
		return defense;
	}

	public void setDefense(Player defense) {
		this.defense = defense;
	}

	public int getUserBudget() {
		return userBudget;
	}

	public void setUserBudget(int userBudget) {
		this.userBudget = userBudget;
	}

	public int getBudgetRemaining() {
		return budgetRemaining;
	}

	public void setBudgetRemaining(int budgetRemaining) {
		this.budgetRemaining = budgetRemaining;
	}

	public float getPointsEarned() {
		return pointsEarned;
	}

	public void setPointsEarned(float pointsEarned) {
		this.pointsEarned = pointsEarned;
	}

	public Player getTightEnd() {
		return tightEnd;
	}

	public void setTightEnd(Player tightEnd) {
		this.tightEnd = tightEnd;
	}

	public int getOpenPositions() {
		return openPositions;
	}

	public void setOpenPositions(int openPositions) {
		this.openPositions = openPositions;
	}
}
