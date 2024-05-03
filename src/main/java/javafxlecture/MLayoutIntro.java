package javafxlecture;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * Created by brian on 5/17/14.
 *
 * http://www.javacodegeeks.com/2012/07/javafx-20-layout-panes-gridpane.html
 */
public class MLayoutIntro extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle(getClass().getSimpleName());

        // --- 1. Create a Pane that can manage your layout for you.
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(40, 0, 0, 50));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        Scene scene = new Scene(gridPane, 300, 150);

        // --- 2. Add some user interface elements
        Label lbl1 = new Label("Username: ");
        GridPane.setHalignment(lbl1, HPos.RIGHT);
        TextField tf1 = new TextField();

        Label lbl2 = new Label("Password: ");
        GridPane.setHalignment(lbl2, HPos.RIGHT);
        PasswordField pf1 = new PasswordField();

        Button loginBtn = new Button("Login");
        GridPane.setMargin(loginBtn, new Insets(10, 0, 0, 0));

        gridPane.add(lbl1, 0, 0);
        gridPane.add(tf1, 1, 0);
        gridPane.add(lbl2, 0, 1);
        gridPane.add(pf1, 1, 1);
        gridPane.add(loginBtn, 1, 2);


        primaryStage.setScene(scene);
        primaryStage.show();


    }
}