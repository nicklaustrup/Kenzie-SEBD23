package com.kenzie.subscriptions;

import com.kenzie.subscriptions.exceptions.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubscriptionLibraryTest {
    private String asin = "ABC123";
    private String customerId = "123456789";
    private String subscriptionId = String.format("%s-%s", customerId, asin);

    @Mock(name="subscriptionDao")
    private SubscriptionDao subscriptionDao;

    @Mock(name="emailServiceClient")
    private EmailServiceClient emailServiceClient;

    @InjectMocks
    private SubscriptionLibrary subscriptionLibrary;

    @BeforeEach
    public void setup() {
    initMocks(this);

    }

    @Test
    public void addMonthlySubscription_newSubscription_sendsNewSubscriptionEmail() throws InvalidInputException {
        Subscription subscription = new MonthlySubscription(subscriptionId, asin, customerId);
        when(subscriptionDao.createSubscription(any())).thenReturn(true);

        // WHEN: you send a new Subscription email
        subscriptionLibrary.addMonthlySubscription(asin, customerId);

        // THEN: verify
        verify(emailServiceClient).sendNewSubscriptionEmail(subscription);
        assertEquals(subscriptionId, subscription.getSubscriptionId());

    }

    @Test
    public void cancelSubscription_subscriptionDoesNotExist_throwsInvalidInputException() {
        when(subscriptionDao.deleteSubscription(subscriptionId)).thenReturn(null);

        assertThrows(InvalidInputException.class,
                () -> subscriptionLibrary.cancelSubscription(subscriptionId));
        verify(subscriptionDao).deleteSubscription(subscriptionId);
    }
}
