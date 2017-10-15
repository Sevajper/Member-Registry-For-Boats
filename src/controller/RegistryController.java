package controller;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.Registry;

public class RegistryController {

	public RegistryController() {

	}

	// Take all the members in the memberList ArrayList and put them into a file on
	// the desktop
	public void saveToRegistry(Registry reg, File file) throws IOException, JAXBException {
		if (reg.getRegistry().isEmpty()) {			// If Registry is empty, prints out following message.
			System.out.println("\n\t\t*** Sorry, you do not have any members in the registry to save! Exiting! ***");
			System.exit(0);
		} else {

			BufferedWriter out = new BufferedWriter(new FileWriter(file));		// Creating a buffered writer to write in the file.
			JAXBContext context = JAXBContext.newInstance(Registry.class);		// Creating a new Registry instance of the JaxBContext for xml converting.
			Marshaller marshaller = context.createMarshaller();					// Marshalling the Registry with the members and boats into the file.
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);		// Setting the format output as JAXB Xml

			marshaller.marshal(reg, out);			// Marshalling the registry into the specified file.

		}

	}

	// Take the members from the file and load them into the ArrayList registry
	public Registry loadFromRegistry(Registry reg, File file) throws FileNotFoundException, JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(Registry.class);		// Create a new Registry instance of the JAXB.
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();		// Create an unmarshaller object to read the file and convert its contents to an object.

		// Read file
		reg = (Registry) jaxbUnmarshaller.unmarshal(file);						// Unmarshall the file into the object.
		System.out.println("Members loaded into Registry!");

		return reg;			// Return the object.
	}
}
