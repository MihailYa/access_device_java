package main.server.commands.adminCommands.commands;

import main.accessDevice.AccessDevice;
import main.server.commands.adminCommands.AbstractAdminCommand;
import main.server.commands.deviceCommands.commands.DeviceEmptyCommand;
import main.server.commands.util.PageFiller;
import main.server.managers.PageManager;
import main.server.servlets.AdminPanelServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends AbstractAdminCommand {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, AccessDevice accessDevice) {
		HttpSession session = request.getSession();
		if (session.getAttribute(AdminPanelServlet.SESSION_ADMIN_ID) != null) {
			session.removeAttribute(AdminPanelServlet.SESSION_ADMIN_ID);
		}

		return new DeviceEmptyCommand().execute(request, response, accessDevice);
	}
}
