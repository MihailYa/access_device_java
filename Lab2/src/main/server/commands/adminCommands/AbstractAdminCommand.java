package main.server.commands.adminCommands;

import main.accessDevice.AccessDevice;
import main.server.commands.ICommand;
import main.server.commands.util.PageFiller;
import main.server.servlets.AdminPanelServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public abstract class AbstractAdminCommand implements ICommand {
	public abstract String execute(HttpServletRequest request, HttpServletResponse response,
	                               AccessDevice accessDevice) throws IOException;

	protected void outputAdminPanelInfo(HttpServletRequest request, HttpServletResponse response,
	                                    AccessDevice accessDevice) {
		PageFiller.outputAdminPanelInfo(request, response, accessDevice);
	}

	protected void checkAdminSession(HttpServletRequest request) {
		Object adminId = request.getSession()
		                        .getAttribute(AdminPanelServlet.SESSION_ADMIN_ID);
		if (adminId == null) {
			throw new RuntimeException("Admin session required for this page");
		}
	}
}
