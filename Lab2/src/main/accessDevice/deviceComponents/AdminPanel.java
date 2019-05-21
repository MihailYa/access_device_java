package main.accessDevice.deviceComponents;

import main.accessDevice.data.Database;
import main.accessDevice.data.dao.*;
import main.accessDevice.data.entities.*;

import java.util.List;

public class AdminPanel {
	private Database database;

	public AdminPanel(Database database) {
		this.database = database;
	}

	public void updateAccessCard(AccessCard accessCard) {
		AccessCardDao accessCardDao = database.getAccessCardDao();
		accessCardDao.update(accessCard);
	}

	public List<AccessCard> getAllAccessCards() {
		AccessCardDao accessCardDao = database.getAccessCardDao();
		PersonDao personDao = database.getPersonDao();
		ScheduleDao scheduleDao = database.getScheduleDao();

		List<AccessCard> accessCards = accessCardDao.getAll();

		for (AccessCard accessCard : accessCards) {
			Person person = personDao.getById(accessCard.getPersonId());
			accessCard.setPerson(person);

			Schedule schedule = scheduleDao.getById(accessCard.getScheduleId());
			accessCard.setSchedule(schedule);
		}

		return accessCards;
	}

	public List<VisitRecord> getAllVisitRecords() {
		VisitorsJournalDao visitorsJournalDao = database.getVisitorsJournalDao();

		return visitorsJournalDao.getAll();
	}

	public List<LockCardRecord> getAllLockCardRecords() {
		LockedCardsJournalDao lockedCardsJournalDao = database.getLockedCardsJournalDao();

		return lockedCardsJournalDao.getAll();
	}
}