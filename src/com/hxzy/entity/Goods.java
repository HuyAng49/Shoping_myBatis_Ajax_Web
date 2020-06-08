package com.hxzy.entity;


public class Goods {
    private Integer id;

    private String productIcon;

    private String productName;

    private String productTypes;

    private Float univalence;

    private Integer storage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon == null ? null : productIcon.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(String productTypes) {
        this.productTypes = productTypes == null ? null : productTypes.trim();
    }

    public Float getUnivalence() {
        return univalence;
    }

    public void setUnivalence(Float univalence) {
        this.univalence = univalence;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }
}