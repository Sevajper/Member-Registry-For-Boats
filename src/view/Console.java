package view;



public class Console {
	
	public void displayWelcome() {
		System.out.println("----------------------------------------");
		System.out.println(" Hello! Welcome to the Member Registry!");
		System.out.println("---------------------------------------- ");
		System.out.print(	
				  "1.  Register a new member"
				+ "\n2.  Update an existing member"
				+ "\n3.  Remove a member"
				+ "\n4.  Register a boat"
				+ "\n5.  Update boat"
				+ "\n6.  Remove a boat" 
				+ "\n7.  Display specific member"
				+ "\n8.  Display verbose list" 
				+ "\n9.  Display compact list"
				+ "\n10. Load from registry"
				+ "\n0.  Save and Exit"
				+ "\n"
				+ "\n100. Display Member Registry Menu"
				+ "\n"
				+ "\nChoose by typing a number: ");
	}
}
