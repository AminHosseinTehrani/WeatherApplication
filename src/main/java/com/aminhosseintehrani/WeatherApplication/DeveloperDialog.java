package com.aminhosseintehrani.WeatherApplication;

import javafx.scene.control.TextInputDialog;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class Created for testing purposes
 */
public class DeveloperDialog {

    private String apiKey;
    TextInputDialog dialog = new TextInputDialog();

    public DeveloperDialog() {

    }

    /**
     * Asks the user for an Api Key
     */
    public void AskForApiKey() {
        AtomicBoolean validApiKeyEntered = new AtomicBoolean(false);
        dialog.setTitle("Developer API KEY request");
        dialog.setHeaderText("Please get an API key from: https://www.weatherbit.io/");
        dialog.setContentText("API Key:");

        while (!validApiKeyEntered.get()) {
            // Show dialog and wait for user input
            dialog.showAndWait().ifPresent(apiKey -> {
                // Check if the entered API key is empty
                if (apiKey.trim().isEmpty()) {
                    // Prompt the user to enter a non-empty API key
                    dialog.setHeaderText("API key cannot be empty. Please get an API key from: https://www.weatherbit.io/");
                } else {
                    // Set the flag to true to exit the loop
                    validApiKeyEntered.set(true);
                }
            });

        }

        setApiKey(dialog.getResult());
    }


    /**
     * Sets the API key
     */
    private void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {

        return apiKey;
    }


}
