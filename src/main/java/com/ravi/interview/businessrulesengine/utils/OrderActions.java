package com.ravi.interview.businessrulesengine.utils;

import com.ravi.interview.businessrulesengine.service.Book;
import com.ravi.interview.businessrulesengine.service.Membership;
import com.ravi.interview.businessrulesengine.service.PhysicalProduct;
import com.ravi.interview.businessrulesengine.service.PurchasedProduct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderActions {
    public String printLabel(PurchasedProduct purchasedProduct) {
        String returnValue;

        if (null == purchasedProduct) {
            return "Invalid/null product received for label printing";
        }
        if (purchasedProduct instanceof PhysicalProduct && purchasedProduct.getLabelType() == ShippingLabelType.ORIGINAL) {
            PhysicalProduct physicalProduct = (PhysicalProduct) purchasedProduct;

            returnValue = printOriginalShippingLabel(physicalProduct.getShippingAddress());

        } else if (purchasedProduct instanceof Book && purchasedProduct.getLabelType() == ShippingLabelType.ORIGINAL_DUPLICATE) {
            Book purchasedBook = (Book) purchasedProduct;

            returnValue = printOriginalShippingLabel(purchasedBook.getShippingAddress());

            if (returnValue.startsWith("Original")) {
                returnValue += "\n" + printDuplicateShippingLabel(purchasedBook.getShippingAddress());
            } else {
                log.info(returnValue);
            }
        } else {
            returnValue = "Invalid shipping label type";
        }
        return returnValue;
    }

    public String printOriginalShippingLabel(String shippingAddress) {
        String returnValue;
        if (null != shippingAddress) {
            log.info("Printing original shipping label");
            returnValue = "Original Shipping Label\n" + shippingAddress;
        } else {
            returnValue = "No Shipping address received for order processing";
            log.error(returnValue);
        }
        return returnValue;
    }

    public String printDuplicateShippingLabel(String shippingAddress) {
        String returnValue = "-----------------------------------------------\n";
        if (null != shippingAddress) {
            log.info("Printing duplicate shipping label for royalty");
            returnValue += "Duplicate Shipping Label\n" + shippingAddress;
        } else {
            returnValue = "No Shipping address set for order processing";
            log.error(returnValue);
        }
        return returnValue;
    }

    public String sendCommissionToAgent(PurchasedProduct purchasedProduct) {
        String returnValue = "";
        if (purchasedProduct.isCommissionPayment()) {
            returnValue = "Agent commission sent.";
        } else {
            returnValue = "No commission to agent";
        }
        return returnValue;
    }

    public String activateMembership(Membership membership) {
        //call a setter to set the membership level
        //call a getter to get the updated membership level
        //send email to the customer about the membership activation or upgrade
        return null;
    }

    public String addFreeFirstAidVideo() {
        //call print Label and add first aid video to it
        return null;
    }
}
