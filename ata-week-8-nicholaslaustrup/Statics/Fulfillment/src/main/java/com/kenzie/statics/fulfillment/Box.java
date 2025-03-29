package com.kenzie.statics.fulfillment;

public class Box {
    private final static double HEIGHT = 72.0;
    private final static double WIDTH = 72.0;
    private final static double LENGTH = 72.0;
    /**
     * Constructs a Box object if the dimensions are valid
     * @param length - length of box
     * @param width - width of box
     * @param height - height of box
     * @throws DimensionValueException if the provided dimensions exceed the max Box size
     */
    public Box(double length, double width, double height) throws DimensionValueException {
    if (!validateBoxDimensions(length, width, height)){
        throw new DimensionValueException("Boxes must be within the specified dimensions!");
    }
    }
    public static boolean validateBoxDimensions(double height, double width, double length) {
        return length <= LENGTH && width <= WIDTH && height <= HEIGHT;
    }
}
