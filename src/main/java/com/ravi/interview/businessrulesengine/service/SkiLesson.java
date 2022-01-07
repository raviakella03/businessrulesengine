package com.ravi.interview.businessrulesengine.service;

import com.ravi.interview.businessrulesengine.utils.MembershipType;
import com.ravi.interview.businessrulesengine.utils.OrderActions;
import com.ravi.interview.businessrulesengine.utils.ProductType;
import com.ravi.interview.businessrulesengine.utils.ShippingLabelType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SkiLesson extends PurchasedProduct{
    MembershipType membershipLevel;

    public SkiLesson() {
        this.productType = ProductType.SKI_LESSON_VIDEO;
        this.membershipLevel = MembershipType.NO_SKI_LESSON;
        this.labelType = ShippingLabelType.EMAIL;
        this.commissionPayment = false;
    }

    public MembershipType getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(MembershipType membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public String processSkiLessonOrder(MembershipType membershipType) {
        String returnValue;
        OrderActions orderActions = new OrderActions();
        returnValue = orderActions.addSkiLesson(this, membershipType);
        return returnValue;
    }
}
