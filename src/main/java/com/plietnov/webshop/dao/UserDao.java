package com.plietnov.webshop.dao;

import com.plietnov.webshop.builder.QueryBuilder;
import com.plietnov.webshop.entity.User;
import com.plietnov.webshop.mapper.UserUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDao.class);
    private static final String SELECT = "SELECT ";
    private static final String INSERT_INTO = "INSERT INTO ";
    private static final String ORDER_PARAM = "(user_login, user_name,user_email,user_phone , user_password, user_ava)";
    private static final String VALUES = "VALUES (? ,? ,? ,? ,? , ?)";
    private static final String ALL = "* ";
    private static final String FROM = "from ";
    private static final String SHOP_USERS = "shop_users ";
    private static final String WHERE = "WHERE ";
    private static final String ID_EQUAL = "id=?";
    private static final String LOGIN_EQUAL = "user_login=?";
    private static final String LOGIN = "user_login";
    private static final String NAME = "user_name";
    private static final String EMAIL = "user_email";
    private static final String PHONE = "user_phone";
    private static final String PASSWORD = "user_password";
    private static final String USER_AVA = "user_ava";
    private static final String USER_AVA_EQUALS = "user_ava=?";
    private static final int FIRST = 1;
    private static final String SQL_INCREMENT_AUTHORIZATION_FAILED_FIELD = "UPDATE user SET authentication_failed=" +
            "authentication_failed+1, lock_date=CURRENT_TIMESTAMP() WHERE user_id=?";

    public UserDao() {
    }

    public boolean incrementAuthorizationFailedField(Connection connection, Integer userId)
            throws SQLException {
        boolean result = false;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INCREMENT_AUTHORIZATION_FAILED_FIELD);
        preparedStatement.setInt(1, userId);
        if (preparedStatement.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    public Optional<User> getUserByLogin(Connection conn, String name) {
        User user = null;
        UserUtils userUtils = new UserUtils();
        QueryBuilder query = new QueryBuilder();
        PreparedStatement ps = null;
        try {
            ps = query.selectAll().from(SHOP_USERS).where(LOGIN, name).build(conn);
            ResultSet rs = ps.executeQuery();
            user = userUtils.userMapping(rs);
            LOGGER.info(user + " User From User Dao");
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                LOGGER.info(e);
            }
        }
        LOGGER.info(ps);
        return Optional.ofNullable(user);
    }

    public User addUser(Connection conn, User user) {
        QueryBuilder qb = new QueryBuilder();
        PreparedStatement ps = null;
        try {
            qb.insertInto(SHOP_USERS)
                    .insertParam(LOGIN, NAME, EMAIL, PHONE, PASSWORD, USER_AVA)
                    .insertParamValues(user.getLogin(),
                            user.getName(),
                            user.getEmail(),
                            user.getPhoneNumber(),
                            user.getPassword(),
                            user.getAva());
            ps = qb.build(conn);
            ps.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                LOGGER.info(e);
            }
        }
        return user;
    }
}
