package com.kenzie.library.model;

import java.util.ArrayList;

public class LibraryBook extends Book {
    private String isbn = "";
    private String subjectList = "";

    public LibraryBook(){
        this.isbn = "";
        this.subjectList = "";
    }

    public LibraryBook(String title, String author){
    this.title = title;
    this.author = author;
    this.isbn = "";
    this.subjectList = "";
    }


    @Override
    public String toString() {
        return "LibraryBook{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", subjectList='" + subjectList + '\'' +
                '}';
    }

    @Override
    public void setBookInfo(String detailString) {
        String[] bookInfo = detailString.split("\\|");
    this.title = bookInfo[0];
    this.author = bookInfo[1];
    this.isbn = bookInfo[2];
    this.subjectList = bookInfo[3];
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(String subjectList) {
        this.subjectList = subjectList;
    }
}