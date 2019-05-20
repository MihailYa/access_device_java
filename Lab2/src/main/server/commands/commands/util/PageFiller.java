package main.server.commands.commands.util;

import main.accessDevice.AccessDevice;
import main.accessDevice.data.entities.AccessCard;
import main.accessDevice.deviceComponents.buttonsPanel.buttons.Button;
import main.server.servlets.AccessDeviceServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageFiller {
	public static void outputAccessDevice(HttpServletRequest request, HttpServletResponse response,
	                                 AccessDevice accessDevice) {
		request.setAttribute(AccessDeviceServlet.OUT_PARAM_MESSAGE, accessDevice.getScreenMessage());
		boolean isDoorLocked = accessDevice.isDoorLocked();
		request.setAttribute(AccessDeviceServlet.OUT_PARAM_IS_DOOR_LOCKED, isDoorLocked);
		boolean isBellRinging = accessDevice.isBellRinging();
		request.setAttribute(AccessDeviceServlet.OUT_PARAM_IS_BELL_RINGING, isBellRinging);

		AccessCard insertedCard = accessDevice.getAccessCardRecipient()
		                                      .getInsertedCard();
		request.setAttribute(AccessDeviceServlet.OUT_PARAM_INSERTED_CARD_ID,
		                     insertedCard != null
				                     ? insertedCard.getId()
				                     : "");

		if (accessDevice.getButtonsPanel()
		                .isButtonPressed(Button.CALL)) {
			request.setAttribute(AccessDeviceServlet.OUT_PARAM_CALL_BUTTON_CHECKED, "checked");
		} else {
			request.setAttribute(AccessDeviceServlet.OUT_PARAM_CALL_BUTTON_CHECKED, "");
		}

		int refreshSeconds = accessDevice.getStateRefreshTimerSeconds();
		if (refreshSeconds != 0) {
			response.setIntHeader("Refresh", refreshSeconds);
		}
	}
}
