package main.server.commands.adminCommands;

import main.accessDevice.AccessDevice;
import main.server.commands.ICommand;
import main.server.commands.util.PageFiller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractAdminCommand implements ICommand {
	public abstract String execute(HttpServletRequest request, HttpServletResponse response,
	                              AccessDevice accessDevice) throws IOException;

	protected void outputAdminPanelInfo(HttpServletRequest request, HttpServletResponse response,
	                                    AccessDevice accessDevice) {
		PageFiller.outputAdminPanelInfo(request, response, accessDevice);
	}
}
