package main.server.servlets;

import main.accessDevice.AccessDevice;
import main.server.commands.AbstractCommandsFactory;
import main.server.commands.CommandsFactoryCreator;
import main.server.managers.AccessDevicesManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "AdminPanelServlet", urlPatterns = "/AdminPanelServlet")
public class AdminPanelServlet extends AbstractServlet {

	public static final String PARAM_ACCESS_CARD_ID = "accessCardId";
	public static final String PARAM_ACCESS_CARD_PERSON_ID = "accessCardPersonId";
	public static final String PARAM_ACCESS_CARD_SCHEDULE_ID = "accessCardScheduleId";
	public static final String PARAM_ACCESS_CARD_PERSON_NAME = "accessCardPersonName";
	public static final String PARAM_ACCESS_CARD_PERSON_SURNAME = "accessCardPersonSurname";
	public static final String PARAM_ACCESS_CARD_SCHEDULE_BEGIN_TIME = "accessCardScheduleBeginTime";
	public static final String PARAM_ACCESS_CARD_SCHEDULE_END_TIME = "accessCardScheduleEndTime";
	public static final String PARAM_IS_ACCESS_CARD_LOCKED = "isAccessCardLocked";

	public static final String OUT_PARAM_ACCESS_CARDS = "accessCards";
	public static final String OUT_PARAM_VISIT_RECORDS = "visitRecords";
	public static final String OUT_PARAM_LOCK_CARD_RECORDS = "lockCardRecords";

	public static final String OUT_PARAM_ERROR_MESSAGE = "errorMessage";


	public static final String SESSION_ADMIN_ID = "sessionAdminId";

	private AccessDevice accessDevice = AccessDevicesManager.getInstance().getAccessDevice();
	private AbstractCommandsFactory commandsFactory =
			CommandsFactoryCreator.getInstance().getCommandsFactory(CommandsFactoryCreator.CommandsFactoryType.ADMIN_COMMANDS_FACTORY);

	private Logger log = Logger.getLogger(AdminPanelServlet.class.getSimpleName());

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(commandsFactory, request, response, accessDevice);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(commandsFactory, request, response, accessDevice);
	}


}
