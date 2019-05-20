package main.server.servlets;

import main.accessDevice.AccessDevice;
import main.server.commands.deviceCommands.AbstractDeviceCommand;
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

@WebServlet(name = "AccessDeviceServlet", urlPatterns = "/")
public class AccessDeviceServlet extends HttpServlet {
	public static final String PARAM_NUMBER_BUTTON = "numberButton";
	public static final String PARAM_CALL_BUTTON = "callButton";
	public static final String PARAM_CALL_BUTTON_PRESS = "callButtonPress";
	public static final String PARAM_CONTROL_BUTTON = "controlButton";

	public static final String PARAM_ADMIN_LOGIN = "adminLogin";
	public static final String PARAM_ADMIN_PASSWORD = "adminPassword";


	public static final String PARAM_ACCESS_CARD_ID = "accessCardId";

	public static final String OUT_PARAM_MESSAGE = "message";
	public static final String OUT_PARAM_IS_DOOR_LOCKED = "isDoorLocked";
	public static final String OUT_PARAM_IS_BELL_RINGING = "isBellRinging";
	public static final String OUT_PARAM_CALL_BUTTON_CHECKED = "callButtonChecked";
	public static final String OUT_PARAM_INSERTED_CARD_ID = "insertedCardId";


	private static final String LOG_CONFIG_FILE = "/logging.properties";

	static {
		String filePath = AccessDeviceServlet.class.getResource(LOG_CONFIG_FILE)
		                                       .getFile();

		System.setProperty("java.util.logging.config.file", filePath);
	}


	private AccessDevice accessDevice = AccessDevicesManager.getInstance().getAccessDevice();
	private DeviceCommandsFactory deviceCommandsFactory = DeviceCommandsFactory.getInstance();

	private Logger log = Logger.getLogger(AccessDeviceServlet.class.getSimpleName());


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("AccessDeviceServlet post request");
		processRequest(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("AccessDeviceServlet get request");
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AbstractDeviceCommand command = deviceCommandsFactory.getCommand(request);
		String page = command.execute(request, response, accessDevice);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
