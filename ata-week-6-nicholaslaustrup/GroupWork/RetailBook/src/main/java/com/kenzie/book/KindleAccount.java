package com.kenzie.book;

public class KindleAccount {
    private Book book;
    private ProductInformation productInformation;
    public KindleAccount(Book book, ProductInformation productInformation){
        this.book = book;
        this.productInformation = productInformation;
    }
}
