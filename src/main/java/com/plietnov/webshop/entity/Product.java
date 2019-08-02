package com.plietnov.webshop.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;

public abstract class Product implements Serializable {

    private Integer id;
    private String nameOfProduct;

    public Product() {
    }

    public Product(int id, String nameOfProduct) {
        this.id = id;
        this.nameOfProduct = nameOfProduct;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("nameOfProduct").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                nameOfProduct.equals(product.nameOfProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOfProduct);
    }
}
