package main.accessDevice.util;

public abstract class StopableRunnable implements Runnable {
	protected boolean isStoped = false;

	public void stop() {
		isStoped = true;
	}
}
