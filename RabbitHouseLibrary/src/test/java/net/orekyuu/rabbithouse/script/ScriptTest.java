package net.orekyuu.rabbithouse.script;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class ScriptTest {

    public String bindingTest = "bind";
    @Test
    public void testAddBind() throws Exception {
        Script script = new ScriptFactory(getClass().getResourceAsStream("test.js"))
                .addBind("bindingTest", bindingTest).create();
        assertEquals(script.callFunction("getBindStr"), bindingTest);
    }

    @Test
    public void testCreate() throws Exception {
        Script script = new ScriptFactory(getClass().getResourceAsStream("test.js")).create();
        assertTrue(script != null);
    }

    @Test(expected = IOException.class)
    public void testCreateException() throws Exception {
        Script script = new ScriptFactory(new FileInputStream("notfound.js")).create();
        assertTrue(script != null);
    }
}