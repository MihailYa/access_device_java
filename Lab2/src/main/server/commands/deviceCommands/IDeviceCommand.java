package main.server.commands.deviceCommands;

import main.accessDevice.AccessDevice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IDeviceCommand {
	String execute(HttpServletRequest request, HttpServletResponse response, AccessDevice accessDevice) throws IOException;
}
