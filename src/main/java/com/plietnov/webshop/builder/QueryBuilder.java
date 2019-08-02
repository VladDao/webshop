package com.plietnov.webshop.builder;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class QueryBuilder {

    private static final Logger LOGGER = Logger.getLogger(QueryBuilder.class);
    private static final String SELECT = "SELECT";
    private static final String ALL = "*";
    private static final String FROM = "FROM";
    private static final String SPACE = " ";
    private static final String WHERE = "WHERE";
    private static final String AND = "AND";
    private static final String OR = "OR";
    private static final String LIKE = "LIKE";
    private static final String PRODUCT_COST = "product_cost";
    private static final String INSERT_INTO = "INSERT INTO ";
    private static final String LIMIT = "LIMIT";
    private static final String OFFSET = "OFFSET";
    private static final String EQUALS_WITH_PARAM = "=?";
    private static final String EQUALS = "=";
    private static final String LOWER = "<";
    private static final String UPPER = ">";
    private static final String MARK = "?";
    private static final String ORDER_BY = "ORDER BY";
    private static final String ASC = "ASC";
    private static final String DESC = "DESC";
    private static final String COUNT = "COUNT(*)";
    private static final String COMA = ",";
    private static final String INNER_JOIN = "INNER JOIN";
    private static final String LEFT_OUTER_JOIN = "LEFT OUTER JOIN";
    private static final String RIGHT_OUTER_JOIN = "RIGHT OUTER JOIN";
    private static final String FULL_OUTER_JOIN = "FULL OUTER JOIN";
    private static final String ON = "ON";

    private StringBuilder builder = new StringBuilder();
    private Map<Integer, String> preparedMap = new HashMap<>();
    private AtomicInteger position = new AtomicInteger(1);

    public String buildStr() {
        return builder.toString();
    }

    public PreparedStatement build(Connection connection) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(builder.toString());
            initPS(ps);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return ps;
    }

    private String andOrWhere() {
        if (StringUtils.contains(builder, WHERE)) {
            return AND;
        } else {
            return WHERE;
        }
    }

    private void initPS(PreparedStatement ps) {
        preparedMap.forEach((key, value) -> {
            try {
                ps.setString(key, value);
            } catch (SQLException ex) {
                LOGGER.error(ex);
            }
        });
    }

    public QueryBuilder orderBy(String name) {
        builder.append(SPACE).append(name);
        return this;
    }

    public QueryBuilder limit(String name) {
        builder.append(SPACE).append(LIMIT).append(SPACE).append(name);
        return this;
    }

    public QueryBuilder offset(String name) {
        builder.append(SPACE).append(OFFSET).append(SPACE).append(name);
        return this;
    }

    public QueryBuilder from(String name) {
        builder.append(SPACE).append(FROM).append(SPACE).append(name);
        return this;
    }

    public QueryBuilder innerJoin(String name) {
        builder.append(SPACE).append(INNER_JOIN).append(SPACE).append(name);
        return this;
    }

    public QueryBuilder leftOuterJoin(String name) {
        builder.append(SPACE).append(LEFT_OUTER_JOIN).append(SPACE).append(name);
        return this;
    }

    public QueryBuilder rightOuterJoin(String name) {
        builder.append(SPACE).append(RIGHT_OUTER_JOIN).append(SPACE).append(name);
        return this;
    }

    public QueryBuilder fullOuterJoin(String name) {
        builder.append(SPACE).append(FULL_OUTER_JOIN).append(SPACE).append(name);
        return this;
    }

    public QueryBuilder on(String column1, String column2) {
        builder.append(SPACE).append(ON).append(SPACE)
                .append(column1).append(EQUALS).append(column2);
        return this;
    }

    public QueryBuilder asc() {
        builder.append(ASC);
        return this;
    }

    public QueryBuilder desc() {
        builder.append(DESC);
        return this;
    }

    public QueryBuilder select(String... columns) {
        if (columns.length > 0) {
            builder.append(SELECT);
            for (String c : columns) {
                builder.append(SPACE).append(c).append(COMA);
            }
            builder.delete(builder.lastIndexOf(COMA), builder.length());
        }
        return this;
    }

    public QueryBuilder insertInto(String name) {
        builder.append(SPACE).append(INSERT_INTO).append(SPACE).append(name);
        return this;
    }

    public QueryBuilder insertParam(String... param) {
        if (param.length > 0) {
            builder.append("(");
            for (String p : param) {
                builder.append(p).append(COMA).append(SPACE);
            }
            builder.delete(builder.lastIndexOf(COMA), builder.length());
            builder.lastIndexOf(COMA);
            builder.append(")");
        }
        return this;
    }

    public QueryBuilder insertParamValues(String... param) {
        if (param.length > 0) {
            builder.append("VALUES (");
            for (String p : param) {
                preparedMap.put(position.getAndIncrement(), p);
                builder.append(SPACE).append(MARK).append(COMA);
            }
            builder.delete(builder.lastIndexOf(COMA), builder.length());
            builder.append(")");
        }
        return this;
    }

    public boolean isContainsWhere() {
        return builder.toString().contains(WHERE);
    }

    public QueryBuilder or(String column, String name) {
        builder.append(addCommand(AND, column, name));
        return this;
    }

    public QueryBuilder and(String column, String name) {
        builder.append(addCommand(AND, column, name));
        return this;
    }

    public QueryBuilder where(String column, String name) {
        builder.append(addCommand(WHERE, column, name));
        return this;
    }

    public QueryBuilder selectAll() {
        builder.append(SELECT)
                .append(SPACE)
                .append(ALL)
                .append(SPACE);
        return this;
    }

    public QueryBuilder selectCount() {
        builder.append(SELECT)
                .append(SPACE)
                .append(COUNT)
                .append(SPACE);
        return this;
    }

    private String addCommand(String sql, String input) {
        preparedMap.put(position.getAndIncrement(), input);
        StringBuilder sb = new StringBuilder();
        sb.append(SPACE)
                .append(sql)
                .append(SPACE)
                .append(MARK)
                .append(SPACE);
        return sb.toString();
    }

    private String addCommand(String sql, String column, String input) {
        preparedMap.put(position.getAndIncrement(), input);
        StringBuilder sb = new StringBuilder();
        sb.append(SPACE)
                .append(sql)
                .append(SPACE)
                .append(column)
                .append(EQUALS_WITH_PARAM)
                .append(SPACE);
        return sb.toString();
    }

    public QueryBuilder costRange(String from, String to) {
        if (StringUtils.isNoneBlank(from) && StringUtils.isNoneBlank(to)) {
            preparedMap.put(position.getAndIncrement(), from);
            preparedMap.put(position.getAndIncrement(), to);
            builder.append(andOrWhere()).append(SPACE)
                    .append("(")
                    .append(PRODUCT_COST).append(UPPER).append(MARK).append(SPACE)
                    .append(AND).append(SPACE)
                    .append(PRODUCT_COST).append(LOWER).append(MARK)
                    .append(")");
        } else if (StringUtils.isNoneBlank(from)) {
            preparedMap.put(position.getAndIncrement(), from);
            builder.append(SPACE).append(andOrWhere()).append(SPACE).append(PRODUCT_COST).append(UPPER).append(MARK).append(SPACE);
        } else if (StringUtils.isNoneBlank(to)) {
            preparedMap.put(position.getAndIncrement(), to);
            builder.append(SPACE).append(andOrWhere()).append(SPACE).append(PRODUCT_COST).append(LOWER).append(MARK).append(SPACE);
        }
        return this;
    }

    public QueryBuilder whereInt(String column, String name) {
        builder.append(SPACE).append(WHERE).append(SPACE).append(column)
                .append("=").append(name);
        return this;
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
