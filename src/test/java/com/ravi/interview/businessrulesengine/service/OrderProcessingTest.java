package com.ravi.interview.businessrulesengine.service;

import com.ravi.interview.businessrulesengine.utils.MembershipType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class OrderProcessingTest {

    @Test
    public void purchasePhysicalProductPositive() {
        PhysicalProduct physicalProduct = new PhysicalProduct();
        String shippingAddress = "Albert Museum\nLondon\nUK.";
        OrderProcessing orderProcessing = new OrderProcessing();

        physicalProduct.setName("Motor Cycle");
        physicalProduct.setPrice(5000);
        physicalProduct.setShippingAddress(shippingAddress);

        String expectedOutput = "Original Shipping Label\n" + shippingAddress + "\nAgent commission sent.";

        assertEquals(expectedOutput, orderProcessing.processOrder(physicalProduct));
    }

    @Test
    public void purchasePhysicalProductNegative_1() {
        PhysicalProduct physicalProduct = new PhysicalProduct();
        String shippingAddress = null;
        OrderProcessing orderProcessing = new OrderProcessing();

        physicalProduct.setName("Motor Cycle");
        physicalProduct.setPrice(5000);
        physicalProduct.setShippingAddress(shippingAddress);

        String expectedOutput = "Invalid/No shipping address received. Not processing the order";

        assertEquals(expectedOutput, orderProcessing.processOrder(physicalProduct));
    }

    @Test
    public void purchaseBookPositive() {
        Book purchasedBook = new Book();
        String shippingAddress = "Central Library\nLondon\nUK.";
        OrderProcessing orderProcessing = new OrderProcessing();

        purchasedBook.setName("Book of Boba Fett");
        purchasedBook.setQuantity(5);
        purchasedBook.setPrice(10);
        purchasedBook.setShippingAddress(shippingAddress);

        String expectedOutput = "Original Shipping Label\n" + shippingAddress;
        expectedOutput += "\n-----------------------------------------------\nDuplicate Shipping Label\n" + shippingAddress;
        expectedOutput += "\nAgent commission sent.";

        assertEquals(expectedOutput, orderProcessing.processOrder(purchasedBook));
    }

    @Test
    public void purchaseBookInvalidAddress() {
        Book purchasedBook = new Book();
        String shippingAddress = null;
        OrderProcessing orderProcessing = new OrderProcessing();

        purchasedBook.setName("Book of Boba Fett");
        purchasedBook.setQuantity(10);
        purchasedBook.setPrice(10);
        purchasedBook.setShippingAddress(shippingAddress);

        String expectedOutput = "No Shipping address received for order processing. Not processing the order.";

        assertEquals(expectedOutput, orderProcessing.processOrder(purchasedBook));
    }

    @Test
    public void purchaseBookZeroQuantity() {
        Book purchasedBook = new Book();
        String shippingAddress = "Central Library\nLondon\nUK.";
        OrderProcessing orderProcessing = new OrderProcessing();

        purchasedBook.setName("Book of Boba Fett");
        purchasedBook.setQuantity(0);
        purchasedBook.setPrice(1000);
        purchasedBook.setShippingAddress(shippingAddress);

        String expectedOutput = "No quantity received to send to royalty dept. Not processing the order.";

        assertEquals(expectedOutput, orderProcessing.processOrder(purchasedBook));
    }

    @Test
    public void purchaseNewMembership() {
        Membership membership = new Membership();
        String shippingAddress = "ravi@membership.com";
        String membershipName = "OTT";
        double membershipPrice = 10;
        MembershipType membershipType = MembershipType.ACCESS_1;
        OrderProcessing orderProcessing = new OrderProcessing();

        String expectedOutput = "Activated " + membershipName + " - " + MembershipType.ACCESS_1 + " for " + shippingAddress.split("@")[0] + ".";
        expectedOutput += "Confirmation mail sent to " + shippingAddress;

        assertEquals(expectedOutput, orderProcessing.processOrder(membershipName, shippingAddress, membershipPrice, membershipType, membership));
    }

    @Test
    public void upgradeMembership() {
        Membership membership = new Membership();
        String shippingAddress = "ravi@membership.com";
        String membershipName = "OTT";
        double membershipPrice = 100;
        MembershipType existingMembershipLevel = MembershipType.ACCESS_2;
        MembershipType newMembershipLevel = MembershipType.FULL_ACCESS;

        OrderProcessing orderProcessing = new OrderProcessing();

        membership.setName(membershipName);
        membership.setMembershipLevel(existingMembershipLevel);

        String expectedOutput = "Changed membership level for " + shippingAddress.split("@")[0];
        expectedOutput += "\nOld: " + existingMembershipLevel + "\nNew: " + newMembershipLevel;
        expectedOutput += "Confirmation mail sent to " + shippingAddress;

        assertEquals(expectedOutput, orderProcessing.processOrder(membershipName, shippingAddress, membershipPrice, newMembershipLevel, membership));
    }

    @Test
    public void removeMembership() {
        Membership membership = new Membership();
        String shippingAddress = "ravi@membership.com";
        String membershipName = "OTT";
        double membershipPrice = 100;
        MembershipType existingMembershipLevel = MembershipType.ACCESS_2;
        MembershipType newMembershipLevel = MembershipType.FULL_ACCESS;
        OrderProcessing orderProcessing = new OrderProcessing();

        String expectedOutput = "Removed membership for " + shippingAddress.split("@")[0] + ".";

        assertEquals(expectedOutput, orderProcessing.processOrder(membershipName, shippingAddress, membershipPrice, newMembershipLevel, membership));
    }
}