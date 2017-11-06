package model;

public class Boat {
	
	public enum BoatType {
		Canoe,
		Motorsailer,
		Sailboat,
		Other
	}

	private String name;
	private BoatType type;
	private int length; 
	/*
	 * A boat is measured from tip of the bow to the center of the stern
	 * (from tip to tail)
	*/

	//constructor to create a boat
	public Boat(String boatName, BoatType boatType, int boatSize) {
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
	
	public Boat() {}
	
	//getters and setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BoatType getType() {
		return type;
	}

	public void setType(BoatType type) {
		this.type = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
