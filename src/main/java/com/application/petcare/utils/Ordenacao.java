package com.application.petcare.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Ordenacao {

    public static void main(String[] args) {
    getFrases();
    }

    public static void getFrases() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().
                    uri(URI.create("https://meowfacts.herokuapp.com/?lang=por-br&count=10")).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonResponse = response.body();
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            selectionSort(jsonArray);

        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    public static void selectionSort(JSONArray jsonArray) {
        String[] frases = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
                frases[i] = jsonArray.getString(i);
        }
        int menorIndice;
        for (int i = 0; i < frases.length - 1; i++) {
            menorIndice = i;
            for (int j = i + 1; j < frases.length; j++) {
                if(frases[j].charAt(0) < frases[menorIndice].charAt(0)){
                    menorIndice = j;
                }
            }
            String temp = frases[menorIndice];
            frases[menorIndice] = frases[i];
            frases[i] = temp;
        }
        for (int i = 0; i < frases.length; i++) {
            System.out.println(frases[i]);
        }
    }

}
