package main.server.commands.deviceCommands;

import main.server.commands.AbstractCommandsFactory;
import main.server.commands.ICommand;
import main.server.commands.deviceCommands.commands.DeviceDefaultCommand;
import main.server.commands.deviceCommands.commands.AccessCardRecipientCommand;
import main.server.commands.deviceCommands.commands.AdminLoginCommand;
import main.server.commands.deviceCommands.commands.ButtonsPanelCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class DeviceCommandsFactory extends AbstractCommandsFactory {

	public static final String PARAM_COMMAND = "command";

	private HashMap<String, AbstractDeviceCommand> commands = new HashMap<>();

	public DeviceCommandsFactory() {
		commands.put("buttonsPanelCommand", new ButtonsPanelCommand());
		commands.put("accessCardRecipientCommand", new AccessCardRecipientCommand());
		commands.put("adminLoginCommand", new AdminLoginCommand());
	}

	@Override
	public ICommand getCommand(HttpServletRequest request) {
		String requestCommand = request.getParameter(PARAM_COMMAND);

		AbstractDeviceCommand command = commands.get(requestCommand);

		if(command == null) {
			command = new DeviceDefaultCommand();
		}

		return command;
	}

}
