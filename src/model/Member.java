package model;

import controller.RegistryController;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "Member")
@XmlType(propOrder = {"name", "persNum", "id", "numOfBoats", "boats", "boat"})
@XmlAccessorType(XmlAccessType.FIELD)


public class Member {

	@XmlElement(name = "MemberName")
	private String name;
	@XmlElement(name = "PersonalNumber")
	private String persNum;
	@XmlElement(name = "IdentityNumber")
	private String id;
	@XmlElement(name = "NumberofBoats")
	private int numOfBoats;
	@XmlElement(name = "BoatInfo")
	private ArrayList<Boat> boats = new ArrayList<Boat>();
	@XmlElement(name = "Boat")
	private Boat boat;
	
	
	public Member() {}
	
	public Member(String memberName, String memberPersNum) {
		this.name = memberName;
		this.persNum = memberPersNum;
	}
	//Member ID is the first letter of the name plus the last three numbers of their personal number.
	public String createID() {
		id = getName().substring(0, 1) + getPersNum().substring(8, 11); //needs a dash when making the personal number
		
		
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersNum() {
		return persNum;
	}

	public void setPersNum(String persNum) {
		this.persNum = persNum;
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
	
	 public String toString(){
	        String temp = "Member: "  + this.name + " "
	                + "\n\tMemberid: " + this.id + "\n"
	                + " " + "\n\tPersonal Number: " + this.persNum
	                + " " + "\n\tNumber of Boats: " + this.numOfBoats
	                + "\n";
	        return temp;
	}
}
