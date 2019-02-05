package Visualization.Contract;

import Engine.Model.Coordinate;
import javafx.stage.Stage;

public interface IVisualizationManager {

    void displayWindow(Stage stage);

    void drawCircle(int x, int y, int radius);

    void addPoint(Coordinate coordinate);

}
