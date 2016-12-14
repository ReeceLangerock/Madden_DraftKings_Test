package maddenCFMInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import application.Main;

public class CSVParser {

	public void readStatCSV(File file, String category, int week){
		if (category.equals("Passing"))
			readPassingStatCSV(file, week);
		else if (category.equals("Rushing"))
			readRushingStatCSV(file,week);
		else if (category.equals("Receiving"))
			readReceivingStatCSV(file,week);
		else if (category.equals("Defensive"))
			readDefensiveStatCSV(file,week);
		
	}
	
	public void readPlayerListCSV() {
		Scanner scanner;
		String[] playerInfo = new String[4];
		try {
			scanner = new Scanner(new File("C:/Users/snorl/Dropbox/Java/JavaFXTest/src/csvData/playerList.csv"));
			scanner.useDelimiter(",|\n");
			while (scanner.hasNext()) {
				playerInfo[0] = scanner.next(); // player name
				playerInfo[1] = scanner.next(); // player team
				playerInfo[2] = scanner.next(); // player position
				playerInfo[3] = scanner.next(); // player overall
				int overallRating = Integer.parseInt(playerInfo[3].trim());

				Player tempPlayer = new Player(playerInfo[0], playerInfo[1], playerInfo[2], overallRating);
				new Main().getPlayerManager().addPlayerToList(tempPlayer);

			}
			scanner.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void readPassingStatCSV(File file, int week) {
		Scanner scanner;
		String[] playerInfo = new String[5];
		try {
			scanner = new Scanner(file);
			scanner.useDelimiter(",|\n");
			while (scanner.hasNext()) {
				playerInfo[0] = scanner.next(); // player name
				playerInfo[1] = scanner.next(); // team abbreviation
				playerInfo[2] = scanner.next(); // passing yards
				playerInfo[3] = scanner.next(); // passing td
				playerInfo[4] = scanner.next(); // passing int
				
				int passingYards = Integer.parseInt(playerInfo[2].trim());
				int passingTD = Integer.parseInt(playerInfo[3].trim());
				int passingInt = Integer.parseInt(playerInfo[4].trim());
					
				new Main().getPlayerManager().updatePassingData(playerInfo[0], playerInfo[1],passingYards, passingTD, passingInt, week);
				

			}
			scanner.close();


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void readRushingStatCSV(File file, int week) {
		
		Scanner scanner;
		String[] playerInfo = new String[4];
		try {
			scanner = new Scanner(file);
			scanner.useDelimiter(",|\n");
			while (scanner.hasNext()) {
				playerInfo[0] = scanner.next(); // player name
				playerInfo[1] = scanner.next(); // team abbreviation
				playerInfo[2] = scanner.next(); // rushing yards
				playerInfo[3] = scanner.next(); // rushing td
				System.out.println(playerInfo[0]);
			int rushingYards = Integer.parseInt(playerInfo[2].trim());
				int rushingTD = Integer.parseInt(playerInfo[3].trim());			
				new Main().getPlayerManager().updateRushingData(playerInfo[0], playerInfo[1],rushingYards, rushingTD,  week);
				

			}
			scanner.close();


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
			
	public void readReceivingStatCSV(File file, int week) {
		Scanner scanner;
		String[] playerInfo = new String[5];
		try {
			scanner = new Scanner(file);
			scanner.useDelimiter(",|\n");
			while (scanner.hasNext()) {
				playerInfo[0] = scanner.next(); // player name
				playerInfo[1] = scanner.next(); // team abbreviation
				playerInfo[2] = scanner.next(); // receptions
				playerInfo[3] = scanner.next(); // receiving yards
				playerInfo[4] = scanner.next(); // receiving td			
				
				int receptions = Integer.parseInt(playerInfo[2].trim());				
				int receivingYards = Integer.parseInt(playerInfo[3].trim());
				int receivingTD = Integer.parseInt(playerInfo[4].trim());	
				

				new Main().getPlayerManager().updateReceivingData(playerInfo[0], playerInfo[1],receivingYards, receivingTD, receptions,  week);
			
				

			}
			scanner.close();


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void readDefensiveStatCSV(File file, int week) {}
		
	

}
