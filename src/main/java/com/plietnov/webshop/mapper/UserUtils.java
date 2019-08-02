package com.plietnov.webshop.mapper;

import com.plietnov.webshop.constant.Constants;
import com.plietnov.webshop.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserUtils {
    private static final Logger LOGGER = Logger.getLogger(UserUtils.class);
    private static final String REGEXP_PASSWORD = "^(?=.*[0-9])" +
            "(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8,})$";
    private static final String REGEXP_GENERAL = "([a-zA-Z0-9]{3,})$";
    private static final String REGEXP_PHONE = "^(\\d{3,})$|^\\+(\\d{3,})$";
    private static final String REGEXP_EMAIL = "^([\\w-.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
    public static final String USER_LOGIN = "user_login";
    public static final String ID = "id";
    public static final String USER_NAME = "user_name";
    public static final String USER_PHONE = "user_phone";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_AVA = "user_ava";

    public User userMapping(HttpServletRequest request) {
        return new User(request.getParameter(Constants.USER_LOGIN),
                request.getParameter(Constants.USER_NAME),
                request.getParameter(Constants.USER_PHONE),
                request.getParameter(Constants.USER_EMAIL),
                request.getParameter(Constants.USER_PASSWORD));
    }

    public User userMapping(ResultSet resultSet) {
        User user = null;
        try {
            if (resultSet.next()) {
                user = new User(resultSet.getString(USER_LOGIN),
                        resultSet.getString(USER_NAME),
                        resultSet.getString(USER_PHONE),
                        resultSet.getString(USER_EMAIL),
                        resultSet.getString(USER_PASSWORD),
                        resultSet.getString(USER_AVA));
                user.setId(resultSet.getInt(ID));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        LOGGER.info(user + " from user mapping ");
        return user;
    }

    public boolean isValidLoginAndPassword(User user) {
        return (user.getPassword().matches(REGEXP_GENERAL) &&
                user.getLogin().matches(REGEXP_GENERAL));
    }

    public boolean userIsValid(User user) {
        return (user.getLogin().matches(REGEXP_GENERAL) &&
                user.getName().matches(REGEXP_GENERAL) &&
                user.getPhoneNumber().matches(REGEXP_PHONE) &&
                user.getEmail().matches(REGEXP_EMAIL) &&
                user.getPassword().matches(REGEXP_PASSWORD));
    }
}
