package com.ravi.interview.businessrulesengine.service;

import com.ravi.interview.businessrulesengine.utils.OrderActions;
import com.ravi.interview.businessrulesengine.utils.ShippingLabelType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderProcessing {
    public String processOrder(PurchasedProduct purchasedProduct) {
        String returnValue = "";
        log.info("Processing order...");
        switch (purchasedProduct.getProductType()) {
            case PHYSICAL_PRODUCT:
                log.info("Physical Product received for order processing");
                PhysicalProduct physicalProduct = (PhysicalProduct) purchasedProduct;
                returnValue = physicalProduct.processPhysicalProductOrder();
                log.info("Return value after processing order the \n" + returnValue);
                break;
            default:
                log.error("Invalid product purchased");
        }
        return returnValue;
    }
}