package com.ravi.interview.businessrulesengine.utils;

import com.ravi.interview.businessrulesengine.service.*;
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
        } else if (purchasedProduct instanceof Membership && purchasedProduct.getLabelType() == ShippingLabelType.EMAIL) {
            Membership membershipOrder = (Membership) purchasedProduct;
            returnValue = sendConfirmationToCustomer(membershipOrder.getShippingAddress());
        } else if (purchasedProduct instanceof SkiLesson && purchasedProduct.getLabelType() == ShippingLabelType.EMAIL) {
            SkiLesson skiLesson = (SkiLesson) purchasedProduct;
            returnValue = addFreeFirstAidVideo(skiLesson.getShippingAddress());
        } else {
            returnValue = "Invalid shipping label type";
        }
        return returnValue;
    }

    private String sendConfirmationToCustomer(String shippingAddress) {
        return "Confirmation mail sent to " + shippingAddress + ".";
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

    public String activateMembership(Membership membership, MembershipType membershipType) {
        String returnValue;
        //call a setter to set the membership level
        membership.setMembershipLevel(membershipType);
        if (membership.getMembershipLevel().equals(membershipType)) {
            returnValue = "Activated " + membership.getName() + " - " + membership.getMembershipLevel() + " for " + membership.getShippingAddress().split("@")[0] + ".";
            returnValue += "\n" + printLabel(membership);
        } else {
            returnValue = "Error while activating membership.";
        }
        return returnValue;
    }

    public String updateMembership(Membership membership, MembershipType membershipType) {
        String returnValue;
        MembershipType existingMembershipLevel = membership.getMembershipLevel();
        if (membership.getMembershipLevel().equals(MembershipType.NO_MEMBERSHIP)) {
            log.info("Membership activation received. Activating membership.");
            returnValue = activateMembership(membership, membershipType);
        } else {
            log.info("Updating membership level for " + membership.getShippingAddress().split("@")[0] + " from " +
                    membership.getMembershipLevel() + " to " + membershipType);
            membership.setMembershipLevel(membershipType);
            if (membership.getMembershipLevel().equals(membershipType)) {
                returnValue = "Changed membership level for " + membership.getShippingAddress().split("@")[0];
                returnValue += "\nOld: " + existingMembershipLevel + "\nNew: " + membership.getMembershipLevel();
                returnValue += "\n" + printLabel(membership);
            } else {
                returnValue = "Error while activating membership.";
            }
        }
        return returnValue;
    }

    public String addSkiLesson(SkiLesson skiLesson, MembershipType membershipType) {
        String returnValue;
        if(skiLesson.getMembershipLevel().equals(MembershipType.SKI_LESSON_ONLY) || skiLesson.getMembershipLevel().equals(MembershipType.FULL_ACCESS_SKI_LESSON)) {
            returnValue = "Ski Lesson already available for you along with Free First-Aid video.";
        } else if (skiLesson.getMembershipLevel().equals(membershipType)) {
            returnValue = "Invalid membership level selected.";
        } else if (skiLesson.getMembershipLevel().equals(MembershipType.NO_SKI_LESSON) && membershipType.equals(MembershipType.SKI_LESSON_ONLY)){
            skiLesson.setMembershipLevel(membershipType);
            if (skiLesson.getMembershipLevel().equals(membershipType)) {
                returnValue = "Ski Lesson purchased successfully.";
                returnValue += "\n" + printLabel(skiLesson);
            } else {
                returnValue = "Error while purchasing Ski Lesson";
                log.error(returnValue);
            }
        } else {
            returnValue = "Invalid Ski lesson type received.";
        }
        return returnValue;
    }
    public String addFreeFirstAidVideo(String shippingAddress) {
        return "Added a free \"First Aid\" video to your purchase.";
    }
}
