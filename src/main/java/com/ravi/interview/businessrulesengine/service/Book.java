package com.ravi.interview.businessrulesengine.service;

import com.ravi.interview.businessrulesengine.utils.ProductType;

public class Book extends PurchasedProduct{
    String shippingAddress;

    public void Book() {
        this.productType = ProductType.BOOK;
    }

    public String getQuantity() {
        return shippingAddress;
    }

    public void setQuantity(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
