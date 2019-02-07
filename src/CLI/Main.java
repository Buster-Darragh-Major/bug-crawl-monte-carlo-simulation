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

    private static final int WINDOW_WIDTH = 4000;
    private static final int WINDOW_HEIGHT = 4000;

    @Override
    public void start(Stage primaryStage) throws Exception {

        IVisualizationManager visualizationManager = new VisualizationManger(WINDOW_HEIGHT, WINDOW_WIDTH,
                new RandomColorPathDrawer(new Color(1, 0, 0, 1)),
                new PngImageExporter(WINDOW_HEIGHT, WINDOW_WIDTH));
        visualizationManager.displayWindow(primaryStage);
        visualizationManager.drawCircle(0, 0, 1);

        IAngleGenerator angleGenerator = new RandomAngleGenerator();
        ICoordinateCrawler coordinateCrawler = new ConstantDistanceCoordinateCrawler(0.005, angleGenerator);
        ICircleExitChecker circleExitChecker = new CircleExitChecker(1);

        new AnimationTimer() {

            private Coordinate _coordinate = new Coordinate(0, 0);

            @Override
            public void handle(long now) {
                Platform.runLater(() -> {
                    Coordinate tempCoord = coordinateCrawler.randomMoveFrom(_coordinate);
                    if (!circleExitChecker.isOutsideCircle(tempCoord)) {
                        _coordinate = tempCoord;
                    }
                    visualizationManager.addPoint(_coordinate);
                });
            }
        }.start();

//        Task task = new Task() {
//            @Override
//            protected Object call() throws Exception {
//
//                Coordinate coordinate = new Coordinate(0, 0);
//
//                while (!circleExitChecker.isOutsideCircle(coordinate)) {
//                    coordinate = coordinateCrawler.randomMoveFrom(coordinate);
//                    visualizationManager.addPoint(coordinate);
//                }
//                return null;
//            }
//        };
//
//        Thread t = new Thread(task);
//        t.start();

//        double[] crawlDistances = new double[] { 0.8, 0.4, 0.2, 0.1, 0.05, 0.025, 0.0125, 0.00625, 0.003125, 0.0015625, 0.00078125, 0.00039062 };
//
//        for (double crawlDistance : crawlDistances){
//
//            IAngleGenerator angleGenerator = new RandomAngleGenerator();
//            ICoordinateCrawler coordinateCrawler = new ConstantDistanceCoordinateCrawler(crawlDistance, angleGenerator);
//            ICircleExitChecker circleExitChecker = new CircleExitChecker(1);
//
//            int[] turnHistory = new int[NUMBER_OF_SIMULATIONS];
//
//            for (int i = 0; i < NUMBER_OF_SIMULATIONS; i++){
//                Coordinate coordinate = new Coordinate(0, 0);
//
//                int noOfTurns = 0;
//                while (!circleExitChecker.isOutsideCircle(coordinate)) {
//                    noOfTurns++;
//                    coordinate = coordinateCrawler.randomMoveFrom(coordinate);
//                }
//
//                turnHistory[i] = noOfTurns;
//            }
//
//            double average = (double)Arrays.stream(turnHistory).sum() / turnHistory.length;
//            System.out.println("Average turns for crawl distance: (" + crawlDistance + ") is:(" + average + ")");
//        }
    }
}
