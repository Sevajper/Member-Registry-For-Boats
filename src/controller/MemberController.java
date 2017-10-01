package controller;

import model.Boat;
import model.Member;
import java.util.Scanner;

public class MemberController {
	RegistryController rc = new RegistryController();

	public void registerMember(Scanner input) {
		System.out.println("Register a new member!");
		System.out.println("Name: ");
		String memberName = input.next();
		
		System.out.println("Personnumer: ");
		String memberPersNum = input.next();
		
		Member mem = new Member(memberName, memberPersNum);
		String memberID = mem.createID();
		mem.setId(memberID);
		System.out.println("Your member ID is " + mem.getId() + " !");
		
		rc.memberList.add(mem);
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
																//Gud help us
		
	}
	
	public void removeMember(Scanner input) {
		Member mem = new Member();
		System.out.println("Remove a member!");
		
		System.out.println("Member id: ");
		String memRemove = input.next();
																//To be continued
	}
	
	public int getMemberID(String ID) {
		for(int i = 0; i < rc.memberList.size();i++) {
			if(rc.memberList.get(i).getId().equals(ID)) {
				return i;
			}	
		}
		return 0;												//Should not be return = 0;
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
