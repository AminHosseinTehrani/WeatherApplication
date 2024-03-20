package com.aminhosseintehrani.WeatherApplication;

import javafx.scene.control.Alert;


/**
 * This is a customer json extraction class
 * It retrieves the json file and extracts the key and value pair form it
 * only works if there is only one object in the json file
 */
public class JSONContentExtraction {

    int numberOfIndexes;
    private int startIndex;
    private int endIndex;

    private int fieldIndex;

    private String fieldString;
    private Integer possibleFieldInt = null;

    public JSONContentExtraction() {


    }

    //Find start and end index of specific string in json file
    public void findStartAndEndIndexforValue(int lookUpIndex, StringBuilder response, String keySeparator,
                                             String fieldEndingIndicator, int position, int endPosition) {


        startIndex = response.indexOf(keySeparator, lookUpIndex) + position;
        endIndex = response.indexOf(fieldEndingIndicator, startIndex) + endPosition;
    }

    //
    public int findFieldLocation(String field, StringBuilder response) {

        return fieldIndex = response.indexOf(field);
    }


    // Extract the value from the key of the json file
    public Object valueExtractor(StringBuilder response, String errorText) {

        try {
            if (response.substring(startIndex, endIndex).matches("-?\\d+(\\.\\d+)?")) {
                return (int) Double.parseDouble(response.substring(startIndex, endIndex));
            }

        } catch (StringIndexOutOfBoundsException exception) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(errorText);


            // Show the alert
            alert.showAndWait();
        }
        if (fieldString != null && fieldString.contains("}")) {
            fieldString = fieldString.replace("}", "");
        }
        return fieldString = response.substring(startIndex, endIndex);

    }


    public int getStartIndex() {
        return startIndex;
    }


    public int getEndIndex() {
        return endIndex;
    }


}
