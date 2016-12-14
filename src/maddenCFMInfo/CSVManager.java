package maddenCFMInfo;

import java.io.File;
import java.util.ArrayList;

public class CSVManager {
	
	private static ArrayList<String> weeklyStatsCSVList = new ArrayList();

	public ArrayList getCSVList(){
		return weeklyStatsCSVList;
	}
	
	public CSVManager(){
		File folder = new File("C:/Users/snorl/Dropbox/Java/JavaFXTest/src/csvData");
		File[] listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        weeklyStatsCSVList.add(listOfFiles[i].getName());
		      
		    }
	}
	}
}
