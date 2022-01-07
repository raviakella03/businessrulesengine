package com.ravi.interview.businessrulesengine.service;

import com.ravi.interview.businessrulesengine.utils.OrderActions;
import com.ravi.interview.businessrulesengine.utils.ShippingLabelType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderProcessing {
    public String processOrder(PurchasedProduct purchasedProduct) {
        String returnValue = "";
        switch (purchasedProduct.getProductType()) {
            case PHYSICAL_PRODUCT:
                PhysicalProduct physicalProduct = new PhysicalProduct();
                physicalProduct = (PhysicalProduct) purchasedProduct;
                log.info("Physical Product received for order processing");
                log.info("Setting commission to agent as true");
                purchasedProduct.setCommissionPayment(true);
                log.info("Setting shipping label as Original");
                purchasedProduct.setLabelType(ShippingLabelType.ORIGINAL);
                log.info("Processing order...");
                returnValue = physicalProduct.processPhysicalProductOrder();
                log.info("Return value after processing order the \n" + returnValue);
                break;
            /*case BOOK:
                Book purchasedBook = new Book();
                purchasedBook = (Book) purchasedProduct;*/

            default:
                log.error("Invalid product purchased");
        }
        return returnValue;
    }
}