package main.server.commands.adminCommands.commands;

import main.accessDevice.AccessDevice;
import main.server.commands.adminCommands.AbstractAdminCommand;
import main.server.managers.PageManager;
import main.server.servlets.AdminPanelServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

public class AdminPanelCommand extends AbstractAdminCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, AccessDevice accessDevice) {
		checkAdminSession(request);

		outputAdminPanelInfo(request, response, accessDevice);

		return PageManager.getInstance()
		                  .getPage(PageManager.PagesIds.ADMIN_PANEL_PAGE);
	}
}
