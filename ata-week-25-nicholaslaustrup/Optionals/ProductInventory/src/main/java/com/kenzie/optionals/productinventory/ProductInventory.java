package com.kenzie.optionals.productinventory;

import org.apache.commons.lang.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ProductInventory collects groups of items to be shipped. It uses a 
 * ProductUtility to obtain individual product names, and to determine
 * whether each item is boxed and ready to be shipped.
 */
public class ProductInventory {
    private ProductUtility productUtility;
    private List<Integer> productIDs;

    /**
     * Constructor.
     * @param productUtility - The service used to retrieve product information
     * @param productIDs - A list of package IDs
     */
    public ProductInventory(ProductUtility productUtility, List<Integer> productIDs) {
        //
        // WARNING: DO NOT EDIT THE CONSTRUCTOR
        // Here's why: Typically, it's a good practice to validate constructor inputs.
        // However in this case, we're specifically asking for validation for these in
        // the methods will you be implementing, and the tests won't work correctly if you
        // do the validation here.
        //
        this.productUtility = productUtility;
        this.productIDs = productIDs;
    }

    /**
     * Find the product names for the IDs in the package.
     * @return Map[Integer, String] of product IDs to product names. Does not include products without names.
     */
    Map<Integer, String> findProductNames() throws IllegalArgumentException{

        if (Objects.isNull(this.productIDs)){
            throw new IllegalArgumentException("productID is null");
        }

        if (Objects.isNull(this.productUtility)){
            throw new IllegalArgumentException("productUtility is null");
        }


        try {
            Map<Integer, String> productList = Optional.of(this.productIDs)
                    .orElse(Collections.emptyList())
                    .stream()
                    .map(id -> new AbstractMap.SimpleEntry<>(id, productUtility.findProductName(id)))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            return productList;

        } catch (NullPointerException a) {
            return Collections.emptyMap();
        }
    }

    /**
     * Determine whether product is ready to ship or not.
     * @param productID the package identifier
     * @return Optional[Boolean] containing whether a product is ready to ship.
     */
    Optional<Boolean> isProductReady(Integer productID) {
        if (Objects.isNull(productID)){
            throw new IllegalArgumentException("The productID was null");
        }

        Optional<Boolean> productReady = Optional.ofNullable(productUtility.isProductReady(productID));

        if (Objects.isNull(productReady)){
            productReady = Optional.empty();
        }

        return productReady;  // Placeholder
    }
}
