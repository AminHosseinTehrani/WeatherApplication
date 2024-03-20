package com.aminhosseintehrani.WeatherApplication;

import javafx.scene.image.Image;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {
    Map<String, Image> imageMap;
    public ImageLoader(){

    }

    //Load images from folder
    public Map LoadImages(){
      imageMap = new HashMap<>();
        File folder = new File("images");

        // Check if the folder exists and is a directory
        if (folder.exists() && folder.isDirectory()) {
            // Get all files in the folder
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    // Check if the file is an image file (you might want to add more checks for specific image formats)
                    if (file.isFile() && file.getName().toLowerCase().endsWith(".png")) {
                        try {
                            // Load the image and add it to the map with the file name (without extension) as the key
                            String key = file.getName().replaceFirst("[.][^.]+$", ""); // Remove file extension
                            Image image = new Image(file.toURI().toString());
                            imageMap.put(key, image);
                        } catch (Exception e) {
                            // Handle any exceptions that occur during image loading
                            System.err.println("Error loading image: " + file.getName());
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            System.err.println("Images folder not found.");
        }
        return imageMap;
        // Now 'imageMap' contains the images with their respective keys
    }


    public Image getImage(String value){

        return imageMap.get(value);
    }
    }


