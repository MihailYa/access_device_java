package main.accessDevice.data.dao;

import main.accessDevice.data.Database;
import main.accessDevice.data.entities.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PersonDaoTest {
	private Database database = new Database();
	private PersonDao personDao;

	@BeforeEach
	void refreshAccessCardDao() {
		database.clearDatabase();
		personDao = database.getPersonDao();
	}

	@Test
	void getAll() {
		List<Person> lst = personDao.getAll();
		assertEquals(0, lst.size());
	}

	@Test
	void insert() {
		Person person = new Person();
		person.setName("testName1");
		person.setSurname("testSurname1");
		personDao.insert(person);

		List<Person> lst = personDao.getAll();

		assertEquals(1, lst.size());
		Person actualPerson = lst.get(0);
		assertEquals(person.getName(), actualPerson.getName());
		assertEquals(person.getSurname(), actualPerson.getSurname());
	}

	@Test
	void delete() {
		Person person = new Person();
		person.setName("testName1");
		person.setSurname("testSurname1");
		person.setId(personDao.insert(person));

		List<Person> lst1 = personDao.getAll();
		assertEquals(1, lst1.size());

		personDao.delete(person);

		List<Person> lst2 = personDao.getAll();
		assertEquals(0, lst2.size());
	}

	@Test
	void getById() {
		Person person1 = new Person();
		person1.setName("testName1");
		person1.setSurname("testSurname1");
		person1.setId(personDao.insert(person1));

		Person person2 = new Person();
		person2.setName("testName2");
		person2.setSurname("testSurname2");
		person2.setId(personDao.insert(person2));

		assertEquals(2, personDao.getAll().size());

		Person actualPerson = personDao.getById(person1.getId());

		assertEquals(person1.getName(), actualPerson.getName());
		assertEquals(person1.getSurname(), actualPerson.getSurname());
	}

	@Test
	void getByIdNull() {
		assertEquals(0, personDao.getAll().size());
		Person actualPerson = personDao.getById(1);
		assertNull(actualPerson);
	}

	@Test
	void update() {
		Person person = new Person();
		person.setName("testName1");
		person.setSurname("testSurname1");
		person.setId(personDao.insert(person));

		assertEquals(1, personDao.getAll().size());

		person.setName("AnotherTestName1");
		person.setSurname("AnotherTestSurname1");
		personDao.update(person);

		List<Person> list = personDao.getAll();

		assertEquals(1, list.size());

		Person actualPerson = list.get(0);

		assertEquals(person.getName(), actualPerson.getName());
		assertEquals(person.getSurname(), actualPerson.getSurname());
	}
}