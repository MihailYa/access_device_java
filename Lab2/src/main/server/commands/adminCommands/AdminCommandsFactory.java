package main.server.commands.adminCommands;

import main.server.commands.AbstractCommandsFactory;
import main.server.commands.Command;
import main.server.commands.adminCommands.commands.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class AdminCommandsFactory extends AbstractCommandsFactory {
	public static final String PARAM_COMMAND = "command";

	private HashMap<String, AbstractAdminCommand> commands = new HashMap<>();

	public AdminCommandsFactory() {
		commands.put("adminPanelCommand", new AdminPanelCommand());
		commands.put("updateAccessCard", new UpdateAccessCardCommand());
		commands.put("insertAccessCard", new InsertAccessCardCommand());
		commands.put("deleteAccessCard", new DeleteAccessCardCommand());
		commands.put("logoutCommand", new LogoutCommand());
	}

	@Override
	public Command getCommand(HttpServletRequest request) {
		String requestCommand = request.getParameter(PARAM_COMMAND);

		AbstractAdminCommand command = commands.get(requestCommand);

		if(command == null) {
			command = new AdminDefaultCommand();
		}

		return command;
	}

}
