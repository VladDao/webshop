package com.plietnov.webshop.dao;

import com.plietnov.webshop.entity.Manufacturer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerDao {

    private static final String SQL_GET_ALL_MANUFACTURERS = "SELECT * FROM manufacturer";
    private static final String SQL_GET_MANUFACTURER_BY_ID = "SELECT * FROM manufacturer WHERE manufacturer.manufacturer_id=?";
    private static final String MANUFACTURER_ID = "manufacturer_id";
    private static final String TITLE = "title";

    public List<Manufacturer> getManufacturers(Connection connection) throws SQLException {
        List<Manufacturer> manufacturers = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_MANUFACTURERS);
        while (resultSet.next()) {
            manufacturers.add(extractManufacturer(resultSet));
        }
        resultSet.close();
        statement.close();
        return manufacturers;
    }

    public Manufacturer getManufacturerById(Connection connection, Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_MANUFACTURER_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Manufacturer manufacturer = null;
        if (resultSet.next()) {
            manufacturer = extractManufacturer(resultSet);
        }
        resultSet.close();
        preparedStatement.close();
        return manufacturer;
    }

    private Manufacturer extractManufacturer(ResultSet resultSet) throws SQLException {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(resultSet.getInt(MANUFACTURER_ID));
        manufacturer.setTitle(resultSet.getString(TITLE));
        return manufacturer;
    }
}
