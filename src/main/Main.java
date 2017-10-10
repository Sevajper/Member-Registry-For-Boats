package main;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import controller.MemberController;
import view.Console;

public class Main {

	public static void main(String[] args) throws JAXBException, FileNotFoundException {
		MemberController mc = new MemberController();
		Console c = new Console();
		mc.appStart(c);
	}

}
