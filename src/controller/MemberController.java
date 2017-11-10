package controller;

import model.Boat;
import model.Boat.BoatType;
import model.Member;
import model.Registry;
import view.Console;
import view.IView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;

public class MemberController {
	private Console mainView = new Console();
	private Registry memberList = new Registry();
	private File file = new File("Member_Registry.txt");
	private RegistryController rc = new RegistryController();
	ArrayList<Member> registryList = new ArrayList<Member>();
	

	// Take the input from the user and compare it with switch cases
	public void getInputResult() { 
		try {
			int selection = mainView.getIntInput();

			switch (selection) {
			case 0:
				rc.saveToRegistry(mainView, memberList, file);
				mainView.savedSuccessfully();
				System.exit(0);
				break;
			case 1:
				registerMember(mainView);
				break;
			case 2:
				updateMember(mainView);
				break;
			case 3:
				removeMember(mainView);
				break;
			case 4:
				registerBoat(mainView);
				break;
			case 5:
				updateBoat(mainView);
				break;
			case 6:
				removeBoat(mainView);
				break;
			case 7:
				displaySpecific(mainView, memberList);
				break;
			case 8:
				displayVerbose(mainView, memberList);
				break;
			case 9:
				displayCompact(mainView, memberList);
				break;
			case 10:
				System.exit(0); //Exit application
			case 100:
				appStart(mainView); // Display instructions
			default:
				mainView.inputError(); // Handle wrong input
				goBack();
			}
		} catch (Exception e) { // Catch any unexpected errors
			mainView.inputError();
			goBack();
		}
	}

