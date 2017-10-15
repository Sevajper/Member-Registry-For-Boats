package controller;

import model.Boat;
import model.Member;
import model.Registry;
import view.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.bind.JAXBException;

public class MemberController {
	private Console c = new Console();
	private Registry memberList = new Registry();
	private File file = new File("Member_Registry.txt");
	private RegistryController rc = new RegistryController();

	// Take the input from the user and compare it with switch cases.
	public void getInputResult() { 
		Scanner input = new Scanner(System.in);
		try {
			int selection = input.nextInt();

			switch (selection) {
			case 0:
				rc.saveToRegistry(memberList, file);
				System.out.println("Saved successfully!");
				System.exit(0);
				break;
			case 1:
				registerMember(input);
				break;
			case 2:
				updateMember(input);
				break;
			case 3:
				removeMember(input);
				break;
			case 4:
				registerBoat(input);
				break;
			case 5:
				updateBoat(input);
				break;
			case 6:
				removeBoat(input);
				break;
			case 7:
				displaySpecific(input);
				break;
			case 8:
				displayVerbose();
				break;
			case 9:
				displayCompact();
				break;
			case 10:
				System.exit(0); //Exit application
			case 100:
				appStart(c); // Display instructions
			default:
				c.inputError(); // Handle wrong input
				goBack();
			}
		} catch (Exception e) { // Catch any unexpected errors
			c.inputError();
			goBack();
		}
	}

