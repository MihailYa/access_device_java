package main.accessDevice.deviceStates;

import main.accessDevice.AccessDevice;
import main.accessDevice.data.entities.AccessCard;
import main.accessDevice.deviceComponents.buttonsPanel.buttons.Button;

public abstract class AccessDeviceState {
	protected final AccessDevice parent;

	public AccessDeviceState(AccessDevice parent) {
		this.parent = parent;
	}

	public void onCardInserted(AccessCard accessCard) {}

	public void onButtonClick(Button button) {}

	public void onPressButton(Button button) {}

	public void onReleaseButton(Button button) {}
}
