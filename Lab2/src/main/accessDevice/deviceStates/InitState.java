package main.accessDevice.deviceStates;

import main.accessDevice.AccessDevice;
import main.accessDevice.data.entities.AccessCard;
import main.accessDevice.deviceComponents.buttonsPanel.buttons.Button;
import main.accessDevice.util.managers.DeviceConfigManager;
import main.accessDevice.util.managers.DeviceMessagesManager;

public class InitState extends AccessDeviceState {

	private final int bellRingingSeconds = DeviceConfigManager.getInstance()
	                                                          .getIntConfig(DeviceConfigManager.ConfigIds.BELL_RINGING_SECONDS);

	public InitState(AccessDevice parent) {
		super(parent);

		String insertCardMessage = DeviceMessagesManager.getInstance()
		                                                .getMessage(DeviceMessagesManager.MessagesIds.INSERT_CARD);

		parent.updateMessage(insertCardMessage);
	}

	public InitState(AccessDevice parent, String additionalMessage) {
		super(parent);

		String additionalMessageInsertCardMessage = DeviceMessagesManager.getInstance()
		                                                                 .getMessage(DeviceMessagesManager.MessagesIds.ADDITIONAL_MESSAGE_INSERT_CARD);

		parent.updateMessage(String.format(additionalMessageInsertCardMessage, additionalMessage));
	}

	@Override
	public void onButtonClick(Button button) {
		synchronized (parent) {
			if (button == Button.CALL && !parent.isBellRinging()) {
				parent.setBellRinging(true);
				startTimer();
			}
		}
	}

	@Override
	public void onCardInserted(AccessCard accessCard) {
		if (parent.getMemory()
		          .verifyCard(accessCard)) {
			String nameInputCodeMessage = DeviceMessagesManager.getInstance()
			                                                   .getMessage(DeviceMessagesManager.MessagesIds.NAME_INPUT_CODE);

			parent.updateMessage(String.format(nameInputCodeMessage, accessCard.getPerson()
			                                                                   .getName()));

			parent.changeState(new AccessCodeInputState(parent));
		} else {
			parent.getAccessCardRecipient().returnCard();
			parent.updateMessage(DeviceMessagesManager.getInstance()
			                                          .getMessage(DeviceMessagesManager.MessagesIds.ACCESS_DENIED));
		}
	}

	private void startTimer() {
		parent.getBellTimer()
		      .start(bellRingingSeconds);
		new Thread(() -> {
			try {
				Thread.sleep(bellRingingSeconds * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			synchronized (parent) {
				if (parent.isBellRinging()) {
					parent.setBellRinging(false);
				}
			}
		}).start();
	}
}
