package bob;

import bob.ui.DialogBox;
import bob.ui.MainWindow;
import bob.ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//@@author {Wang Haitao iP}-reused
public class MainApp extends Application {
    private final Bob bob = new Bob("data/bobTasks.txt");

    @Override
    public void start(Stage stage) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setBob(bob);

            stage.setTitle("Bob");
            stage.show();
        } catch (Exception e) {
            DialogBox.getBobDialog(e.toString());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}