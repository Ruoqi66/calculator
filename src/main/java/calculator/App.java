package calculator;

public final class App {
    private App() { }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java -jar caculator-1.0.0.jar <number> <operator> <number>");
            return;
        }
        try {
            System.out.println(new Calculator().evaluate(args[0], args[1], args[2]));
        } catch (IllegalArgumentException | ArithmeticException exception) {
            System.err.println("Error: " + exception.getMessage());
        }
    }
}
