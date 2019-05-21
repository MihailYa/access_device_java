package main.server.commands;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractCommandsFactory {
	abstract public ICommand getCommand(HttpServletRequest request);

}
