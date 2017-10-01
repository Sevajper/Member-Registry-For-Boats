package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import model.Member;

public class RegistryController {

	public ArrayList<Member> memberList = new ArrayList<Member>();
	String desktop = System.getProperty("user.home");
	File file = new File(desktop);
	
	public RegistryController() {
		
	}
	
	public RegistryController(ArrayList<Member> registry) {
		this.memberList = registry;
	}

	// Take all the members in the memberList ArrayList and put them into a file on
	// the desktop
	public void saveToRegistry() throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(file));

		try {
			JAXBContext context;
			context = JAXBContext.newInstance(Member.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			marshaller.marshal(memberList, out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	// Take the members from the file and load them into the ArrayList registry
	public ArrayList<Member> loadFromRegistry() {
		try {
			JAXBContext context = JAXBContext.newInstance(Member.class);
			Unmarshaller unMarshaller = context.createUnmarshaller();

			return (ArrayList<Member>) unMarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	// IDK what this method does yet
	public void getMembers() {
		memberList.toString();
	}

}