package ru.ttttinkoffhackathon.util;


public class DangerTracker {
    private static boolean isMyAssInDanger;

    public static void setIsMyAssInDanger(boolean b) {
        isMyAssInDanger = b;
    }

    public static boolean getIsMyAssInDanger() {
        return isMyAssInDanger;
    }

    public static void reset() {
        isMyAssInDanger = false;
    }

    public static void updateIfInDanger(boolean b) {
        isMyAssInDanger = b ? b : isMyAssInDanger;
    }
}
