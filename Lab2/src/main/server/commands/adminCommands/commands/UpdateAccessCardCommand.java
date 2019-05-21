package main.server.commands.adminCommands.commands;

import main.accessDevice.AccessDevice;
import main.accessDevice.data.entities.AccessCard;
import main.server.commands.adminCommands.AbstractAdminCommand;
import main.server.managers.PageManager;
import main.server.servlets.AdminPanelServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateAccessCardCommand extends AbstractAdminCommand {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, AccessDevice accessDevice) {
		Object adminId = request.getSession()
		                        .getAttribute(AdminPanelServlet.SESSION_ADMIN_ID);
		if(adminId == null) {
			return new AdminEmptyCommand().execute(request, response, accessDevice);
		}

		String accessCardId = request.getParameter(AdminPanelServlet.PARAM_ACCESS_CARD_ID);
		String personId = request.getParameter(AdminPanelServlet.PARAM_ACCESS_CARD_PERSON_ID);
		String scheduleId = request.getParameter(AdminPanelServlet.PARAM_ACCESS_CARD_SCHEDULE_ID);
		String isAccessCardLocked = request.getParameter(AdminPanelServlet.PARAM_IS_ACCESS_CARD_LOCKED);

		if(accessCardId != null && personId != null && scheduleId != null) {
			AccessCard accessCard = new AccessCard();
			accessCard.setId(Integer.valueOf(accessCardId));
			accessCard.setPersonId(Integer.valueOf(personId));
			accessCard.setScheduleId(Integer.valueOf(scheduleId));
			accessCard.setLocked(isAccessCardLocked != null);

			accessDevice.getMemory().getAdminPanel().updateAccessCard(accessCard);
		}

		outputAdminPanelInfo(request, response, accessDevice);


		return PageManager.getInstance()
		                  .getPage(PageManager.PagesIds.ADMIN_PANEL_PAGE);
	}
}
