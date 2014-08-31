package net.orekyuu.rabbithouse.script;

import javax.script.ScriptException;

/**
 * Scriptを表すインターフェイス
 */
public interface Script {

    /**
     * JavaScriptの関数を呼び出します。
     * @param functionName 関数名
     * @param args 関数に与える引数
     * @return 関数の戻り値
     * @throws javax.script.ScriptException
     * @throws NoSuchMethodException
     */
    Object callFunction(String functionName, Object ... args) throws ScriptException, NoSuchMethodException;
}
