package main.accessDevice.data.dao;

import main.accessDevice.data.Database;
import main.accessDevice.data.entities.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

class ScheduleDaoTest {
	private Database database = new Database();
	private ScheduleDao scheduleDao;

	@BeforeEach
	void clearDatabase() {
		//database.clearDatabase();
		scheduleDao = database.getScheduleDao();
	}

	@Test
	void timeTest() {
//		LocalTime localTime = LocalTime.of(12, 0);
//		Time time = Time.valueOf(localTime);
//		System.out.println(time.getTime());

		//System.out.println(Time.valueOf(LocalTime.of(20, 0)).getTime());
		//System.out.println(Time.valueOf(LocalTime.of(22, 0)).getTime());


		/*Schedule schedule = new Schedule();
		schedule.setBeginTime(localTime);
		schedule.setEndTime(localTime.plusHours(1));

		schedule.setId(scheduleDao.insert(schedule));

		List<Schedule> lst = scheduleDao.getAll();
*/
		//assertEquals(1, lst.size());
		//assertEquals(schedule.getBeginTime(), lst.get(0).getBeginTime());

	}
}