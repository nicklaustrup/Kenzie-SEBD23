package com.kenzie.purchaseorder;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A PurchaseOrder tracks a sales contract between Amazon and a vendor.
 */
public final class PurchaseOrder {

    private final ZonedDateTime orderDate;
    private final BigDecimal subTotal;
    private final String vendor;
    private final List<String> items;

    /**
     * Constructor.
     * @param orderDate - Date purchase was made
     * @param subtotal - Subtotal of purchase
     * @param vendor - Vendor name
     * @param items - List of items purchased.
     */
    public PurchaseOrder(ZonedDateTime orderDate, BigDecimal subtotal, String vendor, List<String> items) {
    this.items = new ArrayList<>(items);
    this.orderDate = orderDate;
    this.subTotal = subtotal;
    this. vendor = vendor;
    }

    /**
     * Determine the total billable cost including taxes.
     * @param taxRate - The appropriate tax rate.
     * @return Cost including tax rate.
     */
    public BigDecimal determineBillableCost(Double taxRate) {
        return subTotal.multiply(BigDecimal.valueOf((taxRate + 1)));
    }

    /**
     * Getter for subtotal.
     * @return subtotal
     */
    public BigDecimal getSubtotal() {
        return subTotal;
    }

    /**
     * Getter for vendor.
     * @return vendor
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * Getter for item list.
     * @return item list
     */
    public List<String> getItems() {
        List<String> defensiveItems = new ArrayList<>();
        defensiveItems.addAll(items);
        return defensiveItems;
    }

    /**
     * Getter for Order Date.
     * @return Order Date
     */
    public ZonedDateTime getOrderDate() {
        return orderDate;
    }
}
