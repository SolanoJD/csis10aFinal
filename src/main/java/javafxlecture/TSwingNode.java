package javafxlecture;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.Color;
import java.awt.Component;
import javax.swing.*;
import javax.swing.table.*;
import java.lang.reflect.RecordComponent;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class TSwingNode extends Application {

    private record Data(String concept, String observer, Instant recordedTimestamp) {}

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(getClass().getSimpleName());

        final var swingNode = new SwingNode();
        final var root = new SplitPane(swingNode, new Pane(new Label("Hello")));
        SwingUtilities.invokeLater(() -> {
            swingNode.setContent(getSwing());
        });

        final var scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    private JComponent getSwing() {


        final var tableColumnModel = new STableColumnModel();
        final var tableModel = new STableModel();
        for (int i = 0; i < 10000; i++) {
            tableModel.data.add(new Data("Grimp" + i, "brian", Instant.now()));
        }

        final var table = new JTable(tableModel, tableColumnModel);
        table.setShowGrid(true);
        table.getTableHeader().setBackground(Color.GRAY);
        final var scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        scrollPane.revalidate();
        return scrollPane;

    }

    private static class STableModel extends AbstractTableModel {
        private List<String> columnNames = Arrays.stream(Data.class.getRecordComponents())
                .map(RecordComponent::getName)
                .toList();
        List<Data> data = new ArrayList<>();

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.size();
        }

        @Override
        public String getColumnName(int column) {
            return super.getColumnName(column);
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data.get(rowIndex);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return Data.class;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    }

    private static class STableColumnModel extends DefaultTableColumnModel {

        public STableColumnModel() {
            init();
        }

        private void init() {
            var tc0 = new  TableColumn(0, 100, new STableCellRenderer<>(Data::concept), null);
            tc0.setHeaderValue("concept");

            var tc1 = new  TableColumn(1, 100, new STableCellRenderer<>(Data::observer), null);
            tc1.setHeaderValue("observer");

            var tc2 = new  TableColumn(2, 200, new STableCellRenderer<>((Data d) -> d.recordedTimestamp.toString()), null);
            tc2.setHeaderValue("timestamp");


            addColumn(tc0);
            addColumn(tc1);
            addColumn(tc2);
        }
    }

    private static class STableCellRenderer<A> extends DefaultTableCellRenderer {

        private final Function<A, String> converter;

        public STableCellRenderer(Function<A, String> converter) {
            this.converter = converter;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            final Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (value != null) {
                final A v = (A) value;
                setText(converter.apply(v));
            }
            else {
                setText(null);
            }
            return component;
        }
    }


}
