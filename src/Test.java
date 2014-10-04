import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by lilium on 13/07/29.
 */
public class Test extends Application {
    public static void main(String... args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Button button = new Button("Open Page");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                final Stage stage1 = new Stage();
                AnchorPane pane = null;
                try {
                    pane = (AnchorPane) FXMLLoader.load(Test.class.getResource("ui/PageViewer.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage1.setScene(new Scene(pane));
                stage1.show();
            }
        });
        Group root = new Group();
        root.getChildren().addAll(button);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
