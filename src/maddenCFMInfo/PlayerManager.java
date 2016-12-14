package maddenCFMInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import userManagement.User;

public class PlayerManager {

	private static File file;
	private ArrayList<Player> playerList = new ArrayList();
	private float maxQBPPG = 0;
	private float maxHBPPG = 0;
	private float maxWRPPG = 0;
	private float maxTEPPG = 0;

	public PlayerManager() {

		try {
			FileInputStream fileStream = new FileInputStream(".playerData.ser");
			ObjectInputStream ois = new ObjectInputStream(fileStream);
			playerList = (ArrayList<Player>) ois.readObject();
			ois.close();
			System.out.println("Player Database Loaded");
			updateMaxPPG();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();

		}
	}

	public void savePlayerList() {

		file = new File(".playerData.ser");
		try {
			FileOutputStream fileStream = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fileStream);
			oos.writeObject(playerList);
			oos.close();
			System.out.println("Player Database saved");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// receives a player object from CSVParser class and adds it to the
	// playerList if not already in list
	public void addPlayerToList(Player playerToAdd) {
		boolean isAlreadyAdded = false;
		for (Player p : playerList) {
			if (p.getName().equals(playerToAdd.getName()) && p.getPositionAbbreviation().toUpperCase()
					.equals(playerToAdd.getPositionAbbreviation().toUpperCase())) {
				System.out.println(playerToAdd.getName() + " - " + p.getName());
				isAlreadyAdded = true;
			}
		}
		if (!isAlreadyAdded) {
			playerList.add(playerToAdd);
			System.out.println("adding " + playerToAdd.getName());

		}

	}

	public ArrayList<Object> getMaxScore() {
		float qbScore = 0, wrScore = 0, hbScore = 0, teScore = 0;
		String qbName = "", wrName= "", hbName="", teName="";
		System.out.println("Getting Max Scores");
		for (Player p : playerList) {
			if (p.getPositionAbbreviation().toLowerCase().equals("qb")) {
				for (int i = 1; i <= 17; i++) {
					if (p.getWeeklyPointsEarned(i) > qbScore){
						qbScore = p.getWeeklyPointsEarned(i);
						qbName = p.getName();
					}
				}
			}
			
			else if (p.getPositionAbbreviation().toLowerCase().equals("hb")) {
				for (int i = 1; i <= 17; i++) {
					if (p.getWeeklyPointsEarned(i) > hbScore){
						hbScore = p.getWeeklyPointsEarned(i);
					hbName = p.getName();
				}}
			}
			else if (p.getPositionAbbreviation().toLowerCase().equals("wr")) {
				for (int i = 1; i <= 17; i++) {
					if (p.getWeeklyPointsEarned(i) > wrScore){
						wrScore = p.getWeeklyPointsEarned(i);	
					wrName = p.getName();
				}}
			}
			else if (p.getPositionAbbreviation().toLowerCase().equals("te")) {
				for (int i = 1; i <= 17; i++) {
					if (p.getWeeklyPointsEarned(i) > teScore){
						teScore = p.getWeeklyPointsEarned(i);
					teName = p.getName();
				}}
			}
			
		}
		
		ArrayList<Object> temp = new ArrayList();
		temp.add(qbScore);
		temp.add(hbScore);
		temp.add(wrScore);
		temp.add(teScore);
		temp.add(qbName);
		temp.add(hbName);
		temp.add(wrName);
		temp.add(teName);

		return temp;

	}

	public void updatePassingData(String name, String team, int passingYards, int passingTD, int passingInt, int week) {
		for (Player p : playerList) {
			if (p.getName().equals(name) && p.getTeamAbbreviation().toLowerCase().equals(team.toLowerCase())) {
				p.setWeeklyPassingInfo(passingYards, passingTD, passingInt, week);
			}
		}

	}

	public void updateRushingData(String name, String team, int rushingYards, int rushingTD, int week) {

		for (Player p : playerList) {
			if (p.getName().equals(name) && p.getTeamAbbreviation().toLowerCase().equals(team.toLowerCase())) {
				p.setWeeklyRushingInfo(rushingYards, rushingTD, week);
				System.out.println("added");

			}
		}

	}

	public void updateReceivingData(String name, String team, int receivingYards, int receivingTD, int receptions,
			int week) {
		for (Player p : playerList) {
			if (p.getName().equals(name) && p.getTeamAbbreviation().toLowerCase().equals(team.toLowerCase())) {
				p.setWeeklyReceivingInfo(receivingYards, receivingTD, receptions, week);
			}
		}

	}

	public ObservableList<PlayerDataModel> getObservablePlayerList(int week) {
		ObservableList<PlayerDataModel> temp = FXCollections.observableArrayList();
		for (Player p : playerList) {
			temp.add(p.generateAndReturnPlayerDataModel(week));
		}
		return temp;
	}

	public ObservableList<PlayerSelectModel> getPlayerSelectModelPlayerList(int week) {
		ObservableList<PlayerSelectModel> temp = FXCollections.observableArrayList();
		for (Player p : playerList) {

			temp.add(p.generateAndReturnPlayerSelectModel(week));

		}

		return temp;

	}

	// -----------------------------------
	// run methods to calculate player fppg, player cost

	public void updatePlayerInfo() {
		System.out.println("Updating Player Info");
		updateMaxPPG();
		for (Player p : playerList) {
			p.calculatePPG();
			p.calculatePlayerPrice();
		}
	}

	public void updateMaxPPG() {
		for (Player p : playerList) {
			if (p.getPositionAbbreviation().toLowerCase().equals("qb")) {
				float temp = p.getPointsPerGame();
				if (temp > maxQBPPG) {
					maxQBPPG = temp;
				}
			} else if (p.getPositionAbbreviation().toLowerCase().equals("hb")) {
				float temp = p.getPointsPerGame();
				if (temp > maxHBPPG) {
					maxHBPPG = temp;
				}
			} else if (p.getPositionAbbreviation().toLowerCase().equals("wr")) {
				float temp = p.getPointsPerGame();
				if (temp > maxWRPPG) {
					maxWRPPG = temp;
				}
			} else if (p.getPositionAbbreviation().toLowerCase().equals("te")) {
				float temp = p.getPointsPerGame();
				if (temp > maxTEPPG) {
					maxTEPPG = temp;
				}
			}

		}

		System.out.println(maxQBPPG + " " + maxHBPPG + " " + maxWRPPG + " " + maxTEPPG);

	}

	public float getMaxQBPPG() {
		return maxQBPPG;
	}

	public float getMaxHBPPG() {
		return maxHBPPG;
	}

	public float getMaxWRPPG() {
		return maxWRPPG;
	}

	public float getMaxTEPPG() {
		return maxTEPPG;
	}

}
