package UnitTests.Engine.Implementation;

import Engine.Implementation.ConstantDistanceCoordinateCrawler;
import Engine.Model.Coordinate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConstantDistanceCoordinateCrawlerTests {

    @Test
    public void Angle0(){
        ConstantDistanceCoordinateCrawler coordinateCrawler = new ConstantDistanceCoordinateCrawler(1,
                () -> Math.toRadians(0));

        Coordinate output = coordinateCrawler.randomMoveFrom(new Coordinate(0, 0));
        assertEquals(0, output.getX(), 0);
        assertEquals(1, output.getY(), 0);
    }

    @Test
    public void Angle90(){
        ConstantDistanceCoordinateCrawler coordinateCrawler = new ConstantDistanceCoordinateCrawler(1,
                () -> Math.toRadians(90));

        Coordinate output = coordinateCrawler.randomMoveFrom(new Coordinate(0, 0));
        assertEquals(1, output.getX(), 0);
        assertEquals(0, output.getY(), 0);
    }

    @Test
    public void Angle180(){
        ConstantDistanceCoordinateCrawler coordinateCrawler = new ConstantDistanceCoordinateCrawler(1,
                () -> Math.toRadians(180));

        Coordinate output = coordinateCrawler.randomMoveFrom(new Coordinate(0, 0));
        assertEquals(0, output.getX(), 0);
        assertEquals(-1, output.getY(), 0);
    }

    @Test
    public void Angle270(){
        ConstantDistanceCoordinateCrawler coordinateCrawler = new ConstantDistanceCoordinateCrawler(1,
                () -> Math.toRadians(270));

        Coordinate output = coordinateCrawler.randomMoveFrom(new Coordinate(0, 0));
        assertEquals(-1, output.getX(), 0);
        assertEquals(0, output.getY(), 0);
    }

    @Test
    public void Angle45(){
        ConstantDistanceCoordinateCrawler coordinateCrawler = new ConstantDistanceCoordinateCrawler(1,
                () -> Math.toRadians(45));

        Coordinate output = coordinateCrawler.randomMoveFrom(new Coordinate(0, 0));
        assertEquals(Math.sin(Math.toRadians(45)), output.getX(), 0);
        assertEquals(Math.cos(Math.toRadians(45)), output.getY(), 0);
    }

    @Test
    public void Angle135(){
        ConstantDistanceCoordinateCrawler coordinateCrawler = new ConstantDistanceCoordinateCrawler(1,
                () -> Math.toRadians(135));

        Coordinate output = coordinateCrawler.randomMoveFrom(new Coordinate(0, 0));
        assertEquals(Math.cos(Math.toRadians(45)), output.getX(), 0);
        assertEquals(-Math.sin(Math.toRadians(45)), output.getY(), 0);
    }

    @Test
    public void Angle225(){
        ConstantDistanceCoordinateCrawler coordinateCrawler = new ConstantDistanceCoordinateCrawler(1,
                () -> Math.toRadians(225));

        Coordinate output = coordinateCrawler.randomMoveFrom(new Coordinate(0, 0));
        assertEquals(-Math.sin(Math.toRadians(45)), output.getX(), 0);
        assertEquals(-Math.cos(Math.toRadians(45)), output.getY(), 0);
    }

    @Test
    public void Angle315(){
        ConstantDistanceCoordinateCrawler coordinateCrawler = new ConstantDistanceCoordinateCrawler(1,
                () -> Math.toRadians(315));

        Coordinate output = coordinateCrawler.randomMoveFrom(new Coordinate(0, 0));
        assertEquals(-Math.cos(Math.toRadians(45)), output.getX(), 0);
        assertEquals(Math.sin(Math.toRadians(45)), output.getY(), 0);
    }

    @Test
    public void OffCenterAngle315(){
        ConstantDistanceCoordinateCrawler coordinateCrawler = new ConstantDistanceCoordinateCrawler(1,
                () -> Math.toRadians(315));

        Coordinate output = coordinateCrawler.randomMoveFrom(new Coordinate(0.5, 0.5));
        assertEquals(-Math.cos(Math.toRadians(45)) + 0.5, output.getX(), 0);
        assertEquals(Math.sin(Math.toRadians(45)) + 0.5, output.getY(), 0);
    }
}
