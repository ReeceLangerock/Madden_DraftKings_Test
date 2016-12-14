package userManagement;

import java.util.ArrayList;

public class UserCreatedTeamManager {
	
	
	private ArrayList<UserCreatedTeam> userCreatedTeamList = new ArrayList();

	
	public void addUserCreatedTeam(UserCreatedTeam userCreatedTeam){
		userCreatedTeamList.add(userCreatedTeam);
	}
}
