package main.server.commands.adminCommands.commands;

import main.accessDevice.AccessDevice;
import main.accessDevice.data.entities.AccessCard;
import main.server.commands.adminCommands.AbstractAdminCommand;
import main.server.commands.util.EntityFiller;
import main.server.managers.PageManager;
import main.server.servlets.AdminPanelServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateAccessCardCommand extends AbstractAdminCommand {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, AccessDevice accessDevice) {
		checkAdminSession(request);

		try {
		AccessCard accessCard = EntityFiller.fillAccessCard(request);

		if(accessCard == null) {
			throw new RuntimeException("Wrong input data");
		}

		accessDevice.getMemory()
		            .getAdminPanel()
		            .updateAccessCard(accessCard);
		} catch (Exception e) {
			request.setAttribute(AdminPanelServlet.OUT_PARAM_ERROR_MESSAGE, "Error: " + e.getMessage());
		}

		outputAdminPanelInfo(request, response, accessDevice);


		return PageManager.getInstance()
		                  .getPage(PageManager.PagesIds.ADMIN_PANEL_PAGE);
	}
}
