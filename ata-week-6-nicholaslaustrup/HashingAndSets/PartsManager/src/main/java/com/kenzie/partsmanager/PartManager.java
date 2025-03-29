package com.kenzie.partsmanager;

import java.util.HashSet;
import java.util.Set;

public class PartManager {
    private Set<DevicePart> deviceParts = new HashSet<>();

    public void addDevicePart(DevicePart devicePart) {
        boolean isAdded = deviceParts.add(devicePart);
    }

    public void printDeviceParts() {
        for (DevicePart devicePart: deviceParts) {
            System.out.println(devicePart);
        }
    }

    public boolean isDevicePartIncluded(DevicePart toCheck){
        return deviceParts.contains(toCheck);
    }

    public boolean removeSpecificPart(DevicePart toRemove){
        return deviceParts.remove(toRemove);
    }

    public Set<DevicePart> getDeviceParts() {
        return deviceParts;
    }
}
