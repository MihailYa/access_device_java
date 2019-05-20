package main.server.servlets;

import main.accessDevice.AccessDevice;
import main.server.commands.commands.AbstractAdminCommand;
import main.server.commands.commands.AdminCommandsFactory;
import main.server.commands.deviceCommands.DeviceCommandsFactory;
import main.server.commands.deviceCommands.IDeviceCommand;
import main.server.managers.AccessDevicesManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "AdminPanelServlet", urlPatterns = "/AdminPanelServlet")
public class AdminPanelServlet extends HttpServlet {

	public static final String PARAM_ACCESS_CARD_ID = "accessCardId";
	public static final String PARAM_ACCESS_CARD_PERSON_ID = "accessCardPersonId";
	public static final String PARAM_ACCESS_CARD_SCHEDULE_ID = "accessCardScheduleId";
	public static final String PARAM_IS_ACCESS_CARD_LOCKED = "isAccessCardLocked";

	public static final String OUT_PARAM_ACCESS_CARDS = "accessCards";
	public static final String OUT_PARAM_VISIT_RECORDS = "visitRecords";
	public static final String OUT_PARAM_LOCK_CARD_RECORDS = "lockCardRecords";

	public static final String SESSION_ADMIN_ID = "sessionAdminId";

	private AccessDevice accessDevice = AccessDevicesManager.getInstance().getAccessDevice();
	private AdminCommandsFactory commandsFactory = AdminCommandsFactory.getInstance();

	private Logger log = Logger.getLogger(AdminPanelServlet.class.getSimpleName());

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("AdminPanelServlet post request");
		processRequest(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("AdminPanelServlet post request");
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AbstractAdminCommand command = commandsFactory.getCommand(request);
		String page = command.execute(request, response, accessDevice);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
