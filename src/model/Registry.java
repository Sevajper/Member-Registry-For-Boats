package model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// The XML root Element which will start off and show what is contained in the xml file
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "memberRegistry")
public class Registry {
	
	/*The Element in this case is the memberList and the unique ID, these will be loaded and saved,
	 *  and because the member class has elements as well, the members in the registry list will all be subdivied
	 *  into their own category.*/
	@XmlElement(name = "memberList")
	private ArrayList<Member> memberList = new ArrayList<Member>();
	@XmlElement private int uniqueId;

	public Registry() {
		uniqueId = 0;
	}
	
	public Registry(ArrayList<Member> members) {
		this.memberList = members;
	}

	public void setRegistry(ArrayList<Member> members) {
		this.memberList = members;
	}

	public boolean checkIfListEmpty() {
		return memberList.isEmpty();
	}
	
	/*
	 * Return the member list
	 */
	public Iterable<Member> returnMemberList() {
		return memberList;
	}
	
	/*
	 * Add a member to the list
	 */
	public void addMember(Member mem) {
		String newId = uniqueId + mem.createID();
		uniqueId++;
		mem.setId(newId);
		memberList.add(mem);
	}
	/*
	 * Remove a member from the list
	 */
	public void removeMember(Member mem) {
		memberList.remove(mem);
	}
	
	/*
	 * Return a specific member from the list based on ID
	 */
	public Member getMember(String ID) {
		Member mem = new Member();
		try {
			for (int i = 0; i < memberList.size(); i++) {
				if (memberList.get(i).getId().equals(ID)) {
					mem = memberList.get(i);
					return mem;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
