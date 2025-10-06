package de.bund.bva.isyfact.datetime.format;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class InFormatParseToLocalTimeTest {

    public static Collection<Object[]> data() {
        return Arrays.asList(
            new Object[][] { { "1:23", LocalTime.of(1, 23) }, { "1:23:45", LocalTime.of(1, 23, 45) },
                { "1:23:45.123", LocalTime.of(1, 23, 45, 123000000) },
                { "1:23:45.123456", LocalTime.of(1, 23, 45, 123456000) },
                { "1:23:45.123456789", LocalTime.of(1, 23, 45, 123456789) }, { "01:23", LocalTime.of(1, 23) },
                { "01:23:45", LocalTime.of(1, 23, 45) },
                { "01:23:45.123", LocalTime.of(1, 23, 45, 123000000) },
                { "01:23:45.123456", LocalTime.of(1, 23, 45, 123456000) },
                { "01:23:45.123456789", LocalTime.of(1, 23, 45, 123456789) } });
    }
    public String input;
    public LocalTime expected;

    @MethodSource("data")
    @ParameterizedTest(name = "{index}: parseToLocalTime({0}) => {1}")
    public void parseToLocalTime(String input, LocalTime expected) {
        initInFormatParseToLocalTimeTest(input, expected);
        assertEquals(expected, InFormat.parseToLocalTime(input));
    }

    public void initInFormatParseToLocalTimeTest(String input, LocalTime expected) {
        this.input = input;
        this.expected = expected;
    }
}