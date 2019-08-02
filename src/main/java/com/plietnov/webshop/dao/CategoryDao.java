package com.plietnov.webshop.dao;

import com.plietnov.webshop.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    private static final String SQL_GET_ALL_CATEGORIES = "SELECT * FROM category";

    private static final String SQL_GET_CATEGORY_BY_ID = "SELECT * FROM category WHERE category.category_id=?";

    public static final String CATEGORY_ID = "category_id";

    public static final String TITLE = "title";

    public List<Category> getCategories(Connection connection) throws SQLException {
        List<Category> categories = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_CATEGORIES);
        while (resultSet.next()) {
            categories.add(extractCategory(resultSet));
        }
        resultSet.close();
        statement.close();
        return categories;
    }

    public Category getCategoryById(Connection connection, int categoryId) {
        return null;
    }

    public Category getCategoryById(Connection connection, Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_CATEGORY_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Category category = null;
        if (resultSet.next()) {
            category = extractCategory(resultSet);
        }
        resultSet.close();
        preparedStatement.close();
        return category;
    }

    private Category extractCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt(CATEGORY_ID));
        category.setTitle(resultSet.getString(TITLE));
        return category;
    }
}
