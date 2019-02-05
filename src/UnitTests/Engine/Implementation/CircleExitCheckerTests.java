package UnitTests.Engine.Implementation;

import Engine.Implementation.CircleExitChecker;
import Engine.Model.Coordinate;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class CircleExitCheckerTests {

    @Test
    public void Center() {
        CircleExitChecker circleExitChecker = new CircleExitChecker(1);
        assertFalse(circleExitChecker.isOutsideCircle(new Coordinate(0, 0)));
    }

    @Test
    public void AlongXAxis() {
        CircleExitChecker circleExitChecker = new CircleExitChecker(1);
        assertFalse(circleExitChecker.isOutsideCircle(new Coordinate(0.5, 0)));
        assertFalse(circleExitChecker.isOutsideCircle(new Coordinate(-0.5, 0)));
    }

    @Test
    public void AlongYAxis() {
        CircleExitChecker circleExitChecker = new CircleExitChecker(1);
        assertFalse(circleExitChecker.isOutsideCircle(new Coordinate(0, 0.5)));
        assertFalse(circleExitChecker.isOutsideCircle(new Coordinate(0, -0.5)));
    }

    @Test
    public void AlongBothAxes() {
        CircleExitChecker circleExitChecker = new CircleExitChecker(1);
        assertFalse(circleExitChecker.isOutsideCircle(new Coordinate(0.5, 0.5)));
        assertFalse(circleExitChecker.isOutsideCircle(new Coordinate(0.5, -0.5)));
        assertFalse(circleExitChecker.isOutsideCircle(new Coordinate(-0.5, 0.5)));
        assertFalse(circleExitChecker.isOutsideCircle(new Coordinate(-0.5, -0.5)));
    }

    @Test
    public void AlongBorder() {
        CircleExitChecker circleExitChecker = new CircleExitChecker(1);
        assertFalse(circleExitChecker.isOutsideCircle(new Coordinate(0, 1)));
        assertFalse(circleExitChecker.isOutsideCircle(new Coordinate(0, -1)));
        assertFalse(circleExitChecker.isOutsideCircle(new Coordinate(1, 0)));
        assertFalse(circleExitChecker.isOutsideCircle(new Coordinate(-1, 0)));
    }

    @Test
    public void OutsideBorder() {
        CircleExitChecker circleExitChecker = new CircleExitChecker(1);
        assertTrue(circleExitChecker.isOutsideCircle(new Coordinate(1, 1)));
        assertTrue(circleExitChecker.isOutsideCircle(new Coordinate(1, -1)));
        assertTrue(circleExitChecker.isOutsideCircle(new Coordinate(-1, 1)));
        assertTrue(circleExitChecker.isOutsideCircle(new Coordinate(-1, -1)));
    }
}
