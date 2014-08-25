package net.orekyuu.rabbithouse.util;

/**
 * 数学に関するユーティリティクラス
 */
public class MathUtil {
    //インスタンス化を防ぐ
    private MathUtil() {
        throw new AssertionError("インスタンス化は出来ません");
    }

    /**
     * 与えられた引数の中から最大値を返します
     * @param args 比較したい数値
     * @return 最大値
     */
    public static int max(final int ... args) {
        int res = args[0];
        for (int i : args) {
            if(i > res)
                res = i;
        }
        return res;
    }

    /**
     * 与えられた引数の中から最大値を返します
     * @param args 比較したい数値
     * @return 最大値
     */
    public static long max(final long ... args) {
        long res = args[0];
        for (long i : args) {
            if(i > res)
                res = i;
        }
        return res;
    }

    /**
     * 与えられた引数の中から最大値を返します
     * @param args 比較したい数値
     * @return 最大値
     */
    public static float max(final float ... args) {
        float res = args[0];
        for (float i : args) {
            if(i > res)
                res = i;
        }
        return res;
    }

    /**
     * 与えられた引数の中から最大値を返します
     * @param args 比較したい数値
     * @return 最大値
     */
    public static double max(final double ... args) {
        double res = args[0];
        for (double i : args) {
            if(i > res)
                res = i;
        }
        return res;
    }

    /**
     * 与えられた引数の中から最大値を返します
     * @param args 比較したい数値
     * @return 最大値
     */
    public static byte max(final byte ... args) {
        byte res = args[0];
        for (byte i : args) {
            if(i > res)
                res = i;
        }
        return res;
    }

    /**
     * 与えられた引数の中から最大値を返します
     * @param args 比較したい数値
     * @return 最大値
     */
    public static short max(final short ... args) {
        short res = args[0];
        for (short i : args) {
            if(i > res)
                res = i;
        }
        return res;
    }

    /**
     * 与えられた引数の中から最小値を返します
     * @param args 比較したい数値
     * @return 最小値
     */
    public static int min(final int ... args) {
        int res = args[0];
        for (int i : args) {
            if(i < res)
                res = i;
        }
        return res;
    }

    /**
     * 与えられた引数の中から最小値を返します
     * @param args 比較したい数値
     * @return 最小値
     */
    public static long min(final long ... args) {
        long res = args[0];
        for (long i : args) {
            if(i < res)
                res = i;
        }
        return res;
    }

    /**
     * 与えられた引数の中から最小値を返します
     * @param args 比較したい数値
     * @return 最小値
     */
    public static float min(final float ... args) {
        float res = args[0];
        for (float i : args) {
            if(i < res)
                res = i;
        }
        return res;
    }

    /**
     * 与えられた引数の中から最小値を返します
     * @param args 比較したい数値
     * @return 最小値
     */
    public static double min(final double ... args) {
        double res = args[0];
        for (double i : args) {
            if(i < res)
                res = i;
        }
        return res;
    }

    /**
     * 与えられた引数の中から最小値を返します
     * @param args 比較したい数値
     * @return 最小値
     */
    public static byte min(final byte ... args) {
        byte res = args[0];
        for (byte i : args) {
            if(i < res)
                res = i;
        }
        return res;
    }

    /**
     * 与えられた引数の中から最小値を返します
     * @param args 比較したい数値
     * @return 最小値
     */
    public static short min(final short ... args) {
        short res = args[0];
        for (short i : args) {
            if(i < res)
                res = i;
        }
        return res;
    }

    /**
     * maxとminの間でvalueを返します<br>
     * valueが範囲を超えた時、切り取られた値を返します<br>
     * clamp(30, 0, 10)の時、戻り値は10となります
     * @param value 切り取る値
     * @param min 範囲の最小値
     * @param max 範囲の最大値
     * @return 指定の範囲で切り取られた値
     */
    public static int clamp(final int value, final int min, final int max) {
        if(value <= min) {
            return min;
        }
        if(max <= value) {
            return max;
        }
        return value;
    }

