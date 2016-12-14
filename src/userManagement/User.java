package userManagement;

import java.io.Serializable;
import java.util.ArrayList;

import application.Main;
import maddenCFMInfo.Player;
import maddenCFMInfo.PlayerSelectModel;

public class User implements Serializable {

	private String username;
	private String emailAddress;
	private byte[] salt; // stores user's encrypted password
	private byte[] encryptedPassword;
	private ArrayList<UserCreatedTeam> weeklyTeams = new ArrayList(17);
	
	
	
	public UserModel generateAndReturnUserModel(){
		float tempTotalPoints = 0;
		float tempAvgPoints = 0;
		Integer tempWeeksPlayed = 0;
		
		for (UserCreatedTeam t : weeklyTeams){
			tempTotalPoints += t.getPointsEarned();
			if(t.getPointsEarned() > 0)
				tempWeeksPlayed++;	
			
		}
		
		tempAvgPoints = tempTotalPoints / tempWeeksPlayed;
		
		UserModel tempModel = new UserModel(username, tempTotalPoints, tempAvgPoints, tempWeeksPlayed );
		
		
		return tempModel;		
		
	}
	
	public void userCreatedTeamUpdate(){
		for (int i = 1; i <=17; i++){
			weeklyTeams.get(i).calculateBudgetRemaining();
			weeklyTeams.get(i).calculateOpenPositions();
			weeklyTeams.get(i).calculatePointsEarned(i);

		}
	}

	public byte[] getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(byte[] encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public User(String username, String emailAddress, byte[] salt, byte[] encPassword,
			ArrayList<UserCreatedTeam> temp) {
		this.username = username;
		this.emailAddress = emailAddress;

		this.salt = salt;
		this.encryptedPassword = encPassword;
		
		for (int i = 0; i <= 17; i++) {
			weeklyTeams.add(new UserCreatedTeam());
		}

	}

	@Override
	public String toString() {
		return "User [username=" + username + ", emailAddress=" + emailAddress + "]";
	}

	public ArrayList<UserCreatedTeam> getWeeklyTeams() {
		return weeklyTeams;
	}

	public void setWeeklyTeams(ArrayList<UserCreatedTeam> weeklyTeams) {
		this.weeklyTeams = weeklyTeams;
	}
	
	public void deletePlayerFromWeeklyTeam(int playerToRemove){
		int currentWeek = new Main().getLiveWeek();
		switch(playerToRemove){
		case 0:
			weeklyTeams.get(currentWeek).setQuarterback(null);
			break;
		case 1:
			weeklyTeams.get(currentWeek).setHalfback1(null);
			break;
		case 2:
			weeklyTeams.get(currentWeek).setHalfback2(null);
			break;
		case 3:
			weeklyTeams.get(currentWeek).setWideReceiver1(null);
			break;
		case 4:
			weeklyTeams.get(currentWeek).setWideReceiver2(null);
			break;
		case 5:
			weeklyTeams.get(currentWeek).setWideReceiver3(null);
			break;
		case 6:
			weeklyTeams.get(currentWeek).setTightEnd(null);
			break;
		case 7:
			weeklyTeams.get(currentWeek).setFlex(null);
			break;
		}
		
	}
	
	public void addPlayerToWeeklyTeam(Player player) {
		System.out.println(player.getPositionAbbreviation().toUpperCase());
		int currentWeek = new Main().getLiveWeek();
		weeklyTeams.get(currentWeek).calculateBudgetRemaining();
		int currentBudget = weeklyTeams.get(currentWeek).getBudgetRemaining();
		String positionABV = player.getPositionAbbreviation().toUpperCase();
		System.out.println(positionABV);
		switch (positionABV) {

		case "QB":
			if (weeklyTeams.get(currentWeek).getQuarterback() == null && currentBudget >= player.getPlayerCost() && player.compareTo(weeklyTeams.get(currentWeek).getQuarterback()) != 0) {
				weeklyTeams.get(currentWeek).setQuarterback(player);			
			}
			break;
		case "HB":
			if (weeklyTeams.get(currentWeek).getHalfback1() == null && currentBudget >= player.getPlayerCost()&&  player.compareTo(weeklyTeams.get(currentWeek).getHalfback1()) != 0) {
				weeklyTeams.get(currentWeek).setHalfback1(player);
				System.out.println(player.getName() +" added");
				break;
			} else if (weeklyTeams.get(currentWeek).getHalfback2() == null && currentBudget >= player.getPlayerCost()&& player.compareTo(weeklyTeams.get(currentWeek).getHalfback2()) != 0) {
				weeklyTeams.get(currentWeek).setHalfback2(player);
				System.out.println(player.getName() +" added");
				break;
			} else if (weeklyTeams.get(currentWeek).getFlex() == null && currentBudget >= player.getPlayerCost()&& player.compareTo(weeklyTeams.get(currentWeek).getFlex()) != 0) {
				weeklyTeams.get(currentWeek).setFlex(player);
				System.out.println(player.getName() +" added");
				break;
			}
			break;
		case "WR":
			if (weeklyTeams.get(currentWeek).getWideReceiver1() == null && currentBudget >= player.getPlayerCost()&& player.compareTo(weeklyTeams.get(currentWeek).getWideReceiver1()) != 0) {
				weeklyTeams.get(currentWeek).setWideReceiver1(player);
				System.out.println(player.getName() +" added");
				break;
			} else if (weeklyTeams.get(currentWeek).getWideReceiver2() == null
					&& currentBudget >= player.getPlayerCost()&& player.compareTo(weeklyTeams.get(currentWeek).getWideReceiver2()) != 0) {
				weeklyTeams.get(currentWeek).setWideReceiver2(player);
				System.out.println(player.getName() +" added");
				break;
			} else if (weeklyTeams.get(currentWeek).getWideReceiver3() == null
					&& currentBudget >= player.getPlayerCost()&& player.compareTo(weeklyTeams.get(currentWeek).getWideReceiver3()) != 0) {
				weeklyTeams.get(currentWeek).setWideReceiver3(player);
				System.out.println(player.getName() +" added");
				break;
			} else if (weeklyTeams.get(currentWeek).getFlex() == null && currentBudget >= player.getPlayerCost()&& player.compareTo(weeklyTeams.get(currentWeek).getFlex()) != 0) {
				weeklyTeams.get(currentWeek).setFlex(player);
				System.out.println(player.getName() +" added");
				break;
			}
			break;
		case "TE":
			if (weeklyTeams.get(currentWeek).getTightEnd() == null && currentBudget >= player.getPlayerCost()&& player.compareTo(weeklyTeams.get(currentWeek).getTightEnd()) != 0) {
				weeklyTeams.get(currentWeek).setTightEnd(player);
				break;
			} else if (weeklyTeams.get(currentWeek).getFlex() == null && currentBudget >= player.getPlayerCost()&& player.compareTo(weeklyTeams.get(currentWeek).getFlex()) != 0) {
				weeklyTeams.get(currentWeek).setFlex(player);
				break;

			}
			break;
		default: System.out.println("all viable positions filled");
		
			

		}
	}
}
