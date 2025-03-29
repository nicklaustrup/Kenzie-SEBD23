package com.kenzie.groupactivity.bigspender.types;

import java.util.Objects;

/**
 * Represents both a Customer and the details of one AWS service spend.
 */
public class CustomerServiceSpend implements Comparable<CustomerServiceSpend>{
    private Customer customer;
    private ServiceSpend serviceSpend;

    /**
     * Constructor taking a customer and a service spend.
     * @param customer the customer.
     * @param serviceSpend The service spend.
     */
    public CustomerServiceSpend(Customer customer, ServiceSpend serviceSpend) {
        this.customer = customer;
        this.serviceSpend = serviceSpend;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ServiceSpend getServiceSpend() {
        return serviceSpend;
    }

    @Override
    public String toString() {
        return "CustomerServiceSpend{" +
               "customer=" + customer +
               ", serviceSpend=" + serviceSpend +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerServiceSpend)) return false;
        CustomerServiceSpend that = (CustomerServiceSpend) o;
        return Objects.equals(customer, that.customer) && Objects.equals(serviceSpend, that.serviceSpend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, serviceSpend);
    }

    @Override
    public int compareTo(CustomerServiceSpend o) {
        int result = customer.compareTo(o.getCustomer());
        if (result == 0) {
            result = this.serviceSpend.getServiceName().compareTo(o.serviceSpend.getServiceName());
            if (result == 0){
                if (this.serviceSpend.getSpend() - o.serviceSpend.getSpend() < 0){
                    return -1;
                } else if (this.serviceSpend.getSpend() - o.serviceSpend.getSpend() > 0) {
                    return 1;
                } else {
                    return 0;
                    }
                }
            }
        return result;
    }
}
