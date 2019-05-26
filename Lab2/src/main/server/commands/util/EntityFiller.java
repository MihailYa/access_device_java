package main.server.commands.util;

import main.accessDevice.data.entities.AccessCard;
import main.accessDevice.data.entities.Person;
import main.accessDevice.data.entities.Schedule;
import main.server.servlets.AdminPanelServlet;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;

public class EntityFiller {

	public static AccessCard fillAccessCard(HttpServletRequest request) {
		String accessCardId = request.getParameter(AdminPanelServlet.PARAM_ACCESS_CARD_ID);
		String personId = request.getParameter(AdminPanelServlet.PARAM_ACCESS_CARD_PERSON_ID);
		String scheduleId = request.getParameter(AdminPanelServlet.PARAM_ACCESS_CARD_SCHEDULE_ID);
		String isAccessCardLocked = request.getParameter(AdminPanelServlet.PARAM_IS_ACCESS_CARD_LOCKED);

		if (accessCardId == null
		    || personId == null
		    || scheduleId == null
		) {
			throw new RuntimeException("Wrong input data for access card");
		}

		AccessCard accessCard = new AccessCard();

		int accessCardIdInt = Integer.parseInt(accessCardId);
		int personIdInt = Integer.parseInt(personId);
		int scheduleIdInt = Integer.parseInt(scheduleId);

		accessCard.setId(accessCardIdInt);
		accessCard.setPersonId(personIdInt);
		accessCard.setScheduleId(scheduleIdInt);
		accessCard.setLocked(isAccessCardLocked != null);


		Person person = fillPersonData(request);
		person.setId(personIdInt);
		accessCard.setPerson(person);

		Schedule schedule = fillScheduleData(request);
		schedule.setId(scheduleIdInt);
		accessCard.setSchedule(schedule);

		return accessCard;
	}

	private static Person fillPersonData(HttpServletRequest request) {
		String accessCardPersonName = request.getParameter(AdminPanelServlet.PARAM_ACCESS_CARD_PERSON_NAME);
		String accessCardPersonSurname = request.getParameter(AdminPanelServlet.PARAM_ACCESS_CARD_PERSON_SURNAME);

		if (accessCardPersonName == null
		    || accessCardPersonSurname == null) {
			throw new RuntimeException("Wrong input data for person");
		}

		Person person = new Person();
		person.setName(accessCardPersonName);
		person.setSurname(accessCardPersonSurname);

		return person;
	}

	private static Schedule fillScheduleData(HttpServletRequest request) {
		String accessCardScheduleBeginTime = request.getParameter(AdminPanelServlet.PARAM_ACCESS_CARD_SCHEDULE_BEGIN_TIME);
		String accessCardScheduleEndTime = request.getParameter(AdminPanelServlet.PARAM_ACCESS_CARD_SCHEDULE_END_TIME);

		if (accessCardScheduleBeginTime == null
		    || accessCardScheduleEndTime == null) {
			throw new RuntimeException("Wrong input data for schedule");
		}

		Schedule schedule = new Schedule();
		LocalTime beginTime = LocalTime.parse(accessCardScheduleBeginTime);
		LocalTime endTime = LocalTime.parse(accessCardScheduleEndTime);

		if(beginTime.isAfter(endTime))
			throw new RuntimeException("Wrong input data for schedule. \"End time\" should be after \"Begin time\"");

		schedule.setBeginTime(beginTime);
		schedule.setEndTime(endTime);

		return schedule;
	}

	public static AccessCard fillAccessCardData(HttpServletRequest request) {
		String isAccessCardLocked = request.getParameter(AdminPanelServlet.PARAM_IS_ACCESS_CARD_LOCKED);

		AccessCard accessCard = new AccessCard();
		accessCard.setLocked(isAccessCardLocked != null);


		Person person = fillPersonData(request);
		accessCard.setPerson(person);

		Schedule schedule = fillScheduleData(request);
		accessCard.setSchedule(schedule);

		return accessCard;
	}
}

