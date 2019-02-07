package Visualization.Implementation;

import Engine.Model.Coordinate;
import Visualization.Contract.IImageExporter;
import Visualization.Contract.IPathDrawer;
import Visualization.Contract.IVisualizationManager;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

// TODO: zoom
// TODO: look at exporting as svg
// TODO: when live running, have different stroke modes to have different look? idk??

public class VisualizationManger extends Canvas implements IVisualizationManager {



    private IPathDrawer _pathDrawer;
    private GraphicsContext _gc;

    private int _windowHeight;
    private int _windowWidth;
    private double _pixelsPerUnitX;
    private double _pixelsPerUnitY;

    private Coordinate _lastCoordinate;
    private IImageExporter _imageExporter;

    /**
     * Defaults the canvas to be 2 units across
     * @param pathDrawer
     */
    public VisualizationManger(int windowHeight, int windowWidth, IPathDrawer pathDrawer, IImageExporter imageExporter) {
        _windowHeight = windowHeight;
        _windowWidth = windowWidth;
        _imageExporter = imageExporter;
        _pathDrawer = pathDrawer;

        _pixelsPerUnitX = _windowWidth / 2;
        _pixelsPerUnitY = _windowHeight / 2;
    }

    @Override
    public void displayWindow(Stage stage) {
        Canvas canvas = new Canvas(_windowWidth, _windowHeight);
        _gc = canvas.getGraphicsContext2D();
        Pane root = new Pane();
        root.setStyle("-fx-padding: 10;");
        root.getChildren().add(canvas);

        Button exportButton = new Button("Export");
        exportButton.setOnAction(ev -> _imageExporter.exportImage(canvas));
        root.getChildren().addAll(exportButton);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Bug Crawler Visualization");
        stage.show();
    }

    @Override
    public void drawCircle(int x, int y, int radius) {
        _pixelsPerUnitX = _windowWidth / (2 * radius);
        _pixelsPerUnitY = _windowHeight / (2 * radius);

        _gc.strokeOval(x, y, _windowWidth, _windowHeight);
    }

    @Override
    public void addPoint(Coordinate coordinate) {

        int centeredX = _windowWidth / 2;
        int centeredY = _windowHeight / 2;

        double plottedX = centeredX + (_pixelsPerUnitX * coordinate.getX());
        double plottedY = centeredY - (_pixelsPerUnitY * coordinate.getY()); // subtracted because canvas y increases as go down

        if (_lastCoordinate != null) {
            _pathDrawer.drawPath(_gc, _lastCoordinate.getX(), _lastCoordinate.getY(), plottedX, plottedY);
        } else {
            _pathDrawer.drawPoint(_gc, plottedX, plottedY);
        }

        // Remember this coordinate to draw from next time
        _lastCoordinate = new Coordinate(plottedX, plottedY);
    }

    @Override
    public void addPoints(List<Coordinate> coordinates) {
        int centeredX = _windowWidth / 2;
        int centeredY = _windowHeight / 2;

        double[] xPoints = new double[coordinates.size()];
        double[] yPoints = new double[coordinates.size()];

        for (int i = 0; i < coordinates.size(); i++) {
            xPoints[i] = centeredX + (_pixelsPerUnitX * coordinates.get(i).getX());
            yPoints[i] = centeredY - (_pixelsPerUnitY * coordinates.get(i).getY()); // subtracted because canvas y increases as go down
        }

        _pathDrawer.drawPath(_gc, xPoints, yPoints, coordinates.size());
    }
}
