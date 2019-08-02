package com.plietnov.webshop.service;

import com.plietnov.webshop.connection.TransactionManager;
import com.plietnov.webshop.dao.ManufacturerDao;
import com.plietnov.webshop.entity.Manufacturer;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class ManufacturerService {

    private static final Logger log = Logger.getLogger(ManufacturerService.class);
    private TransactionManager transactionManager;

    private ManufacturerDao manufacturerDao;

    public ManufacturerService(TransactionManager transactionManager, ManufacturerDao manufacturerDao) {
        this.transactionManager = transactionManager;
        this.manufacturerDao = manufacturerDao;
    }

    public List<Manufacturer> getManufacturers() {
        try {
            return transactionManager.doInTransaction(connection -> manufacturerDao.getManufacturers(connection));
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }
}
