package controller;

import model.Boat;
import model.Member;
import model.Registry;
import view.Console;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.JAXBException;

public class MemberController {
	private Console c = new Console();
	private Registry memberList = new Registry();
	private File file = new File("Member_Registry.txt");
	private RegistryController rc = new RegistryController();

	public void getInputResult() throws IOException {
		Scanner input = new Scanner(System.in);
		try {
			int selection = input.nextInt();

			switch (selection) {

			case 0:
				rc.saveToRegistry(memberList, file);
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
				System.exit(0);

			case 100:
				appStart(c);

			default:

				c.inputError();
				goBack();

			}
		} catch (Exception e) {
			c.inputError();
			goBack();
		}
	}

	public void appStart(view.Console view) throws JAXBException, IOException {
		if (!file.exists()) {
			file.createNewFile();
			System.out.println("New File Created!");
		} else if (file.length() == 0) {
			view.displayWelcome();
			try {
				getInputResult();
			} catch (IOException e) {
				System.out.println("\n\t\t*** Please check input! ***");
			}
		} else {
			System.out.println(file.length());
			memberList = rc.loadFromRegistry(memberList, file);
		}

		view.displayWelcome();
		try {
			getInputResult();
		} catch (IOException e) {
			System.out.println("\n\t\t*** Please check input! ***");
		}
	}

	// Method for registering member
	public void registerMember(Scanner input) {
		System.out.print("------------------------------------------"
				+ "\nRegister a new member! (Type 0 to go back) \n" + "\nFirst name of new member: ");
		String temp = input.next(); // Input which user gives
		temp += input.nextLine();
		temp = temp.substring(0, 1).toUpperCase() + temp.substring(1); // Putting first letter to uppercase and the rest
																		// to what the user inputs.
		goBackOnDemand(temp);
		if (nameCheckDigit(temp) == false) {
			goBack();
		}

		System.out.print("Last name of new member: "); // Getting a last name for the member too
		String temp2 = input.next();
		temp2 += input.nextLine();
		temp2 = temp2.substring(0, 1).toUpperCase() + temp2.substring(1);
		goBackOnDemand(temp2);
		if (nameCheckDigit(temp2) == false) {
			goBack();
		}

		String memberName = temp + " " + temp2; // Adding the two names together to create one Full name
		goBackOnDemand(memberName);

		System.out.print("Personal number in the form YYMMDD-XXXX: "); // Asking for personal number.
		String memberPersNum = input.next();
		goBackOnDemand(memberPersNum);

		if (persNumCheck(memberPersNum)) {
			Member mem = new Member(memberName, memberPersNum);
			String memberID = mem.createID();
			mem.setId(memberID);
			memberList.addMember(mem);

			// If successful, this will be printed out
			c.memberAdded();
			System.out.println(c.printMemberArray(memberList)); 
			goBack();

		} else { // Otherwise and error will pop out
			c.persNumErr();
			goBack();
		}
	}

