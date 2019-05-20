package main.server.commands.deviceCommands.commands;

import main.accessDevice.AccessDevice;
import main.accessDevice.deviceComponents.buttonsPanel.ButtonsPanel;
import main.accessDevice.deviceComponents.buttonsPanel.buttons.Button;
import main.accessDevice.deviceComponents.buttonsPanel.buttons.ButtonHelper;
import main.server.commands.deviceCommands.AbstractDeviceCommand;
import main.server.managers.PageManager;
import main.server.servlets.AccessDeviceServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ButtonsPanelCommand extends AbstractDeviceCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, AccessDevice accessDevice) {
		String numberButton = request.getParameter(AccessDeviceServlet.PARAM_NUMBER_BUTTON);
		String callButton = request.getParameter(AccessDeviceServlet.PARAM_CALL_BUTTON);
		String callButtonPress = request.getParameter(AccessDeviceServlet.PARAM_CALL_BUTTON_PRESS);
		String controlButton = request.getParameter(AccessDeviceServlet.PARAM_CONTROL_BUTTON);

		ButtonsPanel buttonsPanel = accessDevice.getButtonsPanel();
		ButtonHelper buttonHelper = ButtonHelper.getInstance();
		if (numberButton != null) {
			buttonsPanel.clickButton(buttonHelper.getNumberButton(Integer.valueOf(numberButton)));
		}
		if (callButton != null) {
			buttonsPanel.clickButton(Button.CALL);
		}
		if(callButtonPress != null) {
			buttonsPanel.pressButton(Button.CALL);
		} else {
			buttonsPanel.releaseButton(Button.CALL);
		}

		if (controlButton != null) {
			buttonsPanel.clickButton(Button.CONTROL);
		}

		outputAccessDevice(request, response, accessDevice);

		return PageManager.getInstance()
		                  .getPage(PageManager.PagesIds.ACCESS_DEVICE_PAGE);
	}
}
