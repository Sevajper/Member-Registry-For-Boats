package controller;

import model.Boat;
import model.Member;
import view.Console;

import java.io.IOException;
import java.util.Scanner;

public class MemberController {
	Console c = new Console();
	RegistryController rc = new RegistryController();

	public void getInputResult() throws IOException {

		Scanner input = new Scanner(System.in);

		int selection = input.nextInt();

		switch (selection) {

		case 0:
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
			updateBoat();
			break;
		case 6:
			removeBoat();
			break;

		case 7:
			c.displaySpecific();
			break;

		case 8:
			c.displayVerbose();
			break;

		case 9:
			c.displayCompact();
			break;

		case 10:
			rc.saveToRegistry();
			break;

		case 11:
			rc.loadFromRegistry();
			break;
			
		case 100:
			appStart(c);
		default:
			input.close();
			break;

		}
	}

	public void appStart(view.Console view) {
		view.displayWelcome();
		try {
			getInputResult();
		} catch (IOException e) {
			System.err.println("Please check input!");
		}
	}

	public void registerMember(Scanner input) {
		System.out.println("------------------------------------------");
		System.out.println("Register a new member! (Type 0 to go back)");
		System.out.println("");
		System.out.print("Name of new member: ");
		String memberName = input.next();
		memberName = memberName.substring(0, 1).toUpperCase() + memberName.substring(1);
		goBackOnDemand(memberName);

		System.out.print("Personal number in the form YYMMDD-XXXX: ");
		String memberPersNum = input.next();
		goBackOnDemand(memberPersNum);

		if (persNumCheck(memberPersNum)) {
			Member mem = new Member(memberName, memberPersNum);
			String memberID = mem.createID();
			mem.setId(memberID);
			rc.memberList.add(mem);
			System.out.println("");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("x Member successfully added to member registry! x");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("");
			System.out.println(rc.memberList.toString());
			goBack();

		} else {
			System.err.println("Incorrect personal number form, try again!");
			goBack();
			registerMember(input);
		}
	}

	public void updateMember(Scanner input) {
		System.out.println("------------------------------------------");
		System.out.println("Update an existing member! (Type 0 to go back)");
		System.out.println("");
		System.out.print("Please enter existing member's ID: ");
		String temp = input.next();
		temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
		goBackOnDemand(temp);

		if (rc.memberList.isEmpty()) {
			System.err.println("There are no members to update, please register a member first!");
			System.out.print("");
			goBack();
			registerMember(input);
		}
		Member mem = rc.memberList.get(getMemberID(temp));
		System.out.println("");
		System.out.print("New member name: ");
		String name = input.next();
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		goBackOnDemand(name);
		System.out.print("New member personal number in the form YYMMDD-XXXX: ");
		String persnum = input.next();
		goBackOnDemand(persnum);

		if (persNumCheck(persnum)) {
			mem.setPersNum(persnum);
		} else {
			System.err.println("Incorrect personal number form, try again!");
			goBack();
			registerMember(input);
		}

		mem.setName(name);
		System.out.println("");
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("x Member successfully updated! x");
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

		goBack();
	}

	public void removeMember(Scanner input) {
		if (rc.memberList.isEmpty()) {
			System.err.println("There are no members to remove, please register a member first!");
			System.out.print("");
			goBack();
			registerMember(input);
		}
		System.out.println("------------------------------------------");
		System.out.println("Remove a member!");
		System.out.println("");
		System.out.println("Are you sure you want to remove a member?");
		System.out.println("No = 0 , Yes = 1");
		System.out.print("Input: ");
		int text = input.nextInt();
		if (text == 0) {
			goBack();
		} else if (text == 1) {
			System.out.print("Please enter member's ID to remove member: ");
		}
		String temp = input.next();
		temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
			try {
			Member mem = rc.memberList.get(getMemberID(temp));
			rc.memberList.remove(mem);
			System.out.println("");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("x Member successfully removed :( x");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			goBack();
		}catch(Exception e) {
			System.out.println("");
			System.err.println("No member with that ID was found, try again!");
			goBack();
		}
		
	}

	public int getMemberID(String ID) {
		for (int i = 0; i < rc.memberList.size(); i++) {
			if (rc.memberList.get(i).getId().equals(ID)) {
				return i;
			}
		}
		return -1; // Should not be return = 0;
	}

	public void registerBoat(Scanner input) {
		Boat bt = new Boat();

		System.out.println("Assign boat to a member!");

		System.out.println("Name of boat: ");
		String boatName = input.next();
		bt.setName(boatName);

		System.out.println("Boat type: ");
		String boatType = input.next();
		bt.setType(boatType);

		System.out.println("Boat length: ");
		String boatLength = input.next();
		bt.setLength(boatLength);

		// Boat arraylist ?
	}

	public void updateBoat() {
		Boat bt = new Boat();
		System.out.println("Update boat information!");

		System.out.println(""); // Boat id, member id ?
								// To be continued

	}

	public void removeBoat() {
		Boat bt = new Boat();
		System.out.println("Remove a boat!");
		// Gud help us

	}

	public void updateView() {
		// No clue what this is
	}

	public boolean persNumCheck(String persNum) {

		if (persNum.length() >= 8) {
			if (persNum.substring(6, 7).equals("-") && persNum.length() == 11 && charIsDigit(persNum)) {
				return true;
			}
		}
		return false;
	}

	public boolean charIsDigit(String temp) {
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

	public void goBackOnDemand(String name) {
		if (name.equals(Integer.toString(0))) {
			try {
				System.out.println();
				System.out.println("----------------------------");
				System.out.print("Choose by typing a number: ");
				getInputResult();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void goBack() {
		try {
			System.out.println();
			System.out.println("----------------------------");
			System.out.print("Choose by typing a number: ");
			getInputResult();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
