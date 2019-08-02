package com.plietnov.webshop.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Sneakers extends Clothes {
    private String sneakersType;
    private String color;
    private String size;

    public Sneakers() {
    }

    public Sneakers(int id, String nameOfProduct,
                    Manufacturer manufacturer, Category category, String cost, String gender,
                    String season, String sneakersType, String color, String size, String pictures) {
        super(id, nameOfProduct, manufacturer, category, cost, gender, season, pictures);
        this.sneakersType = sneakersType;
        this.color = color;
        this.size = size;
    }

    public String getSneakersType() {
        return sneakersType;
    }

    public void setSneakersType(String sneakersType) {
        this.sneakersType = sneakersType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (o.getClass() != this.getClass())) {
            return false;
        }
        Sneakers sneakers = (Sneakers) o;
        return getId() == (sneakers.getId());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("Id", getId())
                .append("ProductName", getNameOfProduct())
                .append("manufacturer", getManufacturer())
                .append("category", getCategory())
                .append("cost", getCost())
                .append("gender", getGender())
                .append("season", getSeason())
                .append("sneakersType", sneakersType)
                .append("color", color)
                .append("size", size)
                .toString();
    }
}
