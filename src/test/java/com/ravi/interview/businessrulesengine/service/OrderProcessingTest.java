package com.ravi.interview.businessrulesengine.service;

import com.ravi.interview.businessrulesengine.utils.MembershipType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class OrderProcessingTest {

    @Test
    public void testPurchasePhysicalProductPositive() {
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
    public void testPurchasePhysicalProductNegative_1() {
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
    public void testPurchaseBookPositive() {
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
    public void testPurchaseBookInvalidAddress() {
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
    public void testPurchaseBookZeroQuantity() {
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
    public void testNullPurchaseBookZeroQuantity() {
        Book purchasedBook = new Book();
        String shippingAddress = "Central Library\nLondon\nUK.";
        OrderProcessing orderProcessing = new OrderProcessing();

        purchasedBook.setName("Book of Boba Fett");
        purchasedBook.setQuantity(0);
        purchasedBook.setPrice(1000);
        purchasedBook.setShippingAddress(shippingAddress);

        purchasedBook = null;
        String expectedOutput = "Invalid product received for order processing.";

        assertEquals(expectedOutput, orderProcessing.processOrder(purchasedBook));
    }

    @Test
    public void testPurchaseNewMembership() {
        Membership membershipOrder = new Membership();
        OrderProcessing orderProcessing = new OrderProcessing();
        String shippingAddress = "ravi@membership.com";
        String membershipName = "OTT";
        double membershipPrice = 10;
        MembershipType newMembershipLevel = MembershipType.ACCESS_1;

        membershipOrder.setName(membershipName);
        membershipOrder.setPrice(membershipPrice);
        membershipOrder.setShippingAddress(shippingAddress);

        String expectedOutput = "Activated " + membershipName + " - " + MembershipType.ACCESS_1 + " for " + shippingAddress.split("@")[0] + ".";
        expectedOutput += "\nConfirmation mail sent to " + shippingAddress + ".";

        assertEquals(expectedOutput, orderProcessing.processOrder(membershipOrder, newMembershipLevel));
    }

    @Test
    public void testUpgradeMembership() {
        Membership membershipOrder = new Membership();
        String shippingAddress = "ravi@membership.com";
        String membershipName = "OTT";
        double membershipPrice = 100;
        MembershipType existingMembershipLevel = MembershipType.ACCESS_2;
        MembershipType newMembershipLevel = MembershipType.FULL_ACCESS_NO_SKI_LESSON;

        OrderProcessing orderProcessing = new OrderProcessing();

        membershipOrder.setName(membershipName);
        membershipOrder.setPrice(membershipPrice);
        membershipOrder.setShippingAddress(shippingAddress);
        membershipOrder.setMembershipLevel(existingMembershipLevel);

        String expectedOutput = "Changed membership level for " + shippingAddress.split("@")[0];
        expectedOutput += "\nOld: " + existingMembershipLevel + "\nNew: " + newMembershipLevel;
        expectedOutput += "\n" + "Confirmation mail sent to " + shippingAddress + ".";

        assertEquals(expectedOutput, orderProcessing.processOrder(membershipOrder, newMembershipLevel));
    }

    @Test
    public void testNullUpgradeMembership() {
        Membership membershipOrder = new Membership();
        String shippingAddress = "ravi@membership.com";
        String membershipName = "OTT";
        double membershipPrice = 100;
        MembershipType existingMembershipLevel = MembershipType.ACCESS_2;
        MembershipType newMembershipLevel = MembershipType.FULL_ACCESS_NO_SKI_LESSON;

        OrderProcessing orderProcessing = new OrderProcessing();

        membershipOrder.setName(membershipName);
        membershipOrder.setPrice(membershipPrice);
        membershipOrder.setShippingAddress(shippingAddress);
        membershipOrder.setMembershipLevel(existingMembershipLevel);

        String expectedOutput = "Invalid membership order received.";
        membershipOrder = null;
        assertEquals(expectedOutput, orderProcessing.processOrder(membershipOrder, newMembershipLevel));
    }

    @Test
    public void testUpgradeMembershipNullMembershipType() {
        Membership membershipOrder = new Membership();
        String shippingAddress = "ravi@membership.com";
        String membershipName = "OTT";
        double membershipPrice = 100;
        MembershipType existingMembershipLevel = MembershipType.ACCESS_2;
        MembershipType newMembershipLevel = MembershipType.FULL_ACCESS_NO_SKI_LESSON;

        OrderProcessing orderProcessing = new OrderProcessing();

        membershipOrder.setName(membershipName);
        membershipOrder.setPrice(membershipPrice);
        membershipOrder.setShippingAddress(shippingAddress);
        membershipOrder.setMembershipLevel(existingMembershipLevel);

        String expectedOutput = "Invalid membership type received.";
        newMembershipLevel = null;
        assertEquals(expectedOutput, orderProcessing.processOrder(membershipOrder, newMembershipLevel));
    }

    @Test
    public void testSkiLessonPurchase() {
        SkiLesson skiLesson = new SkiLesson();
        String shippingAddress = "ravi@membership.com";
        MembershipType newMembershipType = MembershipType.SKI_LESSON_ONLY;
        String videoName = "Ski Lesson";
        double videoPrice = 20;

        OrderProcessing orderProcessing = new OrderProcessing();

        skiLesson.setName(videoName);
        skiLesson.setPrice(videoPrice);
        skiLesson.setShippingAddress(shippingAddress);

        String expectedOutput = "Ski Lesson purchased successfully.\nAdded a free \"First Aid\" video to your purchase.";

        assertEquals(expectedOutput, orderProcessing.processOrder(skiLesson, newMembershipType));
    }

    @Test
    public void testSkiLessonPurchaseWrongMembershipLevel() {
        SkiLesson skiLesson = new SkiLesson();
        String shippingAddress = "ravi@membership.com";
        MembershipType newMembershipType = MembershipType.NO_MEMBERSHIP;
        String videoName = "Ski Lesson";
        double videoPrice = 20;

        OrderProcessing orderProcessing = new OrderProcessing();

        skiLesson.setName(videoName);
        skiLesson.setPrice(videoPrice);
        skiLesson.setShippingAddress(shippingAddress);

        String expectedOutput = "Invalid Ski lesson type received.";

        assertEquals(expectedOutput, orderProcessing.processOrder(skiLesson, newMembershipType));
    }

    @Test
    public void testSkiLessonPurchaseNullMembershipLevel() {
        SkiLesson skiLesson = new SkiLesson();
        String shippingAddress = "ravi@membership.com";
        MembershipType newMembershipType = MembershipType.SKI_LESSON_ONLY;
        String videoName = "Ski Lesson";
        double videoPrice = 20;

        OrderProcessing orderProcessing = new OrderProcessing();

        skiLesson.setName(videoName);
        skiLesson.setPrice(videoPrice);
        skiLesson.setShippingAddress(shippingAddress);

        String expectedOutput = "Ski Lesson type received as null.";
        newMembershipType = null;
        assertEquals(expectedOutput, orderProcessing.processOrder(skiLesson, newMembershipType));
    }

}