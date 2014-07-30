package net.orekyuu.rabbithouse.block;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * BlockLoaderでロードしたデータを代入することを表すアノテーション<br>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BlockField {
    /**
     * Blockのname要素に指定した文字列を入れる
     */
    String name();
}
