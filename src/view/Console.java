package view;

import java.util.Scanner;
import controller.RegistryController;
import controller.MemberController;

public class Console {

	public void displayWelcome() {
		System.out.println("Hello! Welcome to the Member Registry!"
				+ "\nPlease select an option from below: "
				+ "\n1. Register a new member"
				+ "\n2. Update an existing member"
				+ "\n3. Delete a member"
				+ "\n4. Register a boat"
				+ "\n5. Update boat"
				+ "\n6. Remove a boat" 
				+ "\n7. Display specific member"
				+ "\n8. Display verbose list" 
				+ "\n9. Display compact list"
				+ "\n10. Save to registry"
				+ "\n11. Load from registry"
				+ "\n0. Exit");
	}
	public void getInputResult() {
		
		Scanner input = new Scanner(System.in);
		MemberController mc = new MemberController();
		RegistryController rc = new RegistryController();
		
		 int selection = input.nextInt();

         switch (selection){
         
         case 0:
             System.exit(0);
             break;
         
         case 1:
             mc.registerMember();
             break;
         
         case 2:
             mc.updateMember();
             break;
         
         case 3:
             mc.removeMember();
             break;
         
         case 4:
             mc.registerBoat();
             break;
         
         case 5:
        	 mc.updateBoat();
             break;
         case 6:
             mc.removeBoat();
             break;
         
         case 7:
             mc.getMemberInfo(0);
             break;
         
         case 8:
             displayVerbose();
             break;
         
         case 9:
             displayCompact();
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

	public void displayCompact() {

	}

	public void displayVerbose() {

	}

	public void displaySpecific() {

	}

}
