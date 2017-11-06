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

	public void setType(BoatType boatType) {	
		try {
			this.type = boatType;
			if(!validateType(boatType))
			{
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public int getLength() {
		return length;
	}

	public void setLength(int boatLength) {
		
		try {
			this.length = boatLength;
			if(!validateLength(boatLength))
			{
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
	//data validation helper methods
	private boolean validateLength(int length) {
		return (length > 0);
	}
	
	private boolean validateType(BoatType type) {
		for(BoatType validType : BoatType.values()) {
			if(validType != type) {
				return false;
			}
		}
		return true;
	}
	
	
}
