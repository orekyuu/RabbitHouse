package net.orekyuu.rabbithouse.item;

/**
 * Item.jsonのファイルフォーマットが条件を満たしていない時の例外
 */
public class ItemDataFormatException extends RuntimeException {
    ItemDataFormatException(String message) {
        super(message);
    }
}
