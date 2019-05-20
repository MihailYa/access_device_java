package main.accessDevice.data.dao;

import main.accessDevice.data.entities.Admin;
import main.accessDevice.data.entities.Person;
import main.accessDevice.data.util.SqlStatementsManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao extends AbstractDao<Admin> {

	public AdminDao(Connection connection) {
		super(connection);
	}

	public Admin find(Admin entity) {
		PreparedStatement statement = getStatement(SqlStatementsManager.OperationType.FIND);
		try {
			fillStatement(statement, entity);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return createEntityFromRow(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closePrepareStatement(statement);
		}

		return null;
	}

	@Override
	protected PreparedStatement getStatement(SqlStatementsManager.OperationType operationType) {
		String sqlStatement = SqlStatementsManager.getInstance()
		                                          .getSqlStatement(Admin.class,
		                                                           operationType);

		return getPrepareStatement(sqlStatement);
	}

	@Override
	protected Admin createEntityFromRow(ResultSet row) throws SQLException {
		Admin entity = new Admin();
		entity.setId(row.getInt(1));
		entity.setLogin(row.getString(2));
		entity.setPassword(row.getString(3));

		return entity;
	}

	@Override
	protected void fillStatement(PreparedStatement statement, Admin entity) throws SQLException {
		statement.setString(1, entity.getLogin());
		statement.setString(2, entity.getPassword());
	}

	@Override
	protected void fillUpdateStatement(PreparedStatement statement, Admin entity) throws SQLException {
		statement.setString(1, entity.getLogin());
		statement.setString(2, entity.getPassword());
		statement.setInt(3, entity.getId());
	}
}
