package main.accessDevice.deviceComponents.accessCardRecipient;

import main.accessDevice.data.entities.AccessCard;

import java.util.logging.Logger;

public class AccessCardRecipient {

	private Logger log = Logger.getLogger(AccessCardRecipient.class.getSimpleName());
	private AccessCard insertedCard;
	private ICardDataReceiver cardDataReceiver;

	public AccessCardRecipient(ICardDataReceiver cardDataReceiver) {
		this.cardDataReceiver = cardDataReceiver;
	}

	public void insertCard(AccessCard insertedCard) {
		this.insertedCard = insertedCard;
		cardDataReceiver.onCardInserted(insertedCard);
	}

	public AccessCard getInsertedCard() {
		return insertedCard;
	}

	public void lockCard() {
		log.config("Lock card: " + insertedCard);
		insertedCard.setLocked(true);
	}

	public void returnCard() {
		log.config("Return card: " + insertedCard);
		insertedCard = null;
	}
}
