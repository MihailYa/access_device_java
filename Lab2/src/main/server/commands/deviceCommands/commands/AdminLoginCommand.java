package main.server.commands.deviceCommands.commands;

import main.accessDevice.AccessDevice;
import main.accessDevice.data.entities.Admin;
import main.server.commands.deviceCommands.AbstractDeviceCommand;
import main.server.commands.util.PageFiller;
import main.server.managers.MessageManager;
import main.server.managers.PageManager;
import main.server.servlets.AccessDeviceServlet;
import main.server.servlets.AdminPanelServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminLoginCommand extends AbstractDeviceCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, AccessDevice accessDevice) throws IOException {
		String login = request.getParameter(AccessDeviceServlet.PARAM_ADMIN_LOGIN);
		String password = request.getParameter(AccessDeviceServlet.PARAM_ADMIN_PASSWORD);

		if(login != null || password != null) {
			Admin admin = new Admin();
			admin.setLogin(login);
			admin.setPassword(password);

			if(accessDevice.getMemory().verifyAdmin(admin)) {
				request.getSession().setAttribute(AdminPanelServlet.SESSION_ADMIN_ID, admin.getId());

				return PageManager.getInstance().getPage(PageManager.PagesIds.ADMIN_PANEL_SERVLET);
			} else {

				outputAccessDevice(request, response, accessDevice);
				PageFiller.outputLoginErrorMessage(request, response,
				                                   MessageManager.getInstance().getMessage(MessageManager.MessagesIds.WRONG_LOGIN_OR_PASSWORD));

				return PageManager.getInstance().getPage(PageManager.PagesIds.ACCESS_DEVICE_PAGE);
			}
		}




		return new DeviceEmptyCommand().execute(request, response, accessDevice);
	}
}
