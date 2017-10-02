package main;

import javax.xml.bind.JAXBException;

import controller.MemberController;
import view.Console;

public class Main {

	public static void main(String[] args) throws JAXBException {
		MemberController mc = new MemberController();
		Console c = new Console();
		mc.appStart(c);
	}

}
