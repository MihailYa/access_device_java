package main.accessDevice.data.dao;

import main.accessDevice.data.util.SqlStatementsManager;
import main.accessDevice.data.entities.AccessCard;
import main.accessDevice.data.entities.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDao extends AbstractDao<Person> {

	public PersonDao(Connection connection) {
		super(connection);
	}

	@Override
	protected PreparedStatement getStatement(SqlStatementsManager.OperationType operationType) {
		String sqlStatement = SqlStatementsManager.getInstance()
		                                          .getSqlStatement(Person.class,
		                                                           operationType);

		return getPrepareStatement(sqlStatement);
	}

	@Override
	protected Person createEntityFromRow(ResultSet row) throws SQLException {
		Person entity = new Person();
		entity.setId(row.getInt(1));
		entity.setName(row.getString(2));
		entity.setSurname(row.getString(3));

		return entity;
	}

	@Override
	protected void fillInsertStatement(PreparedStatement statement, Person entity) throws SQLException {
		statement.setString(1, entity.getName());
		statement.setString(2, entity.getSurname());
	}

	@Override
	protected void fillUpdateStatement(PreparedStatement statement, Person entity) throws SQLException {
		statement.setString(1, entity.getName());
		statement.setString(2, entity.getSurname());
		statement.setInt(3, entity.getId());
	}
}
