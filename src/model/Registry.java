package model;

import java.util.ArrayList;

public class Registry {

	private ArrayList<Member> memberList = new ArrayList<Member>();

	public Registry() {

	}
	
	public Registry(ArrayList<Member> members) {
		this.memberList = members;
	}

	public void setRegistry(ArrayList<Member> members) {
		this.memberList = members;
	}

	public ArrayList<Member> getRegistry() {
		return memberList;
	}

	public void addMember(Member mem) {
		memberList.add(mem);
	}
	
	public Member getMember(String ID) {
		Member mem = new Member();
		if (memberList.isEmpty()) {
			System.out.println("The Member Registry is empty.");
		} else {
			for (int i = 0; i < memberList.size(); i++) {
				if (memberList.get(i).getId().equals(ID)) {
					mem = memberList.get(i);
				}
			}
		}
		return mem;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (memberList.isEmpty()) {
			sb.append("The Member Registry is empty.");
		} else
			for (Member list : memberList)
				sb.append(list);
		return sb.toString();
	}
}
