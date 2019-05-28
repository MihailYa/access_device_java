package main.server.commands.adminCommands.commands;

import main.accessDevice.AccessDevice;
import main.server.commands.adminCommands.AbstractAdminCommand;
import main.server.commands.deviceCommands.commands.DeviceDefaultCommand;
import main.server.servlets.AdminPanelServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends AbstractAdminCommand {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, AccessDevice accessDevice) {
		HttpSession session = request.getSession(false);
		if (session != null)
			session.invalidate();

		return new DeviceDefaultCommand().execute(request, response, accessDevice);
	}
}
