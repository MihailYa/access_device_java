package main.accessDevice.deviceComponents.buttonsPanel;

import main.accessDevice.deviceComponents.buttonsPanel.buttons.Button;

public interface ButtonsPanelEventsReceiver {
	void onButtonClick(Button button);
	void onPressButton(Button button);
	void onReleaseButton(Button button);
}
