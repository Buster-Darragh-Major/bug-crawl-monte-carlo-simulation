package CLI;

import Engine.Contract.IAngleGenerator;
import Engine.Contract.ICircleExitChecker;
import Engine.Contract.ICoordinateCrawler;
import Engine.Implementation.CircleExitChecker;
import Engine.Implementation.ConstantDistanceCoordinateCrawler;
import Engine.Implementation.RandomAngleGenerator;
import Engine.Model.Coordinate;

import java.util.Arrays;

public class Main {

    private static final int NUMBER_OF_SIMULATIONS = 100;

    public static void main(String[] args){

        double[] crawlDistances = new double[] { 0.8, 0.4, 0.2, 0.1, 0.05, 0.025, 0.0125, 0.00625, 0.003125, 0.0015625, 0.00078125, 0.00039062 };

        for (double crawlDistance : crawlDistances){

            IAngleGenerator angleGenerator = new RandomAngleGenerator();
            ICoordinateCrawler coordinateCrawler = new ConstantDistanceCoordinateCrawler(crawlDistance, angleGenerator);
            ICircleExitChecker circleExitChecker = new CircleExitChecker(1);

            int[] turnHistory = new int[NUMBER_OF_SIMULATIONS];

            for (int i = 0; i < NUMBER_OF_SIMULATIONS; i++){
                Coordinate coordinate = new Coordinate(0, 0);

                int noOfTurns = 0;
                while (!circleExitChecker.isOutsideCircle(coordinate)) {
                    noOfTurns++;
                    coordinate = coordinateCrawler.randomMoveFrom(coordinate);
                }

                turnHistory[i] = noOfTurns;
            }

            double average = (double)Arrays.stream(turnHistory).sum() / turnHistory.length;
            System.out.println("Average turns for crawl distance: (" + crawlDistance + ") is:(" + average + ")");
        }
    }
}
