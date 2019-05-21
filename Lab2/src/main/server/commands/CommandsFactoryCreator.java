package main.server.commands;

import main.server.commands.adminCommands.AbstractAdminCommand;
import main.server.commands.adminCommands.AdminCommandsFactory;
import main.server.commands.adminCommands.commands.AdminEmptyCommand;
import main.server.commands.adminCommands.commands.AdminPanelCommand;
import main.server.commands.adminCommands.commands.UpdateAccessCardCommand;
import main.server.commands.deviceCommands.DeviceCommandsFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class CommandsFactoryCreator {
	private static CommandsFactoryCreator ourInstance = new CommandsFactoryCreator();

	public static CommandsFactoryCreator getInstance() {
		return ourInstance;
	}

	public enum CommandsFactoryType {
		ADMIN_COMMANDS_FACTORY,
		DEVICE_COMMANDS_FACTORY
	}

	private CommandsFactoryCreator() {
	}

	public AbstractCommandsFactory getCommandsFactory(CommandsFactoryType commandsFactoryType) {
		switch (commandsFactoryType) {
			case ADMIN_COMMANDS_FACTORY:
				return new AdminCommandsFactory();
			case DEVICE_COMMANDS_FACTORY:
				return new DeviceCommandsFactory();
			default:
				return null;
		}
	}
}
