package view;

import model.Boat;
import model.Member;

public interface IView {
	
	//menu specific
	void displayWelcome();
	
	void displayAddMember();
	void displayUpdateMember();
	void displayRemoveMember();
	
	void displayAddBoat();
	void displayUpdateBoat();
	void displayRemoveBoat();
	
	void displayCompactTitle();
	void displayVerboseTitle();
	void displaySpecificTitle();
	
	void printBoatArray(Iterable<Boat> boatList);
	void printMember(Member mem);
	void printCompactMember(Member mem);
	
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
	void enterID();
	
	//successful operations
	void memberAdded();
	void memberUpdated();
	void memberRemoved();
	
	void boatAdded();
	void boatUpdated();
	void boatRemoved();
	void savedSuccessfully();
	void fileCreated();
	void membersLoaded();
	
	//errors
	void inputError(); 
	void persNumErr();
	void goBackError();
	void IDNotFoundError();
	void boatsNotFoundError();
	void emptyRegistry();
	void boatTaken();
	void whitespaceUsed();
	void otherCharUsed(String name, int position);
	void fileNotFound();
	void exitOnEmpty();
	
}
