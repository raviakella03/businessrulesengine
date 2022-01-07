package com.ravi.interview.businessrulesengine.service;

import com.ravi.interview.businessrulesengine.utils.MembershipType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderProcessing {
    public String processOrder(PurchasedProduct purchasedProduct) {
        String returnValue;
        if (null == purchasedProduct) {
            returnValue = "Invalid product received for order processing.";
            log.error(returnValue);
            return returnValue;
        }
        log.info("Processing order...");
        if (null == purchasedProduct.getProductType()) {
            returnValue = "Product Type is set to null for the current product";
            log.error("Product type is set to null for Book");
            return returnValue;
        }
        switch (purchasedProduct.getProductType()) {
            case PHYSICAL_PRODUCT:
                log.info("Physical Product received for order processing");
                PhysicalProduct physicalProduct = (PhysicalProduct) purchasedProduct;

                returnValue = physicalProduct.processPhysicalProductOrder();

                log.info("Return value after processing order the \n" + returnValue);
                break;
            case BOOK:
                log.info("Book received by order processing");
                Book purchasedBook = (Book) purchasedProduct;

                returnValue = purchasedBook.processBookOrder();

                log.info("Return value after processing order the \n" + returnValue);
                break;
            default:
                returnValue = "Invalid product purchased";
                log.info(returnValue);
        }
        return returnValue;
    }

    public String processOrder(PurchasedProduct purchasedProduct, MembershipType membershipType) {
        String returnValue;
        if (null == purchasedProduct) {
            returnValue = "Invalid membership order received.";
            log.error(returnValue);
            return returnValue;
        }
        log.info("Processing order...");
        if (null == purchasedProduct.getProductType()) {
            returnValue = "Product Type is set to null for the current product";
            log.error("Product type is set to null for Book");
            return returnValue;
        }

        switch (purchasedProduct.getProductType()) {
            case MEMBERSHIP:
                if (null != membershipType) {
                    log.info("Membership order received for order processing");
                    Membership membershipOrder = (Membership) purchasedProduct;
                    returnValue = membershipOrder.processMembershipOrder(membershipType);
                } else {
                    returnValue = "Invalid membership type received.";
                }
                break;
            case SKI_LESSON_VIDEO:

                if (null != membershipType) {
                    log.info("Ski Lesson Video received for order processing");
                    SkiLesson skiLesson = (SkiLesson) purchasedProduct;
                    returnValue = skiLesson.processSkiLessonOrder(membershipType);
                } else {
                    returnValue = "Ski Lesson type received as null.";
                    log.error(returnValue);
                    return returnValue;
                }
                break;
            default:
                returnValue = "Invalid product purchased";
                log.info(returnValue);
        }
        return returnValue;
    }
}