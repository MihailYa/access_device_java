package main.accessDevice.deviceStates;

import main.accessDevice.AccessDevice;
import main.accessDevice.util.managers.DeviceConfigManager;
import main.accessDevice.util.managers.DeviceMessagesManager;
import main.accessDevice.deviceComponents.buttonsPanel.buttons.Button;
import main.accessDevice.util.security.CodeMasker;

public class InputControlCodeForAccessCodeChanging extends AccessDeviceState {

	private final int controlCodeSize =
			DeviceConfigManager.getInstance()
			                   .getIntConfig(DeviceConfigManager.ConfigIds.CONTROL_CODE_SIZE);
	private StringBuilder controlCode = new StringBuilder(controlCodeSize);

	public InputControlCodeForAccessCodeChanging(AccessDevice parent) {
		super(parent);

		String inputControlCodeForAccessCodeChangingMessage =
				DeviceMessagesManager.getInstance()
				                     .getMessage(DeviceMessagesManager.MessagesIds.INPUT_CONTROL_CODE_FOR_ACCESS_CODE_CHANGING);

		parent.updateMessage(inputControlCodeForAccessCodeChangingMessage);
	}

	@Override
	public void onButtonClick(Button button) {
		if(!button.isNumberButton())
			return;

		controlCode.append(button.getValue());

		if(controlCode.length() == controlCodeSize) {
			if(parent.getMemory().verifyControlCode(controlCode.toString())) {
				parent.changeState(new InputNewAccessCodeState(parent));
			} else {
				String wrongControlCodeMessage =
						DeviceMessagesManager.getInstance()
						                     .getMessage(DeviceMessagesManager.MessagesIds.WRONG_CONTROL_CODE);

				parent.changeState(new InitState(parent, wrongControlCodeMessage));
			}
		} else {
			String encodedControlCodeMessage =
					DeviceMessagesManager.getInstance()
					                     .getMessage(DeviceMessagesManager.MessagesIds.ENCODED_CONTROL_CODE);
			parent.updateMessage(String.format(encodedControlCodeMessage, CodeMasker.maskCode(controlCode.length())));
		}
	}
}
