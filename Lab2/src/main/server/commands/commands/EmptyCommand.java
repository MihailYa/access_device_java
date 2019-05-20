package main.server.commands.commands;

import main.accessDevice.AccessDevice;
import main.server.commands.ICommand;
import main.server.commands.commands.util.PageFiller;
import main.server.managers.PageManager;
import main.server.servlets.AccessDeviceServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmptyCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, AccessDevice accessDevice) {
		PageFiller.outputAccessDevice(request, response, accessDevice);

		return PageManager.getInstance().getPage(PageManager.PagesIds.DEFAULT_PAGE);
	}
}
