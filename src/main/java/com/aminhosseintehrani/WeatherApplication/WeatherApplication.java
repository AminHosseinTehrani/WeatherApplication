package com.aminhosseintehrani.WeatherApplication;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;


import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;

import java.net.URL;


public class WeatherApplication extends Application {


    private ImageLoader imageLoader;

    static Scene scene;
    HTMLRequest htmlRequest;

    SceneContentController sceneContentController;

    DeveloperDialog developerDialog;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WeatherApplication.class.getResource("hello-view.fxml"));


        scene = new Scene(fxmlLoader.load(), 500, 500);

        WeatherAPIController weatherAPIController = new WeatherAPIController("302e55c960d54fff82974120ade7ac7c");


        developerDialog = new DeveloperDialog();
        developerDialog.AskForApiKey();

        imageLoader = new ImageLoader();
        imageLoader.LoadImages();


        try {


            sceneContentController = new SceneContentController();
            Text retrievedTempText = (Text)
                    sceneContentController.findSceneNode("#textfortemp", scene);
            Text retrievedCityText = (Text)
                    sceneContentController.findSceneNode("#cityID", scene);
            ImageView imageView =
                    sceneContentController.findSceneImageNode("#weatherimage", scene);

            Button searchButton =
                    sceneContentController.findSceneButtonNode("#searchButton", scene);

            TextField textField =
                    sceneContentController.findSceneTextFieldNode("#inputTextField", scene);
            textField.setOnAction(event -> {
                System.out.println("Text entered: " + textField.getText());
                // Add your code here to handle the action event
                searchButton.fire();

            });


            searchButton.setOnAction(event -> {


                try {
                    // Specify the URL for the GET request
                    String url = "https://api.weatherbit.io/v2.0/current";



                    String key = developerDialog.getApiKey();
                    weatherAPIController.setKey(key);
                    String encodedApiKey = weatherAPIController.encode(weatherAPIController.getKey());
                    String encodedCity = weatherAPIController.encode(textField.getText());


                    String fullURL = url + "?key=" + encodedApiKey + "&city=" + encodedCity;
                    htmlRequest = new HTMLRequest();



                    htmlRequest.createUrlObject(fullURL);


                    // Open a connection to the URL
                    htmlRequest.openURLConnection();


                    // Set the request method to GET
                    htmlRequest.setRequestMethod("GET");


                    // Get the response code
                    htmlRequest.getResponseCode();


                    htmlRequest.readResponseFromServer();


                } catch (Exception e) {
                    e.printStackTrace();
                }


                //String jsonData = "{\"count\":1,\"data\":[{\"app_temp\":2.9,\"aqi\":17,\"city_name\":\"Vancouver\",\"clouds\":94,\"country_code\":\"CA\",\"datetime\":\"2024-03-11:21\",\"dewpt\":4.9,\"dhi\":104.04,\"dni\":842.34,\"elev_angle\":36.8,\"ghi\":601.94,\"gust\":7.6367188,\"h_angle\":15,\"lat\":49.24966,\"lon\":-123.11934,\"ob_time\":\"2024-03-11 21:17\",\"pod\":\"d\",\"precip\":2.0800781,\"pres\":994,\"rh\":94,\"slp\":1003.156,\"snow\":0,\"solar_rad\":163,\"sources\":[\"rtma\",\"radar\",\"satellite\"],\"state_code\":\"02\",\"station\":\"F1856\",\"sunrise\":\"14:30\",\"sunset\":\"02:12\",\"temp\":7,\"timezone\":\"America/Vancouver\",\"ts\":1710191840,\"uv\":1.496141,\"vis\":16,\"weather\":{\"description\":\"Light rain\",\"code\":500,\"icon\":\"r01d\"},\"wind_cdir\":\"E\",\"wind_cdir_full\":\"east\",\"wind_dir\":84,\"wind_spd\":4.5273438}]}";
                String jsonData = htmlRequest.getStringBuilder().toString();



                System.out.println(jsonData + "jsondata");

                String cityNotFoundError = "City could not be found";


                JSONContentExtraction jsonContentExtraction = new JSONContentExtraction();


                jsonContentExtraction.findStartAndEndIndexforValue(jsonContentExtraction.findFieldLocation(
                        "\"temp\":", htmlRequest.getStringBuilder()), htmlRequest.getStringBuilder(), ":", ",", 1, 0);


                int temperatureStr = (int) jsonContentExtraction.valueExtractor(htmlRequest.getStringBuilder(), cityNotFoundError);
                System.out.println(temperatureStr);

                jsonContentExtraction.findStartAndEndIndexforValue(jsonContentExtraction.findFieldLocation(
                        "\"city_name\":", htmlRequest.getStringBuilder()), htmlRequest.getStringBuilder(), ":", ",", 2, -1);

                String cityStr = (String) jsonContentExtraction.valueExtractor(htmlRequest.getStringBuilder(), cityNotFoundError);
                System.out.println(cityStr);


                jsonContentExtraction.findStartAndEndIndexforValue(jsonContentExtraction.findFieldLocation(
                        "\"code\":", htmlRequest.getStringBuilder()), htmlRequest.getStringBuilder(), ":", ",", 1, 0);

                System.out.println("is a " + jsonContentExtraction.valueExtractor(htmlRequest.getStringBuilder(), cityNotFoundError));
                int codeStr = (Integer) jsonContentExtraction.valueExtractor(htmlRequest.getStringBuilder(), cityNotFoundError);
                System.out.println(codeStr + "Codestr");


                imageView.setImage(imageLoader.getImage(String.valueOf(codeStr)));
                retrievedTempText.setText(temperatureStr + " " + "°");
                retrievedCityText.setText(cityStr);

            });


        } catch (Exception e) {
            e.printStackTrace();
        }


        stage.setTitle("Weather");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {


        launch();


    }


}
