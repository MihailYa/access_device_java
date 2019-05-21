package main.server.commands.adminCommands;

import main.server.commands.AbstractCommandsFactory;
import main.server.commands.ICommand;
import main.server.commands.adminCommands.commands.AdminEmptyCommand;
import main.server.commands.adminCommands.commands.AdminPanelCommand;
import main.server.commands.adminCommands.commands.UpdateAccessCardCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class AdminCommandsFactory extends AbstractCommandsFactory {
	public static final String PARAM_COMMAND = "command";

	private HashMap<String, AbstractAdminCommand> commands = new HashMap<String, AbstractAdminCommand>();

	public AdminCommandsFactory() {
		commands.put("adminPanelCommand", new AdminPanelCommand());
		commands.put("updateAccessCard", new UpdateAccessCardCommand());
	}

	@Override
	public ICommand getCommand(HttpServletRequest request) {
		String requestCommand = request.getParameter(PARAM_COMMAND);

		AbstractAdminCommand command = commands.get(requestCommand);

		if(command == null) {
			command = new AdminEmptyCommand();
		}

		return command;
	}

}
