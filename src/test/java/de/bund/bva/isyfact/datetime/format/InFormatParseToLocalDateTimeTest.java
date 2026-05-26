package de.bund.bva.isyfact.datetime.format;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class InFormatParseToLocalDateTimeTest {

    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { "1.8.2017 1:23", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "1.8.2017 1:23:45", LocalDateTime.of(2017, 8, 1, 1, 23, 45) },
            { "1.8.2017 1:23:45.123", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123000000) },
            { "1.8.2017 1:23:45.123456", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123456000) },
            { "1.8.2017 1:23:45.123456789", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123456789) },
            { "1.8.2017 1:23 +02:30", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "1.8.2017 1:23 Europe/Berlin", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "1.8.2017 01:23", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "1.8.2017 01:23:45", LocalDateTime.of(2017, 8, 1, 1, 23, 45) },
            { "1.8.2017 01:23:45.123", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123000000) },
            { "1.8.2017 01:23:45.123456", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123456000) },
            { "1.8.2017 01:23:45.123456789", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123456789) },
            { "1.8.2017 01:23 +02:30", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "1.8.2017 01:23 Europe/Berlin", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "1.08.2017 1:23", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "1.08.2017 1:23:45", LocalDateTime.of(2017, 8, 1, 1, 23, 45) },
            { "1.08.2017 1:23:45.123", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123000000) },
            { "1.08.2017 1:23:45.123456", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123456000) },
            { "1.08.2017 1:23:45.123456789", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123456789) },
            { "1.08.2017 1:23 +02:30", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "1.08.2017 1:23 Europe/Berlin", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "1.08.2017 01:23", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "1.08.2017 01:23:45", LocalDateTime.of(2017, 8, 1, 1, 23, 45) },
            { "1.08.2017 01:23:45.123", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123000000) },
            { "1.08.2017 01:23:45.123456", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123456000) },
            { "1.08.2017 01:23:45.123456789", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123456789) },
            { "1.08.2017 01:23 +02:30", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "1.08.2017 01:23 Europe/Berlin", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "01.8.2017 1:23", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "01.8.2017 1:23:45", LocalDateTime.of(2017, 8, 1, 1, 23, 45) },
            { "01.8.2017 1:23:45.123", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123000000) },
            { "01.8.2017 1:23:45.123456", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123456000) },
            { "01.8.2017 1:23:45.123456789", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123456789) },
            { "01.8.2017 1:23 +02:30", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "01.8.2017 1:23 Europe/Berlin", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "01.8.2017 01:23", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "01.8.2017 01:23:45", LocalDateTime.of(2017, 8, 1, 1, 23, 45) },
            { "01.8.2017 01:23:45.123", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123000000) },
            { "01.8.2017 01:23:45.123456", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123456000) },
            { "01.8.2017 01:23:45.123456789", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123456789) },
            { "01.8.2017 01:23 +02:30", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "01.8.2017 01:23 Europe/Berlin", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "01.08.2017 1:23", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "01.08.2017 1:23:45", LocalDateTime.of(2017, 8, 1, 1, 23, 45) },
            { "01.08.2017 1:23:45.123", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123000000) },
            { "01.08.2017 1:23:45.123456", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123456000) },
            { "01.08.2017 1:23:45.123456789", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123456789) },
            { "01.08.2017 1:23 +02:30", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "01.08.2017 1:23 Europe/Berlin", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "01.08.2017 01:23", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "01.08.2017 01:23:45", LocalDateTime.of(2017, 8, 1, 1, 23, 45) },
            { "01.08.2017 01:23:45.123", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123000000) },
            { "01.08.2017 01:23:45.123456", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123456000) },
            { "01.08.2017 01:23:45.123456789", LocalDateTime.of(2017, 8, 1, 1, 23, 45, 123456789) },
            { "01.08.2017 01:23 +02:30", LocalDateTime.of(2017, 8, 1, 1, 23, 0) },
            { "01.08.2017 01:23 Europe/Berlin", LocalDateTime.of(2017, 8, 1, 1, 23, 0) } });
    }
    public String input;
    public LocalDateTime expected;

    @MethodSource("data")
    @ParameterizedTest(name = "{index}: parseToLocalDateTime({0}) => {1}")
    public void parseToLocalDateTime(String input, LocalDateTime expected) {
        initInFormatParseToLocalDateTimeTest(input, expected);
        assertEquals(expected, InFormat.parseToLocalDateTime(input));
    }

    public void initInFormatParseToLocalDateTimeTest(String input, LocalDateTime expected) {
        this.input = input;
        this.expected = expected;
    }
}
