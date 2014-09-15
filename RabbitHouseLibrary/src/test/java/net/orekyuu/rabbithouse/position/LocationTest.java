package net.orekyuu.rabbithouse.position;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class LocationTest {

    class MockWorld extends World {

        public MockWorld() {
            //null, "", null, WorldSettings, null
            super(null, "test", null, new WorldSettings(0, WorldSettings.GameType.NOT_SET, false, false, WorldType.DEFAULT), null);
        }

        @Override
        protected IChunkProvider createChunkProvider() {
            return null;
        }

        @Override
        protected int func_152379_p() {
            return 0;
        }

        @Override
        public Entity getEntityByID(int p_73045_1_) {
            return null;
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }

    @Test(expected = NullPointerException.class)
    public void testCreateException() throws Exception {
        new Location((World) null);
    }

    @Test
    public void testSetAndGetPosition() throws Exception {
        Location location = new Location(new MockWorld());
        Position position = new Position(1.0F, 2.0F, 3.0F);
        location.setPosition(position);
        assertEquals(location.getPosition(), position);

        Location location1 = new Location(new MockWorld());
        Position position1 = new Position(5.0F, 8.0F, 13.0F);
        location1.setPosition(position1);
        assertEquals(location1.getPosition(), position1);
    }

    @Test
    public void testGetDistanceAndGetMultiplyDistance() throws Exception {
        World testWorld = new MockWorld();
        Location location1 = new Location(testWorld);
        Position position1 = new Position(1.0F, 2.0F, 3.0F);
        location1.setPosition(position1);

        Location location2 = new Location(testWorld);
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
        World testWorld = new MockWorld();
        //Commit時に未使用と警告が出る
        Location location3 = new Location(testWorld);
        Position position3 = new Position(1.0F, 2.0F, 3.0F);
        Location location4 = new Location(testWorld);
        Position position4 = new Position(1.0F, 2.0F, 3.0F);

        //交換可能性
        assertTrue(location3.equals(location4));
        assertTrue(location4.equals(location3));
        //equalsで等しい時、hashCodeは等しくなければいけない
        assertEquals(location3.hashCode(), location4.hashCode());
    }
}