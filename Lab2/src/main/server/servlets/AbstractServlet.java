package main.server.servlets;

import main.accessDevice.AccessDevice;
import main.server.commands.AbstractCommandsFactory;
import main.server.commands.ICommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractServlet extends HttpServlet {



	protected void processRequest(AbstractCommandsFactory commandsFactory, HttpServletRequest request,
	                              HttpServletResponse response, AccessDevice accessDevice)
			throws ServletException, IOException {
		ICommand command = commandsFactory.getCommand(request);
		String page = command.execute(request, response, accessDevice);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
