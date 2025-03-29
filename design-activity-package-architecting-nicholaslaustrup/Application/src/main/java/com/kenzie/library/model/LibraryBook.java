package com.kenzie.library.model;

public class LibraryBook extends Book {
    //class subclass properties
    public String isbn;
    public String subjectList;

    //method 1
    @Override
    public String convertBookToString(){
        String bookString = "";
        return bookString;
    }

    //method 2
    @Override
    public void setBookInfo(String detailString){
        //method that sets book info
    }
}
