package com.plietnov.webshop.service;

import com.plietnov.webshop.connection.TransactionManager;
import com.plietnov.webshop.dao.UserDao;
import com.plietnov.webshop.entity.User;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Optional;

public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);
    private UserDao userDao;
    private TransactionManager transactionManager;

    public UserService(TransactionManager transactionManager, UserDao userDao) {
        this.transactionManager = transactionManager;
        this.userDao = userDao;
    }

    public User userAuthorization(String login, String password) {
        Optional<User> user = Optional.empty();
        try {
            user = transactionManager.doInTransaction(conn -> userDao.getUserByLogin(conn, login));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        }
        return null;
    }

    public void addUser(User user) {
        try {
            transactionManager.doInTransaction(conn -> userDao.addUser(conn, user));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    public boolean incrementAuthorizationFailedField(Integer userId) {
        try {
            return transactionManager.doInTransaction(connection -> userDao
                    .incrementAuthorizationFailedField(connection, userId));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return false;
    }

    public boolean isUserExist(User user) {
        Optional<User> us = Optional.empty();
        try {
            us = transactionManager.doInTransaction(conn -> userDao.getUserByLogin(conn, user.getLogin()));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return us.isPresent();
    }
}
