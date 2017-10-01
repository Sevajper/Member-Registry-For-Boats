package main;

import controller.MemberController;
import view.Console;

public class Main {

	public static void main(String[] args) {
		MemberController mc = new MemberController();
		Console c = new Console();
		mc.appStart(c);
	}

}
