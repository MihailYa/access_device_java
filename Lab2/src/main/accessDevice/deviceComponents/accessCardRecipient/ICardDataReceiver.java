package main.accessDevice.deviceComponents.accessCardRecipient;

import main.accessDevice.data.entities.AccessCard;

public interface ICardDataReceiver {
	void onCardInserted(AccessCard accessCard);
}
