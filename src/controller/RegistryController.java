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
import view.IView;

public class RegistryController {

	public RegistryController() {

	}

	/*
	 * Take all the members in the memberList ArrayList and put them into a file on
	 * the desktop
	 */
	public void saveToRegistry(IView view, Registry reg, File file) throws IOException, JAXBException {
		if (reg.checkIfListEmpty()) {												// If Registry is empty, prints out following message.
			view.exitOnEmpty();
			System.exit(0);
		} else {

			BufferedWriter out = new BufferedWriter(new FileWriter(file));		// Creating a buffered writer to write in the file.
			JAXBContext context = JAXBContext.newInstance(Registry.class);		// Creating a new Registry instance of the JaxBContext for xml converting.
			Marshaller marshaller = context.createMarshaller();					// Marshalling the Registry with the members and boats into the file.
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);		// Setting the format output as JAXB Xml

			marshaller.marshal(reg, out);										// Marshalling the registry into the specified file.

		}

	}

	// Take the members from the file and load them into the ArrayList registry
	public Registry loadFromRegistry(IView view, Registry reg, File file) throws FileNotFoundException, JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(Registry.class);		// Create a new Registry instance of the JAXB.
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();		// Create an unmarshaller object to read the file and convert its contents to an object.

		// Read file
		reg = (Registry) jaxbUnmarshaller.unmarshal(file);						// Unmarshall the file into the object.
		view.membersLoaded();

		return reg;																// Return the object.
	}
	
}
