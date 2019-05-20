package main.server.commands;

import main.accessDevice.AccessDevice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {
	String execute(HttpServletRequest request, HttpServletResponse response, AccessDevice accessDevice);
}
