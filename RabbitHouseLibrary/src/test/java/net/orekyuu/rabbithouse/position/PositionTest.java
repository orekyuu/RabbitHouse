package net.orekyuu.rabbithouse.position;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class PositionTest {

    @Test
    public void testGet() {
        Position position = new Position(1, 2, 3);
        assertEquals(position.getX(), 1.0, 0.001);
        assertEquals(position.getY(), 2.0, 0.001);
        assertEquals(position.getZ(), 3.0, 0.001);

        Position position2 = new Position(3, 1, 2);
        assertEquals(position2.getX(), 3.0, 0.001);
        assertEquals(position2.getY(), 1.0, 0.001);
        assertEquals(position2.getZ(), 2.0, 0.001);
    }

    @Test
    public void testPlus() {
        Position pos1 = new Position(0, 0, 0);

        Position x = pos1.plusX(1);
        assertEquals(x.getX(), 1.0, 0.001);
        assertEquals(x.getY(), 0.0, 0.001);
        assertEquals(x.getZ(), 0.0, 0.001);

        Position y = pos1.plusY(1);
        assertEquals(y.getX(), 0.0, 0.001);
        assertEquals(y.getY(), 1.0, 0.001);
        assertEquals(y.getZ(), 0.0, 0.001);

        Position z = pos1.plusZ(1);
        assertEquals(z.getX(), 0.0, 0.001);
        assertEquals(z.getY(), 0.0, 0.001);
        assertEquals(z.getZ(), 1.0, 0.001);

        Position pos2 = new Position(1, 1, 1);
        Position plus = pos1.plus(pos2);
        assertEquals(plus.getX(), 1.0, 0.001);
        assertEquals(plus.getY(), 1.0, 0.001);
        assertEquals(plus.getZ(), 1.0, 0.001);
    }

    @Test
    public void testMinus() {
        Position pos1 = new Position(0, 0, 0);

        Position x = pos1.minusX(1);
        assertEquals(x.getX(), -1.0, 0.001);
        assertEquals(x.getY(), 0.0, 0.001);
        assertEquals(x.getZ(), 0.0, 0.001);

        Position y = pos1.minusY(1);
        assertEquals(y.getX(), 0.0, 0.001);
        assertEquals(y.getY(), -1.0, 0.001);
        assertEquals(y.getZ(), 0.0, 0.001);

        Position z = pos1.minusZ(1);
        assertEquals(z.getX(), 0.0, 0.001);
        assertEquals(z.getY(), 0.0, 0.001);
        assertEquals(z.getZ(), -1.0, 0.001);

        Position pos2 = new Position(1, 1, 1);
        Position minus = pos1.minus(pos2);
        assertEquals(minus.getX(), -1.0, 0.001);
        assertEquals(minus.getY(), -1.0, 0.001);
        assertEquals(minus.getZ(), -1.0, 0.001);
    }

    @Test
    public void testDistance() {
        Position pos1 = new Position(0, 0, 0);
        Position pos2 = new Position(1, 1, 1);
        assertEquals(pos1.getDistance(pos2), 1.732, 0.001);
        assertEquals(pos1.getMultiplyDistance(pos2), 3.0, 0.001);

        Position pos3 = new Position(3, 3, 3);
        Position pos4 = new Position(4, 4, 4);
        assertEquals(pos3.getDistance(pos4), 1.732, 0.001);
        assertEquals(pos3.getMultiplyDistance(pos4), 3.0, 0.001);
    }

    @Test
    public void testEquals() {
        Position pos1 = new Position(0, 0, 0);
        Position pos2 = new Position(0, 0, 0);

        //交換可能性
        assertTrue(pos1.equals(pos2));
        assertTrue(pos2.equals(pos1));
        //equalsで等しい時、hashCodeは等しくなければいけない
        assertEquals(pos1.hashCode(), pos2.hashCode());
    }

}