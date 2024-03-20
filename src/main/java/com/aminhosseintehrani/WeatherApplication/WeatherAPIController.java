package com.aminhosseintehrani.WeatherApplication;



import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

public class WeatherAPIController {

    private String key;


public WeatherAPIController(String key){


    this.key = key;

}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }



/** Encode String as UTF-8 * to send over URL*/
    public String encode(String toEncode) throws UnsupportedEncodingException {

  return URLEncoder.encode(toEncode, "UTF-8");

    }










}
