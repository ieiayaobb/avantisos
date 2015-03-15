package com.avantis.os.modules.prod.entity;

import com.avantis.os.common.persistence.DataEntity;

import java.util.Date;

/**
 * Created by admin on 2015/3/12.
 */
public class Product extends DataEntity<Product> {
    private String id;

    private String name;

    private String belong;

    private Date dateTime;

    private Boolean isOut;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getIsOut() {
        return isOut;
    }

    public void setIsOut(Boolean isOut) {
        this.isOut = isOut;
    }
}
