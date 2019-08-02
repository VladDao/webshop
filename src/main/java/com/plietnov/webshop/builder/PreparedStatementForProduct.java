package com.plietnov.webshop.builder;

import com.plietnov.webshop.bean.RepositoryBean;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class PreparedStatementForProduct {

    private static final Logger LOGGER = Logger.getLogger(PreparedStatementForProduct.class);
    private static final String SHOP_PRODUCT = "shop_product";
    private Connection connection;
    private RepositoryBean rb;
    private QueryBuilder qb;
    private Map<String, String> webMap;

    public PreparedStatementForProduct(Connection conn, Map<String, String> webMap, RepositoryBean rb) {
        this.webMap = webMap;
        this.connection = conn;
        this.rb = rb;
        setPageAmount();
        String first = calculateFirstElement();
        qb = new QueryBuilder();
        qb = qb.selectAll().from(SHOP_PRODUCT);
        addFilter(qb);
        qb.costRange(rb.getLoverCost(), rb.getUpperCost());
        addOrder(qb);
        setLimitAndOffset(first);
    }

    public void PSGetProductById(String id) {
        qb = new QueryBuilder();
        qb.selectAll().from(SHOP_PRODUCT).whereInt("id", id);
    }

    public PreparedStatementForProduct() {
    }

    private void setLimitAndOffset(String first) {
        if (rb.getElementPerPage() != null) {
            qb.limit(webMap.get(rb.getElementPerPage()));
        } else {
            qb.limit("5");
        }
        qb.offset(first);
    }

    private String calculateFirstElement() {
        if (rb.getCurrentPage() != null) {
            return String.valueOf((Integer.parseInt(rb.getCurrentPage()) - 1)
                    * Integer.parseInt(webMap.get(rb.getElementPerPage())));
        } else {
            return "0";
        }
    }

    private void setPageAmount() {
        if (rb.getElementPerPage() != null) {
            int pa = (int) Math.ceil(countNumberOfItems() / Integer.parseInt(rb.getElementPerPage()));
            rb.setPageAmount(String.valueOf(pa));
        } else {
            rb.setPageAmount("1");
        }
    }

    private int countNumberOfItems() {
        qb = new QueryBuilder();
        qb = qb.selectCount().from(SHOP_PRODUCT);
        addFilter(qb);
        if (rb.getLoverCost() != null && rb.getLoverCost().matches("\\d+")) {
            qb.costRange(rb.getLoverCost(), rb.getUpperCost());
        }
        PreparedStatement ps = qb.build(connection);
        LOGGER.info(ps);
        int count = 0;
        try {
            ResultSet rs = ps.executeQuery();
            rs.next();
            count = rs.getInt("count");
        } catch (SQLException e) {
            LOGGER.info(e);
        }
        return count;
    }

    public PreparedStatement getPreparedStatement(Connection connection) {
        return qb.build(connection);
    }

    public void addFilter(QueryBuilder qb) {
        rb.getFilter().entrySet().forEach(e -> addFilter(qb, e));
    }

    private void addFilter(QueryBuilder qb, Map.Entry<String, String[]> filter) {
        String[] param = filter.getValue();
        for (int i = 0; i < param.length; i++) {
            if (i == 0 && !qb.isContainsWhere()) {
                qb.where(filter.getKey(), webMap.get(param[i]));
            } else if (i == 0) {
                qb.and(filter.getKey(), webMap.get(param[i]));
            } else {
                qb.or(filter.getKey(), webMap.get(param[i]));
            }
        }
    }

    private void addOrder(QueryBuilder qb) {
        if (rb.getOrderBy() != null) {
            qb.orderBy(webMap.get(rb.getOrderBy()));
        }
    }
}
