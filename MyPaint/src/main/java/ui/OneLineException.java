package ui;

public class OneLineException {

    public static void throwIfIsNull(Object target) {
        if (target == null) {
            throw new NullPointerException();
        }
    }
}
