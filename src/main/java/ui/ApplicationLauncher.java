package ui;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import domain.Competition;
import uicontrollers.CompetitionsGUIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ApplicationLauncher extends Application {
    private Stage stage;
    private Scene scene;
    private List<Competition> complist;

    public static void main(String[] args){
        launch();
    }

    public void start(Stage stage) throws IOException {
        this.stage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationLauncher.class.getResource("/CompetitionsGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 557, 347);
        stage.setTitle("Competitions");

        stage.setScene(scene);
        stage.show();
    }
}
