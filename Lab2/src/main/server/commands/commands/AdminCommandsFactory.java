package main.server.commands.commands;

import main.server.commands.commands.adminCommands.AdminEmptyCommand;
import main.server.commands.commands.adminCommands.AdminPanelCommand;
import main.server.commands.deviceCommands.commands.DeviceEmptyCommand;
import main.server.commands.commands.adminCommands.UpdateAccessCardCommand;
import main.server.commands.deviceCommands.IDeviceCommand;
import main.server.commands.deviceCommands.commands.AccessCardRecipientCommand;
import main.server.commands.deviceCommands.commands.AdminLoginCommand;
import main.server.commands.deviceCommands.commands.ButtonsPanelCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class AdminCommandsFactory {
	private static AdminCommandsFactory ourInstance = new AdminCommandsFactory();

	public static AdminCommandsFactory getInstance() {
		return ourInstance;
	}

	public static final String PARAM_COMMAND = "command";

	private HashMap<String, AbstractAdminCommand> commands = new HashMap<String, AbstractAdminCommand>();

	private AdminCommandsFactory() {
		commands.put("adminPanelCommand", new AdminPanelCommand());
		commands.put("updateAccessCard", new UpdateAccessCardCommand());
	}

	public AbstractAdminCommand getCommand(HttpServletRequest request) {
		String requestCommand = request.getParameter(PARAM_COMMAND);

		AbstractAdminCommand command = commands.get(requestCommand);

		if(command == null) {
			command = new AdminEmptyCommand();
		}

		return command;
	}

}
