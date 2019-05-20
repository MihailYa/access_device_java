package main.server.commands.commands.deviceCommands;

import main.accessDevice.AccessDevice;
import main.accessDevice.data.entities.AccessCard;
import main.accessDevice.deviceComponents.buttonsPanel.buttons.Button;
import main.server.commands.ICommand;
import main.server.commands.commands.util.PageFiller;
import main.server.servlets.AccessDeviceServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractDeviceCommand implements ICommand {
	protected void outputAccessDevice(HttpServletRequest request, HttpServletResponse response,
	                                  AccessDevice accessDevice) {
		PageFiller.outputAccessDevice(request, response, accessDevice);
	}
}
