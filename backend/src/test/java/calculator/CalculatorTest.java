package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test void addsPositiveValues() { assertEquals(4.0, calculator.evaluate("2", "+", "2")); }
    @Test void subtractsNegativeResult() { assertEquals(-5.0, calculator.evaluate("3", "-", "8")); }
    @Test void multipliesByZero() { assertEquals(0.0, calculator.evaluate("-9", "*", "0"), 0.000001); }
    @Test void dividesExactly() { assertEquals(2.5, calculator.evaluate("10", "/", "4")); }
    @Test void rejectsDivisionByZero() { assertThrows(ArithmeticException.class, () -> calculator.evaluate("1", "/", "0")); }
    @Test void rejectsNegativeZeroDivisor() { assertThrows(ArithmeticException.class, () -> calculator.evaluate("1", "/", "-0.0")); }
    @Test void computesRemainder() { assertEquals(1.0, calculator.evaluate("7", "%", "3")); }
    @Test void computesPower() { assertEquals(8.0, calculator.evaluate("2", "^", "3")); }
    @Test void rejectsRemainderByZero() { assertThrows(ArithmeticException.class, () -> calculator.evaluate("7", "%", "0")); }
    @Test void rejectsBlankOperand() { assertThrows(IllegalArgumentException.class, () -> calculator.evaluate(" ", "+", "2")); }
    @Test void rejectsInvalidNumber() { assertThrows(IllegalArgumentException.class, () -> calculator.evaluate("two", "+", "2")); }
    @Test void rejectsUnknownOperator() { assertThrows(IllegalArgumentException.class, () -> calculator.evaluate("1", "?", "2")); }
}

