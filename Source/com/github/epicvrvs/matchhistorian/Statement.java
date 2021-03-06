package com.github.epicvrvs.matchhistorian;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Statement implements AutoCloseable {
	private PreparedStatement statement;
	private int index;
	
	public Statement(Connection connection, String query) throws SQLException {
		statement = connection.prepareStatement(query);
		index = 1;
	}
	
	public void close() throws SQLException {
		statement.close();
	}
	
	public int update() throws SQLException {
		int output = statement.executeUpdate();
		return output;
	}
	
	public ResultSet query() throws SQLException {
		return statement.executeQuery();
	}
	
	public int getInsertId() throws SQLException {
		ResultSet keys = statement.getGeneratedKeys();
		if(!keys.first())
			throw new SQLException("Unable to retrieve generated keys");
		return keys.getInt(1);
	}
	
	public void setInteger(int n) throws SQLException {
		statement.setInt(index++, n);
	}
	
	public void setBoolean(boolean value) throws SQLException {
		statement.setBoolean(index++, value);
	}
	
	public void setString(String string) throws SQLException {
		statement.setString(index++, string);
	}
	
	public void setArray(Array array) throws SQLException {
		statement.setArray(index++, array);
	}
	
	public void setNull(int type) throws SQLException {
		statement.setNull(index++, type);
	}
	
	public void setDate(Date date) throws SQLException {
		statement.setDate(index++, date);
	}
}
