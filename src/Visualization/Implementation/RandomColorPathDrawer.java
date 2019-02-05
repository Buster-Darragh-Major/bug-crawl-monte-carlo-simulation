package Visualization.Implementation;

import Visualization.Contract.IPathDrawer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Random;

public class RandomColorPathDrawer implements IPathDrawer {

    private Color _lastColor;

    public RandomColorPathDrawer(Color startingColor) {

        _lastColor = startingColor;
    }

    @Override
    public void drawPath(GraphicsContext gc, double x1, double y1, double x2, double y2) {

        Paint originalStroke = gc.getStroke(); // Save gcs original stroke value

        Color color = getNeighbouringColor(_lastColor);
        _lastColor = color;
        gc.setStroke(color);
        gc.strokeLine(x1, y1, x2, y2);

        gc.setStroke(originalStroke); // Return gc to its original stroke color
    }

    @Override
    public void drawPoint(GraphicsContext gc, double x, double y) {

        Paint originalStroke = gc.getStroke(); // Save gcs original stroke value

        Color color = getNeighbouringColor(_lastColor);
        _lastColor = color;
        gc.setStroke(color);
        gc.fillOval(x, y, 1, 1);

        gc.setStroke(originalStroke); // Return gc to its original stroke color
    }

    private Color getNeighbouringColor(Color color) {

        double randomRed = Math.round(2 * new Random().nextDouble()) - 1;
        double randomGreen = Math.round(2 * new Random().nextDouble()) - 1;
        double randomBlue = Math.round(2 * new Random().nextDouble()) - 1;

        return new Color(
                normalizeColor(color.getRed() + (randomRed * 0.01)),
                normalizeColor(color.getGreen() + (randomGreen * 0.01)),
                normalizeColor(color.getBlue() + (randomBlue * 0.01)), 1);
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
