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
		if (reg.getRegistry().isEmpty()) {
			System.out.println("\n\t\t*** Sorry, you do not have any members in the registry to save! Exiting! ***");
			System.exit(0);
		} else {

			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			JAXBContext context = JAXBContext.newInstance(Registry.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			marshaller.marshal(reg, out);

		}

	}

	// Take the members from the file and load them into the ArrayList registry
	public Registry loadFromRegistry(Registry reg, File file) throws FileNotFoundException, JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(Registry.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		// Read file
		reg = (Registry) jaxbUnmarshaller.unmarshal(file);
		System.out.println("Members loaded into Registry!");

		return reg;
	}
}
