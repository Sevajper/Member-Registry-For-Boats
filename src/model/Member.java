package model;

public class Member {

	private String name;
	private int persNum;
	private int id;
	private int numOfBoats;
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
