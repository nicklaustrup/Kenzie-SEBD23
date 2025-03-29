package com.kenzie.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Main {
    // Initialize the required constant variable here
    static final String GET_URL = "http://www.boredapi.com/api/activity";

    //Formats URL query string with one property
    public static String formatURL(String URLString, String parameter, String value){
        String formattedURL = URLString + "?" + parameter + "=" + value ;
        return formattedURL;
    }

    //Overload method: Formats URL query string with two properties
    public static String formatURL(String URLString, String parameter1, String value1,String parameter2, String value2){
        String formattedURL = URLString + "?" + parameter1 + "=" + value1 + "&" + parameter2 + "=" + value2;
        return formattedURL;
    }
    
    public static String getURLResponse(String URLString) {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(URLString);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            if (statusCode == 200){
                return httpResponse.body();
            } else {
                throw new Exception("No activity found");
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String formatActivityOutput(String jsonString) throws JsonProcessingException, Exception {
        ObjectMapper mapper = new ObjectMapper();
        ActivityDTO activityDTO = mapper.readValue(jsonString, ActivityDTO.class);

        try {
        if (activityDTO.getActivity() == null){
            throw new Exception("No activity found");
        } else {
            return activityDTO.toString();
        }
    } catch (Exception e) {
        return e.getMessage();
    }

    }

    public static String getActivityRandom(String URL) {
        String urlFormat =  formatURL(URL, "random", null);
        return getURLResponse(urlFormat);
    }

    public static String getActivityType(String URL, String type) throws com.fasterxml.jackson.core.JsonProcessingException,IOException{
        String urlFormat =  formatURL(URL, "type", type);
        return getURLResponse(urlFormat);
    }

    public static String getActivityWithPrice(String URL, double price) throws com.fasterxml.jackson.core.JsonProcessingException,IOException{
        String urlFormat =  formatURL(URL, "price", String.valueOf(price));
        return getURLResponse(urlFormat);
    }

    public static String getActivityTypeForGroup(String URL, String type, int numParticipants) {
        String urlFormat =  formatURL(URL, "type", type, "participants", String.valueOf(numParticipants));
        return getURLResponse(urlFormat);
    }

    /** Do not modify main method **/
    public static void main(String[] args) throws IOException {
        String response;
        try {
            System.out.println("Are you feeling bored? Try these activities: ");
            
            //parse JSON string into formatted output
            System.out.println(formatActivityOutput(getActivityRandom(GET_URL)));
            System.out.println(formatActivityOutput(getActivityType(GET_URL, "education")));
            System.out.println(formatActivityOutput(getActivityWithPrice(GET_URL, 0)));
            System.out.println(formatActivityOutput(getActivityTypeForGroup(GET_URL, "recreational",4)));
            
            //Test for error checking: this last one does not have a return. Should print "No activity found"
            System.out.println(formatActivityOutput(getActivityType(GET_URL, "mayhem")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