	public void updateMember(Scanner input) {
		System.out.println("------------------------------------------");
		System.out.println("Update an existing member! (Type 0 to go back)\n");
		System.out.print("Please enter existing member's ID: ");
		String temp = input.next();
		goBackOnDemand(temp);
		temp = temp.substring(0, 1) + temp.substring(1, 2).toUpperCase() + temp.substring(2);
		ifEmptyGoBack();
		try {
			Member mem = getMembers().get(getMemberID(temp));
			System.out.println("");

			System.out.print("Update member first name: ");
			String name = input.next();
			name += input.nextLine();
			name = name.substring(0, 1).toUpperCase() + name.substring(1);
			goBackOnDemand(name);
			if (nameCheckDigit(name) == false) {
				goBack();
			}
			System.out.print("Update member last name: ");
			String name2 = input.next();
			name2 += input.nextLine();
			goBackOnDemand(name2);
			if (nameCheckDigit(name2) == false) {
				goBack();
			}

			String realName = name + " " + name2;

			System.out.print("New member personal number in the form YYMMDD-XXXX: ");
			String persnum = input.next();
			goBackOnDemand(persnum);

			if (persNumCheck(persnum)) {
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

	public void removeMember(Scanner input) {
		ifEmptyGoBack();
		System.out.println("------------------------------------------");
		System.out.println("Remove a member! \n" + "\nAre you sure you want to remove a member?");
		System.out.println("No = 0 , Yes = 1" + "\nInput: ");
		int text = input.nextInt();
		if (text == 0) {
			goBack();
		} else if (text == 1) {
			System.out.print("Please enter member's ID to remove member: ");
		}
		String temp = input.next();
		temp = temp.substring(0, 1) + temp.substring(1, 2).toUpperCase() + temp.substring(2);
		try {
			Member mem = getMembers().get(getMemberID(temp));
			getMembers().remove(mem);
			c.memberRemoved();
			goBack();
		} catch (Exception e) {
			System.out.println("\n\t\t*** A member with that ID was not found, try again! ***");
			goBack();
		}

	}

	public void registerBoat(Scanner input) {
		Boat bt = new Boat();

		System.out.println("Register a boat to a member! (Type 0 to go back!)");

		System.out.println("Please input member ID: ");
		String id = input.next();
		goBackOnDemand(id);
		id = id.substring(0, 1) + id.substring(1, 2).toUpperCase() + id.substring(2);
		ifEmptyGoBack();
		try {
			Member mem = getMembers().get(getMemberID(id));
			System.out.println("\n\t\t*** One word name allowed! ***");
			System.out.print("Name of boat: ");
			String boatName = input.next();
			boatName = boatName.substring(0, 1).toUpperCase() + boatName.substring(1);
			goBackOnDemand(boatName);
			checkBoatName(boatName);
			System.out.println("Please choose a boat type:" + "\n1.Sailboat" + "\n2.Motorsailer" + "\n3.Kayak\\Canoe"
					+ "\n4.Other" + "\n");
			System.out.print("Input: ");
			String boatType = input.next();
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

			System.out.print("Boat length (in metres): ");
			String boatLength = input.next();
			goBackOnDemand(boatLength);
			if (checkBoatSize(boatLength) == false) {
				goBack();
			}
			bt.setLength(boatLength);
			bt.setType(boatType);
			bt.setName(boatName);
			mem.setBoat(bt);
			mem.setBoats(bt);
			c.printBoatArray(mem.getBoats());

			int i = mem.getNumOfBoats();
			i++;
			mem.setNumOfBoats(i);
			c.boatAdded();
			goBack();
		} catch (Exception e) {
			System.out.println("\n\t\t*** Input error, try again! ***");
			goBack();
		}
	}

	public void updateBoat(Scanner input) {
		System.out.println("------------------------------------------");
		System.out.println("Update an existing boat! (Type 0 to go back)\n");
		System.out.print("Please enter existing member's ID: ");
		String temp = input.next();
		temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
		goBackOnDemand(temp);
		ifEmptyGoBack();
		for (int i = 0; i < getMembers().size(); i++) { // Checking that boat registry for member is not
																	// empty
			if (getMembers().get(i).getId().equals(temp)) {
				if (getMembers().get(i).getBoats().isEmpty()) {
					System.out.print("\n\t\t*** There are no boats to remove, please register a boat first! ***");
					goBack();
				}
			}
		}
		System.out.print("Please enter existing boat's name: ");
		String eBoatName = input.next();
		eBoatName = eBoatName.substring(0, 1).toUpperCase() + eBoatName.substring(1); // Changes to capital first letter
		goBackOnDemand(temp);
		for (int i = 0; i < getMembers().size(); i++) {
			for (int j = 0; j < getMembers().get(i).getBoats().size(); j++) {
				if (getMembers().get(i).getBoats().get(j).getName().equals(eBoatName)) {
					try {
						System.out.println("Boat found!\n");
						System.out.print("Update boat name: ");
						String boatName = input.next();
						goBackOnDemand(boatName);
						checkBoatName(boatName);
						System.out.print("Please choose a new boat type:" + "\n1.Sailboat" + "\n2.Motorsailer"
								+ "\n3.Kayak\\Canoe" + "\n4.Other" + "\n");
						System.out.print("Input: ");
						String boatType = input.next();
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
						// Updating Boat Length
						System.out.print("Update boat length: ");
						String boatLength = input.next();
						goBackOnDemand(boatLength);
						if (checkBoatSize(boatLength) == false) {
							goBack();
						}
						getMembers().get(i).getBoats().get(j).setName(boatName);
						getMembers().get(i).getBoats().get(j).setType(boatType);
						getMembers().get(i).getBoats().get(j).setLength(boatLength);
						c.boatUpdated();
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

	public void removeBoat(Scanner input) {

		System.out.println("------------------------------------------");
		System.out.println("Delete an existing boat! (Type 0 to go back)");
		System.out.println("");
		System.out.print("Please enter existing member's ID: ");
		String temp = input.next();
		goBackOnDemand(temp);
		temp = temp.substring(0, 1) + temp.substring(1, 2).toUpperCase() + temp.substring(2);
		ifEmptyGoBack();
		Member mem = getMembers().get(getMemberID(temp));

		if (mem.getBoats().isEmpty()) {
			System.out.println("\n\t\t*** This member has no boats! Please register a boat to this member first! ***");
			goBack();
		}
		System.out.print("Please enter existing boat's name: ");
		String eBoatName = input.next();
		goBackOnDemand(temp);
		eBoatName = eBoatName.substring(0, 1).toUpperCase() + eBoatName.substring(1);
		for (int i = 0; i < mem.getBoats().size(); i++) {
			try {
				if (mem.getBoats().get(i).getName().equals(eBoatName)) {
					System.out.println("Boat found!");
					mem.getBoats().remove(i);
					int q = mem.getNumOfBoats();
					q--;
					mem.setNumOfBoats(q);
					c.boatRemoved();
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

	private Integer getMemberID(String ID) {
		for (int i = 0; i < getMembers().size(); i++) {
			if (getMembers().get(i).getId().equals(ID)) {
				return i;
			}
		}
		return null; // Should not be return = 0;
	}

	private boolean persNumCheck(String persNum) {
		if (persNum.length() >= 8) {
			if (persNum.substring(6, 7).equals("-") && persNum.length() == 11 && charIsDigit(persNum)) {

				return true;
			}
		}
		return false;
	}

	private boolean checkBoatSize(String name) {
		for (int i = 0; i < name.length(); i++) {
			if (!Character.isDigit(name.charAt(i))) {
				System.out.println("\n\t\t*** The length can only be a positive integer, try again! ***\n");
				return false;
			}
		}
		return true;
	}

	private boolean nameCheckDigit(String name) {
		for (int i = 0; i < name.length(); i++) {
			if (!Character.isLetter(name.charAt(i))) {
				char temp = name.charAt(i);
				if (temp == ' ') {
					String nameTemp = "\" \" ---> Whitespace";
					System.err.println("You used the character " + nameTemp);
					System.err.println("The name can only contain letters, try again! \n");
					return false;
				} else {
					System.err.println("You used the character " + "\"" + name.charAt(i) + "\"");
					System.err.println("The name can only contain letters, try again! \n");
					return false;
				}
			}
		}
		return true;
	}

	private void checkBoatName(String name) {
		for (int j = 0; j < getMembers().size(); j++) {
			for (int i = 0; i < getMembers().get(j).getBoats().size(); i++) { // Checking that the give
																							// Members boat registry is
																							// not empty.
				if (getMembers().get(j).getBoats().isEmpty()) {
					continue;
				} else if (getMembers().get(j).getBoats().get(i).getName().equals(name)) {
					System.out.print("\n\t\t*** Boat name is taken! Please choose another name. ***");
					goBack();
				}
			}
		}
	}

	private boolean charIsDigit(String temp) {
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

	private void goBackOnDemand(String name) {
		if (name.equals(Integer.toString(0))) {
			try {
				c.goBackError();
				getInputResult();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void goBack() {
		try {
			c.goBackError();
			getInputResult();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/*
	 * Method to display a verbose list of the members in the registry and their
	 * boats numbers as well as the boats descriptions
	 */
	private void displayVerbose() {
		System.out.println("=========== Displaying a verbose list of the members ===========");
		ifEmptyGoBack();
		for (int i = 0; i < getMembers().size(); i++) {
			System.out.println(c.printMember(getMembers().get(i)) +
				c.printBoatArray(getMembers().get(i).getBoats()));
		}
		goBack();
	}

	/*
	 * Method to display a compact list of the members in the registry and their
	 * boats numbers
	 */
	private void displayCompact() {
		System.out.println("=========== Displaying a compact list of the members ===========");
		ifEmptyGoBack();
		for (int i = 0; i < getMembers().size(); i++) {
			System.out.print("\nMember: " + getMembers().get(i).getName() + "\nMember ID: "
					+ getMembers().get(i).getId() + "\nNumber of Boats: "
					+ getMembers().get(i).getNumOfBoats() + "\n");
		}
		goBack();
	}

	/* Method to display a specific members information */
	private void displaySpecific(Scanner ID) {
		System.out.println("=================== Displaying specific member =================");
		ifEmptyGoBack();
		System.out.println("Please enter member ID!  (Input 0 to go back) ");
		String temp = ID.next();
		goBackOnDemand(temp);
		temp = temp.substring(0, 1) + temp.substring(1, 2).toUpperCase() + temp.substring(2);
		Member mem = memberList.getMember(temp);
		if (mem == null) {
			System.out.println("\n\t\t*** A member with that ID was not found, try again! ***");
			goBack();
		} else {
			System.out.println(c.printMember(mem) +
					c.printBoatArray(mem.getBoats()));
			goBack();
		}
	}
	
	private void ifEmptyGoBack() {
		if (getMembers().isEmpty()) {
			System.out.println("\n\t\t*** The Member Registry is currently empty. ***");
			goBack();
		}
	}

	private ArrayList<Member> getMembers() {
		return memberList.getRegistry();
	}
}