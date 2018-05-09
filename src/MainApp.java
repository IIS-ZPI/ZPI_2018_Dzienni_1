import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class MainApp extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setTop(new Label("Hello taxes!"));
        Scene scene = new Scene(root,640,480);
        primaryStage.setScene(scene);
        primaryStage.show();

        DataLoader dataLoader = new DataLoader("taxes.dat");
        Map<String, List<Float>> data = dataLoader.getData();
        System.out.println(data.toString());
    }
}
