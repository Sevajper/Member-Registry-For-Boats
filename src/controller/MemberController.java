package controller;

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
		int memberID = mem.createID();
		mem.setId(memberID);
		System.out.println("Your member ID is " + mem.getId() + " !");
		
		rc.memberList.add(mem);
	}
	
	public void updateMember() {
		
	}
	
	public void removeMember() {
		
	}
	
	public Member getMemberInfo(String persNum) {
		Member mem = new Member();
		
		return mem;
	}
	
	public void registerBoat() {
		
	}
	
	public void updateBoat() {
		
	}
	
	public void removeBoat() {
		
	}
	
	public void updateView() {
		
	}
}
