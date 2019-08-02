package com.plietnov.webshop.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class Order {

    private int id;

    private OrderStatus orderStatus;

    private String stateDetails;

    private Date date;

    private User user;

    private OrderContents orderContents;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("orderStatus", orderStatus)
                .append("stateDetails", stateDetails)
                .append("date", date)
                .append("user", user)
                .append("orderContents", orderContents)
                .toString();
    }
}