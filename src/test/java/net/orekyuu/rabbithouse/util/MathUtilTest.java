package net.orekyuu.rabbithouse.util;
import junit.framework.TestCase;

public class MathUtilTest extends TestCase {

    public void testMax() {
        assertEquals(5, MathUtil.max(1, 2, 5, 3, 4));
        assertEquals(5L, MathUtil.max(1L, 2L, 5L, 3L, 4L));
        assertEquals(5.0, MathUtil.max(1, 0, 2.0, 5.0, 3.0, 4.0), 0.0);
        assertEquals(5f, MathUtil.max(1f, 2f, 5f, 3f, 4f), 0.0f);
        assertEquals(5, MathUtil.max((byte) 1, (byte) 2, (byte) 5, (byte) 3, (byte) 4));
        assertEquals(5, MathUtil.max((short) 1, (short) 2, (short) 5, (short) 3, (short) 4));
    }

    public void testMin() {
        assertEquals(1, MathUtil.min(1, 2, 5, 3, 4));
        assertEquals(1L, MathUtil.min(1L, 2L, 5L, 3L, 4L));
        assertEquals(1.0, MathUtil.min(1.0, 2.0, 5.0, 3.0, 4.0), 0.0);
        assertEquals(1f, MathUtil.min(1f, 2f, 5f, 3f, 4f), 0.0f);
        assertEquals(1, MathUtil.min((byte) 1, (byte) 2, (byte) 5, (byte) 3, (byte) 4));
        assertEquals(1, MathUtil.min((short) 1, (short) 2, (short) 5, (short) 3, (short) 4));
    }

    public void testClamp() {
        //int
        assertEquals(5, MathUtil.clamp(5, 1, 10));
        assertEquals(1, MathUtil.clamp(-1, 1, 10));
        assertEquals(10, MathUtil.clamp(20, 1, 10));

        //long
        assertEquals(5L, MathUtil.clamp(5L, 1L, 10L));
        assertEquals(1L, MathUtil.clamp(-1L, 1L, 10L));
        assertEquals(10L, MathUtil.clamp(20L, 1L, 10L));

        //double
        assertEquals(5d, MathUtil.clamp(5d, 1d, 10d), 0.0);
        assertEquals(1d, MathUtil.clamp(-1d, 1d, 10d), 0.0);
        assertEquals(10d, MathUtil.clamp(20d, 1d, 10d), 0.0);

        //float
        assertEquals(5f, MathUtil.clamp(5f, 1f, 10f), 0.0f);
        assertEquals(1f, MathUtil.clamp(-1f, 1f, 10f), 0.0f);
        assertEquals(10f, MathUtil.clamp(20f, 1f, 10f), 0.0f);

        //byte
        assertEquals(5, MathUtil.clamp((byte) 5, (byte) 1, (byte) 10));
        assertEquals(1, MathUtil.clamp((byte) -1, (byte) 1, (byte) 10));
        assertEquals(10, MathUtil.clamp((byte) 20, (byte) 1, (byte) 10));

        //short
        assertEquals(5, MathUtil.clamp((short) 5, (short) 1, (short) 10));
        assertEquals(1, MathUtil.clamp((short) -1, (short) 1, (short) 10));
        assertEquals(10, MathUtil.clamp((short) 20, (short) 1, (short) 10));
    }

    public void testIsOver() {
        assertFalse(MathUtil.isOver(5, 0, 10));
        assertFalse(MathUtil.isOver(0, 0, 10));
        assertFalse(MathUtil.isOver(10, 0, 10));
        assertTrue(MathUtil.isOver(-1, 0, 10));
        assertTrue(MathUtil.isOver(11, 0, 10));

        assertFalse(MathUtil.isOver(5L, 0L, 10L));
        assertFalse(MathUtil.isOver(0L, 0L, 10L));
        assertFalse(MathUtil.isOver(10L, 0L, 10L));
        assertTrue(MathUtil.isOver(-1L, 0L, 10L));
        assertTrue(MathUtil.isOver(11L, 0L, 10L));

        assertFalse(MathUtil.isOver(5d, 0d, 10d));
        assertFalse(MathUtil.isOver(0d, 0d, 10d));
        assertFalse(MathUtil.isOver(10d, 0d, 10d));
        assertTrue(MathUtil.isOver(-1d, 0d, 10d));
        assertTrue(MathUtil.isOver(11d, 0d, 10d));

        assertFalse(MathUtil.isOver(5f, 0f, 10f));
        assertFalse(MathUtil.isOver(0f, 0f, 10f));
        assertFalse(MathUtil.isOver(10f, 0f, 10f));
        assertTrue(MathUtil.isOver(-1f, 0f, 10f));
        assertTrue(MathUtil.isOver(11f, 0f, 10f));

        assertFalse(MathUtil.isOver((byte)5, (byte)0, (byte)10));
        assertFalse(MathUtil.isOver((byte)0, (byte)0, (byte)10));
        assertFalse(MathUtil.isOver((byte)10, (byte)0, (byte)10));
        assertTrue(MathUtil.isOver((byte)-1, (byte)0, (byte)10));
        assertTrue(MathUtil.isOver((byte)11, (byte)0, (byte)10));

        assertFalse(MathUtil.isOver((short)5, (short)0, (short)10));
        assertFalse(MathUtil.isOver((short)0, (short)0, (short)10));
        assertFalse(MathUtil.isOver((short)10, (short)0, (short)10));
        assertTrue(MathUtil.isOver((short)-1, (short)0, (short)10));
        assertTrue(MathUtil.isOver((short)11, (short)0, (short)10));
    }
}