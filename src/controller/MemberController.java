package controller;

import model.Boat;
import model.Member;
import model.Registry;
import view.Console;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;

public class MemberController {
	Console c = new Console();
	RegistryController rc = new RegistryController();
	private Registry memberList = new Registry();
	String desktop = System.getProperty("user.home");
	File file = new File(desktop, "Member_Registry.txt");
	XMLInputFactory inputFactory = XMLInputFactory.newFactory();
	

	public void getInputResult() throws IOException, JAXBException {

		Scanner input = new Scanner(System.in);

		int selection = input.nextInt();

		switch (selection) {

		case 0:
			saveToRegistry();
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("Exiting program! See You Next Time!");
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
			displaySpecific(input);
			break;

		case 8:
			displayVerbose();
			break;

		case 9:
			displayCompact();
			break;

		case 10:
			loadFromRegistry();
			break;

		case 100:
			appStart(c);

		default:
			System.err.println("Wrong input, please choose a number between 0-11 or 100 to display menu");
			System.out.println(" ");
			goBack();

		}
	}

	public void appStart(view.Console view) throws JAXBException {
		view.displayWelcome();
		try {
			getInputResult();
		} catch (IOException e) {
			System.err.println("Please check input!");
		}
	}

	public void registerMember(Scanner input) throws JAXBException {
		System.out.println("------------------------------------------");
		System.out.println("Register a new member! (Type 0 to go back)");
		System.out.println("");
		System.out.print("First name of new member: ");
		String temp = input.next();
		temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
		goBackOnDemand(temp);
		if (nameCheck(temp) == false) {
			goBack();
		}

		System.out.print("Last name of new member: ");
		String temp2 = input.next();
		temp2 = temp2.substring(0, 1).toUpperCase() + temp2.substring(1);
		goBackOnDemand(temp2);
		if (nameCheck(temp2) == false) {
			goBack();
		}

		String memberName = temp + " " + temp2;
		goBackOnDemand(memberName);

		System.out.print("Personal number in the form YYMMDD-XXXX: ");
		String memberPersNum = input.next();
		goBackOnDemand(memberPersNum);

		if (persNumCheck(memberPersNum)) {
			Member mem = new Member(memberName, memberPersNum);
			String memberID = mem.createID();
			mem.setId(memberID);
			memberList.addMember(mem);

			System.out.println("");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("x Member successfully added to member registry! x");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("");
			System.out.println(memberList.toString());
			goBack();

		} else {
			persNumErr();
			goBack();
		}
	}

	public void updateMember(Scanner input) throws JAXBException {
		System.out.println("------------------------------------------");
		System.out.println("Update an existing member! (Type 0 to go back)");
		System.out.println("");
		System.out.print("Please enter existing member's ID: ");
		String temp = input.next();
		temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
		goBackOnDemand(temp);

		if (memberList.getRegistry().isEmpty()) {
			System.err.println("There are no members to update, please register a member first!");
			System.out.flush();
			System.err.flush();
			System.out.print("");
			goBack();
			registerMember(input);
		}
		try {
			Member mem = memberList.getRegistry().get(getMemberID(temp));
			System.out.println("");

			System.out.print("Update member first name: ");
			String name = input.next();
			name = name.substring(0, 1).toUpperCase() + name.substring(1);
			goBackOnDemand(name);

			System.out.print("Update member last name: ");
			String name2 = input.next();
			goBackOnDemand(name2);

			String realName = name + " " + name2;

			System.out.print("New member personal number in the form YYMMDD-XXXX: ");
			String persnum = input.next();
			goBackOnDemand(persnum);

			if (persNumCheck(persnum)) {
				mem.setPersNum(persnum);
			} else {
				persNumErr();
				goBack();
			}

			mem.setName(realName);
			System.out.println("");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("x Member successfully updated! x");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			goBack();

		} catch (Exception e) {
			System.err.println("A member with that ID was not found, try again!");
			System.out.flush();
			System.err.flush();
			System.out.println("");
			goBack();
		}
	}

