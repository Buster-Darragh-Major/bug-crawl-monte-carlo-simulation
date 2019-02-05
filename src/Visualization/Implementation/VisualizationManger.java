package Visualization.Implementation;

import Engine.Model.Coordinate;
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

public class VisualizationManger extends Canvas implements IVisualizationManager {

    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 600;

    private Canvas _canvas;
    private GraphicsContext _gc;
    private double _pixelsPerUnitX;
    private double _pixelsPerUnitY;

    private Coordinate _lastCoordinate;
    private Color _lastColor;

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
            Color color;
            if (_lastColor != null) {
                double randomRed = Math.round(2 * new Random().nextDouble()) - 1;
                double randomGreen = Math.round(2 * new Random().nextDouble()) - 1;
                double randomBlue = Math.round(2 * new Random().nextDouble()) - 1;
                color = new Color(
                        flatten(_lastColor.getRed() + (randomRed * 0.01)),
                        flatten(_lastColor.getGreen() + (randomGreen * 0.01)),
                        flatten(_lastColor.getBlue() + randomBlue * 0.01), 1);
            } else {
                color = new Color(1, 0, 0, 1);
            }
            _lastColor = color;
            _gc.setStroke(color);
            _gc.strokeLine(_lastCoordinate.getX(), _lastCoordinate.getY(), plottedX, plottedY);
        } else {
            _gc.fillOval(plottedX, plottedY, 1, 1);
        }

        // Remember this coordinate to draw from next time
        _lastCoordinate = new Coordinate(plottedX, plottedY);
    }

    private double flatten(double in) {
        if (in > 1) {
            return 1;
        } else if (in < 0) {
            return 0;
        } else {
            return in;
        }
    }
}
