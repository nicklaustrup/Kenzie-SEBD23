package com.kenzie.subscriptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailServiceClientTest {

    private String customerId = "barnikki";
    private String email = "barnikki@amazon.com";
    private Customer customer = new Customer(customerId, email, "Nikki", "Barry");
    Subscription subscription = new MonthlySubscription("barnikki-B06XH36LTN", "B06XH36LTN", customerId);

    @Mock(name="customerDoa")
    private CustomerDao customerDao;

    @Mock(name="emailService")
    private EmailService emailService;

    @InjectMocks
    private EmailServiceClient emailServiceClient;

    @BeforeEach
    public void setup() {
        initMocks(this);
        }

    @Test
    public void sendNewSubscriptionEmail_emailAddressExists_returnTrue() {
        // GIVEN: valid subscription & customer data (with an email address)
        when(customerDao.getCustomer(customerId)).thenReturn(customer);

        // WHEN: you send a new Subscription email
        emailServiceClient.sendNewSubscriptionEmail(subscription);

        // THEN: Send the email and return true
        verify(emailService).sendEmail(customer.getEmailAddress(),
                (String.format("Dear %s, you have a new subscription %s for item %s",
                customer.getFullName(), subscription.getSubscriptionId(), subscription.getAsin())));
    }
}
