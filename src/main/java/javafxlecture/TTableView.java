package javafxlecture;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.Instant;

public class TTableView extends Application {

    public static class Data {
        private String concept;
        private String observer;
        private Instant recordedTimestamp;

        public Data(String concept, String observer, Instant recordedTimestamp) {
            this.concept = concept;
            this.observer = observer;
            this.recordedTimestamp = recordedTimestamp;
        }

        public String getConcept() {
            return concept;
        }

        public String getObserver() {
            return observer;
        }

        public Instant getRecordedTimestamp() {
            return recordedTimestamp;
        }
    }

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(getClass().getSimpleName());
        var tableView = new TableView<Data>();
        tableView.setTableMenuButtonVisible(true);

        var conceptCol = new TableColumn<Data, String>("Concept");
        conceptCol.setCellValueFactory(new PropertyValueFactory<>("concept"));
        conceptCol.setId("Concept");

        var obsCol = new TableColumn<Data, String>("Observer");
        obsCol.setCellValueFactory(new PropertyValueFactory<>("observer"));
        obsCol.setId("Observer");

        var timeCol = new TableColumn<Data, Instant>("Timestamp");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("recordedTimestamp"));
        timeCol.setId("Timestamp");

        tableView.getColumns().addAll(conceptCol, obsCol, timeCol);

        final var scene = new Scene(tableView, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        for (int i = 0; i < 10000; i++) {
            tableView.getItems().add(new Data("Grimp" + i, "brian", Instant.now()));
        }

    }

}
