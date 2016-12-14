package maddenCFMInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import application.Main;

public class Player implements Serializable, Comparable {

	private UUID playerID;
	
	// general player info pulled from DL or Madden site
	private String name = "";
	private String playerPosition = "";
	private String positionAbbreviation = "";
	private int overallRating = 0;
	private String team = "";
	private String teamAbbreviation = "";
	private String opponentTeam = "";
	private int gamesPlayed = 0;

	// ArrayList to hold weekly stats in relevant categories
	private ArrayList<Integer> rushingYards = new ArrayList(17);
	private ArrayList<Integer> receivingYards = new ArrayList(17);
	private ArrayList<Integer> passingYards = new ArrayList(17);
	private ArrayList<Integer> rushingTD = new ArrayList(17);
	private ArrayList<Integer> receivingTD = new ArrayList(17);
	private ArrayList<Integer> passingTD = new ArrayList(17);
	private ArrayList<Integer> passingInt = new ArrayList(17);
	private ArrayList<Integer> fumbles = new ArrayList(17);
	private ArrayList<Integer> receptions = new ArrayList(17);
	private ArrayList<Float> weeklyPointsEarned = new ArrayList(17);
	private float totalPointsEarned = 0;
	private float pointsPerGame = 0;
	private int weeksPlayed = 0;

	// cost of player to be calculated using player stats
	private int playerCost = 0;

	// aggregate of weekly player stats
	private int totalRushingYards = 0;
	private int totalPassingYards = 0;
	private int totalReceivingYards = 0;
	private int totalPassingTD = 0;
	private int totalPassingInt = 0;
	private int totalRushingTD = 0;
	private int totalReceivingTD = 0;
	private int totalReceptions = 0;
	private int totalFumbles = 0;

	// constructor for when player is first created
	public Player(String name, String teamAbbreviation, String positionAbbreviation, int overall) {
		playerID = UUID.randomUUID();
		// set name with info pulled from csv
		this.name = name;
		this.teamAbbreviation = teamAbbreviation;
		this.positionAbbreviation = positionAbbreviation;
		this.overallRating = overall;
		// initialize arrays to hold weekly stats
		for (int i = 0; i <= 17; i++) {
			weeklyPointsEarned.add((float) (0));
			passingYards.add(0);
			passingTD.add(0);
			passingInt.add(0);
			rushingYards.add(0);
			rushingTD.add(0);
			receivingTD.add(0);
			receivingYards.add(0);
			receptions.add(0);
			fumbles.add(0);

		}

	}

	public void calculateWeeksPlayed() {
		for (int i = 0; i <= weeklyPointsEarned.size() - 1; i++) {
			if (weeklyPointsEarned.get(i) != 0)
				weeksPlayed++;
		}
	}

	public void calculatePPG() {
		calculateWeeksPlayed();
		aggregatePointsEarned();
		if (weeksPlayed == 0)
			weeksPlayed = 1;
		pointsPerGame = totalPointsEarned / weeksPlayed;
		
	}

	// update the weekly passing stats for the player
	public void setWeeklyPassingInfo(int pYards, int pTD, int pInt, int week) {

		passingYards.set(week, pYards - totalPassingYards);
		passingTD.set(week, pTD - totalPassingTD);
		passingInt.set(week, pInt - totalPassingInt);
		calculateWeeklyPointsEarned(week);
	}

	public void setWeeklyRushingInfo(int rushingYards, int rushingTD, int week) { // dl
																					// doesn't
																					// track
																					// player
																					// fumbles
		this.rushingYards.set(week, rushingYards - totalRushingYards);
		this.rushingTD.set(week, rushingTD - totalRushingTD);
		// this.fumbles.set(week, fumbles - totalFumbles);
		calculateWeeklyPointsEarned(week);
	}

	public void setWeeklyReceivingInfo(int receivingYards, int receivingTD, int receptions, int week) {

		this.receivingYards.set(week, receivingYards - totalReceivingYards);
		this.receivingTD.set(week, receivingTD - totalReceivingTD);
		this.receptions.set(week, receptions - totalReceptions);
		calculateWeeklyPointsEarned(week);
	}

