package main.server.commands.deviceCommands;

import main.accessDevice.AccessDevice;
import main.server.commands.Command;
import main.server.commands.util.PageFiller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractDeviceCommand implements Command {

	protected void outputAccessDevice(HttpServletRequest request, HttpServletResponse response,
	                                  AccessDevice accessDevice) {
		PageFiller.outputAccessDevice(request, response, accessDevice);
	}
}
