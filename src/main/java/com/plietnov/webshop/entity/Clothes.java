package com.plietnov.webshop.entity;

public class Clothes extends Product {

    private Manufacturer manufacturer;
    private Category category;
    private String cost;
    private String gender;
    private String season;
    private String pictures;

    public Clothes() {
    }

    public Clothes(int id, String nameOfProduct,
                   Manufacturer manufacturer, Category category, String cost, String gender, String season, String pictures) {
        super(id, nameOfProduct);
        this.manufacturer = manufacturer;
        this.category = category;
        this.cost = cost;
        this.gender = gender;
        this.season = season;
        this.pictures = pictures;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (o.getClass() != this.getClass())) {
            return false;
        }
        Clothes clothes = (Clothes) o;
        return getId() == (clothes.getId());
    }

    @Override
    public String toString() {

        return "Clothes{" +
                "Id='" + getId() + '\'' +
                ", ProductName='" + getNameOfProduct() + '\'' +
                ", manufacturer='" + getManufacturer() + '\'' +
                ", cost='" + getCost() + '\'' +
                ", gender='" + getGender() + '\'' +
                ", season='" + getSeason() + '\'' +
                '}';
    }
}
