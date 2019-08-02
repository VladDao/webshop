package com.plietnov.webshop.connection;

import java.sql.SQLException;

public interface TransactionManager {

    <T> T doInTransaction(Process process) throws SQLException;
}
