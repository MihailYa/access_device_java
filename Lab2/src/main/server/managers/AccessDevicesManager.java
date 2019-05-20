package main.server.managers;

import main.accessDevice.AccessDevice;
import main.accessDevice.deviceComponents.AdminPanel;

public class AccessDevicesManager {
	private static AccessDevicesManager instance;

	public static AccessDevicesManager getInstance() {
		if(instance == null) {
			instance = new AccessDevicesManager();
		}
		return instance;
	}

	private AccessDevice accessDevice;

	private AccessDevicesManager() {
		accessDevice = new AccessDevice();
	}

	public AccessDevice getAccessDevice() {
		return accessDevice;
	}

}
