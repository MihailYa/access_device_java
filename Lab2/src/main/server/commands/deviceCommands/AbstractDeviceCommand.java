package main.server.commands.deviceCommands;

import main.accessDevice.AccessDevice;
import main.server.commands.deviceCommands.IDeviceCommand;
import main.server.commands.util.PageFiller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractDeviceCommand {
	public abstract String execute(HttpServletRequest request, HttpServletResponse response,
	                              AccessDevice accessDevice) throws IOException;

	protected void outputAccessDevice(HttpServletRequest request, HttpServletResponse response,
	                                  AccessDevice accessDevice) {
		PageFiller.outputAccessDevice(request, response, accessDevice);
	}
}
