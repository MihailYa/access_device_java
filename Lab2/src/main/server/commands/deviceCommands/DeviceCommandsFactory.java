package main.server.commands.deviceCommands;

import main.server.commands.deviceCommands.commands.DeviceEmptyCommand;
import main.server.commands.commands.adminCommands.AdminPanelCommand;
import main.server.commands.commands.adminCommands.UpdateAccessCardCommand;
import main.server.commands.deviceCommands.commands.AccessCardRecipientCommand;
import main.server.commands.deviceCommands.commands.AdminLoginCommand;
import main.server.commands.deviceCommands.commands.ButtonsPanelCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class DeviceCommandsFactory {
	private static DeviceCommandsFactory ourInstance = new DeviceCommandsFactory();

	public static DeviceCommandsFactory getInstance() {
		return ourInstance;
	}

	public static final String PARAM_COMMAND = "command";

	private HashMap<String, AbstractDeviceCommand> commands = new HashMap<>();

	private DeviceCommandsFactory() {
		commands.put("buttonsPanelCommand", new ButtonsPanelCommand());
		commands.put("accessCardRecipientCommand", new AccessCardRecipientCommand());
		commands.put("adminLoginCommand", new AdminLoginCommand());
	}

	public AbstractDeviceCommand getCommand(HttpServletRequest request) {
		String requestCommand = request.getParameter(PARAM_COMMAND);

		AbstractDeviceCommand command = commands.get(requestCommand);

		if(command == null) {
			command = new DeviceEmptyCommand();
		}

		return command;
	}

}
