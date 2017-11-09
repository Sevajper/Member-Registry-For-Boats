package view;

import model.Member;
import model.Registry;

public interface IView {
	
	//menu specific
	void displayWelcome();
	
	void displayAddMember();
	void displayUpdateMember();
	void displayRemoveMember();
	
	void displayAddBoat();
	void displayUpdateBoat();
	void displayRemoveBoat();
	
	void displayCompact(Member mem);
	void displayVerbose(Registry reg);
	void displaySpecific(Registry reg);
	
	//input specific
	String getStringInput();
	int getIntInput();
	String getNameInput();
	
	void displayLastName();
	void displayPersNum();
	void displayMembersID();
	
	void displayBoatName();
	void displayBoatType();
	void displayBoatLength();
	
	
	void updateFirstName();
	void updateLastName();
	void updatePersNum();
	
	void displayBoatFound();
	void findBoat();
	void update();
	//successful operations
	void memberAdded();
	void memberUpdated();
	void memberRemoved();
	
	void boatAdded();
	void boatUpdated();
	void boatRemoved();
	
	void savedSuccessfully();
	
	//errors
	void inputError(); 
	void persNumErr();
	void goBackError();
	void IDNotFoundError();
	void boatsNotFoundError();
	
}
