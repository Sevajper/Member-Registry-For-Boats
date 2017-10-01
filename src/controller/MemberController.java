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
		boolean persNum;
		System.out.println("Register a new member!");
		System.out.println("enter name or input 0 to go back: ");
		String memberName = input.next();

		if (memberName.equals(Integer.toString(0))) {
			try {
				System.out.println("Returning back.");
				getInputResult();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Personnumer in the form YYMMDD-XXXX: ");
		String memberPersNum = input.next();
		if (memberPersNum.substring(6, 7).equals("-")) {
			persNum = true;
		} else {
			persNum = false;
		}
		if (persNum == true) {
			Member mem = new Member(memberName, memberPersNum);
			String memberID = mem.createID();
			mem.setId(memberID);
			System.out.println("Your member ID is " + mem.getId() + " !");
			System.out.println("");
			System.out.println("");

			rc.memberList.add(mem);
			System.out.println(rc.memberList.toString());
			goBack();

		}

		else {
			System.err.print("Error, something went wrong. Try again");
			System.out.println("");
			System.out.println("");
			registerMember(input);
		}
	}

	public void updateMember(Scanner input) {
		System.out.println("Please enter member ID or input 0 to go back: ");
		String temp = input.next();

		if (temp.equals(Integer.toString(0))) {
			goBack();
		}

		Member mem = rc.memberList.get(getMemberID(temp));
		System.out.println("Update an existing member!");

		System.out.println("New member name: ");
		String name = input.next();
		mem.setName(name);

		System.out.println("New member personnumer: ");
		String persnum = input.next();
		mem.setPersNum(persnum);

		goBack();

		// rc.memberlist.add(mem);
		// Overwriting in the arraylist ?
	}

	public void removeMember(Scanner input) {

		System.out.println("Are you sure you want to remove a member?");
		System.out.println("No = 0 , Yes = 1");
		System.out.print("Input: ");
		int text = input.nextInt();
		if (text == 0) {
			System.out.println("Returning to Member Registry");
			appStart(c);
		} else if (text == 1) {
			System.out.print("Please enter member ID: ");
		}
		String temp = input.next();
		System.out.println(rc.memberList.toString()); // Check if member exists
		Member mem = rc.memberList.get(getMemberID(temp));
		rc.memberList.remove(mem);
		System.out.println("Member removed successfully!");
		System.out.println(rc.memberList.toString());
		goBack();
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

	public void goBack() {
		try {
			System.out.print("Select next instruction: ");
			getInputResult();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
