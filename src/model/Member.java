package model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

// XML variables to put as Header for the file.
@XmlRootElement(name = "Member")
@XmlType(propOrder = {"name", "persNum", "id", "numOfBoats", "boats", "boat"})
@XmlAccessorType(XmlAccessType.FIELD)

public class Member {
	
	// The XMLElement is the name which is going to be shown so that the file is neat and organized.
	@XmlElement(name = "Name")
	private String name;
	@XmlElement(name = "PersonalNumber")
	private String persNum;
	@XmlElement(name = "IdentityNumber")
	private String id;
	@XmlElement(name = "NumberofBoats")
	private int numOfBoats;
	@XmlElement(name = "BoatInfo")
	private ArrayList<Boat> boats = new ArrayList<Boat>();
	@XmlElement(name = "boat")
	private Boat boat = new Boat();
	
	public Member() {
		
	}
	
	public Member(String memberName, String memberPersNum) {
		this.name = memberName;
		this.persNum = memberPersNum;
	}
	/*
	 * Member ID is a unique number starting from 0,
	 * that increases by 1 after each member registration, 
	 * plus the first letter of the name,
	 * plus the last three numbers of their personal number.
	 */
	public String createID() {
		
		id = getName().substring(0, 1).toUpperCase() 
			 + getPersNum().substring(8, 11); // Needs a dash when making the personal number
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		try {
			this.name = name;
			if(validateName(name))
			{
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public String getPersNum() {
		return persNum;
	}

	public void setPersNum(String persNum) {
		try {
			this.persNum = persNum;
			if(!validatePersNum(persNum))
			{
				throw new IllegalArgumentException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNumOfBoats() {
		return numOfBoats;
	}

	public void setNumOfBoats(int numOfBoats) {
		this.numOfBoats = numOfBoats;
	}
	
	public Boat getBoat() {
		return boat;
	}

	public void setBoat(Boat boat) {
		this.boat = boat;
	}
	public ArrayList<Boat> getBoats() { //change to iterable
		return boats;
	}
	public void setBoats(Boat boat) {
		boats.add(boat);
	}
	
	//data validation helper methods
	private boolean validateName(String name) {
		String regx = "^[\\p{L} .'-]+$";
		if(!name.matches(regx)) {
			return false;
		}
		return true;
	}
	
	private boolean validatePersNum(String perNum) {
		String regx = "^[0-9]+(-[0-9]+)";
		if (persNum.length() >= 8) {
			if (persNum.substring(6, 7).equals("-") && persNum.length() == 11 && persNum.matches(regx)) {
				return true;
			}
		}
		return false;
	}
}
