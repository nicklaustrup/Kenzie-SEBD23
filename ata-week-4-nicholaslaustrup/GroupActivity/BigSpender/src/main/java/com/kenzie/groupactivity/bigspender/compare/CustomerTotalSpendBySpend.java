package com.kenzie.groupactivity.bigspender.compare;

import com.kenzie.groupactivity.bigspender.types.CustomerServiceSpend;
import com.kenzie.groupactivity.bigspender.types.CustomerTotalSpend;

import java.util.Comparator;

public class CustomerTotalSpendBySpend implements Comparator<CustomerTotalSpend> {



    @Override
    public int compare(CustomerTotalSpend o1, CustomerTotalSpend o2) {
        return Long.compare(o1.getTotalSpend(), o2.getTotalSpend());
    }
}
