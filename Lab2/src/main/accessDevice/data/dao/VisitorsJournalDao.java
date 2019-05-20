package main.accessDevice.data.dao;

import main.accessDevice.data.entities.VisitRecord;
import main.accessDevice.data.util.Converter;
import main.accessDevice.data.util.SqlStatementsManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VisitorsJournalDao extends AbstractDao<VisitRecord> {

	public VisitorsJournalDao(Connection connection) {
		super(connection);
	}

	@Override
	protected PreparedStatement getStatement(SqlStatementsManager.OperationType operationType) {
		String sqlStatement = SqlStatementsManager.getInstance()
		                                          .getSqlStatement(VisitRecord.class,
		                                                           operationType);

		return getPrepareStatement(sqlStatement);
	}

	@Override
	protected VisitRecord createEntityFromRow(ResultSet row) throws SQLException {
		VisitRecord entity = new VisitRecord();
		entity.setId(row.getInt(1));
		entity.setAccessCardId(row.getInt(2));
		entity.setVisitDateTime(Converter.toLocalDateTime(row.getTimestamp(3)));

		return entity;
	}

	@Override
	protected void fillStatement(PreparedStatement statement, VisitRecord entity) throws SQLException {
		statement.setInt(1, entity.getAccessCardId());
		statement.setTimestamp(2, Converter.toTimestamp(entity.getVisitDateTime()));
	}

	@Override
	protected void fillUpdateStatement(PreparedStatement statement, VisitRecord entity) throws SQLException {
		statement.setInt(1, entity.getAccessCardId());
		statement.setTimestamp(2, Converter.toTimestamp(entity.getVisitDateTime()));
		statement.setInt(3, entity.getId());
	}


}
