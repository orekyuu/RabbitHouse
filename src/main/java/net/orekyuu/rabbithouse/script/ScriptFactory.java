package net.orekyuu.rabbithouse.script;

import javax.script.Bindings;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.io.*;

/**
 * Scriptを作成するクラス
 */
public class ScriptFactory {

    private InputStream inputStream;
    private Bindings bindings;

    /**
     * @param path ファイルのパス
     * @throws FileNotFoundException
     */
    public ScriptFactory(String path) throws FileNotFoundException {
        this(new BufferedInputStream(new FileInputStream(path)));
    }

    /**
     * @param is ファイルのInputStream
     */
    public ScriptFactory(InputStream is) {
        inputStream = is;
        bindings = new SimpleBindings();
    }

    /**
     * 第一引数の値でオブジェクトをJavaScriptにバインドします。
     * @param fieldName JavaScriptに定義する変数名
     * @param obj 変数にバインドするオブジェクト
     * @return ScriptFactory
     */
    public ScriptFactory addBind(String fieldName, Object obj) {
        bindings.put(fieldName, obj);
        return this;
    }

    /**
     * Scriptを作成します。
     * @return Script
     * @throws ScriptException
     */
    public Script create() throws ScriptException, IOException {
        SimpleScript simpleScript = new SimpleScript(inputStream);
        simpleScript.load(bindings);
        return simpleScript;
    }
}
