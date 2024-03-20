package com.aminhosseintehrani.WeatherApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HTMLRequest {

    URL obj;

    HttpURLConnection connection;
    StringBuilder response;
    int responseCode;

    public HTMLRequest(){

    }


    public void createUrlObject(String fullUrl) throws MalformedURLException {
        obj = new URL(fullUrl);

    }

    public void openURLConnection() throws IOException {

        connection = (HttpURLConnection)  obj.openConnection();
    }


    public void setRequestMethod(String requestMethod) throws ProtocolException {
        connection.setRequestMethod(requestMethod);
    }

    public void getResponseCode() throws IOException {
        responseCode = connection.getResponseCode();
    }

    public void readResponseFromServer(){
     try {

         BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
         String inputLine;
         response = new StringBuilder();
         while ((inputLine = in.readLine()) != null) {
             response.append(inputLine);
         }
         in.close();
     }
     catch(Exception e){
         e.printStackTrace();
     }

    }



    public StringBuilder getStringBuilder(){
        return response;
    }




}
