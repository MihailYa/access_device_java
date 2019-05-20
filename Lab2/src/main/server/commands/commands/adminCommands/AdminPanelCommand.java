package main.server.commands.commands.adminCommands;

import main.accessDevice.AccessDevice;
import main.server.commands.commands.AbstractAdminCommand;
import main.server.commands.deviceCommands.commands.DeviceEmptyCommand;
import main.server.managers.PageManager;
import main.server.servlets.AdminPanelServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

public class AdminPanelCommand extends AbstractAdminCommand {

	Logger log = Logger.getLogger(AdminPanelCommand.class.getSimpleName());

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, AccessDevice accessDevice) {
		log.info("Execute AdminPanelCommand command");

		Object adminId = request.getSession()
		                          .getAttribute(AdminPanelServlet.SESSION_ADMIN_ID);

		if(adminId == null) {
			log.info("adminId == null. Return empty command");
			return new AdminEmptyCommand().execute(request, response, accessDevice);
		}


		outputAdminPanelInfo(request, response, accessDevice);


		return PageManager.getInstance()
		                  .getPage(PageManager.PagesIds.ADMIN_PANEL_PAGE);
	}
}
