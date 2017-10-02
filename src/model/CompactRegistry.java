package model;

import java.util.ArrayList;

public class CompactRegistry {
	private ArrayList<Object> compactList = new ArrayList<Object>();

	public CompactRegistry() {

	}

	public CompactRegistry(ArrayList<Object> members) {
		this.compactList = members;
	}
	
	public void setCompactRegistry(ArrayList<Object> members) {
		this.compactList = members;
	}
	
	public ArrayList<Object> getCompactRegistry(){
		return compactList;
	}
	
	public void addCompactMember(Member mem) {
		compactList.add(mem.getName());
		compactList.add(mem.getId());
		compactList.add(mem.getNumOfBoats());
	}
}
