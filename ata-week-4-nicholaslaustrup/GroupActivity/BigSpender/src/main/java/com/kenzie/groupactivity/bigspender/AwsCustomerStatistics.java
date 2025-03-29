package com.kenzie.groupactivity.bigspender;

import com.kenzie.groupactivity.bigspender.compare.CustomerTotalSpendBySpend;
import com.kenzie.groupactivity.bigspender.dao.AwsServiceInvoiceDao;
import com.kenzie.groupactivity.bigspender.types.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class that uses the AWS customer spending data provided by an
 * AwsServiceInvoiceDao to calculate information about the
 * customers.
 */
public class AwsCustomerStatistics {
    private Customer customer;
    private long totalSpend;
    private List<ServiceSpend> serviceSpends;
    private AwsServiceInvoiceDao awsServiceInvoiceDao;


    /**
     * Creates a statistics instance with the provided DAO.
     * @param awsServiceInvoiceDao The AwsServiceInvoiceDao to use
     */
    public AwsCustomerStatistics(AwsServiceInvoiceDao awsServiceInvoiceDao) {
        this.awsServiceInvoiceDao = awsServiceInvoiceDao;
    }

    /**
     * Produces a list of the single service each AWS customer spent the most on,
     * sorted by customer name (ascending).
     * @return A list of CustomerServiceSpend representing the highest service
     *         spend for each customer, sorted by customer name
     */
    public List<CustomerServiceSpend> getTopServiceSpendForEachCustomer() {
        // PARTICIPANTS: Which list needs to be sorted and returned?
        List<CustomerServiceSpend> topCustomerServiceSpends =
                awsServiceInvoiceDao.getHighestServiceSpendsForEachCustomer();

        Collections.sort(topCustomerServiceSpends);

        return topCustomerServiceSpends;
    }

    /**
     * Produces a list of *all* service spends of each AWS customer,
     * sorted by total spend (descending), then by the service spend (individual
     * service spend (descending), then service name).
     *
     * @return A list of CustomerServiceSpend, sorted by AWS customer
     * total spend and individual service spend.
     */
    public List<CustomerServiceSpend> getTopItemizedSpends() {
        // PARTICIPANTS: Implement according to Javadoc and README
        List<CustomerServiceSpend> report = new ArrayList<>();
        List<CustomerTotalSpend> totalSpends = awsServiceInvoiceDao.getAllServiceSpends();

        Collections.sort(totalSpends, new CustomerTotalSpendBySpend().reversed());

        for (CustomerTotalSpend currentSpend : totalSpends) {
            //get the current customer
            Customer currCustomer = currentSpend.getCustomer();
            //get all the services the customer used
            List<ServiceSpend> serviceSpends = currentSpend.getServiceSpends();

            Collections.sort(serviceSpends, new ServiceSpendByService().reversed());
            //for each service, add the customer and the service spend to the CustomerServiceSpend report
            for (ServiceSpend serviceSpend : serviceSpends) {
                report.add(new CustomerServiceSpend(currCustomer, serviceSpend));
            }
        }
        return report;

    }
}
