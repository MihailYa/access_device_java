package main.accessDevice.data.dao;

import main.accessDevice.data.entities.LockCardRecord;
import main.accessDevice.data.entities.VisitRecord;
import main.accessDevice.data.util.Converter;
import main.accessDevice.data.util.SqlStatementsManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LockedCardsJournalDao extends AbstractDao<LockCardRecord> {

	public LockedCardsJournalDao(Connection connection) {
		super(connection);
	}

	@Override
	protected PreparedStatement getStatement(SqlStatementsManager.OperationType operationType) {
		String sqlStatement = SqlStatementsManager.getInstance()
		                                          .getSqlStatement(LockCardRecord.class,
		                                                           operationType);

		return getPrepareStatement(sqlStatement);
	}

	@Override
	protected LockCardRecord createEntityFromRow(ResultSet row) throws SQLException {
		LockCardRecord entity = new LockCardRecord();
		entity.setId(row.getInt(1));
		entity.setAccessCardId(row.getInt(2));
		entity.setLockingDateTime(Converter.toLocalDateTime(row.getTimestamp(3)));

		return entity;
	}

	@Override
	protected void fillInsertStatement(PreparedStatement statement, LockCardRecord entity) throws SQLException {
		statement.setInt(1, entity.getAccessCardId());
		statement.setTimestamp(2, Converter.toTimestamp(entity.getLockingDateTime()));
	}

	@Override
	protected void fillUpdateStatement(PreparedStatement statement, LockCardRecord entity) throws SQLException {
		statement.setInt(1, entity.getAccessCardId());
		statement.setTimestamp(2, Converter.toTimestamp(entity.getLockingDateTime()));
		statement.setInt(3, entity.getId());
	}


}
