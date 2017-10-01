package model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "Member")
@XmlType(propOrder = {"Name", "Personal Number", "Identity Number", "Number of Boats", "Boat Info"})
@XmlAccessorType(XmlAccessType.FIELD)

public class Member {

	@XmlElement(name = "Member Name")
	private String name;
	@XmlElement(name = "Personal Number")
	private int persNum;
	@XmlElement(name = "Identity Number")
	private int id;
	@XmlElement(name = "Number of Boats")
	private int numOfBoats;
	@XmlElement(name = "Boat Info")
	private ArrayList<Boat> boats = new ArrayList<Boat>();
	
	private Boat boat;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPersNum() {
		return persNum;
	}

	public void setPersNum(int persNum) {
		this.persNum = persNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
}
