package net.orekyuu.rabbithouse.util;

public class Log {
    public static void print(Object object, String prefix, String info) {
        System.out.println(new StringBuilder().append("[").append(object.getClass().getName()).append("]").append(prefix).append(": ").append(info).toString());
    }

    public static void print(Object object, String prefix, Object info) {
        print(object, prefix, info.toString());
    }
}
