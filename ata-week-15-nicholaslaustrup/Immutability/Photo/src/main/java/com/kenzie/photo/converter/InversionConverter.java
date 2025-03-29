package com.kenzie.photo.converter;


import com.kenzie.photo.model.ConversionType;
import com.kenzie.photo.model.Pixel;
import com.kenzie.photo.model.PrimePhoto;
import com.kenzie.photo.model.RGB;
import com.kenzie.photo.util.PrimePhotoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts an image to a inverted version.
 */
public class InversionConverter implements PrimePhotoConverter {

    public String convert(final PrimePhoto image, final String imageName) {
        List<Pixel> pixels = new ArrayList<>();

        for (Pixel pixel : image.getPixels()) {
            RGB rgb = new RGB(pixel.getRGB().getRed(), pixel.getRGB().getGreen(), pixel.getRGB().getBlue(), pixel.getRGB().getTransparency());
            rgb = rgb.invert();
            pixels.add(new Pixel(pixel.getX(), pixel.getY(), rgb));
        }

        PrimePhoto convertedImage = new PrimePhoto(pixels, image.getHeight(), image.getWidth(), image.getType());

        return PrimePhotoUtil.savePrimePhoto(convertedImage, imageName, ConversionType.INVERSION);
    }
}
