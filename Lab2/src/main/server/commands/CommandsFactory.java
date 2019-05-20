package main.server.commands;

import main.server.commands.commands.EmptyCommand;
import main.server.commands.commands.deviceCommands.AccessCardRecipientCommand;
import main.server.commands.commands.deviceCommands.ButtonsPanelCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class CommandsFactory {
	private static CommandsFactory ourInstance = new CommandsFactory();

	public static CommandsFactory getInstance() {
		return ourInstance;
	}

	private HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

	private CommandsFactory() {
		commands.put("buttonsPanelCommand", new ButtonsPanelCommand());
		commands.put("accessCardRecipientCommand", new AccessCardRecipientCommand());
	}

	public ICommand getCommand(HttpServletRequest request) {
		String requestCommand = request.getParameter("command");

		ICommand command = commands.get(requestCommand);

		if(command == null) {
			command = new EmptyCommand();
		}

		return command;
	}

}
