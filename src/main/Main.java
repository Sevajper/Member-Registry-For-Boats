package main;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import controller.MemberController;
import view.Console;

public class Main {

	public static void main(String[] args) throws IOException, XMLStreamException, JAXBException {
		MemberController mc = new MemberController();
		Console c = new Console();
			mc.appStart(c);
		
	}
}
