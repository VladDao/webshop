package com.plietnov.webshop.dao;

import com.plietnov.webshop.bean.RepositoryBean;
import com.plietnov.webshop.builder.PreparedStatementForProduct;
import com.plietnov.webshop.entity.Product;
import com.plietnov.webshop.mapper.ProductUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductDao {
    private static final Logger LOGGER = Logger.getLogger(ProductDao.class);
    private static final String SELECT = "SELECT ";
    private static final String INSERT_INTO = "INSERT INTO ";
    private static final String ORDER_PARAM = "(product_name, category, manufacturer, product_cost, picture_name)";
    private static final String VALUES = "VALUES (?,?,?,?,?);";
    private static final String ALL = "* ";
    private static final String FROM = "from ";
    private static final String WHERE = "WHERE ";
    private static final int FIRST = 1;
    private static final String SHOP_PRODUCT = "shop_product ";
    private static final String ID_EQUAL = "id=?";
    private static final String PRODUCT_NAME_EQUAL = "product_name=?";
    private static final String CATEGORY_EQUAL = "category=?";
    private static final String MANUFACTURER_EQUAL = "manufacturer=?";
    private static final String PRODUCT_COST = "product_cost";

    public Optional<Product> getProductById(Connection conn, String id) {
        Product product = null;
        PreparedStatementForProduct qb = new PreparedStatementForProduct();
        qb.PSGetProductById(id);
         PreparedStatement ps;
        try {
            ps = qb.getPreparedStatement(conn);
            LOGGER.info(ps);
            ResultSet rs = ps.executeQuery();
            product = ProductUtils.productMapping(rs, conn);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return Optional.ofNullable(product);
    }

    public Optional<List<Product>> getProducts(Connection conn, Map<String, String> webMap, RepositoryBean repositoryBean) {
        List<Product> products = null;
        PreparedStatementForProduct qb = new PreparedStatementForProduct(conn,webMap,repositoryBean);
        PreparedStatement ps;
        try {
            ps = qb.getPreparedStatement(conn);
            LOGGER.info(ps);
            ResultSet rs = ps.executeQuery();
            products = ProductUtils.productsMapping(rs, conn);
        } catch (SQLException e) {
            LOGGER.info(e);
        }
        return Optional.ofNullable(products);
    }
}
