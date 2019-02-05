package Engine.Model;

public class Coordinate {

    private double _x;
    private double _y;

    public Coordinate(double x, double y){
        _x = x;
        _y = y;
    }

    public double getX() {
        return _x;
    }

    public void setX(double x) {
        _x = x;
    }

    public double getY() {
        return _y;
    }

    public void setY(double y) {
        _y = y;
    }
}
