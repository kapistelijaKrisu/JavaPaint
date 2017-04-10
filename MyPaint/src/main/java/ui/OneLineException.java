package ui;

public class OneLineException {

    public static void nullTest(Object target) {
        if (target == null) {
            throw new NullPointerException();
        }
    }
}
