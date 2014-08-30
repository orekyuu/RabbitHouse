package net.orekyuu.rabbithouse.loader;

import java.util.List;

/**
 * 引数を受け取り、初期化を行うことができることを表すインターフェイスです。<br>
 * argsで引数を与える場合は、Initializableを実装する必要があります。
 */
public interface Initializable {
    void initialize(List<String> args);
}
