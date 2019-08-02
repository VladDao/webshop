package com.plietnov.webshop.bean;

import com.plietnov.webshop.entity.Product;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class RepositoryBean implements Serializable {

    private String loverCost = "0";

    private String upperCost = String.valueOf(Integer.MAX_VALUE);

    private String orderBy;

    private String page;

    private String pageNumber;

    private String pageAmount;

    private String elementPerPage;

    private String numberOfElementPerPage;

    private List<Product> productList;

    private Map<String, String[]> filter;

    private boolean directOrder = true;

    public boolean isDirectOrder() {
        return directOrder;
    }

    public void setDirectOrder(boolean directOrder) {
        this.directOrder = directOrder;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getLoverCost() {
        return loverCost;
    }

    public void setLoverCost(String loverCost) {
        this.loverCost = loverCost;
    }

    public String getUpperCost() {
        return upperCost;
    }

    public void setUpperCost(String upperCost) {
        this.upperCost = upperCost;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getPage() {
        return page;
    }

    public void setCurrentPage(String page) {
        this.page = page;
    }

    public String getCurrentPage() {
        return page;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public String getPageAmount() {
        return pageAmount;
    }

    public void setPageAmount(String pageAmount) {
        this.pageAmount = pageAmount;
    }

    public String getNumberOfElementPerPage() {
        return numberOfElementPerPage;
    }

    public void setNumberOfElementPerPage(String numberOfElementPerPage) {
        this.numberOfElementPerPage = numberOfElementPerPage;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getElementPerPage() {
        return elementPerPage;
    }

    public void setElementPerPage(String elementPerPage) {
        this.elementPerPage = elementPerPage;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Map<String, String[]> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, String[]> filter) {
        this.filter = filter;
    }
}
