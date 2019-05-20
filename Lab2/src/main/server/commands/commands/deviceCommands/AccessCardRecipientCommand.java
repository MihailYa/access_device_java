package main.server.commands.commands.deviceCommands;

import main.accessDevice.AccessDevice;
import main.accessDevice.data.entities.AccessCard;
import main.server.commands.commands.EmptyCommand;
import main.server.managers.PageManager;
import main.server.servlets.AccessDeviceServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessCardRecipientCommand extends AbstractDeviceCommand {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, AccessDevice accessDevice) {
		String accessCardId = request.getParameter(AccessDeviceServlet.PARAM_ACCESS_CARD_ID);

		if(accessCardId == null || accessCardId.isEmpty()) {
			return new EmptyCommand().execute(request, response, accessDevice);
		}

		AccessCard accessCard = new AccessCard();
		accessCard.setId(Integer.valueOf(accessCardId));

		accessDevice.getAccessCardRecipient().insertCard(accessCard);

		outputAccessDevice(request, response, accessDevice);

		return PageManager.getInstance()
		                  .getPage(PageManager.PagesIds.ACCESS_DEVICE_PAGE);
	}
}
