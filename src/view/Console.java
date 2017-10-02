package view;



public class Console {
	
	public void displayWelcome() {
		System.out.println("----------------------------------------");
		System.out.println(" Hello! Welcome to the Member Registry!");
		System.out.println("---------------------------------------- ");
		System.out.print(	
				  "1.  Register a new member"
				+ "\n2.  Update an existing member"
				+ "\n3.  Delete a member"
				+ "\n4.  Register a boat"
				+ "\n5.  Update boat"
				+ "\n6.  Remove a boat" 
				+ "\n7.  Display specific member"
				+ "\n8.  Display verbose list" 
				+ "\n9.  Display compact list"
				+ "\n10. Save to registry"
				+ "\n11. Load from registry"
				+ "\n0.  Exit"
				+ "\n"
				+ "\nChoose by typing a number between 0-11: ");
	}
	

	public void displayCompact() {
		
	}

	public void displayVerbose() {

	}

	public void displaySpecific() {

	}

}
