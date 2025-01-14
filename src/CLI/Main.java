package CLI;

import Engine.Contract.IAngleGenerator;
import Engine.Contract.ICircleExitChecker;
import Engine.Contract.ICoordinateCrawler;
import Engine.Implementation.CircleExitChecker;
import Engine.Implementation.ConstantDistanceCoordinateCrawler;
import Engine.Implementation.RandomAngleGenerator;
import Engine.Model.Coordinate;
import Visualization.Contract.IVisualizationManager;
import Visualization.Implementation.PngImageExporter;
import Visualization.Implementation.RandomColorPathDrawer;
import Visualization.Implementation.VisualizationManger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;
    private static final int NUMBER_OF_TURNS_PER_FRAME = 100000;
    private static final double LINE_THICKNESS = 0.3;
    private static final double SPEED_OF_COLOR_CHANGE = 0.1;
    private static final double CRAWL_SIZE = 0.0005;
    private static final Color STARTING_COLOR = Color.RED;
    private static final Color BACKGROUND_COLOR= Color.BLACK;

    @Override
    public void start(Stage primaryStage) throws Exception {

        IVisualizationManager visualizationManager = new VisualizationManger(WINDOW_HEIGHT, WINDOW_WIDTH,
                new RandomColorPathDrawer(STARTING_COLOR, LINE_THICKNESS, SPEED_OF_COLOR_CHANGE),
                new PngImageExporter(WINDOW_HEIGHT, WINDOW_WIDTH));
        visualizationManager.displayWindow(primaryStage, BACKGROUND_COLOR);

        IAngleGenerator angleGenerator = new RandomAngleGenerator();
        ICoordinateCrawler coordinateCrawler = new ConstantDistanceCoordinateCrawler(CRAWL_SIZE, angleGenerator);
        ICircleExitChecker circleExitChecker = new CircleExitChecker(1);

        new AnimationTimer() {

            private Coordinate _coordinate = new Coordinate(0, 0);

            @Override
            public void handle(long now) {
                Platform.runLater(() -> {
                    for (int i = 0; i < NUMBER_OF_TURNS_PER_FRAME; i++){
                        Coordinate tempCoord = coordinateCrawler.randomMoveFrom(_coordinate);
                        if (!circleExitChecker.isOutsideCircle(tempCoord)) {
                            _coordinate = tempCoord;
                        }
                        visualizationManager.addPoint(_coordinate);
                    }
                });
            }
        }.start();
    }
}
