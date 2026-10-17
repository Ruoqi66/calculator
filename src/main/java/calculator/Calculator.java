package calculator;

import org.apache.commons.lang3.StringUtils;

public final class Calculator {
    public double evaluate(String left, String operator, String right) {
        if (StringUtils.isBlank(left) || StringUtils.isBlank(operator) || StringUtils.isBlank(right)) {
            throw new IllegalArgumentException("Operands and operator must not be blank.");
        }

        final double x;
        final double y;
        try {
            x = Double.parseDouble(left);
            y = Double.parseDouble(right);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Operands must be valid numbers.", exception);
        }

        return switch (operator) {
            case "+" -> x + y;
            case "-" -> x - y;
            case "*" -> x * y;
            case "/" -> {
                if (y == 0.0d) {
                    throw new ArithmeticException("Division by zero is not allowed.");
                }
                yield x / y;
            }
            case "%" -> {
                if (y == 0.0d) {
                    throw new ArithmeticException("Remainder by zero is not allowed.");
                }
                yield x % y;
            }
            case "^" -> Math.pow(x, y);
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        };
    }
}











