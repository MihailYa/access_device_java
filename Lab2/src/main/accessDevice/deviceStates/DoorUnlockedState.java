package main.accessDevice.deviceStates;

import main.accessDevice.AccessDevice;
import main.accessDevice.util.DeviceConfigManager;
import main.accessDevice.util.DeviceMessagesManager;
import main.accessDevice.deviceComponents.buttonsPanel.buttons.Button;

public class DoorUnlockedState extends AccessDeviceState {

	private final int doorUnlockedSeconds =
			DeviceConfigManager.getInstance()
			                   .getIntConfig(DeviceConfigManager.ConfigIds.DOOR_UNLOCKED_SECONDS);
	private volatile boolean isTimerStopped = false;

	public DoorUnlockedState(AccessDevice parent) {
		super(parent);
		unlockDoor();
		startTimer();
	}

	public DoorUnlockedState(AccessDevice parent, String additionalMessage) {
		super(parent);
		parent.setDoorLocked(false);
		String youHaveTimeMessage =
				DeviceMessagesManager.getInstance()
				                     .getMessage(DeviceMessagesManager.MessagesIds.ADDITIONAL_MESSAGE_YOU_HAVE_TIME);

		parent.updateMessage(String.format(youHaveTimeMessage, additionalMessage, doorUnlockedSeconds));

		startTimer();
	}

	private void unlockDoor() {
		parent.setDoorLocked(false);

		String youHaveTimeMessage =
				DeviceMessagesManager.getInstance()
				                     .getMessage(DeviceMessagesManager.MessagesIds.YOU_HAVE_TIME);

		parent.updateMessage(String.format(youHaveTimeMessage, doorUnlockedSeconds));
	}

	private void startTimer() {
		synchronized (parent) {
			parent.getDoorTimer().start(doorUnlockedSeconds);
		}

		new Thread(() -> {
			try {
				Thread.sleep(doorUnlockedSeconds * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (isTimerStopped)
				return;

			synchronized (parent) {
				if (!parent.isDoorLocked()) {
					parent.setDoorLocked(true);
					String doorLocked =
							DeviceMessagesManager.getInstance()
							                     .getMessage(DeviceMessagesManager.MessagesIds.DOOR_LOCKED);

					parent.changeState(new InitState(parent, doorLocked));
				}
			}
		}).start();
	}

	@Override
	public void onPressButton(Button button) {
		if (button == Button.CALL) {
			isTimerStopped = true;
			parent.getDoorTimer().stop();
			if (!parent.isDoorLocked())
				parent.setDoorLocked(true);
			parent.changeState(new InputControlCodeForControlCodeChangingState(parent));
		}
	}

	@Override
	public void onButtonClick(Button button) {
		if (button == Button.CONTROL) {
			isTimerStopped = true;
			parent.getDoorTimer().stop();
			if (!parent.isDoorLocked())
				parent.setDoorLocked(true);
			parent.changeState(new InputControlCodeForAccessCodeChanging(parent));
		}
	}
}
