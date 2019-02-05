package Engine.Implementation;

import Engine.Contract.IAngleGenerator;

import java.util.Random;

public class RandomAngleGenerator implements IAngleGenerator {

    @Override
    public double getAngle() {
        return Math.toRadians(360 * new Random().nextDouble());
    }
}
