package model;

public class Boat {

	private String name;
	private String type;
	private int length; 
	/*
	 * A boat is measured from tip of the bow to the center of the stern
	 * (from tip to tail)
	*/

	//constructor to create a boat
	public Boat(String boatName, String boatType, int boatSize) {
		this.name = boatName;
		this.type = boatType;
		this.length = boatSize;
	}
	
	//constructor to get a boat
	public Boat(Boat boat) {
		this.name = boat.getName();
		this.type = boat.getType();
		this.length = boat.getLength();
	}
	
	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	//Boat description
	public String toString() {
		StringBuilder description = new StringBuilder();
		description.append(
				"\nBoat description: " 
				+ "\nName: " + this.name
				+ "\nBoat type: " + this.type
				+ "\nBoat length: " + this.length);
		return description.toString();
	}
}
