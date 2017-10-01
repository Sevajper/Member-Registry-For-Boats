package controller;

import model.Boat;
import model.Member;
import view.Console;

import java.io.IOException;
import java.util.Scanner;

public class MemberController {
	RegistryController rc = new RegistryController();
	Console c = new Console();
	
	
	public void appStart(view.Console view) {
		view.displayWelcome();
		try {
			view.getInputResult();
		} catch (IOException e) {
			System.err.println("Please check input!");
		}
	}
	
	public void registerMember(Scanner input) {
		boolean persNum;
		System.out.println("Register a new member!");
		System.out.println("Name: ");
		String memberName = input.next();
		
		System.out.println("Personnumer in the form YYMMDD-XXXX: ");
		String memberPersNum = input.next();
		if (memberPersNum.substring(6, 7).equals("-")) {
			persNum = true;
		}
		else {
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
		try {
			c.getInputResult();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
		else {
			System.out.print("Error, something went wrong. Try again");
			System.out.println("");
			System.out.println("");
			registerMember(input);
		}
	}
	
	public void updateMember(Scanner input) {
		System.out.println("Please enter member ID: ");
		String temp = input.next();
		Member mem = rc.memberList.get(getMemberID(temp));
		System.out.println("Update an existing member!");
																
		System.out.println("New member name: ");
		String name = input.next();
		mem.setName(name);
		
		System.out.println("New member personnumer: ");
		String persnum = input.next();
		mem.setPersNum(persnum);
																//rc.memberlist.add(mem);
																//Overwriting in the arraylist ?
	}
	
	public void removeMember(Scanner input) {
		Console c = new Console();
		System.out.println("Are you sure you want to remove a member?");
		System.out.println("No = 0 , Yes = 1");
		int text = input.nextInt();
		if(text == 0) {
			System.out.println("Returning to Member Registry");
			appStart(c);
		}else if(text == 1) {
			System.out.println("Please enter member ID: ");
		}
		String temp = input.next();
		Member mem = rc.memberList.get(getMemberID(temp));
																//To be continued
	}
	
	public int getMemberID(String ID) {
		for(int i = 0; i < rc.memberList.size();i++) {
			if(rc.memberList.get(i).getId().equals(ID)) {
				return i;
			}	
		}
		return -1;												//Should not be return = 0;
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
		
																 //Boat arraylist ?
	}
	
	public void updateBoat() {
		Boat bt = new Boat();
		System.out.println("Update boat information!");
		
		System.out.println("");                                   //Boat id, member id ?
																  //To be continued
		
	}
	
	public void removeBoat() {
		Boat bt = new Boat();
		System.out.println("Remove a boat!");
																	//Gud help us
		
	}
	
	public void updateView() {
																	//No clue what this is
	}
}
