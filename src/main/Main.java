package main;

import java.io.IOException;
import javax.xml.bind.JAXBException;

import controller.MemberController;
import view.Console;

public class Main {

	public static void main(String[] args) throws JAXBException, IOException {
		MemberController mc = new MemberController();
		Console c = new Console();
		mc.appStart(c);
	}

}
