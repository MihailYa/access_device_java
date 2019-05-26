package main.accessDevice;

import main.accessDevice.data.entities.AccessCard;
import main.accessDevice.deviceComponents.Memory;
import main.accessDevice.deviceComponents.accessCardRecipient.AccessCardRecipient;
import main.accessDevice.deviceComponents.accessCardRecipient.ICardDataReceiver;
import main.accessDevice.deviceComponents.buttonsPanel.ButtonsPanel;
import main.accessDevice.deviceComponents.buttonsPanel.IButtonsPanelEventsReceiver;
import main.accessDevice.deviceComponents.buttonsPanel.buttons.Button;
import main.accessDevice.deviceStates.AccessDeviceState;
import main.accessDevice.deviceStates.InitState;
import main.accessDevice.util.timer.TimeLeftTimer;

import java.util.logging.Logger;

public class AccessDevice implements IButtonsPanelEventsReceiver, ICardDataReceiver {

	private Logger log = Logger.getLogger(AccessDevice.class.getSimpleName());

	private AccessDeviceState accessDeviceState = new InitState(this);
	private String screenMessage;
	private ButtonsPanel buttonsPanel = new ButtonsPanel(this);
	private AccessCardRecipient accessCardRecipient = new AccessCardRecipient(this);
	private boolean isDoorLocked = true;
	private boolean isBellRinging = false;
	private TimeLeftTimer bellTimer = new TimeLeftTimer();
	private TimeLeftTimer doorTimer = new TimeLeftTimer();
	private Memory memory = new Memory();

	private OnAccessDeviceEvent onAccessDeviceEvent;

	@Override
	public void onCardInserted(AccessCard accessCard) {
		log.config("Card inserted: " + accessCard);
		accessDeviceState.onCardInserted(accessCard);
	}

	@Override
	public void onButtonClick(Button button) {
		log.config("Button clicked: " + button);
		accessDeviceState.onButtonClick(button);
	}

	@Override
	public void onPressButton(Button button) {
		log.config("Button pressed: " + button);
		accessDeviceState.onPressButton(button);
	}

	@Override
	public void onReleaseButton(Button button) {
		log.config("Button released: " + button);
		accessDeviceState.onReleaseButton(button);
	}

	public void updateMessage(String screenMessage) {
		log.config("Message updated: " + screenMessage);
		this.screenMessage = screenMessage;
	}

	public void changeState(AccessDeviceState newState) {
		accessDeviceState = newState;
		log.config("Change state to " + newState.getClass()
		                                        .getSimpleName());
	}

	public String getScreenMessage() {
		return screenMessage;
	}

	public ButtonsPanel getButtonsPanel() {
		return buttonsPanel;
	}

	public AccessCardRecipient getAccessCardRecipient() {
		return accessCardRecipient;
	}

	public Memory getMemory() {
		return memory;
	}

	public boolean isDoorLocked() {
		return isDoorLocked;
	}

	public boolean isBellRinging() {
		return isBellRinging;
	}

	public void setDoorLocked(boolean doorLocked) {
		log.config("Set door locked: " + doorLocked);
		isDoorLocked = doorLocked;
		if (onAccessDeviceEvent != null)
			onAccessDeviceEvent.onUpdateDoorLocked(isDoorLocked);
	}

	public void setBellRinging(boolean bellRinging) {
		log.config("Set bell ringing: " + bellRinging);
		isBellRinging = bellRinging;

		if (onAccessDeviceEvent != null)
			onAccessDeviceEvent.onUpdateBellRinging(isBellRinging);
	}

	public int getStateRefreshTimerSeconds() {
		int bellTime = bellTimer.getTimeLeftSeconds();
		int doorTime = doorTimer.getTimeLeftSeconds();

		if (bellTime == 0) {
			return doorTime;
		}

		if (doorTime == 0) {
			return bellTime;
		}

		return bellTime < doorTime ? bellTime : doorTime;
	}

	public TimeLeftTimer getBellTimer() {
		return bellTimer;
	}

	public TimeLeftTimer getDoorTimer() {
		return doorTimer;
	}

	public void setOnAccessDeviceEvent(OnAccessDeviceEvent onAccessDeviceEvent) {
		this.onAccessDeviceEvent = onAccessDeviceEvent;
	}

	public static interface OnAccessDeviceEvent {
		void onUpdateDoorLocked(boolean isDoorLocked);

		void onUpdateBellRinging(boolean isBellRinging);
	}
}
