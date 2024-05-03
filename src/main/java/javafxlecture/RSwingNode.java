package javafxlecture;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.lang.reflect.RecordComponent;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class RSwingNode extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(getClass().getSimpleName());

        final var swingNode = new SwingNode();
        final var root = new BorderPane(swingNode);
        SwingUtilities.invokeLater(() -> {
            swingNode.setContent(getSwing());
        });

        final var scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    private JComponent getSwing() {

        String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};

        Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)}
        };


        final var table = new JTable(data, columnNames);
        table.setShowGrid(true);
        table.getTableHeader().setBackground(Color.WHITE);
        final var scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        scrollPane.revalidate();
        return scrollPane;

    }


}