package main.server.commands.adminCommands.commands;

import main.accessDevice.AccessDevice;
import main.accessDevice.data.entities.AccessCard;
import main.server.commands.adminCommands.AbstractAdminCommand;
import main.server.managers.PageManager;
import main.server.servlets.AdminPanelServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAccessCardCommand extends AbstractAdminCommand {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, AccessDevice accessDevice) {
		checkAdminSession(request);

		String accessCardId = request.getParameter(AdminPanelServlet.PARAM_ACCESS_CARD_ID);

		try {
			if (accessCardId == null) {
				throw new RuntimeException("Wrong input data");
			}

			int accessCardIdInt = Integer.parseInt(accessCardId);

			AccessCard accessCard = new AccessCard();
			accessCard.setId(accessCardIdInt);
			accessDevice.getMemory()
			            .getAdminPanel()
			            .deleteAccessCard(accessCard);
		} catch (Exception e) {
			request.setAttribute(AdminPanelServlet.OUT_PARAM_ERROR_MESSAGE, "Error: " + e.getMessage());
		}

		outputAdminPanelInfo(request, response, accessDevice);



		return PageManager.getInstance()
		                  .getPage(PageManager.PagesIds.ADMIN_PANEL_PAGE);
	}
}
