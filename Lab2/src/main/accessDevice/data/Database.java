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

	private AdminDao adminDao;

	private Logger log = Logger.getLogger(Database.class.getSimpleName());

	public Database() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String databasePath = getClass().getResource(DATABASE_FILE_NAME)
		                                .getPath()
		                                .replace("%20", " ");

		String url = "jdbc:sqlite:" + databasePath;
		try {
			connection = DriverManager.getConnection(url);
			log.info("Database opened: " + databasePath);
		} catch (SQLException e) {
			e.printStackTrace();
			log.severe("Can't open database. DB path: " + databasePath + "Cause:\n" + e);
		}


		accessCardDao = new AccessCardDao(connection);
		personDao = new PersonDao(connection);
		scheduleDao = new ScheduleDao(connection);
		visitorsJournalDao = new VisitorsJournalDao(connection);
		lockedCardsJournalDao = new LockedCardsJournalDao(connection);

		adminDao = new AdminDao(connection);
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

	public AdminDao getAdminDao() {
		return adminDao;
	}
}
