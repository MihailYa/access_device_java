package main.server.commands;

import main.server.commands.adminCommands.AdminCommandsFactory;
import main.server.commands.deviceCommands.DeviceCommandsFactory;

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
