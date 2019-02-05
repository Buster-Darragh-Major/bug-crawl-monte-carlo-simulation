package Visualization.Implementation;

import Engine.Model.Coordinate;
import Visualization.Contract.IPathDrawer;
import Visualization.Contract.IVisualizationManager;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

// TODO: refactor!
// TODO: mode where bug jumps back in, run indefinately?
// TODO: look at exporting as svg
// TODO: Live running
// TODO: when live running, have different stroke modes to have different look? idk??

public class VisualizationManger extends Canvas implements IVisualizationManager {

    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 600;

    private Canvas _canvas;
    private IPathDrawer _pathDrawer;
    private GraphicsContext _gc;
    private double _pixelsPerUnitX;
    private double _pixelsPerUnitY;

    private Coordinate _lastCoordinate;

    public VisualizationManger(IPathDrawer pathDrawer) {

        _pathDrawer = pathDrawer;
    }

    @Override
    public void displayWindow(Stage stage) {
        _canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        _gc = _canvas.getGraphicsContext2D();
        Pane root = new Pane();
        root.setStyle("-fx-padding: 10;");
        root.getChildren().add(_canvas);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Creation of a Canvas");
        stage.show();
    }

    @Override
    public void drawCircle(int x, int y, int radius) {
        _pixelsPerUnitX = WINDOW_WIDTH / (2 * radius);
        _pixelsPerUnitY = WINDOW_HEIGHT / (2 * radius);

        _gc.strokeOval(x, y, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    @Override
    public void addPoint(Coordinate coordinate) {

        int centeredX = WINDOW_WIDTH / 2;
        int centeredY = WINDOW_HEIGHT / 2;

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
}
