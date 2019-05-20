package main.accessDevice.deviceStates;

import main.accessDevice.AccessDevice;
import main.accessDevice.util.DeviceConfigManager;
import main.accessDevice.util.DeviceMessagesManager;
import main.accessDevice.deviceComponents.buttonsPanel.buttons.Button;
import main.accessDevice.util.CodeMasker;

public class InputNewControlCodeState extends AccessDeviceState {

	private final int controlCodeSize =
			DeviceConfigManager.getInstance()
			                   .getIntConfig(DeviceConfigManager.ConfigIds.CONTROL_CODE_SIZE);
	private StringBuilder newControlCode = new StringBuilder(controlCodeSize);

	public InputNewControlCodeState(AccessDevice parent) {
		super(parent);

		String inputNewControlCodeMessage =
				DeviceMessagesManager.getInstance()
				                     .getMessage(DeviceMessagesManager.MessagesIds.INPUT_NEW_CONTROL_CODE);

		parent.updateMessage(inputNewControlCodeMessage);
	}

	@Override
	public void onButtonClick(Button button) {
		if(!button.isNumberButton())
			return;

		newControlCode.append(button.getValue());

		if(newControlCode.length() == controlCodeSize) {
			parent.getMemory().modifyControlCode(newControlCode.toString());

			String controlCodeSuccessfullyModifiedMessage =
					DeviceMessagesManager.getInstance()
					                     .getMessage(DeviceMessagesManager.MessagesIds.CONTROL_CODE_SUCCESSFULLY_MODIFIED);

			parent.changeState(new DoorUnlockedState(parent, controlCodeSuccessfullyModifiedMessage));
		} else {
			String encodedControlCodeMessage =
					DeviceMessagesManager.getInstance()
					                     .getMessage(DeviceMessagesManager.MessagesIds.ENCODED_CONTROL_CODE);

			parent.updateMessage(String.format(encodedControlCodeMessage, CodeMasker.maskCode(newControlCode.length())));
		}
	}
}