	// Application start > check for existing saved file > (if not) create new file > display menu > get input 
	public void appStart(view.Console view) throws JAXBException, FileNotFoundException{
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("\n\t\t*** File was not found! ***");
				e.printStackTrace();
			}
			System.out.println("New File Created!");
		} else if (file.length() == 0) {
			view.displayWelcome();
			getInputResult();
		} else {
			memberList = rc.loadFromRegistry(memberList, file);
		}
		view.displayWelcome();
		getInputResult();
	}

	// Method for registering a member > get first and last name > set ID > assign default boat number 0 > add to registry
	public void registerMember(Scanner input) {
		System.out.print("------------------------------------------"
				+ "\nRegister a new member! (Type 0 to go back) \n" + "\nFirst name of new member: ");
		String temp = input.next(); // Take input from user
		temp += input.nextLine();
		temp = temp.substring(0, 1).toUpperCase() + temp.substring(1); // Set first letter to uppercase
		goBackOnDemand(temp);
		nameCheckDigit(temp);

		System.out.print("Last name of new member: "); 
		String temp2 = input.next();
		temp2 += input.nextLine();
		temp2 = temp2.substring(0, 1).toUpperCase() + temp2.substring(1); // Set first letter to uppercase
		goBackOnDemand(temp2);
		nameCheckDigit(temp2);
		String memberName = temp + " " + temp2; // Adding the two names together to create one full name
		goBackOnDemand(memberName);

		System.out.print("Personal number in the form YYMMDD-XXXX: "); 
		String memberPersNum = input.next();
		goBackOnDemand(memberPersNum);

		if (persNumCheck(memberPersNum) && persNumVerify(memberPersNum)) { // If personal number has correct form set member and add to registry
			Member mem = new Member(memberName, memberPersNum);
			String memberID = mem.createID();
			mem.setId(memberID);
			memberList.addMember(mem);
			c.memberAdded();
			goBack();
		} else { // Otherwise an error will pop out
			c.persNumErr();
			goBack();
		}
	}

	// Update a member > get ID > change info (same data/error handling as registerMember()) > replace in registry 
	public void updateMember(Scanner input) {
		System.out.println("----------------------------------------------"
				+ "\nUpdate an existing member! (Type 0 to go back)\n");
		System.out.print("Please enter existing member's ID: ");
		String temp = input.next();
		goBackOnDemand(temp);
		temp = temp.substring(0, 1) + temp.substring(1, 2).toUpperCase() + temp.substring(2);	// Making the first Letter of the persons first name to be uppercase.
		ifEmptyGoBack();	// If the registry is empty, there are no members to update, it will print an error message and go back to the main menu.
		try {
			Member mem = getMembers().get(getMemberID(temp));
			System.out.println("");

			System.out.print("Update member first name: ");
			String name = input.next();
			name += input.nextLine();
			name = name.substring(0, 1).toUpperCase() + name.substring(1);	// set first letter to uppercase
			goBackOnDemand(name);	// If the input is a 0, then it will go back to the main menu
			nameCheckDigit(name);		// Checks that the name does not contain anything but letters
			System.out.print("Update member last name: ");
			String name2 = input.next();		// Checking and doing the same stuff with the first name to the last name.
			name2 += input.nextLine();
			name2 = name2.substring(0, 1) + name2.substring(1);
			goBackOnDemand(name2);
			nameCheckDigit(name2);
			String realName = name + " " + name2;

			System.out.print("New member personal number in the form YYMMDD-XXXX: ");
			String persnum = input.next();
			goBackOnDemand(persnum);

			if (persNumCheck(persnum) && persNumVerify(persnum)) {		// Checking that the personal number is in the correct format and that there is no other personal number like that.
				mem.setPersNum(persnum);
			} else {
				c.persNumErr();
				goBack();
			}

			mem.setName(realName);
			c.memberUpdated();
			goBack();

		} catch (Exception e) {
			System.out.println("\n\t\t*** A member with that ID was not found, try again! ***");
			goBack();
		}
	}
	
	// Remove member > check if user really wants to remove > check if empty > remove from registry
	public void removeMember(Scanner input) {
		ifEmptyGoBack();		// Can't remove any members if there are no members right?
		System.out.println("-------------------------------------------------------------------"
				+ "\nRemove a member! \n" + "\nAre you sure you want to remove a member?\n");
		System.out.print("No = 0 , Yes = 1" + "\nInput: ");
		int text = input.nextInt();		// Checking what the user input to see if they want to really remove a member or not.
		if (text == 0) {
			goBack();
		} else if (text == 1) {
			System.out.print("Please enter member's ID to remove member: ");
		}
		String temp = input.next();
		temp = temp.substring(0, 1) + temp.substring(1, 2).toUpperCase() + temp.substring(2); // Formatting the Members ID to allow leisure of writing for the user.
		try {
			Member mem = getMembers().get(getMemberID(temp));		// Getting a member with the ID value that was input.
			getMembers().remove(mem);								// Removing said member from the registry.
			c.memberRemoved();										// Giving confirmation that the Member has been removed.
			goBack();
		} catch (Exception e) {
			System.out.println("\n\t\t*** A member with that ID was not found, try again! ***");		// If a wrong ID was input, this error will show
			goBack();
		}

	}

	// Register a boat > get member ID > find member > set boat info > add boat to Member
	public void registerBoat(Scanner input) {
		System.out.print("\nRegister a boat to a member! (Type 0 to go back!)"
				+ "\nPlease input member ID: ");
		String id = input.next();
		goBackOnDemand(id);
		id = id.substring(0, 1) + id.substring(1, 2).toUpperCase() + id.substring(2); // The Member ID is taken because the boat must be connected to a certain member
		ifEmptyGoBack();		// If the registry is empty, then this will error message will show.
		try {
			Boat bt = new Boat();		// Create a new boat to have its details set and registered to a member.
			Member mem = getMembers().get(getMemberID(id));
			System.out.println("\n\t\t*** One word name allowed! ***\n");
			System.out.print("Name of boat: ");
			String boatName = input.next();	
			boatName = boatName.substring(0, 1).toUpperCase() + boatName.substring(1);		// The boats name will be first uppercase and lowercase after that.
			goBackOnDemand(boatName);		
			checkBoatName(boatName);		
			System.out.println("Please choose a boat type:" + "\n1.Sailboat" + "\n2.Motorsailer" + "\n3.Kayak\\Canoe"
					+ "\n4.Other" + "\n");
			System.out.print("Input: ");
			String boatType = input.next();		// Getting the input for type of boat the user is wishing to register.
			goBackOnDemand(boatType);	

			if (boatType.equals("1")) {			// Different kinds of boat types.
				boatType = "Sailboat";
			} else if (boatType.equals("2")) {
				boatType = "Motorsailer";
			} else if (boatType.equals("3")) {
				boatType = "Kayak\\Canoe";
			} else if (boatType.equals("4")) {
				boatType = "Other";
			} else {
				System.out.println("\n\t\t*** Input error, try again! ***");
				goBack();
			}

			System.out.print("Boat length (in metres): ");		
			String boatLength = input.next();					
			checkBoatSize(boatLength);							// Checking that the size is a positive integer and not negative or letters
			bt.setLength(boatLength);							// Setting the values of the boat to the created boat object.
			bt.setType(boatType);
			bt.setName(boatName);
			mem.setBoat(bt);									// Setting the current boat of the member to the one registered.
			mem.setBoats(bt);									// Adding the boat to the members collection.
			int numberOfBoats = mem.getNumOfBoats();
			numberOfBoats++;
			mem.setNumOfBoats(numberOfBoats);					// Increment the number of boats of the person.
			c.boatAdded();
			goBack();
		} catch (Exception e) {
			System.out.println("\n\t\t*** Input error, try again! ***");
			goBack();
		}
	}

	// Update a boat > get member by ID > check if boat exists (if boat registry is empty) > update info 
	public void updateBoat(Scanner input) {
		System.out.println("--------------------------------------------"
				+ "\nUpdate an existing boat! (Type 0 to go back)\n");
		System.out.print("Please enter existing member's ID: ");
		String temp = input.next();		// Takes a string input for member ID
		temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);	// Formats the ID so that the Letter is uppercase.
		goBackOnDemand(temp);
		ifEmptyGoBack();		// If the members registry is empty, goes back to main menu.
		for (int i = 0; i < getMembers().size(); i++) { 
			if (getMembers().get(i).getId().equals(temp)) {
				if (getMembers().get(i).getBoats().isEmpty()) {
					System.out.print("\n\t\t*** There are no boats to remove, please register a boat first! ***");
					goBack();
				}
			}
		}
		System.out.print("Please enter existing boat's name: ");
		String eBoatName = input.next();
		eBoatName = eBoatName.substring(0, 1).toUpperCase() + eBoatName.substring(1); 
		goBackOnDemand(temp);
		for (int i = 0; i < getMembers().size(); i++) {		// Nested for loop to go through each member and look through their boat collection to find the boat.
			for (int j = 0; j < getMembers().get(i).getBoats().size(); j++) {
				if (getMembers().get(i).getBoats().get(j).getName().equals(eBoatName)) {		// Checking to see if the boat name is already applied to another boat in the members collection
					try {
						System.out.print("\nBoat found!"
								+ "\nUpdate boat name: ");
						String boatName = input.next();
						goBackOnDemand(boatName);
						checkBoatName(boatName);		// input boat name
						System.out.print("Please choose a new boat type:" + "\n1.Sailboat" + "\n2.Motorsailer"
								+ "\n3.Kayak\\Canoe" + "\n4.Other" + "\n");
						System.out.print("Input: ");
						String boatType = input.next();		// Input boat type
						goBackOnDemand(boatType);

						if (boatType.equals("1")) {
							boatType = "Sailboat";
						} else if (boatType.equals("2")) {
							boatType = "Motorsailer";
						} else if (boatType.equals("3")) {
							boatType = "Kayak\\Canoe";
						} else if (boatType.equals("4")) {
							boatType = "Other";
						} else {
							System.out.println("\n\t\t*** Input error, try again! ***");
							goBack();
						}
						
						System.out.print("Update boat length (in metres): ");
						String boatLength = input.next();		// Input boat length
						goBackOnDemand(boatLength);
						checkBoatSize(boatLength);
						getMembers().get(i).getBoats().get(j).setName(boatName);
						getMembers().get(i).getBoats().get(j).setType(boatType);
						getMembers().get(i).getBoats().get(j).setLength(boatLength);
						c.boatUpdated();		// Message to show boat has been updated
						goBack();
					} catch (Exception e) {
						System.out.println("\n\t\t*** Input error, try again! ***");
						goBack();
					}
				}
			}
		}
		System.out.println("\n\t\t*** Something went wrong, try again! ***");
		goBack();

	}

	// Remove boat > get member by ID > check if boat exists > remove from registry
	public void removeBoat(Scanner input) {

		System.out.println("--------------------------------------------"
				+ "\nDelete an existing boat! (Type 0 to go back)\n");
		System.out.print("Please enter existing member's ID: ");
		String temp = input.next();			// Input for getting member ID, and for checking what member it is.
		goBackOnDemand(temp);
		temp = temp.substring(0, 1) + temp.substring(1, 2).toUpperCase() + temp.substring(2);
		ifEmptyGoBack();		// If registry is empty, go back.
		Member mem = getMembers().get(getMemberID(temp));
		if (mem.getBoats().isEmpty()) {
			System.out.println("\n\t\t*** This member has no boats! Please register a boat to this member first! ***");
			goBack();
		}
		System.out.print("Please enter existing boat's name: ");
		String eBoatName = input.next();		// Input for boat name.
		goBackOnDemand(temp);
		eBoatName = eBoatName.substring(0, 1).toUpperCase() + eBoatName.substring(1);
		for (int i = 0; i < mem.getBoats().size(); i++) {
			try {
				if (mem.getBoats().get(i).getName().equals(eBoatName)) {
					System.out.println("Boat found!");
					mem.getBoats().remove(i);			// Remove boat from member collection.
					int numOfBoats = mem.getNumOfBoats();
					numOfBoats--;
					mem.setNumOfBoats(numOfBoats);
					c.boatRemoved();					// Confirmation message that boat has been removed.
					goBack();
				} else {
					System.out.println("\n\t\t*** Sorry, no such boat found! ***");
					goBack();
				}
			} catch (Exception e) {
				System.out.println("\n\t\t*** Input error, try again! ***");
				goBack();
			}
		}
	}
	
	// Method to display a detailed list of the members in the registry 
	public void displayVerbose() {
			System.out.println("\n=========== Displaying a verbose list of the members ===========");
			ifEmptyGoBack();
			for (int i = 0; i < getMembers().size(); i++) {
				System.out.println(c.printMember(getMembers().get(i)));
				if(!getMembers().get(i).getBoats().isEmpty()) {
					System.out.println( "Boat(s) description:\n" + c.printBoatArray(getMembers().get(i).getBoats()));
				}		
			}
			goBack();
		}
		
	// Method to display a compact list of the members in the registry 
	public void displayCompact() {
			System.out.println("\n=========== Displaying a compact list of the members ===========");
			ifEmptyGoBack();
			for (int i = 0; i < getMembers().size(); i++) {
				System.out.print("\nMember: " + getMembers().get(i).getName() + "\nMember ID: "
						+ getMembers().get(i).getId() + "\nNumber of Boats: "
						+ getMembers().get(i).getNumOfBoats() + "\n");
			}
			goBack();
		}

	// Method to display a specific members information 
	public void displaySpecific(Scanner ID) {
			System.out.println("\n=================== Displaying specific member =================");
			ifEmptyGoBack();
			System.out.print("\nPlease enter member ID (Input 0 to go back): ");
			String temp = ID.next();
			goBackOnDemand(temp);
			temp = temp.substring(0, 1) + temp.substring(1, 2).toUpperCase() + temp.substring(2);
			Member mem = memberList.getMember(temp);
			if (mem == null) {
				System.out.println("\n\t\t*** A member with that ID was not found, try again! ***");
				goBack();
			} else {
				System.out.println(c.printMember(mem) 
						+ "\n\tBoat(s) description: \n"
						+ c.printBoatArray(mem.getBoats()));
				goBack();
			}
		}

	/*
	 * Private helper methods
	 */
	private Integer getMemberID(String ID) { // Return Member location in ArrayList based on ID
		for (int i = 0; i < getMembers().size(); i++) {
			if (getMembers().get(i).getId().equals(ID)) {
				return i;
			}
		}
		return null; 
	}
	
	private boolean persNumVerify(String persNum) {
		for (int i = 0; i < memberList.getRegistry().size(); i++) {
			if (persNum.equals(memberList.getRegistry().get(i).getPersNum())) {
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

	private void checkBoatSize(String length) { // Check that the boat length is a positive integer; 0 will return user to menu 
		for (int i = 0; i < length.length(); i++) {
			if (!Character.isDigit(length.charAt(i))) {
				System.out.println("\n\t\t*** The length can only be a positive integer, try again! ***\n");
				goBack();
			}
		}
	}

	private void nameCheckDigit(String name) { // Check that member name is written without special characters
		for (int i = 0; i < name.length(); i++) {
			if (!Character.isLetter(name.charAt(i))) {
				char temp = name.charAt(i);
				if (temp == ' ') {
					String nameTemp = "\" \" ---> Whitespace";
					System.out.println("\n\t\t*** You used the character " + nameTemp + " ***"
							+ "\n\t\t*** The name can only contain letters, try again! ***\n");
					goBack();
				} else {
					System.out.println("\n\t\t*** You used the character " + "\"" + name.charAt(i) + "\"" + " ***"
							+ "\n\t\t*** The name can only contain letters, try again! ***\n");
					goBack();
				}
			}
		}
	}

	private void checkBoatName(String name) { // Check so that two (or more) boats cannot have the same name
		for (int j = 0; j < getMembers().size(); j++) {
			for (int i = 0; i < getMembers().get(j).getBoats().size(); i++) { 
				if (getMembers().get(j).getBoats().isEmpty()) {
					continue;
				} else if (getMembers().get(j).getBoats().get(i).getName().equals(name)) {
					System.out.print("\n\t\t*** Boat name is taken! Please choose another name. ***");
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
				c.goBackError();
				getInputResult();
		}
	}

	private void goBack() { // Go back to main menu
			c.goBackError();
			getInputResult();
	}

	private void ifEmptyGoBack() { // Go back to main menu if the member registry is empty
		if (getMembers().isEmpty()) {
			System.out.println("\n\t\t*** The Member Registry is currently empty. ***");
			goBack();
		}
	}

	private ArrayList<Member> getMembers() { // Shortcut for getting the ArrayList from the Registry
		return memberList.getRegistry();
	}
}