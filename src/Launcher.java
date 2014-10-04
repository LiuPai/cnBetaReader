import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import net.WebPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

/**
 * Created by lilium on 13/07/30.
 */
public class Launcher extends Application {

    private void init(Stage stage){
        Group root = new Group();
        stage.setScene(new Scene(root));
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
       // webEngine.loadContent(new WebPage(246526).getContent());
        VBox vBox = new VBox(5);
        vBox.getChildren().setAll(webView);
        VBox.setVgrow(webView, Priority.ALWAYS);
        root.getChildren().add(vBox);
    }
    @Override
    public void start(Stage stage) throws Exception {
        init(stage);
        stage.show();
    }
    public static void main(String... args){
        launch(args);
        }
}
