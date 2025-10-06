package de.bund.bva.isyfact.datetime.format;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class InFormatParseToOffsetTimeTest {

    public static Collection<Object[]> data() {
        ZoneOffset zo = ZoneOffset.ofHoursMinutes(2, 30);

        return Arrays.asList(new Object[][] { { "1:23 +02:30", OffsetTime.of(1, 23, 0, 0, zo) },
            { "1:23:45 +02:30", OffsetTime.of(1, 23, 45, 0, zo) },
            { "1:23:45.123 +02:30", OffsetTime.of(1, 23, 45, 123000000, zo) },
            { "1:23:45.123456 +02:30", OffsetTime.of(1, 23, 45, 123456000, zo) },
            { "1:23:45.123456789 +02:30", OffsetTime.of(1, 23, 45, 123456789, zo) },
            { "01:23 +02:30", OffsetTime.of(1, 23, 0, 0, zo) },
            { "01:23:45 +02:30", OffsetTime.of(1, 23, 45, 0, zo) },
            { "01:23:45.123 +02:30", OffsetTime.of(1, 23, 45, 123000000, zo) },
            { "01:23:45.123456 +02:30", OffsetTime.of(1, 23, 45, 123456000, zo) },
            { "01:23:45.123456789 +02:30", OffsetTime.of(1, 23, 45, 123456789, zo) } });
    }
    public String input;
    public OffsetTime expected;

    @MethodSource("data")
    @ParameterizedTest(name = "{index}: parseToOffsetTime({0}) => {1}")
    public void parseToOffsetTime(String input, OffsetTime expected) {
        initInFormatParseToOffsetTimeTest(input, expected);
        assertEquals(expected, InFormat.parseToOffsetTime(input));
    }

    public void initInFormatParseToOffsetTimeTest(String input, OffsetTime expected) {
        this.input = input;
        this.expected = expected;
    }
}
