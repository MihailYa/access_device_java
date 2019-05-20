package main.accessDevice.deviceStates;

import main.accessDevice.AccessDevice;
import main.accessDevice.util.DeviceConfigManager;
import main.accessDevice.util.DeviceMessagesManager;
import main.accessDevice.deviceComponents.buttonsPanel.buttons.Button;
import main.accessDevice.util.CodeMasker;

public class InputNewAccessCodeState extends AccessDeviceState {

	private final int accessCodeSize = DeviceConfigManager.getInstance()
	                                                      .getIntConfig(DeviceConfigManager.ConfigIds.ACCESS_CODE_SIZE);
	private StringBuilder newAccessCode = new StringBuilder(accessCodeSize);

	public InputNewAccessCodeState(AccessDevice parent) {
		super(parent);

		String inputNewControlCodeMessage =
				DeviceMessagesManager.getInstance()
				                     .getMessage(DeviceMessagesManager.MessagesIds.INPUT_NEW_ACCESS_CODE);

		parent.updateMessage(inputNewControlCodeMessage);
	}

	@Override
	public void onButtonClick(Button button) {
		if(!button.isNumberButton())
			return;

		newAccessCode.append(button.getValue());

		if(newAccessCode.length() == accessCodeSize) {
			parent.getMemory().modifyAccessCode(newAccessCode.toString());

			String accessCodeSuccessfullyModifiedMessage =
					DeviceMessagesManager.getInstance()
					                     .getMessage(DeviceMessagesManager.MessagesIds.ACCESS_CODE_SUCCESSFULLY_MODIFIED);

			parent.changeState(new DoorUnlockedState(parent, accessCodeSuccessfullyModifiedMessage));
		} else {
			String encodedAccessCodeMessage =
					DeviceMessagesManager.getInstance()
					                     .getMessage(DeviceMessagesManager.MessagesIds.ENCODED_ACCESS_CODE);

			parent.updateMessage(String.format(encodedAccessCodeMessage, CodeMasker.maskCode(newAccessCode.length())));
		}
	}
}
