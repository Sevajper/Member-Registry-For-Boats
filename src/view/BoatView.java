package view;

import java.util.Scanner;

import model.Boat.BoatType;

public class BoatView {
		private Scanner input = new Scanner(System.in);
		private String boatName;
		private String boatTypeInput;
		private int boatLength;
		private BoatType bType;

		public void oneWordWarning() {
		System.out.println("\n\t\t*** One word name allowed! ***\n");
	}
		
		public String boatNameRegistration() {
		System.out.print("Name of boat: ");
		boatName = input.next();	
		boatName = boatName.substring(0, 1).toUpperCase() + boatName.substring(1);		// The boats name will be first uppercase and lowercase after that		
		return boatName;
	}
		
		public BoatType boatTypeRegistration() throws Exception {
			System.out.println("Please choose a boat type:" + "\n1.Sailboat" + "\n2.Motorsailer" + "\n3.Kayak\\Canoe"
					+ "\n4.Other" + "\n");
			System.out.print("Input: ");
			boatTypeInput = input.next();
			bType = null;
			
			if (boatTypeInput.equals("1")) {			// Different kinds of boat boatTypes
				bType= BoatType.Sailboat;
			} else if (boatTypeInput.equals("2")) {
				bType = BoatType.Motorsailer;
			} else if (boatTypeInput.equals("3")) {
				bType = BoatType.Canoe;
			} else if (boatTypeInput.equals("4")) {
				bType = BoatType.Other;
			} else {
				throw new Exception("Wrong input");
			}
			return bType;
		}
		
		public int boatLengthRegistration() throws Exception {
			System.out.print("Boat length (in meters): ");		
			boatLength = input.nextInt();
			if(boatLength<0) {
				throw new Exception("Exception");
			}
			return boatLength;
		}
	
	public void boatAdded() {
		System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" 
				         + "\nx Boat successfully registered! x"
				         + "\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
	}
	
	public void boatUpdated() {
		System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
				         + "\nx Boat successfully updated! x"
				         + "\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
	}
	
	public void boatRemoved() {
		System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
				         + "\nx Boat successfully removed! x"
				         + "\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
	}
	
	//-----------GETTERS & SETTERS-----------//
	
	public int getBoatLength() {
		return boatLength;
	}

	public void setBoatLength(int boatLength) {
		this.boatLength = boatLength;
	}
	
	public String getBoatTypeInput() {
		return boatTypeInput;
	}

	public void setBoatTypeInput(String boatTypeInput) {
		this.boatTypeInput = boatTypeInput;
	}
	
	public String getBoatName() {
		return boatName;
	}

	public BoatType getBoatType() {
		return bType;
	}

	public void setBoatType(BoatType boatType) {
		this.bType = boatType;
	}

	public void setBoatName(String boatName) {
		this.boatName = boatName;
	}
}
