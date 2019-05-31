package main.server.commands;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractCommandsFactory {
	abstract public Command getCommand(HttpServletRequest request);

}