	public void removeMember(Scanner input) throws JAXBException {
		if (memberList.getRegistry().isEmpty()) {
			System.err.println("There are no members to remove, please register a member first!");
			System.out.flush();
			System.err.flush();
			System.out.println(" ");
			goBack();
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
			Member mem = memberList.getRegistry().get(getMemberID(temp));
			memberList.getRegistry().remove(mem);
			System.out.println("");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("x Member successfully removed :( x");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			goBack();
		} catch (Exception e) {
			System.err.println("A member with that ID was not found, try again!");
			System.out.flush();
			System.err.flush();
			System.out.println(" ");
			goBack();
		}

	}

	public Integer getMemberID(String ID) {
		for (int i = 0; i < memberList.getRegistry().size(); i++) {
			if (memberList.getRegistry().get(i).getId().equals(ID)) {
				return i;
			}
		}
		return null; // Should not be return = 0;
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

	public void persNumErr() {
		System.err.println("Incorrect personal number form, try again!");
		System.out.flush();
		System.err.flush();
		System.out.println("");
	}

	public boolean persNumCheck(String persNum) {

		if (persNum.length() >= 8) {
			if (persNum.substring(6, 7).equals("-") && persNum.length() == 11 && charIsDigit(persNum)) {

				return true;
			}
		}
		return false;
	}

	public boolean nameCheck(String name) {
		for (int i = 0; i < name.length(); i++) {
			if (Character.isDigit(name.charAt(i))) {
				System.err.println("The name cannot have digits, try again!");
				System.out.println("");
				return false;
			}
		}
		return true;
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

	public void goBackOnDemand(String name) throws JAXBException {
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

	public void goBack() throws JAXBException {
		try {
			System.out.println();
			System.out.println("----------------------------");
			System.out.print("Choose by typing a number: ");
			getInputResult();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void displayVerbose() throws JAXBException {
		System.out.println("=========== Displaying a verbose list of the members ===========");
		if (memberList.getRegistry().isEmpty()) {
			System.err.println("The Member Registry is currently empty.");
			goBack();
		} else {
			memberList.getRegistry().stream().forEach(System.out::println);
			goBack();
		}
	}

	public void displayCompact() throws JAXBException {
		System.out.println("=========== Displaying a compact list of the members ===========");
		if (memberList.getRegistry().isEmpty()) {
			System.err.println("The Member Registry is currently empty.");
			goBack();
		} else {
			for (int i = 0; i < memberList.getRegistry().size(); i++) {
				System.out.print("\nMember: " + memberList.getRegistry().get(i).getName() + "\nMember ID: "
						+ memberList.getRegistry().get(i).getId() + "\nNumber of Boats: "
						+ memberList.getRegistry().get(i).getNumOfBoats() + "\n");
			}
			goBack();
		}
	}

	public void displaySpecific(Scanner ID) throws JAXBException {
		System.out.println("=================== Displaying specific member =================");
		if (memberList.getRegistry().isEmpty()) {
			System.err.println("The Member Registry is currently empty.");
			goBack();
		} else {
			System.out.println("Please enter member ID!  (Input 0 to go back) ");
			String temp = ID.next();
			temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
			goBackOnDemand(temp);
			try {
				Member mem = memberList.getMember(temp);
				System.out.println(mem);
				goBack();
			} catch (Exception e) {
				System.err.println("A member with that ID was not found, try again!");
				System.out.println("");
				goBack();
			}
		}
	}

	// Take all the members in the memberList ArrayList and put them into a file on
	// the desktop
	public void saveToRegistry() throws IOException, JAXBException {
		if (memberList.getRegistry().isEmpty()) {
			System.err.println("Sorry, you do not have any members in the Registry to Save!");
		}
		else {

			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			JAXBContext context = JAXBContext.newInstance(Registry.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			marshaller.marshal(memberList, out);

			System.out.print("Your members have successfully been Saved in C:\\Users\\(Your Name)");

		}

	}

	// Take the members from the file and load them into the ArrayList registry
	public Registry loadFromRegistry() throws FileNotFoundException {
		
		JAXBContext jaxbContext;

		try {
			jaxbContext = JAXBContext.newInstance(Registry.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			// Read file
			memberList = (Registry) jaxbUnmarshaller.unmarshal(file);
			System.out.println("Your list has been loaded!");
			goBack();
			return memberList;
		} catch (JAXBException e) {
			System.out.println("Sorry! Members could not be loaded right now.");
			e.printStackTrace();
		}
		
		
		return null;
		
		
		
	}

	// IDK what this method does yet
	public ArrayList<Member> getMembers() {
		return memberList.getRegistry();
	}
}
