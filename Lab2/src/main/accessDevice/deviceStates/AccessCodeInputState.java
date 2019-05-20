package main.accessDevice.deviceStates;

import main.accessDevice.AccessDevice;
import main.accessDevice.util.DeviceConfigManager;
import main.accessDevice.util.DeviceMessagesManager;
import main.accessDevice.deviceComponents.accessCardRecipient.AccessCardRecipient;
import main.accessDevice.deviceComponents.buttonsPanel.buttons.Button;
import main.accessDevice.util.CodeMasker;

public class AccessCodeInputState extends AccessDeviceState {

	private final int accessCodeSize =
			DeviceConfigManager.getInstance()
			                   .getIntConfig(DeviceConfigManager.ConfigIds.ACCESS_CODE_SIZE);
	private final int maxAttempts =
			DeviceConfigManager.getInstance()
			                   .getIntConfig(DeviceConfigManager.ConfigIds.ACCESS_CODE_INPUT_MAX_ATTEMPTS);

	private StringBuilder code = new StringBuilder(accessCodeSize);
	private int attemptsCountLeft = maxAttempts;

	public AccessCodeInputState(AccessDevice parent) {
		super(parent);
	}

	@Override
	public void onButtonClick(Button button) {
		if (!button.isNumberButton())
			return;

		code.append(button.getValue());
		if (code.length() == accessCodeSize) {
			if (parent.getMemory()
			          .verifyAccessCode(code.toString())) {

				AccessCardRecipient accessCardRecipient = parent.getAccessCardRecipient();
				parent.getMemory()
				      .recordCardAccess(accessCardRecipient.getInsertedCard());
				accessCardRecipient.returnCard();

				parent.changeState(new DoorUnlockedState(parent));

			} else {
				if (attemptsCountLeft == 0) {
					String wrongPasswordCardIsLockedMessage =
							DeviceMessagesManager.getInstance()
							                     .getMessage(DeviceMessagesManager.MessagesIds.WRONG_PASSWORD_CARD_IS_LOCKED);

					AccessCardRecipient accessCardRecipient = parent.getAccessCardRecipient();
					accessCardRecipient.lockCard();
					parent.getMemory()
					      .lockCard(accessCardRecipient.getInsertedCard());

					accessCardRecipient.returnCard();

					parent.changeState(new InitState(parent, wrongPasswordCardIsLockedMessage));
				} else {
					code.setLength(0);
					--attemptsCountLeft;
					String wrongPasswordAttemptsLeftMessage =
							DeviceMessagesManager.getInstance()
							                     .getMessage(DeviceMessagesManager.MessagesIds.WRONG_PASSWORD_ATTEMPTS_LEFT);

					parent.updateMessage(String.format(wrongPasswordAttemptsLeftMessage, attemptsCountLeft));
				}
			}
		} else {
			String encodedPasswordMessage =
					DeviceMessagesManager.getInstance()
					                     .getMessage(DeviceMessagesManager.MessagesIds.ENCODED_ACCESS_CODE);

			parent.updateMessage(String.format(encodedPasswordMessage, CodeMasker.maskCode(code.length())));
		}
	}

}
