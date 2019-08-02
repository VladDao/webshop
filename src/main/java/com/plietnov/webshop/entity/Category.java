package com.plietnov.webshop.entity;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


@Data
public class Category {
    private int id;

    private String title;

    public Category() {
    }

    public Category(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("title", title)
                .toString();
    }

}
