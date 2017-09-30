package model;

import controller.RegistryController;

public class Member {

	private String name;
	private String persNum;
	private int id = 0;
	private int numOfBoats;
	private Boat boat;
	RegistryController rc = new RegistryController();
	
	public Member() {}
	
	public Member(String memberName, String memberPersNum) {
		this.name = memberName;
		this.persNum = memberPersNum;
	}

	public int createID() {
		id += 1;
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
