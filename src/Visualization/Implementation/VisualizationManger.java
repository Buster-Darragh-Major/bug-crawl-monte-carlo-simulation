package Visualization.Implementation;

import Engine.Model.Coordinate;
import Visualization.Contract.IPathDrawer;
import Visualization.Contract.IVisualizationManager;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

// TODO: zoom
// TODO: mode where bug jumps back in, run indefinately?
// TODO: look at exporting as svg
// TODO: Live running
// TODO: when live running, have different stroke modes to have different look? idk??

public class VisualizationManger extends Canvas implements IVisualizationManager {

    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 600;

    private IPathDrawer _pathDrawer;
    private GraphicsContext _gc;

    private double _pixelsPerUnitX;
    private double _pixelsPerUnitY;

    private Coordinate _lastCoordinate;

    /**
     * Defaults the canvas to be 2 units across
     * @param pathDrawer
     */
    public VisualizationManger(IPathDrawer pathDrawer) {

        _pixelsPerUnitX = WINDOW_WIDTH / 2;
        _pixelsPerUnitY = WINDOW_HEIGHT / 2;
        _pathDrawer = pathDrawer;
    }

    @Override
    public void displayWindow(Stage stage) {
        Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        _gc = canvas.getGraphicsContext2D();
        Pane root = new Pane();
        root.setStyle("-fx-padding: 10;");
        root.getChildren().add(canvas);

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

    @Override
    public void addPoints(List<Coordinate> coordinates) {
        int centeredX = WINDOW_WIDTH / 2;
        int centeredY = WINDOW_HEIGHT / 2;

        double[] xPoints = new double[coordinates.size()];
        double[] yPoints = new double[coordinates.size()];

        for (int i = 0; i < coordinates.size(); i++) {
            xPoints[i] = centeredX + (_pixelsPerUnitX * coordinates.get(i).getX());
            yPoints[i] = centeredY - (_pixelsPerUnitY * coordinates.get(i).getY()); // subtracted because canvas y increases as go down
        }

        _pathDrawer.drawPath(_gc, xPoints, yPoints, coordinates.size());
    }
}
