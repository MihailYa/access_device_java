package main.accessDevice.util;

public class TimeLeftTimer {

	private volatile int timeLeftSeconds;
	private StopableRunnable timerRunnable;

	public void start(int seconds) {
		if(seconds < timeLeftSeconds) {
			return;
		}
		if(timerRunnable != null) {
			timerRunnable.stop();
		}

		timeLeftSeconds = seconds;

		timerRunnable =
		new StopableRunnable() {
			@Override
			public void run() {
				while (timeLeftSeconds != 0) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(isStoped) {
						return;
					}
					synchronized (TimeLeftTimer.this) {
						--timeLeftSeconds;
					}
				}
			}
		};

		new Thread(timerRunnable).start();
	}

	public void stop() {
		if(timerRunnable != null) {
			timerRunnable.stop();
			timerRunnable = null;
		}
		timeLeftSeconds = 0;
	}

	public int getTimeLeftSeconds() {
		return timeLeftSeconds;
	}
}
