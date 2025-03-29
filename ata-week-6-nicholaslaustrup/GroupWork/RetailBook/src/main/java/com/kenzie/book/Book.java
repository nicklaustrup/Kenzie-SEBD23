package com.kenzie.book;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class Book {
    private ProductInformation productInformation;
    private String author;
    private BigDecimal length;
//    private String asin;
//    private String title;
//    private String description;
//    private Set<String> imageUrls;


    public Book(ProductInformation productInformation, String author, BigDecimal length) {
        this.productInformation = productInformation;
        this.author = author;
        this.length = length;
//        this.asin = productInformation.getAsin();
//        this.title = productInformation.getDisplayName();
//        this.description = productInformation.getDescription();
//        this.imageUrls = productInformation.getImageUrls();
    }


    public String getAsin() {
        return this.productInformation.getAsin();
    }

    public String getTitle() {
        return this.productInformation.getDisplayName();
    }

    public String getAuthor() {
        return this.author;
    }

    public String getDescription() {
        return this.productInformation.getDescription();
    }

    public Set<String> getImageUrls() {
        return this.productInformation.getImageUrls();
    }

    public BigDecimal getLength() {
        return this.length;
    }

    public double calculateSimilarity(Book other) {
        return productInformation.calculateSimilarity(other.getProductInformation());
    }
    public ProductInformation getProductInformation(){return this.productInformation;}

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        Book that = (Book) o;
        return Objects.equals(getAsin(), that.getAsin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAsin());
    }
}