	public float getTotalPointsEarned() {
		return totalPointsEarned;
	}

	public void setTotalPointsEarned(float totalPointsEarned) {
		this.totalPointsEarned = totalPointsEarned;
	}

	public void calculatePlayerPrice() {
		float maxPPG = 0;
		switch (positionAbbreviation.toLowerCase()) {
		case "qb":
			maxPPG = new Main().getPlayerManager().getMaxQBPPG();
			break;
		case "hb":
			maxPPG = new Main().getPlayerManager().getMaxHBPPG();
			break;
		case "wr":
			maxPPG = new Main().getPlayerManager().getMaxWRPPG();
			break;
		case "te":
			maxPPG = new Main().getPlayerManager().getMaxTEPPG();
			break;

		}

		float cost = ((pointsPerGame / maxPPG) * 7000) + 3000;

		playerCost = (int) cost;
		playerCost = ((playerCost + 99) / 100) * 100;

	System.out.println(name + ": " + "Max PPG: " + maxPPG + " | cost: " + cost + " | playerCost: " + playerCost);

	}

	public void calculateWeeklyPointsEarned(int week) {

		float rushingPoints = (float) (rushingYards.get(week) * .1 + rushingTD.get(week) * 6);
		float passingPoints = (float) (passingYards.get(week) * .04 + passingTD.get(week) * 4);
		float receivingPoints = (float) (receivingYards.get(week) * .1 + receivingTD.get(week) * 6
				+ receptions.get(week));
		weeklyPointsEarned.set(week, rushingPoints + passingPoints + receivingPoints);
		aggregatePointsEarned();
	}

	public void aggregatePointsEarned() {
		for (Float f : weeklyPointsEarned) {
			totalPointsEarned += f;
		}

	}

	public PlayerDataModel generateAndReturnPlayerDataModel(int week) {
		calculatePlayerPrice();
		PlayerDataModel temp = new PlayerDataModel(name, teamAbbreviation, positionAbbreviation, rushingYards.get(week),
				receivingYards.get(week), passingYards.get(week), rushingTD.get(week), receivingTD.get(week),
				passingTD.get(week), passingInt.get(week), fumbles.get(week), receptions.get(week), overallRating);

		return temp;
	}

	public PlayerSelectModel generateAndReturnPlayerSelectModel(int week) {
		int opponentRank = 0;
		PlayerSelectModel temp = new PlayerSelectModel(positionAbbreviation, name, opponentTeam, opponentRank, pointsPerGame, playerCost, this);

		return temp;
	}

	@Override
	public String toString() {
		return "Player [name= " + name + ", playerPosition= " + positionAbbreviation + ", overallRating= " + overallRating
				+ ", team= " + teamAbbreviation + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getTotalPointsEarned1() {
		return totalPointsEarned;
	}

	public String getTeamAbbreviation() {
		return teamAbbreviation;
	}

	public void setTeamAbbreviation(String teamAbbreviation) {
		this.teamAbbreviation = teamAbbreviation;
	}

	public String getPositionAbbreviation() {
		return positionAbbreviation;
	}

	public void setPositionAbbreviation(String positionAbbreviation) {
		this.positionAbbreviation = positionAbbreviation;
	}

	public float getWeeklyPointsEarned(int week) {
		return weeklyPointsEarned.get(week);
	}

	public float getPointsPerGame() {		
		return pointsPerGame;
	}

	public void setPointsPerGame(float pointsPerGame) {
		this.pointsPerGame = pointsPerGame;
	}

	public int getPlayerCost() {
		return playerCost;
	}

	public void setPlayerCost(int playerCost) {
		this.playerCost = playerCost;
	}

	
	public int compareTo(Player otherPlayer) {
		if(otherPlayer == null)
			return 1;
		if (otherPlayer.playerID.equals(this.playerID)){
			System.out.println("same player");
			return 0;
		
		}
		else return 1;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
