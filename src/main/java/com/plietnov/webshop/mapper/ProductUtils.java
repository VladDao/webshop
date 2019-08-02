package com.plietnov.webshop.mapper;

import com.plietnov.webshop.dao.CategoryDao;
import com.plietnov.webshop.dao.ManufacturerDao;
import com.plietnov.webshop.entity.Category;
import com.plietnov.webshop.entity.Clothes;
import com.plietnov.webshop.entity.Manufacturer;
import com.plietnov.webshop.entity.Product;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductUtils {

    private static final Logger LOGGER = Logger.getLogger(ProductUtils.class);
    private static final String ID = "id";
    private static final String PRODUCT_NAME = "product_name";
    private static final String CATEGORY = "category";
    private static final String MANUFACTURER = "manufacturer";
    private static final String PRODUCT_COST = "product_cost";
    private static final String PICTURE_NAME = "picture_name";
    private static final String CATEGORY_ID = "category_id";
    private static final String MANUFACTURER_ID = "manufacturer_id";

    public static List<Product> productsMapping(ResultSet resultSet, Connection connection) {
        List<Product> products = new LinkedList<>();
        try {
            while (resultSet.next()) {
                Clothes clothes = new Clothes();
                clothes.setId(resultSet.getInt(ID));
                clothes.setNameOfProduct(resultSet.getString(PRODUCT_NAME));
                int categoryId = resultSet.getInt(CATEGORY_ID);
                Category category = new CategoryDao().getCategoryById(connection, categoryId);
                clothes.setCategory(category);
                Integer manufacturerId = resultSet.getInt(MANUFACTURER_ID);
                Manufacturer manufacturer = new ManufacturerDao().getManufacturerById(connection, manufacturerId);
                clothes.setManufacturer(manufacturer);
                clothes.setCost(resultSet.getString(PRODUCT_COST));
                clothes.setPictures((resultSet.getString(PICTURE_NAME)));
                products.add(clothes);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        products.forEach(LOGGER::info);
        return products;
    }

    public static Product productMapping(ResultSet resultSet, Connection connection) {
        Product product = null;
        try {
            while (resultSet.next()) {
                Clothes clothes = new Clothes();
                clothes.setId(resultSet.getInt(ID));
                clothes.setNameOfProduct(resultSet.getString(PRODUCT_NAME));
                int categoryId = resultSet.getInt(CATEGORY_ID);
                Category category = new CategoryDao().getCategoryById(connection, categoryId);
                clothes.setCategory(category);
                Integer manufacturerId = resultSet.getInt(MANUFACTURER_ID);
                Manufacturer manufacturer = new ManufacturerDao().getManufacturerById(connection, manufacturerId);
                clothes.setManufacturer(manufacturer);
                clothes.setCost(resultSet.getString(PRODUCT_COST));
                clothes.setPictures((resultSet.getString(PICTURE_NAME)));
                product = clothes;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return product;
    }
}
