package Engine.Implementation;

import Engine.Contract.IAngleGenerator;
import Engine.Contract.ICoordinateCrawler;
import Engine.Model.Coordinate;

public class ConstantDistanceCoordinateCrawler implements ICoordinateCrawler {

    private double _distance;
    private IAngleGenerator _angleGenerator;

    public ConstantDistanceCoordinateCrawler(double distance, IAngleGenerator angleGenerator) {
        _distance = distance;
        _angleGenerator = angleGenerator;
    }

    @Override
    public Coordinate randomMoveFrom(Coordinate coordinate) {

        // Use trig to find new location of coordinates from randomly generated angle
        double angleToTravel = _angleGenerator.getAngle();

        double shiftX;
        double shiftY;

        if (angleToTravel < Math.toRadians(90)) {
            shiftX = Math.sin(angleToTravel) * _distance;
            shiftY = Math.cos(angleToTravel) * _distance;
        } else if (angleToTravel < Math.toRadians(180)) {
            shiftX = Math.cos(angleToTravel - Math.toRadians(90)) * _distance;
            shiftY = -Math.sin(angleToTravel - Math.toRadians(90)) * _distance;
        } else if (angleToTravel < Math.toRadians(270)) {
            shiftX = -Math.sin(angleToTravel - Math.toRadians(180)) * _distance;
            shiftY = -Math.cos(angleToTravel - Math.toRadians(180)) * _distance;
        } else {
            shiftX = -Math.cos(angleToTravel - Math.toRadians(270)) * _distance;
            shiftY = Math.sin(angleToTravel - Math.toRadians(270)) * _distance;
        }

        return new Coordinate(
                coordinate.getX() + shiftX,
                coordinate.getY() + shiftY);
    }
}
