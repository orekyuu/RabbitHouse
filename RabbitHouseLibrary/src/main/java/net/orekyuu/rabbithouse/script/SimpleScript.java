package net.orekyuu.rabbithouse.script;

import javax.script.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * GUIを描画するためのスクリプト
 */
class SimpleScript implements Script {

    private boolean isAutoReload;
    private InputStream is;
    private Invocable script;

    SimpleScript(InputStream is) {
        this.is = is;
    }

    /**
     * ロードを行います
     */
    void load(Bindings bindings) throws ScriptException, IOException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
        Reader reader = new InputStreamReader(is);
        engine.eval(reader);
        script = (Invocable) engine;
        reader.close();
    }

    @Override
    public Object callFunction(String functionName, Object ... args) throws ScriptException, NoSuchMethodException {
        return script.invokeFunction(functionName, args);
    }
}
