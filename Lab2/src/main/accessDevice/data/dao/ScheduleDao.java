package main.accessDevice.data.dao;

import main.accessDevice.data.util.Converter;
import main.accessDevice.data.util.SqlStatementsManager;
import main.accessDevice.data.entities.AccessCard;
import main.accessDevice.data.entities.Schedule;

import java.sql.*;
import java.util.logging.Logger;

public class ScheduleDao extends AbstractDao<Schedule> {

	private Logger log = Logger.getLogger(ScheduleDao.class.getSimpleName());

	public ScheduleDao(Connection connection) {
		super(connection);
	}

	@Override
	protected PreparedStatement getStatement(SqlStatementsManager.OperationType operationType) {
		String sqlStatement = SqlStatementsManager.getInstance()
		                                          .getSqlStatement(Schedule.class,
		                                                           operationType);

		return getPrepareStatement(sqlStatement);
	}

	@Override
	protected Schedule createEntityFromRow(ResultSet row) throws SQLException {
		Schedule entity = new Schedule();
		entity.setId(row.getInt(1));
		entity.setBeginTime(row.getTime(2).toLocalTime());
		entity.setEndTime(row.getTime(3).toLocalTime());

		return entity;
	}

	@Override
	protected void fillStatement(PreparedStatement statement, Schedule entity) throws SQLException {
		statement.setTime(1, Time.valueOf(entity.getBeginTime()));
		statement.setTime(2, Time.valueOf(entity.getEndTime()));
	}

	@Override
	protected void fillUpdateStatement(PreparedStatement statement, Schedule entity) throws SQLException {
		statement.setTime(1, Time.valueOf(entity.getBeginTime()));
		statement.setTime(2, Time.valueOf(entity.getEndTime()));
		statement.setInt(3, entity.getId());
	}


}
