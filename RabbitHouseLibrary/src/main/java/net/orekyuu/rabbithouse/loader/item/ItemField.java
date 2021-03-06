package net.orekyuu.rabbithouse.loader.item;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ItemLoaderでロードしたデータを代入することを表すアノテーション
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ItemField {
    /**
     * Itemのname要素に指定した文字列を入れる
     *
     * @return Itemの名前
     */
    String value();
}
