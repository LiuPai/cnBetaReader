package ui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.Page;

import java.io.IOException;

/**
 * Created by lilium on 13/08/25.
 */
public class PageViewerController {
    @FXML
    private Label fxTitle;
    @FXML
    void initialize(){
        fxTitle.setText("TEST");
    }

}
