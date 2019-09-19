//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.09.16 at 04:08:37 PM EDT 
//


package com.gobucket.dynamodb.repositories.models.coupon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="coupon")
public class Coupon {

    protected int pid;
    protected int nid;
    protected String zid;
    protected Long couponid;
    protected String link;
    protected String description;
    protected String image;
    protected String activedate;
    protected String shutoff;
    protected String expiration;
    protected String majorCategory;
    protected String minorCategory;
    protected String brand;
    protected int value;
    protected int geotarget;

    public Coupon() {
    	
    }
    @DynamoDBAttribute
    public int getPID() {
        return pid;
    }

    public void setPID(int value) {
        this.pid = value;
    }

    @DynamoDBAttribute
    public int getNID() {
        return nid;
    }

    public void setNID(int value) {
        this.nid = value;
    }

    @DynamoDBAttribute
    public String getZID() {
        return zid;
    }

    public void setZID(String value) {
        this.zid = value;
    }

    @DynamoDBHashKey
    public Long getCouponid() {
        return couponid;
    }

    public void setCouponid(Long value) {
        this.couponid = value;
    }

    @DynamoDBAttribute
    public String getLink() {
        return link;
    }

    public void setLink(String value) {
        this.link = value;
    }

    @DynamoDBAttribute
    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    @DynamoDBAttribute
    public String getImage() {
        return image;
    }

    public void setImage(String value) {
        this.image = value;
    }

    @DynamoDBAttribute
    public String getActivedate() {
        return activedate;
    }

    public void setActivedate(String value) {
        this.activedate = value;
    }

    @DynamoDBAttribute
    public String getShutoff() {
        return shutoff;
    }

    public void setShutoff(String value) {
        this.shutoff = value;
    }

    @DynamoDBAttribute
    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String value) {
        this.expiration = value;
    }

    @DynamoDBAttribute
    public String getMajorCategory() {
        return majorCategory;
    }

    public void setMajorCategory(String value) {
        this.majorCategory = value;
    }

    @DynamoDBAttribute
    public String getMinorCategory() {
        return minorCategory;
    }

    public void setMinorCategory(String value) {
        this.minorCategory = value;
    }

    //@DynamoDBIndexHashKey(globalSecondaryIndexName = "idx_global_brand")
    @DynamoDBAttribute
    public String getBrand() {
        return brand;
    }

    public void setBrand(String value) {
        this.brand = value;
    }

    @DynamoDBAttribute
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @DynamoDBAttribute
    public int getGeotarget() {
        return geotarget;
    }

    public void setGeotarget(int value) {
        this.geotarget = value;
    }
}