    /**
     * maxとminの間でvalueを返します<br>
     * valueが範囲を超えた時、切り取られた値を返します<br>
     * clamp(30, 0, 10)の時、戻り値は10となります
     * @param value 切り取る値
     * @param min 範囲の最小値
     * @param max 範囲の最大値
     * @return 指定の範囲で切り取られた値
     */
    public static long clamp(final long value, final long min, final long max) {
        if(value <= min) {
            return min;
        }
        if(max <= value) {
            return max;
        }
        return value;
    }

    /**
     * maxとminの間でvalueを返します<br>
     * valueが範囲を超えた時、切り取られた値を返します<br>
     * clamp(30, 0, 10)の時、戻り値は10となります
     * @param value 切り取る値
     * @param min 範囲の最小値
     * @param max 範囲の最大値
     * @return 指定の範囲で切り取られた値
     */
    public static double clamp(final double value, final double min, final double max) {
        if(value <= min) {
            return min;
        }
        if(max <= value) {
            return max;
        }
        return value;
    }

    /**
     * maxとminの間でvalueを返します<br>
     * valueが範囲を超えた時、切り取られた値を返します<br>
     * clamp(30, 0, 10)の時、戻り値は10となります
     * @param value 切り取る値
     * @param min 範囲の最小値
     * @param max 範囲の最大値
     * @return 指定の範囲で切り取られた値
     */
    public static float clamp(final float value, final float min, final float max) {
        if(value <= min) {
            return min;
        }
        if(max <= value) {
            return max;
        }
        return value;
    }

    /**
     * maxとminの間でvalueを返します<br>
     * valueが範囲を超えた時、切り取られた値を返します<br>
     * clamp(30, 0, 10)の時、戻り値は10となります
     * @param value 切り取る値
     * @param min 範囲の最小値
     * @param max 範囲の最大値
     * @return 指定の範囲で切り取られた値
     */
    public static byte clamp(final byte value, final byte min, final byte max) {
        if(value <= min) {
            return min;
        }
        if(max <= value) {
            return max;
        }
        return value;
    }

    /**
     * maxとminの間でvalueを返します<br>
     * valueが範囲を超えた時、切り取られた値を返します<br>
     * clamp(30, 0, 10)の時、戻り値は10となります
     * @param value 切り取る値
     * @param min 範囲の最小値
     * @param max 範囲の最大値
     * @return 指定の範囲で切り取られた値
     */
    public static short clamp(final short value, final short min, final short max) {
        if(value <= min) {
            return min;
        }
        if(max <= value) {
            return max;
        }
        return value;
    }

    /**
     * minとmaxの間からvalueが出ていないか判定します
     * @param value 調べたい値
     * @param min 最小値
     * @param max 最大値
     * @return minとmaxの間に収まっていればtrue
     */
    public static boolean isOver(final int value, final int min, final int max) {
        return min > value || value > max;
    }

    /**
     * minとmaxの間からvalueが出ていないか判定します
     * @param value 調べたい値
     * @param min 最小値
     * @param max 最大値
     * @return minとmaxの間に収まっていればtrue
     */
    public static boolean isOver(final long value, final long min, final long max) {
        return min > value || value > max;
    }

    /**
     * minとmaxの間からvalueが出ていないか判定します
     * @param value 調べたい値
     * @param min 最小値
     * @param max 最大値
     * @return minとmaxの間に収まっていればtrue
     */
    public static boolean isOver(final double value, final double min, final double max) {
        return min > value || value > max;
    }
    /**
     * minとmaxの間からvalueが出ていないか判定します
     * @param value 調べたい値
     * @param min 最小値
     * @param max 最大値
     * @return minとmaxの間に収まっていればtrue
     */
    public static boolean isOver(final float value, final float min, final float max) {
        return min > value || value > max;
    }
    /**
     * minとmaxの間からvalueが出ていないか判定します
     * @param value 調べたい値
     * @param min 最小値
     * @param max 最大値
     * @return minとmaxの間に収まっていればtrue
     */
    public static boolean isOver(final byte value, final byte min, final byte max) {
        return min > value || value > max;
    }
    /**
     * minとmaxの間からvalueが出ていないか判定します
     * @param value 調べたい値
     * @param min 最小値
     * @param max 最大値
     * @return minとmaxの間に収まっていればtrue
     */
    public static boolean isOver(final short value, final short min, final short max) {
        return min > value || value > max;
    }
}
