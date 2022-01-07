package com.ravi.interview.businessrulesengine.service;

import com.ravi.interview.businessrulesengine.utils.ProductType;
import com.ravi.interview.businessrulesengine.utils.ShippingLabelType;

public abstract class PurchasedProduct {
    ProductType productType;
    String name;
    ShippingLabelType labelType;
    boolean commissionPayment;
    double price;
    String shippingAddress;

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCommissionPayment() {
        return commissionPayment;
    }

    public void setCommissionPayment(boolean commissionPayment) {
        this.commissionPayment = commissionPayment;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ShippingLabelType getLabelType() {
        return labelType;
    }

    public void setLabelType(ShippingLabelType labelType) {
        this.labelType = labelType;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
