package com.kenzie.library.http.service;

public interface HTTPRequestClient {
    //debug flag turns on verbose console logging
    public static final boolean DEBUG = false;
    //base URL is set as constant
    public static final String GET_URL = "http://openlibrary.org/search.json";

    //interface methods
    public void sendGET(String request, String url);
    public void checkError();
    public String getResponse();

}
