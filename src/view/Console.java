package view;

import java.util.Scanner;

import model.Member;
import model.Registry;

public class Console implements IView{	
	
	private Scanner scan = new Scanner(System.in);
	
	public void displayWelcome() {
		System.out.println("----------------------------------------");
		System.out.println(" Hello! Welcome to the Member Registry!");
		System.out.println("---------------------------------------- ");
		System.out.print("1.  Register a new member" 
				+ "\n2.  Update an existing member" 
				+ "\n3.  Remove a member"
				+ "\n4.  Register a boat" 
				+ "\n5.  Update boat" 
				+ "\n6.  Remove a boat"
				+ "\n7.  Display specific member" 
				+ "\n8.  Display verbose list" 
				+ "\n9.  Display compact list"
				+ "\n10. Exit without saving" 
				+ "\n0.  Save and Exit" + "\n" 
				+ "\n100. Display Member Registry Menu"
				+ "\n\nChoose from menu by typing a number: ");
	}
	
	/*
	 * 
	 * Menu specific outputs
	 */
	@Override
	public void displayAddMember() {
		System.out.print("------------------------------------------"
				+ "\nRegister a new member! (Type 0 to go back) \n" + "\nFirst name of new member: ");
		
	}

	@Override
	public void displayUpdateMember() {
		System.out.println("----------------------------------------------"
				+ "\nUpdate an existing member! (Type 0 to go back)\n");
		System.out.print("Please enter existing member's ID: ");
	}

	@Override
	public void displayRemoveMember() {
		System.out.println("-------------------------------------------------------------------"
				+ "\nRemove a member! \n" + "\nAre you sure you want to remove a member?\n");
		System.out.print("No = 0 , Yes = 1" + "\nInput: ");
	}
	
	@Override
	public void displayAddBoat() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayUpdateBoat() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayRemoveBoat() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayCompact(Member mem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayVerbose(Registry reg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySpecific(Registry reg) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * 
	 * Input specific 
	 */
	public String getStringInput() {
		return scan.next();
	}
	
	public int getIntInput() {
		int i = 0;
		try
		{
		  System.out.println("");
		  i = scan.nextInt();
		}
		catch(IllegalArgumentException exception)
		{

		  System.err.println("");
		}
		return i;
	}
	
	@Override
	public String getNameInput() {
		String temp = scan.next();
		return temp += scan.nextLine();
		
	}

	@Override
	public void displayLastName() {
		System.out.print("Last name of new member: "); 
		
	}

	@Override
	public void displayPersNum() {
		System.out.print("Personal number in the form YYMMDD-XXXX: "); 
	}
	
	@Override
	public void updateFirstName() {
		System.out.print("\nUpdate member first name: ");
		
	}

	@Override
	public void updateLastName() {
		System.out.print("Update member last name: ");
		
	}

	@Override
	public void updatePersNum() {
		System.out.print("New member personal number in the form YYMMDD-XXXX: ");
		
	}
	
	/*
	 * Successful operations
	 */
	public void memberAdded() {
		System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
						 + "\nx Member successfully added to member registry! x"
						 + "\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
	}

	public void memberUpdated() {
		System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" 
						 + "\nx Member successfully updated! x"
						 + "\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
	}

	public void memberRemoved() {
		System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" 
				         + "\nx Member successfully removed :( x"
				         + "\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
	}
	
	@Override
	public void boatAdded() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void boatUpdated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void boatRemoved() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void savedSuccessfully() {
		System.out.println("Saved successfully!");
		
	}
	
	/*
	 * Unsuccessful operations
	 */
	public void inputError() {	
		System.out.println("\n\t\t*** Wrong input, please choose a number between 0-10 or 100 to display menu ***");
	}

	public void persNumErr() {
		System.out.println("\n\t\t*** Incorrect personal number form or a member with the last 4 digits of the personal number already exists, try again! ***");
	}
	
	public void goBackError() {
		System.out.print("\n----------------------------"
				       + "\nType \"100\" to display menu! "
				       + "\nChoose from menu by typing a number: ");
	}

	

	@Override
	public void IDNotFoundError() {
		System.out.println("\n\t\t*** A member with that ID was not found, try again! ***");
		
	}

	@Override
	public void displayMembersID() {
		System.out.print("Please enter member's ID to remove member: ");
	}

	
	
}
