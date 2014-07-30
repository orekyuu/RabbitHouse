package net.orekyuu.rabbithouse.block;

/**
 * Block.jsonのファイルフォーマットが条件を満たしていない時の例外
 */
public class BlockDataFormatException extends RuntimeException {
    BlockDataFormatException(String message) {
        super(message);
    }
}
