package net.orekyuu.rabbithouse.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;

/**
 * JavaScriptを使ってGUIを描画するためのクラス
 */
public class ScriptHelper {

    private Reader reader;
    private Invocable engine;
    /**
     * @param path jsのパス
     */
    public ScriptHelper(String path) throws FileNotFoundException {
        this(new FileInputStream(path));
    }

    /**
     * @param is jsのInputStream
     */
    public ScriptHelper(InputStream is) {
        reader = new InputStreamReader(new BufferedInputStream(is));
    }

    /**
     * 初期化を行います
     * @throws IOException
     */
    public void init() throws IOException {
        try {
            load();
        } catch (ScriptException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void load() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        engine.eval(reader);
        this.engine = (Invocable) engine;
    }

    private void close() throws IOException {
        if(reader != null) {
            reader.close();
        }
    }

    /**
     * バックグラウンドの描画を行う
     * @param xSize xSize
     * @param ySize ySize
     * @param width 横幅
     * @param height 高さ
     * @param gui GuiScreen
     * @param renderer FontRenderer
     */
    public void drawBackground(int xSize, int ySize, int width, int height, GuiScreen gui, FontRenderer renderer) {
        try {
            engine.invokeFunction("drawBackground", xSize, ySize, width, height, gui, renderer);
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * フォアグラウンドの描画を行う
     * @param xSize xSize
     * @param ySize ySize
     * @param width 幅
     * @param height 高さ
     * @param gui GuiScreen
     * @param renderer FontRenderer
     */
    public void drawForeground(int xSize, int ySize, int width, int height, GuiScreen gui, FontRenderer renderer) {
        try {
            engine.invokeFunction("drawForeground", xSize, ySize, width, height, gui, renderer);
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
