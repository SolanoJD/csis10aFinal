package javafxlecture;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * http://docs.oracle.com/javafx/2/webview/jfxpub-webview.htm
 */
public class KWebView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle(getClass().getSimpleName());

        // --- 1. Create a WebView. It is an instance of javafx.scene.Parent so it can
        // be
        // added directly to the scene (i.e. you don't need to create a Group node)
        WebView webView = new WebView();
        Scene scene = new Scene(webView, 600, 500, Color.CORAL);

        // --- 2. Tell it which web site to load. The WebEngine has built in support for
        // scrolling and
        // mouse events
        WebEngine webEngine = webView.getEngine();
        webEngine.load("http://www.mbari.org");

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
