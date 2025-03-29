package com.kenzie.groupactivity.bigspender.types;

import java.util.Comparator;

public class ServiceSpendByService implements Comparator<ServiceSpend> {
@Override
    public int compare(ServiceSpend o1, ServiceSpend o2){
    int result = Long.compare(o1.getSpend(), o2.getSpend());
    if (result == 0) {
        return o1.getServiceName().compareTo(o2.getServiceName());
    }
    return result;
    }
}

