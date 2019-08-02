package com.plietnov.webshop.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface Process<T> {

    T action(Connection connection) throws SQLException;
}
