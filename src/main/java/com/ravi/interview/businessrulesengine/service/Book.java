package com.ravi.interview.businessrulesengine.service;

import com.ravi.interview.businessrulesengine.utils.ProductType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Book extends PurchasedProduct {
    String shippingAddress;
    int quantity;

    public void Book() {
        this.productType = ProductType.BOOK;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShippingAddress() { return shippingAddress; }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String processPhysicalProductOrder() {
        String returnValue = "";

        return returnValue;
    }
}
