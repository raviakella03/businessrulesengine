package com.ravi.interview.businessrulesengine.service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderProcessingTest {

    @Test
    public void purchasePhysicalProduct() {
        PhysicalProduct physicalProduct = new PhysicalProduct();
        String shippingAddress = "Albert Museum\nLondon\nUK.";
        OrderProcessing orderProcessing = new OrderProcessing();

        String expectedOutput = "Original Shipping Label\n"+ shippingAddress + "\nAgent commission sent";

        physicalProduct.setName("Motor Cycle");
        physicalProduct.setShippingAddress(shippingAddress);

        assertEquals(expectedOutput, orderProcessing.processOrder(physicalProduct));
    }

    /*@Test
    public void purchaseBook() {
        PurchasedProduct purchasedProduct = new Book();
        String shippingAddress = "Albert Museum\nLondon\nUK.";
        OrderProcessing orderProcessing = new OrderProcessing();

        String expectedOutput = shippingAddress + "\nOriginal Shipping Label\nDuplicate Shipping Label\nAgent commission sent";

        purchasedProduct.setName("Book of Boba Fett");
    }*/
}