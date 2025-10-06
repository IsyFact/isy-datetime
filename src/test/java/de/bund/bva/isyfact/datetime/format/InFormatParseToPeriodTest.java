package de.bund.bva.isyfact.datetime.format;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Period;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class InFormatParseToPeriodTest {

    private static final Period P7A6M5D = Period.of(7, 6, 5);

    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { "5d", Period.ofDays(5) }, { "6M", Period.ofMonths(6) },
            { "7a", Period.ofYears(7) }, { "6M 5d", Period.of(0, 6, 5) }, { "7a 6M 5d", Period.of(7, 6, 5) },
            { " 7a   6M 5d ", Period.of(7, 6, 5) }, { "0a 0M 0d", Period.ZERO }, });
    }
    public String input;
    public Period expected;

    @MethodSource("data")
    @ParameterizedTest(name = "{index}: parseToPeriod({0}) => {1}")
    public void parseToPeriod(String input, Period expected) {
        initInFormatParseToPeriodTest(input, expected);
        assertEquals(expected, InFormat.parseToPeriod(input));
    }

    public void initInFormatParseToPeriodTest(String input, Period expected) {
        this.input = input;
        this.expected = expected;
    }
}