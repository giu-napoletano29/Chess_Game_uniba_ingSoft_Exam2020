package it.uniba.main;

import it.uniba.command.Command;

/*
 * The main class for the project
 * <br>It must be customized to meet the project
 * assignment specifications
 * <br><b>DO NOT RENAME</b>
 */

/**
 * <b>This is the main class</b>
 * <br><b>Responsibility</b>
 * <br>This class can:
 * <br>- manage the application start
 * <br>This class know:
 * <br>- "Command" class methods
 *
 * <br><b>This class is <i>" control "</i></b>.
 */
public final class AppMain {

	/**
	 * Private constructor.
	 */
	private AppMain() {}

	/**
	 * This is the main entry of the application.
	 * @param args Input stream parameter
	 */
	public static void main(final String[] args) {
		Command.menu();
		String cmd = Command.enterCommand();
		while(true)
		{
			Command.commandList(cmd);
			cmd = Command.enterCommand();
		}
	}

}

