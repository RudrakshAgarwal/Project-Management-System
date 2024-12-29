package com.projectManagement.service;

import com.projectManagement.model.PlanType;
import com.projectManagement.model.Subscription;
import com.projectManagement.model.User;

public interface SubscriptionService {
    Subscription createSubscription(User user);

    Subscription getUserSubscription(Long userId) throws Exception;

    Subscription upgradeSubscription(Long userId, PlanType planType);

    boolean isValid(Subscription subscription);

}
