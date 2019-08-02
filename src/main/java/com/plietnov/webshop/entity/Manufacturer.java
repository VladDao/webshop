package com.plietnov.webshop.entity;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Data
public class Manufacturer {
    private Integer id;

    private String title;

    public Manufacturer() {
    }

    public Manufacturer(Integer id, String title) {
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
