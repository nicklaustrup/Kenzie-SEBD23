package com.kenzie.library.model;

//DO NOT MODIFY THIS ABSTRACT CLASS
public abstract class Book {
    protected String title;
    protected String author;

    //constructor(s)
    public Book(){
        this.title = "";
        this.author = "";
    }

    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }

    //abstract methods
    public abstract String toString();
    public abstract void setBookInfo(String detailString);

    //defined methods
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
