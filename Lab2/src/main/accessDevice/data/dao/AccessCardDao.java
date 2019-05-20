package main.accessDevice.data.dao;

import main.accessDevice.data.util.SqlStatementsManager;
import main.accessDevice.data.entities.AccessCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessCardDao extends AbstractDao<AccessCard> {

	public AccessCardDao(Connection connection) {
		super(connection);
	}

	@Override
	protected PreparedStatement getStatement(SqlStatementsManager.OperationType operationType) {
		String sqlStatement = SqlStatementsManager.getInstance()
		                                          .getSqlStatement(AccessCard.class,
		                                                           operationType);

		return getPrepareStatement(sqlStatement);
	}

	@Override
	protected AccessCard createEntityFromRow(ResultSet row) throws SQLException {
		AccessCard entity = new AccessCard();
		entity.setId(row.getInt(1));
		entity.setPersonId(row.getInt(2));
		entity.setScheduleId(row.getInt(3));
		entity.setLocked(row.getBoolean(4));

		return entity;
	}

	@Override
	protected void fillStatement(PreparedStatement statement, AccessCard entity) throws SQLException {
		statement.setInt(1, entity.getPersonId());
		statement.setInt(2, entity.getScheduleId());
		statement.setBoolean(3, entity.isLocked());
	}

	@Override
	protected void fillUpdateStatement(PreparedStatement statement, AccessCard entity) throws SQLException {
		statement.setInt(1, entity.getPersonId());
		statement.setInt(2, entity.getScheduleId());
		statement.setBoolean(3, entity.isLocked());
		statement.setInt(4, entity.getId());
	}
}
