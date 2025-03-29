package com.kenzie.library;

//import dependencies
import com.kenzie.library.book.service.BookRequestService;
import com.kenzie.library.logger.service.Logger;
import com.kenzie.library.model.LibraryBook;

import java.util.ArrayList;



public class Application {


    public static LibraryBook convertResponseToLibraryBook(String response){
    LibraryBook book = new LibraryBook();
    book.setBookInfo(response);
    return book;
    }

    //TODO: Remove comments and complete method to return ArrayList of LibraryBook
    public static ArrayList<LibraryBook> returnBookList(String response1,
                                                        String response2,
                                                        String response3,
                                                        String response4,
                                                        String response5) {
        ArrayList<LibraryBook> bookList = new ArrayList<>();
        bookList.add(convertResponseToLibraryBook(response1));
        bookList.add(convertResponseToLibraryBook(response2));
        bookList.add(convertResponseToLibraryBook(response3));
        bookList.add(convertResponseToLibraryBook(response4));
        bookList.add(convertResponseToLibraryBook(response5));


        return bookList;
    }

    public static void main(String[] args) {
        //Use these constant values as input
        final String BOOK_1 = "The Hobbit|J.R.R. Tolkien||";
        final String BOOK_2 = "Dune|Frank Herbert||";
        final String BOOK_3 = "The Nonsense Book|Mr. Myzlplk||";
        final String BOOK_4 = "The Cat in the Hat|Dr. Seuss||";
        final String BOOK_5 = "The Art of War|Sun Tzu||";

        try {
            //1. Instantiate Singleton Logger object to log: "Process starting"
            Logger.getInstance().logMessage("Process starting");

            //2. Repeat for all five books listed
            //      a. Call BookRequestService.sendGetRequest()
            String responseString1 = BookRequestService.sendGetRequest(BOOK_1);
            Logger.getInstance().logMessage(responseString1);
            String responseString2 = BookRequestService.sendGetRequest(BOOK_2);
            Logger.getInstance().logMessage(responseString2);
            String responseString3 = BookRequestService.sendGetRequest(BOOK_3);
            Logger.getInstance().logMessage(responseString3);
            String responseString4 = BookRequestService.sendGetRequest(BOOK_4);
            Logger.getInstance().logMessage(responseString4);
            String responseString5 = BookRequestService.sendGetRequest(BOOK_5);
            Logger.getInstance().logMessage(responseString5);
            System.out.println(returnBookList(responseString1, responseString2, responseString3, responseString4, responseString5));
            Logger.getInstance().logMessage("Process complete");
               }
        catch (Exception e){
            System.out.println("Error occurred: unable to request book info");
            System.out.println("Exception info:" + e.getMessage());
        }
    }

}
