package Engine.Implementation;

import Engine.Contract.ICircleExitChecker;
import Engine.Model.Coordinate;

public class CircleExitChecker implements ICircleExitChecker {

    private double _radius;

    public CircleExitChecker(double radius) {
        _radius = radius;
    }

    @Override
    public boolean isOutsideCircle(Coordinate coordinate) {
        return _radius < Math.sqrt(
                Math.pow(Math.abs(coordinate.getX()), 2) +
                Math.pow(Math.abs(coordinate.getY()), 2));
    }
}
