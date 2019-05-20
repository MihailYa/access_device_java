package main.accessDevice.data;

import main.accessDevice.data.dao.*;
import main.accessDevice.data.entities.Schedule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.logging.Logger;

public class Database {
	private static final String DATABASE_FILE_NAME = "/test.db";

	private Connection connection;
	private AccessCardDao accessCardDao;
	private PersonDao personDao;
	private ScheduleDao scheduleDao;
	private VisitorsJournalDao visitorsJournalDao;
	private LockedCardsJournalDao lockedCardsJournalDao;

	private Logger log = Logger.getLogger(Database.class.getSimpleName());

	public Database() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		log.info("Database URI: " + getClass().getResource(DATABASE_FILE_NAME).getPath());
		//getClass().getResource(DATABASE_FILE_NAME).get
		String url = "jdbc:sqlite:" + getClass().getResource(DATABASE_FILE_NAME).getPath().replace("%20", " ");
		try {
			connection = DriverManager.getConnection(url);

			System.out.println("Connection established");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		accessCardDao = new AccessCardDao(connection);
		personDao = new PersonDao(connection);
		scheduleDao = new ScheduleDao(connection);
		visitorsJournalDao = new VisitorsJournalDao(connection);
		lockedCardsJournalDao = new LockedCardsJournalDao(connection);
	}

	public void clearDatabase() {
		visitorsJournalDao.deleteAll();
		lockedCardsJournalDao.deleteAll();
		accessCardDao.deleteAll();
		personDao.deleteAll();
		scheduleDao.deleteAll();
	}


	public AccessCardDao getAccessCardDao() {
		return accessCardDao;
	}

	public PersonDao getPersonDao() {
		return personDao;
	}

	public ScheduleDao getScheduleDao() {
		return scheduleDao;
	}

	public VisitorsJournalDao getVisitorsJournalDao() {
		return visitorsJournalDao;
	}

	public LockedCardsJournalDao getLockedCardsJournalDao() {
		return lockedCardsJournalDao;
	}
}
