package model;

import controller.RegistryController;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;




public class Member {

	private String name;
	private String persNum;
	private String id;
	private int numOfBoats;
	private ArrayList<Boat> boats = new ArrayList<Boat>();
	private Boat boat;
	
	
	public Member() {}
	
	public Member(String memberName, String memberPersNum) {
		this.name = memberName;
		this.persNum = memberPersNum;
	}
	//Member ID is the first letter of the name plus the last three numbers of their personal number.
	public String createID() {
		id = getName().substring(0, 1).toUpperCase() + getPersNum().substring(8, 11); //needs a dash when making the personal number
		
		
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
	        String temp = 
	        		  "\nMember: "  + this.name + " "
	                + "\nMember ID: " + this.id
	                + " " + "\nPersonal Number: " + this.persNum
	                + " " + "\nNumber of Boats: " + this.numOfBoats
	                +"\n";
	        return temp;
	}
}
