package com.plietnov.webshop.service;

import com.plietnov.webshop.connection.TransactionManager;
import com.plietnov.webshop.dao.CategoryDao;
import com.plietnov.webshop.entity.Category;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class CategoryService {

    private static final Logger LOGGER = Logger.getLogger(CategoryService.class);

    private TransactionManager transactionManager;

    private CategoryDao categoryDao;

    public CategoryService(TransactionManager transactionManager, CategoryDao categoryDao) {
        this.transactionManager = transactionManager;
        this.categoryDao = categoryDao;
    }

    public List<Category> getCategories() {
        try {
            return transactionManager.doInTransaction(connection -> categoryDao.getCategories(connection));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }
}
