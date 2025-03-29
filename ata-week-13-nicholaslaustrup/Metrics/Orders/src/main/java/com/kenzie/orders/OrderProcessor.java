package com.kenzie.orders;


import com.amazonaws.services.cloudwatch.model.StandardUnit;
import com.kenzie.orders.resources.CreditProcessor;
import com.kenzie.orders.resources.CustomerManager;
import com.kenzie.orders.resources.InventoryManager;
import com.kenzie.orders.resources.Order;

/**
 * Class representing final state of the coding activity.
 */
public class OrderProcessor {

    // TODO - refactor this class so all dependencies are accepted via constructor
    private CustomerManager customerManager;
    private InventoryManager inventoryManager;
    private MetricsPublisher metricsPublisher;
    private CreditProcessor creditProcessor;

    /**
     * Constructs a OrderProcessor object.
     *
     * @param metricsPublisher Used to publish metrics to CloudWatch.
     */
    public OrderProcessor(MetricsPublisher metricsPublisher, CustomerManager customerManager, InventoryManager inventoryManager, CreditProcessor creditProcessor) {
        this.metricsPublisher = metricsPublisher;
        this.customerManager = customerManager;
        this.inventoryManager = inventoryManager;
        this.creditProcessor = creditProcessor;
    }

    /**
     * Processes an order and payment.
     *
     * @param newOrder The order to be processed
     */
    public void processOrder(Order newOrder) {
        double startTime = System.currentTimeMillis();
        try {
            creditProcessor.processPayment(newOrder);
            customerManager.verifyCustomerInfo(newOrder);
            int pickListNumber = inventoryManager.createPickList(newOrder);
            inventoryManager.processPickList(pickListNumber);

            //log metrics
            metricsPublisher.addMetric("ORDER_TOTALS", newOrder.getTotalPrice(), StandardUnit.None);
        } catch (Exception e) {
            System.out.println("Error processing order " + newOrder.getOrderId());

            //log metrics
            metricsPublisher.addMetric("ORDER_FAILURES", 1, StandardUnit.Count);
        }
        double endTime = System.currentTimeMillis();
        double timeInMilliseconds = endTime - startTime;
        metricsPublisher.addMetric("ORDER_PROCESSING_TIMES", timeInMilliseconds, StandardUnit.Milliseconds);
        metricsPublisher.addMetric("ORDER_FAILURES", 0, StandardUnit.Count);

    }
}
