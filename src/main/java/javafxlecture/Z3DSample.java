package javafxlecture;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.scene.image.Image;

/**
 * Created by brian on 5/17/14.
 */
public class Z3DSample extends Application {

    double anchorX;
    double anchorY;
    double anchorAngle;
    Image bumpMap = new Image(getClass().getResourceAsStream("earth.jpg"));


    public static void main(String[] args) {
        System.setProperty("prism.dirtyopts", "false");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(getClass().getSimpleName());

        PhongMaterial boxMaterial = new PhongMaterial();
        boxMaterial.setDiffuseColor(Color.GREEN);
        boxMaterial.setSpecularColor(Color.WHITESMOKE);

        PhongMaterial sphereMaterial = new PhongMaterial();
        sphereMaterial.setDiffuseColor(Color.BISQUE);
        sphereMaterial.setSpecularColor(Color.LIGHTBLUE);
        sphereMaterial.setBumpMap(bumpMap);

        Box box = new Box(400, 400, 400);
        box.setMaterial(boxMaterial);
        box.setTranslateX(250);
        box.setTranslateY(250);
        box.setTranslateZ(450);

        Sphere sphere = new Sphere(200);
        sphere.setMaterial(sphereMaterial);
        sphere.setTranslateX(250);
        sphere.setTranslateY(250);
        sphere.setTranslateZ(50);

        final Group parent = new Group(box, sphere);
        parent.setTranslateZ(500);
        parent.setRotationAxis(new Point3D(1, 1, 1));

        Group root = new Group(parent);
        Scene scene = new Scene(root, 500, 500, true);
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                anchorX = event.getSceneX();
                anchorY = event.getSceneY();
                anchorAngle = parent.getRotate();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                parent.setRotate(anchorAngle + anchorX - event.getSceneX());
            }
        });

        PointLight pointLight = new PointLight(Color.ANTIQUEWHITE);
        pointLight.setTranslateX(15);
        pointLight.setTranslateY(-10);
        pointLight.setTranslateZ(-100);

        root.getChildren().add(pointLight);

        addCamera(scene);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public PerspectiveCamera addCamera(Scene scene) {
        PerspectiveCamera camera = new PerspectiveCamera(false);
        scene.setCamera(camera);
        return camera;
    }
}




