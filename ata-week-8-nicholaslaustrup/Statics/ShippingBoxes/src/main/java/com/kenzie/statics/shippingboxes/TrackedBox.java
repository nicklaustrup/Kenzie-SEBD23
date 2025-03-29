package com.kenzie.statics.shippingboxes;

import com.amazonaws.services.dynamodbv2.xspec.S;

import java.util.UUID;

public class TrackedBox {

    // PARTICIPANTS: DO NOT CHANGE THE FIELDS boxId or trackingId below. These fields should not be static.
    private String boxId;
    private String trackingId;

    public TrackedBox(String boxId) {
    this.boxId = boxId;
    this.trackingId = createTrackingId();
    }

    public String getTrackingId() {
        return this.trackingId;
    }

    public String getBoxId() {
        return this.boxId;
    }

    public String createTrackingId() {
        return this.boxId + String.valueOf(UUID.randomUUID());
    }
}
