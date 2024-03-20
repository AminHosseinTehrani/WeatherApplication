module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.aminhosseintehrani.WeatherApplication to javafx.fxml;
    exports com.aminhosseintehrani.WeatherApplication;
}