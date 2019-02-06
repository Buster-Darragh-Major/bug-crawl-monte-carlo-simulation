package Visualization.Contract;

import Engine.Model.Coordinate;
import javafx.scene.canvas.GraphicsContext;

public interface IPathDrawer {

    void drawPath(GraphicsContext gc, double x1, double y1, double x2, double y2);

    void drawPath(GraphicsContext gc, double[] xPoints, double[] yPoints, int nPoints);

    void drawPoint(GraphicsContext gc, double x, double y);

}
