package Visualization.Contract;

import Engine.Model.Coordinate;
import javafx.stage.Stage;

import java.util.List;

public interface IVisualizationManager {

    void displayWindow(Stage stage);

    void drawCircle(int x, int y, int radius);

    void addPoint(Coordinate coordinate);

    void addPoints(List<Coordinate> coordinates);

}
