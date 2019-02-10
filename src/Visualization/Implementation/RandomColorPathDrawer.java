package Visualization.Implementation;

import Visualization.Contract.IPathDrawer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Random;

public class RandomColorPathDrawer implements IPathDrawer {

    private double _lineThickness;
    private double _speedOfColorChange;

    private double _lastRed;
    private double _lastGreen;
    private double _lastBlue;

    public RandomColorPathDrawer(Color startingColor, double lineThickness, double speedOfColorChange) {

        _lineThickness = lineThickness;
        _speedOfColorChange = speedOfColorChange;

        _lastRed = startingColor.getRed();
        _lastGreen = startingColor.getGreen();
        _lastBlue = startingColor.getBlue();
    }

    @Override
    public void drawPath(GraphicsContext gc, double x1, double y1, double x2, double y2) {

        Paint originalStroke = gc.getStroke(); // Save gcs original stroke value

        Color color = getNeighbouringColor();
        gc.setStroke(color);
        gc.setLineWidth(_lineThickness);
        gc.strokeLine(x1, y1, x2, y2);

        gc.setStroke(originalStroke); // Return gc to its original stroke color
    }

    @Override
    public void drawPath(GraphicsContext gc, double[] xPoints, double[] yPoints, int nPoints) {
        Paint originalStroke = gc.getStroke(); // Save gcs original stroke value

        Color color = getNeighbouringColor();
        gc.setStroke(color);
        gc.setLineWidth(_lineThickness);
        gc.strokePolyline(xPoints, yPoints, nPoints);

        gc.setStroke(originalStroke); // Return gc to its original stroke color
    }

    @Override
    public void drawPoint(GraphicsContext gc, double x, double y) {

        Paint originalStroke = gc.getStroke(); // Save gcs original stroke value

        Color color = getNeighbouringColor();
        gc.setStroke(color);
        gc.setLineWidth(_lineThickness);
        gc.fillOval(x, y, 1, 1);

        gc.setStroke(originalStroke); // Return gc to its original stroke color
    }

    private Color getNeighbouringColor() {

        double randomRed = (2 * _speedOfColorChange * new Random().nextDouble()) - _speedOfColorChange;
        double randomGreen = (2 * _speedOfColorChange * new Random().nextDouble()) - _speedOfColorChange;
        double randomBlue = (2 * _speedOfColorChange * new Random().nextDouble()) - _speedOfColorChange;

        return new Color(
                _lastRed = normalizeColor(_lastRed + (randomRed * 0.01)),
                _lastGreen = normalizeColor(_lastGreen + (randomGreen * 0.01)),
                _lastBlue = normalizeColor(_lastBlue + (randomBlue * 0.01)), 1);
    }

    private double normalizeColor(double in) {
        if (in > 1) {
            return 1;
        } else if (in < 0) {
            return 0;
        } else {
            return in;
        }
    }
}
