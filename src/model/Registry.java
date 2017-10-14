package model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "memberRegistry")
public class Registry {

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

	public ArrayList<Member> getRegistry() {
		return memberList;
	}

	public void addMember(Member mem) {
		String newId = uniqueId + mem.createID();
		uniqueId++;
		mem.setId(newId);
		memberList.add(mem);
	}
	
	public Member getMember(String ID) {
		Member mem = new Member();
		for (int i = 0; i < memberList.size(); i++) {
				if (memberList.get(i).getId().equals(ID)) {
					mem = memberList.get(i);
					return mem;
				}
			}
		return null;
	}
}
