package com.plietnov.webshop.connection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManagerImpl implements TransactionManager {

    private DataSource ds;

    public TransactionManagerImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public <T> T doInTransaction(Process process) throws SQLException {
        Connection connection = getConnection();
        Object result = null;
        try {
            result = process.action(connection);
            commitAndClose(connection);
        } catch (SQLException e) {
            rollbackAndClose(connection);
        }
        return (T) result;
    }

    private Connection getConnection() throws SQLException {
        Connection con = null;
        con = ds.getConnection();
        con.setAutoCommit(false);
        return con;
    }

    private void commitAndClose(Connection connection) throws SQLException {
        connection.commit();
        connection.close();
    }

    private void rollbackAndClose(Connection connection) throws SQLException {
        connection.rollback();
        connection.close();
    }
}
