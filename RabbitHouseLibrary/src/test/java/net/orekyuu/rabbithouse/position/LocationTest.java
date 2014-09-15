package net.orekyuu.rabbithouse.position;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LocationTest {

    class MockWorld extends World {

        public MockWorld() {
            super(null, "test", (WorldProvider) null, null, null);
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
        Position position1 = new Position(2.0F, 2.0F, 3.0F);
        location.setPosition(position1);
        assertEquals(location1.getPosition(), position1);
    }

    @Test
    public void testGetMultiplyDistance() throws Exception {

    }

    @Test
    public void testGetDistance() throws Exception {

    }

    @Test
    public void testEquals() throws Exception {

    }
}