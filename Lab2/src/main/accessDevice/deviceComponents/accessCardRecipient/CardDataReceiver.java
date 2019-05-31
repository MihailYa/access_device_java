package main.accessDevice.deviceComponents.accessCardRecipient;

import main.accessDevice.data.entities.AccessCard;

public interface CardDataReceiver {
	void onCardInserted(AccessCard accessCard);
}
