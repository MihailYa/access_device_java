package main.accessDevice.data.dao;

import main.accessDevice.data.util.SqlStatementsManager;
import main.accessDevice.data.entities.AccessCard;
import main.accessDevice.data.entities.BaseEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDao<E extends BaseEntity> {
	protected Connection connection;


	public AbstractDao(Connection connection) {
		this.connection = connection;
	}

	public boolean delete(E entity) {
		PreparedStatement statement = getStatement(SqlStatementsManager.OperationType.DELETE);
		try {
			statement.setInt(1, entity.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closePrepareStatement(statement);
		}

		return true;
	}

	public boolean update(E entity) {
		PreparedStatement statement = getStatement(SqlStatementsManager.OperationType.UPDATE);
		try {
			fillUpdateStatement(statement, entity);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closePrepareStatement(statement);
		}

		return true;
	}

	public int insert(E entity) {
		PreparedStatement statement = getStatement(SqlStatementsManager.OperationType.INSERT);
		int generatedId;
		try {
			fillInsertStatement(statement, entity);
			statement.executeUpdate();

			generatedId = statement.getGeneratedKeys().getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			closePrepareStatement(statement);
		}

		return generatedId;
	}

	public List<E> getAll() {
		List<E> lst = new LinkedList<>();
		PreparedStatement statement = getStatement(SqlStatementsManager.OperationType.SELECT_ALL);
		try {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				E entity = createEntityFromRow(resultSet);
				lst.add(entity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePrepareStatement(statement);
		}

		return lst;
	}

	public E getById(int id) {
		PreparedStatement statement = getStatement(SqlStatementsManager.OperationType.SELECT_BY_ID);

		E entity = null;
		try {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				entity = createEntityFromRow(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closePrepareStatement(statement);
		}

		return entity;
	}

	public boolean deleteAll() {
		PreparedStatement statement = getStatement(SqlStatementsManager.OperationType.DELETE_ALL);
		try {
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closePrepareStatement(statement);
		}

		return true;
	}

	protected PreparedStatement getPrepareStatement(String sql) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ps;
	}

	protected void closePrepareStatement(PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected abstract PreparedStatement getStatement(SqlStatementsManager.OperationType operationType);

	protected abstract E createEntityFromRow(ResultSet row) throws SQLException;

	protected abstract void fillInsertStatement(PreparedStatement statement, E entity) throws SQLException;

	protected abstract void fillUpdateStatement(PreparedStatement statement, E entity) throws SQLException;
}
