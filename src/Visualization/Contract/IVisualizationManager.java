package Visualization.Contract;

import Engine.Model.Coordinate;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public interface IVisualizationManager {

    void displayWindow(Stage stage, Color backgroundColor);

    void drawCircle(int x, int y, int radius);

    void addPoint(Coordinate coordinate);

    void addPoints(List<Coordinate> coordinates);

}
