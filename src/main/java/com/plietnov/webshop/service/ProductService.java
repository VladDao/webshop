package com.plietnov.webshop.service;

import com.plietnov.webshop.bean.RepositoryBean;
import com.plietnov.webshop.connection.TransactionManager;
import com.plietnov.webshop.dao.ProductDao;
import com.plietnov.webshop.entity.Product;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductService {

    private static final Logger LOGGER = Logger.getLogger(ProductService.class);
    private ProductDao productDao;
    private TransactionManager transactionManager;

    public ProductService(TransactionManager transactionManager, ProductDao productDao) {
        this.transactionManager = transactionManager;
        this.productDao = productDao;
    }

    public List<Product> getProducts(Map<String, String> webMap, RepositoryBean repositoryBean) {
        Optional<List<Product>> input = null;
        try {
            input = transactionManager.doInTransaction(conn ->
                    productDao.getProducts(conn, webMap, repositoryBean));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return input.orElseGet(LinkedList::new);
    }

    public Product getProductById(String id) {
        try {
            return transactionManager.doInTransaction(conn -> productDao.getProductById(conn, id).get());
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }
}