	// Application start > check for existing saved file > (if not) create new file > display menu > get input 
	public void appStart(IView view) throws JAXBException, FileNotFoundException{
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				view.fileNotFound();
				e.printStackTrace();
			}
			view.fileCreated();
			
		} else if (file.length() == 0) {
			view.displayWelcome();
			getInputResult();
		} else {
			memberList = rc.loadFromRegistry(view, memberList, file);
		}
		view.displayWelcome();
		getInputResult();
	}

	// Method for registering a member > get first and last name > set ID > assign default boat number 0 > add to registry
	public void registerMember(IView view) { 
		view.displayAddMember();
		String firstName = view.getNameInput(); // Take input from user
		firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1); // Set first letter to uppercase
		goBackOnDemand(firstName);
		nameCheckDigit(firstName);

		view.displayLastName();
		String lastName = view.getNameInput();
		lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1); // Set first letter to uppercase
		goBackOnDemand(lastName);
		nameCheckDigit(lastName);
		String memberName = firstName + " " + lastName; // Adding the two names together to create one full name
		goBackOnDemand(memberName);

		view.displayPersNum();
		String memberPersNum = view.getStringInput();
		goBackOnDemand(memberPersNum);

		if (persNumCheck(memberPersNum) && persNumVerify(memberPersNum)) { // If personal number has correct form set member and add to registry
			Member mem = new Member(memberName, memberPersNum);
			String memberID = mem.createID();
			mem.setId(memberID);
			memberList.addMember(mem);
			view.memberAdded();
			goBack();
		} else { // Otherwise an error will pop out
			view.persNumErr();
			goBack();
		}
	}

	// Update a member > get ID > change info (same data/error handling as registerMember()) > replace in registry 
	public void updateMember(IView view) {
		view.displayUpdateMember();
		String temp = view.getStringInput();
		goBackOnDemand(temp);
		temp = temp.substring(0, 1) + temp.substring(1, 2).toUpperCase() + temp.substring(2);	// Making the first Letter of the persons first name to be uppercase
		ifEmptyGoBack();	// If the registry is empty, there are no members to update, it will print an error message and go back to the main menu
		try {
			Member mem = memberList.getMember(temp);
			view.updateFirstName();
			String firstName = view.getNameInput();
			firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);	// Set first letter to uppercase
			goBackOnDemand(firstName);	// If the input is a 0, then it will go back to the main menu
			nameCheckDigit(firstName);		// Checks that the name does not contain anything but letters
			view.updateLastName();
			String lastName = view.getNameInput();		// Check that last name does not contain anything but letters
			lastName = lastName.substring(0, 1) + lastName.substring(1);
			goBackOnDemand(lastName);
			nameCheckDigit(lastName);
			String memberName = lastName + " " + lastName;
			view.updatePersNum();
			String persnum = view.getStringInput();
			goBackOnDemand(persnum);

			if (persNumCheck(persnum)) {		// Checking that the personal number is in the correct format
				mem.setPersNum(persnum);
			} else {
				view.persNumErr();
				goBack();
			}

			mem.setName(memberName);
			view.memberUpdated();
			goBack();

		} catch (Exception e) {
			view.IDNotFoundError();
			goBack();
		}
	}
	
	// Remove member > check if user really wants to remove > check if empty > remove from registry
	public void removeMember(IView view) {
		ifEmptyGoBack();		// Can't remove any members if there are no members right?
		view.displayRemoveMember();
		int text = view.getIntInput();		// Checking what the user input to see if they want to really remove a member or not
		if (text == 0) {
			goBack();
		} else if (text == 1) {
			view.displayMembersID();
		}
		String temp = view.getStringInput();
		temp = temp.substring(0, 1) + temp.substring(1, 2).toUpperCase() + temp.substring(2); // Formatting the Members ID to allow leisure of writing for the user
		try {
			Member mem = memberList.getMember(temp);		// Getting a member with the ID value that was input
			memberList.removeMember(mem);								// Removing said member from the registry
			view.memberRemoved();										// Giving confirmation that the Member has been removed
			goBack();
		} catch (Exception e) {
			view.IDNotFoundError();
			goBack();
		}

	}

	// Register a boat > get member ID > find member > set boat info > add boat to Member
	public void registerBoat(IView view) {
		view.displayAddBoat();
		String id = view.getStringInput();
		goBackOnDemand(id);
		id = id.substring(0, 1) + id.substring(1, 2).toUpperCase() + id.substring(2); // The Member ID is taken because the boat must be connected to a certain member
		ifEmptyGoBack(); // If the registry is empty, then this will error message will show
		try {
			Boat bt = new Boat();		// Create a new boat to have its details set and registered to a member
			Member mem = memberList.getMember(id);
			view.displayBoatName();
			String boatName = view.getStringInput();	
			boatName = boatName.substring(0, 1).toUpperCase() + boatName.substring(1);		// The boats name will be first uppercase and lowercase after that
			goBackOnDemand(boatName);		
			checkBoatName(boatName);		
			view.displayBoatType();
			String selectBoat = view.getStringInput();		// Getting the input for boatType of boat the user is wishing to register
			goBackOnDemand(selectBoat);	
			BoatType boatType = BoatType.Other; //Default boat type is Other
			boatType = setBoatType(selectBoat, boatType, view);
			
			view.displayBoatLength();
			int boatLength = view.getIntInput();
			goBackOnDemand(Integer.toString(boatLength));
			bt.setLength(boatLength);							// Setting the values of the boat to the created boat object
			bt.setType(boatType);
			bt.setName(boatName);
			mem.setBoat(bt);									// Setting the current boat of the member to the one registered
			mem.addBoat(bt);									// Adding the boat to the members collection
		
			int numberOfBoats = mem.getNumOfBoats();
			numberOfBoats++;
			mem.setNumOfBoats(numberOfBoats);					// Increment the number of boats of the person
			view.boatAdded(); 
			goBack();
			
		} catch (Exception e) {
			view.inputError();
			goBack();
		}
	}

	// Update a boat > get member by ID > check if boat exists (if boat registry is empty) > update info 
	public void updateBoat(IView view) {
		view.displayUpdateBoat();
		String temp = view.getStringInput();	// Takes a string input for member ID
		temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);	// Formats the ID so that the Letter is uppercase
		goBackOnDemand(temp);
		ifEmptyGoBack();		// If the members registry is empty, goes back to main menu
		for (Member m : memberList.returnMemberList()) { 
			if (m.getId().equals(temp)) {
				if (m.checkIfBoatsEmpty()) {
					view.boatsNotFoundError();
					goBack();
				}
			}
		}
		view.findBoat();
		String eBoatName = view.getStringInput();
		eBoatName = eBoatName.substring(0, 1).toUpperCase() + eBoatName.substring(1); 
		goBackOnDemand(temp);
		for (Member m : memberList.returnMemberList()) {		// Nested for loop to go through each member and look through their boat collection to find the boat
			for (Boat b : m.getBoats()) {
				if (b.getName().equals(eBoatName)) {		// Checking to see if the boat name is already applied to another boat in the members collection
					try {
						view.displayBoatFound();
						String boatName = view.getStringInput();
						goBackOnDemand(boatName);
						checkBoatName(boatName);		// input boat name
						view.update(); 
						view.displayBoatType();
						String selectBoat = view.getStringInput();		// Getting the input for boatType of boat 
						goBackOnDemand(selectBoat);	
						BoatType boatType = BoatType.Other;
						boatType = setBoatType(selectBoat, boatType, view);
						view.update();
						view.displayBoatLength();
						int boatLength = view.getIntInput();		// Input boat length
						goBackOnDemand(Integer.toString(boatLength));                           
						m.getBoat().setName(boatName);
						m.getBoat().setType(boatType);
						m.getBoat().setLength(boatLength);
						view.boatUpdated();		// Message to show boat has been updated
						goBack();
					} catch (Exception e) {
						view.inputError();
						goBack();
					}
				}
			}
		}
		view.inputError();
		goBack();

	}

	// Remove boat > get member by ID > check if boat exists > remove from registry
	public void removeBoat(IView view) {
		view.displayRemoveBoat();
		String temp = view.getStringInput();		// Input for getting member ID, and for checking what member it is
		goBackOnDemand(temp);
		temp = temp.substring(0, 1) + temp.substring(1, 2).toUpperCase() + temp.substring(2);
		ifEmptyGoBack();		// If registry is empty, go back
		Member mem = memberList.getMember(temp);
		if (mem.checkIfBoatsEmpty()) {
			view.boatsNotFoundError();
			goBack();
		}
		view.findBoat();
		String eBoatName = view.getStringInput();		// Input for boat name
		goBackOnDemand(eBoatName); 
		eBoatName = eBoatName.substring(0, 1).toUpperCase() + eBoatName.substring(1);
		for (Boat b : mem.getBoats()) {
			try {
				if (b.getName().equals(eBoatName)) {
					mem.removeBoat(b);;			// Remove boat from member collection
					int numOfBoats = mem.getNumOfBoats();
					numOfBoats--;
					mem.setNumOfBoats(numOfBoats);
					view.boatRemoved();					
					goBack();
				} else {
					view.inputError();
					goBack();
				}
			} catch (Exception e) {
				view.inputError();
				goBack();
			}
		}
	}
	
	// Method to display a detailed list of the members in the registry 
	public void displayVerbose(IView view, Registry reg) {
			view.displayVerboseTitle();
			ifEmptyGoBack();
			for (Member m : reg.returnMemberList()) {
				view.printMember(m);
				if(!m.checkIfBoatsEmpty()) {
					view.printBoatArray(m.getBoats());
				}		
			}
			goBack();
		}
		
	// Method to display a compact list of the members in the registry 
	public void displayCompact(IView view, Registry reg) {
			view.displayCompactTitle();
			ifEmptyGoBack();
			for (Member m : reg.returnMemberList()) {
				view.printCompactMember(m);
			}
			goBack();
		}

	// Method to display a specific members information 
	public void displaySpecific(IView view, Registry reg) {
			view.displaySpecificTitle();
			ifEmptyGoBack();
			view.enterID();
			String temp = view.getStringInput();
			goBackOnDemand(temp);
			temp = temp.substring(0, 1) + temp.substring(1, 2).toUpperCase() + temp.substring(2);
			Member mem = reg.getMember(temp);
			if (mem == null) {
				view.IDNotFoundError();
				goBack();
			} else {
				view.printMember(mem);
				view.printBoatArray(mem.getBoats());
				goBack();
			}
		}
	
	/*
	 * Private helper methods (mainly validation)
	 */
	private boolean persNumVerify(String persNum) {
		for (Member m : memberList.returnMemberList()) {
			if (persNum.equals(m.getPersNum())) {
				return false;
			}
		}
		return true;
	}

	private boolean persNumCheck(String persNum) { // Check form of personal number > accepted form "YYMMDD-XXXX"
		if (persNum.length() >= 8) {
			if (persNum.substring(6, 7).equals("-") && persNum.length() == 11 && charIsDigit(persNum)) {
				return true;
			}
		}
		return false;
	}

	private void nameCheckDigit(String name) { // Check that member name is written without special characters
		for (int i = 0; i < name.length(); i++) {
			if (!Character.isLetter(name.charAt(i))) {
				char temp = name.charAt(i);
				if (temp == ' ') {
					mainView.whitespaceUsed();
					goBack();
				} else {
					mainView.otherCharUsed(name, i);
					goBack();
				}
			}
		}
	}

	private void checkBoatName(String name) { // Check so that two (or more) boats cannot have the same name
		for (Member m : memberList.returnMemberList()) {
			for (Boat b : m.getBoats()) { 
				if (m.checkIfBoatsEmpty()) {
					continue;
				} else if (b.getName().equals(name)) {
					mainView.boatTaken();
					goBack();
				}
			}
		}
	}

	private boolean charIsDigit(String temp) { // Helper method for nameCheckDigit() - checks that input is integers
		for (int i = 0; i < 6; i++) {
			if (!Character.isDigit(temp.charAt(i))) {
				return false;
			}
		}
		for (int j = 7; j < 11; j++) {
			if (!Character.isDigit(temp.charAt(j))) {
				return false;
			}
		}
		return true;
	}

	private void goBackOnDemand(String name) { // Go back to main menu if user wants to (by inputing 0)
		if (name.equals(Integer.toString(0))) {
				mainView.goBackError();
				getInputResult();
		}
	}

	private void goBack() { // Go back to main menu
		mainView.goBackError();
		getInputResult();
	}

	private void ifEmptyGoBack() { // Go back to main menu if the member registry is empty
		if (memberList.checkIfEmpty()) {
			mainView.emptyRegistry();
			goBack();
		}
	}

	private BoatType setBoatType(String input, BoatType boat, IView view) {
		if (input.equals("1")) {			// Different kinds of boat boatTypes
			boat = BoatType.Sailboat;
		} else if (input.equals("2")) {
			boat = BoatType.Motorsailer;
		} else if (input.equals("3")) {
			boat = BoatType.Canoe;
		} else if (input.equals("4")) {
			boat = BoatType.Other;
		} else {
			view.inputError();
			goBack();
		}
		return boat;
	}
}