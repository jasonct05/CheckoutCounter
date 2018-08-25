package Util;

public class Utility {
    public static final boolean debug = false;
    public static void debug(String s) {
        if (Utility.debug) {
            System.out.println(s);
        }
    }
}
