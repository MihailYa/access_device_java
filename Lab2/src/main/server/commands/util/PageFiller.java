package main.server.commands.util;

import main.accessDevice.AccessDevice;
import main.accessDevice.data.entities.AccessCard;
import main.accessDevice.data.entities.LockCardRecord;
import main.accessDevice.data.entities.VisitRecord;
import main.accessDevice.deviceComponents.AdminPanel;
import main.accessDevice.deviceComponents.buttonsPanel.buttons.Button;
import main.server.servlets.AccessDeviceServlet;
import main.server.servlets.AdminPanelServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

	public static void outputLoginErrorMessage(HttpServletRequest request, HttpServletResponse response,
	                                      String loginErrorMessage) {
		request.setAttribute(AccessDeviceServlet.OUT_LOGIN_ERROR_MESSAGE, loginErrorMessage);
	}

	public static void outputAdminPanelInfo(HttpServletRequest request, HttpServletResponse response,
	                               AccessDevice accessDevice) {
		AdminPanel adminPanel = accessDevice.getMemory()
		                                    .getAdminPanel();

		List<AccessCard> accessCards = adminPanel.getAllAccessCards();
		request.setAttribute(AdminPanelServlet.OUT_PARAM_ACCESS_CARDS, accessCards);

		List<VisitRecord> visitRecords = adminPanel.getAllVisitRecords();
		request.setAttribute(AdminPanelServlet.OUT_PARAM_VISIT_RECORDS, visitRecords);

		List<LockCardRecord> lockCardRecords = adminPanel.getAllLockCardRecords();
		request.setAttribute(AdminPanelServlet.OUT_PARAM_LOCK_CARD_RECORDS, lockCardRecords);
	}
}
