package com.kenzie.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class FruitHttpClient {
    public static String getDesiredURL(FruitCriteria fc){
        switch(fc){
            case ALL:
                return "https://www.fruityvice.com/api/fruit/all";
            case LOW_CARB:
                return "https://www.fruityvice.com/api/fruit/carbohydrates?max=5";
            case HIGH_CALORIE:
                return "https://www.fruityvice.com/api/fruit/calories?min=100";
            default:
                return "";
        }
    }

    public String getFruits(String url) {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                return httpResponse.body();
            } else {
                return String.format("GET request failed: %d status code received", statusCode);
            }
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }
    }

    public static List<FruitDTO> getFruitsList(String httpResponseBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<FruitDTO>> typeReferenceListFruitDTO =  new TypeReference<>(){};
        return objectMapper.readValue(httpResponseBody, typeReferenceListFruitDTO);
    }
    
        public static FruitDTO createFruitDTO(){
            FruitDTO fruitDTO = new FruitDTO();
            FruitDTO.Nutritions nutritions = new FruitDTO.Nutritions();
            fruitDTO.setGenus("Fragaria");
            fruitDTO.setName("Blackberry");
            fruitDTO.setFamily("Rosaceae");
            fruitDTO.setOrder("Rosales");
            nutritions.setCarbohydrates(5.5);
            nutritions.setProtein(0);
            nutritions.setFat(0.4);
            nutritions.setCalories(29);
            nutritions.setSugar(5.4);
            fruitDTO.setNutritions(nutritions);
        return fruitDTO;
    }

    public static String fruitToJSON(FruitDTO fruit) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        return objectMapper.writeValueAsString(fruit);
    }

    public String putFruit(String fruitStr, String url) throws JsonProcessingException {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(fruitStr))
                .build();
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            if (statusCode == 201 || statusCode == 202) {
                return httpResponse.body();
            } else {
                return String.format("PUT request failed: %d status code received", statusCode);
            }
        } catch (InterruptedException | IOException e) {
            return e.getMessage();
        }
    }
}
