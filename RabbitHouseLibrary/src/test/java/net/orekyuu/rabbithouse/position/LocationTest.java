package net.orekyuu.rabbithouse.position;

import mockit.Mocked;
import net.minecraft.world.World;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class LocationTest {

    @Mocked
    private World world;

    @Test(expected = NullPointerException.class)
    public void testCreateException() throws Exception {
        new Location((World) null);
    }

    @Test
    public void testSetAndGetPosition() throws Exception {
        Location location = new Location(world);
        Position position = new Position(1.0F, 2.0F, 3.0F);
        location.setPosition(position);
        assertEquals(location.getPosition(), position);

        Location location1 = new Location(world);
        Position position1 = new Position(5.0F, 8.0F, 13.0F);
        location1.setPosition(position1);
        assertEquals(location1.getPosition(), position1);
    }

    @Test
    public void testGetDistanceAndGetMultiplyDistance() throws Exception {
        Location location1 = new Location(world);
        Position position1 = new Position(1.0F, 2.0F, 3.0F);
        location1.setPosition(position1);

        Location location2 = new Location(world);
        Position position2 = new Position(6.0F, 5.0F, 4.0F);
        location2.setPosition(position2);

        //Position
        assertEquals(location1.getDistance(position2), position1.getDistance(position2), 0.001);
        assertEquals(location1.getMultiplyDistance(position2), position1.getMultiplyDistance(position2), 0.001);
        //Location
        assertEquals(location1.getDistance(location2), position1.getDistance(position2), 0.001);
        assertEquals(location1.getMultiplyDistance(location2), position1.getMultiplyDistance(position2), 0.001);
    }

    @Test
    public void testEquals() throws Exception {
        //Commit時に未使用と警告が出る
        Location location3 = new Location(world);
        Position position3 = new Position(1.0F, 2.0F, 3.0F);
        Location location4 = new Location(world);
        Position position4 = new Position(1.0F, 2.0F, 3.0F);

        //交換可能性
        assertTrue(location3.equals(location4));
        assertTrue(location4.equals(location3));
        //equalsで等しい時、hashCodeは等しくなければいけない
        assertEquals(location3.hashCode(), location4.hashCode());
    }
}