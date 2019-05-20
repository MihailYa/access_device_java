package main.server.commands.commands.adminCommands;

import main.accessDevice.AccessDevice;
import main.server.commands.commands.AbstractAdminCommand;
import main.server.commands.deviceCommands.IDeviceCommand;
import main.server.commands.deviceCommands.commands.DeviceEmptyCommand;
import main.server.commands.util.PageFiller;
import main.server.managers.PageManager;
import main.server.servlets.AdminPanelServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminEmptyCommand extends AbstractAdminCommand {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, AccessDevice accessDevice) {
		if(request.getSession().getAttribute(AdminPanelServlet.SESSION_ADMIN_ID) == null) {
			return new DeviceEmptyCommand().execute(request, response, accessDevice);
		}

		PageFiller.outputAdminPanelInfo(request, response, accessDevice);
		return PageManager.getInstance().getPage(PageManager.PagesIds.ADMIN_DEFAULT_PAGE);
	}
}
