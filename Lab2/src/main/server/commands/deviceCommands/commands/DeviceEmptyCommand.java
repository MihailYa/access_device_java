package main.server.commands.deviceCommands.commands;

import main.accessDevice.AccessDevice;
import main.server.commands.deviceCommands.AbstractDeviceCommand;
import main.server.commands.util.PageFiller;
import main.server.managers.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeviceEmptyCommand extends AbstractDeviceCommand {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, AccessDevice accessDevice) {
		PageFiller.outputAccessDevice(request, response, accessDevice);

		return PageManager.getInstance().getPage(PageManager.PagesIds.DEFAULT_PAGE);
	}
}
