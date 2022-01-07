package com.ravi.interview.businessrulesengine.service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderProcessingTest {

    @Test
    public void purchasePhysicalProductPositive() {
        PhysicalProduct physicalProduct = new PhysicalProduct();
        String shippingAddress = "Albert Museum\nLondon\nUK.";
        OrderProcessing orderProcessing = new OrderProcessing();

        String expectedOutput = "Original Shipping Label\n"+ shippingAddress + "\nAgent commission sent";
        physicalProduct.setName("Motor Cycle");
        physicalProduct.setPrice(5000);
        physicalProduct.setShippingAddress(shippingAddress);

        assertEquals(expectedOutput, orderProcessing.processOrder(physicalProduct));
    }

    @Test
    public void purchasePhysicalProductNegative_1() {
        PhysicalProduct physicalProduct = new PhysicalProduct();
        String shippingAddress = null;
        OrderProcessing orderProcessing = new OrderProcessing();

        String expectedOutput = "Invalid/No shipping address received";

        physicalProduct.setName("Motor Cycle");
        physicalProduct.setPrice(5000);
        physicalProduct.setShippingAddress(shippingAddress);

        assertEquals(expectedOutput, orderProcessing.processOrder(physicalProduct));
    }

    @Test
    public void purchaseBookPositive() {
        Book purchasedBook = new Book();
        String shippingAddress = "Central Library\nLondon\nUK.";
        OrderProcessing orderProcessing = new OrderProcessing();

        String expectedOutput = "Original Shipping Label\n"+ shippingAddress;
        expectedOutput += "-----------------------------------------------\nDuplicate Shipping Label\n" + shippingAddress;
        expectedOutput += "\nAgent commission sent";

        purchasedBook.setName("Book of Boba Fett");
        purchasedBook.setQuantity(5);
        purchasedBook.setPrice(10);
        purchasedBook.setShippingAddress(shippingAddress);
        assertEquals(expectedOutput, orderProcessing.processOrder(purchasedBook));
    }

    @Test
    public void purchaseBookInvalidAddress() {
        Book purchasedBook = new Book();
        String shippingAddress = null;
        OrderProcessing orderProcessing = new OrderProcessing();

        String expectedOutput = "Invalid/No shipping address received. Not processing the order.";

        purchasedBook.setName("Book of Boba Fett");
        purchasedBook.setQuantity(10);
        purchasedBook.setPrice(10);
        purchasedBook.setShippingAddress(shippingAddress);
        assertEquals(expectedOutput, orderProcessing.processOrder(purchasedBook));
    }

    @Test
    public void purchaseBookZeroQuantity() {
        Book purchasedBook = new Book();
        String shippingAddress = "Central Library\nLondon\nUK.";
        OrderProcessing orderProcessing = new OrderProcessing();

        String expectedOutput = "No quantity received to send to royalty dept. Not processing the order.";

        purchasedBook.setName("Book of Boba Fett");
        purchasedBook.setQuantity(0);
        purchasedBook.setPrice(10);
        purchasedBook.setShippingAddress(shippingAddress);
        assertEquals(expectedOutput, orderProcessing.processOrder(purchasedBook));
    }
}