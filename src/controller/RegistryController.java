package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.Element;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.Member;

public class RegistryController {
	
	public ArrayList<Member> memberList = new ArrayList<Member>();
	String desktop = System.getProperty("user.home");
	File file = new File(desktop, "Member Registry.txt");;
	XMLInputFactory inputFactory = XMLInputFactory.newFactory();
	static Member m;
	
	public RegistryController() {
		
	}
	
	public RegistryController(ArrayList<Member> registry) {
		this.memberList = registry;
	}

	// Take all the members in the memberList ArrayList and put them into a file on
	// the desktop
	public void saveToRegistry() throws IOException {
		
		if(memberList.isEmpty()) {
			System.err.println("Sorry, you do not have any members in the Registry to Save!");
		}

		else { 
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
		try {
			JAXBContext context;
			context = JAXBContext.newInstance(Member.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			for(int i = 0; i < memberList.size(); i++) {
				
			marshaller.marshal(memberList.get(i), out);
			}
			
			System.out.print("Your members have successfully been Saved in C:\\Users");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		}

	}

	// Take the members from the file and load them into the ArrayList registry
	public void loadFromRegistry() throws FileNotFoundException, JAXBException {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Member.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	     
	    //We had written this file in marshalling example
	    Member emps = (Member) jaxbUnmarshaller.unmarshal(file);
	     
	    for(Member emp : getMembers())
	    {
	    	memberList.add(emp);
	        System.out.println(emp.getName());
	        System.out.println(emp.getPersNum());
	    }
	}

	// IDK what this method does yet
	public ArrayList<Member> getMembers() {
		return memberList;
	}

}