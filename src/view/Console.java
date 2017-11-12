package view;

import java.util.Scanner;

import model.Boat;
import model.Member;

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
		System.out.print("\nRegister a boat to a member! (Type 0 to go back!)"
				+ "\nPlease input member ID: ");
	}

	@Override
	public void displayUpdateBoat() {
		System.out.println("--------------------------------------------"
				+ "\nUpdate an existing boat! (Type 0 to go back)\n");
		System.out.print("Please enter existing member's ID: ");
	}

	@Override
	public void displayRemoveBoat() {
		System.out.println("--------------------------------------------"
				+ "\nDelete an existing boat! (Type 0 to go back)\n");
		System.out.print("Please enter existing member's ID: ");
	}

	@Override
	public void displayCompactTitle() {
		System.out.println("\n=========== Displaying a compact list of the members ===========");
	}

	@Override
	public void displayVerboseTitle() {
		System.out.println("\n=========== Displaying a verbose list of the members ===========");
	}

	@Override
	public void displaySpecificTitle() {
		System.out.println("\n=================== Displaying specific member =================");
	}
	
	@Override
	public void printBoatArray(Iterable<Boat> boatList) {
		StringBuilder list = new StringBuilder();
		System.out.println( "Boat(s) description:");
		for(Boat b : boatList) {
			list.append("\nName: " + b.getName()
					+ "\nBoat type: " + b.getType()
					+ "\nBoat length (meters): " + b.getLength()
	                +"\n" + "----------------------------");
		}	
		System.out.println(list.toString());
	}

	@Override
	public void printMember(Member member) {
		StringBuilder list = new StringBuilder();
		list.append("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
				+"\nMember: "  + member.getName() + " "
				+ "\nMember ID: " + member.getId()
	            + " " + "\nPersonal Number: " + member.getPersNum()
	            + " " + "\nNumber of Boats: " + member.getNumOfBoats()
	            + "\n" + "----------------------------");	
		System.out.println(list.toString());
	}
	
	@Override
	public void printCompactMember(Member member) {
		StringBuilder list = new StringBuilder();
		list.append("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
				+"\nMember: "  + member.getName() + " "
				+ "\nMember ID: " + member.getId()
	            + " " + "\nNumber of Boats: " + member.getNumOfBoats()
	            + "\n" + "----------------------------");	
		System.out.println(list.toString());
	}
	
	/*
	 * Input specific 
	 */
	@Override
	public String getStringInput() {
		return scan.next();
	}
	
	@Override
	public int getIntInput() {
		int i = 1000;
		do {
		    while (!scan.hasNextInt()) {
		        scan.next(); 
		        input();
		    }
		    i = scan.nextInt();
		} while (i < 0);
		return i;
	}
	
	@Override
	public int getBoatLength(){
		 String character = scan.next();
		 for(int i = 0; i < character.length();i++) {
			 if(!Character.isDigit(character.charAt(i))) {
				 return 1010101010;
		  	}
		 }
		  int result = Integer.parseInt(character);
		  return result; 
		}
	
	@Override
	public void input() {
		System.out.print("Try again! Input: ");
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
	public void displayMembersID() {
		System.out.print("Please enter member's ID to remove member: ");
	}

	@Override
	public void displayBoatName() {
		System.out.println("\n\t\t*** One word name allowed! ***\n");
		System.out.print("Name of boat: ");
	}

	@Override
	public void displayBoatType() {
		System.out.println("Please choose a boat type:" + "\n1.Sailboat" + "\n2.Motorsailer" + "\n3.Kayak\\Canoe"
				+ "\n4.Other" + "\n");
		System.out.print("Input: ");
	}
	
	@Override
	public void displayBoatLength() {
		System.out.print("Boat length (in metres): ");		
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
	
	@Override
	public void displayBoatFound() {
		System.out.print("\nBoat found!"
				+ "\nUpdate boat name: ");
	}
	
	@Override
	public void enterBoatName() {
		System.out.print("Please enter existing boat's name: ");
	}
	
	@Override
	public void update() {
		System.out.print("Update > > > ");
	}
	
	@Override
	public void enterID() {
		System.out.print("\nPlease enter member ID (Input 0 to go back): ");
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
		System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" 
		         + "\nx Boat successfully registered! x"
		         + "\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
	}

	@Override
	public void boatUpdated() {
		System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
		         + "\nx Boat successfully updated! x"
		         + "\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
	}

	@Override
	public void boatRemoved() {
		System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
		         + "\nx Boat successfully removed! x"
		         + "\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
	}
	
	@Override
	public void savedSuccessfully() {
		System.out.println("Saved successfully!");
	}
	
	@Override
	public void fileCreated() {
		System.out.println("New File Created!");
	}

	@Override
	public void membersLoaded() {
		System.out.println("Members loaded into Registry!");
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
	public void boatsNotFoundError() {
		System.out.print("\n\t\t*** There are no boats to remove, please register a boat first! ***");
	}
	
	@Override
	public void emptyRegistry() {
		System.out.println("\n\t\t*** The Member Registry is currently empty. ***");
	}

	@Override
	public void boatTaken() {
		System.out.print("\n\t\t*** Boat name is taken! Please choose another name. ***");
	}

	@Override
	public void whitespaceUsed() {
		String nameTemp = "\" \" ---> Whitespace";
		System.out.println("\n\t\t*** You used the character " + nameTemp + " ***"
				+ "\n\t\t*** The name can only contain letters, try again! ***\n");
	}

	@Override
	public void otherCharUsed(String name, int position) {
		System.out.println("\n\t\t*** You used the character " + "\"" + name.charAt(position) + "\"" + " ***"
				+ "\n\t\t*** The name can only contain letters, try again! ***\n");
	}

	@Override
	public void fileNotFound() {
		System.out.println("\n\t\t*** File was not found! ***");
	}

	@Override
	public void exitOnEmpty() {
		System.out.println("\n\t\t*** Sorry, you do not have any members in the registry to save! Exiting! ***");
	}
	
	@Override
	public void boatNotFound() {
		System.out.println("\n\t\t*** Sorry, there is no boat with that name! ***");
	}
	
	@Override
	public void invalidBoatLength() {
		System.out.println("\n\t\t*** Length must be a positive integer! ***");
	}
}
