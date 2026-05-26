package de.bund.bva.isyfact.datetime.format;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class InFormatParseToZonedDateTimeTest {

    private static ZonedDateTime expectedZonedDateTime =
        ZonedDateTime.of(2017, 8, 1, 1, 23, 0, 0, ZoneId.of("Europe/Berlin"));

    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { "1.8.2017 1:23 Europe/Berlin", expectedZonedDateTime },
            { "1.8.2017 01:23 Europe/Berlin", expectedZonedDateTime },
            { "1.08.2017 1:23 Europe/Berlin", expectedZonedDateTime },
            { "1.08.2017 01:23 Europe/Berlin", expectedZonedDateTime },
            { "01.8.2017 1:23 Europe/Berlin", expectedZonedDateTime },
            { "01.8.2017 01:23 Europe/Berlin", expectedZonedDateTime },
            { "01.08.2017 1:23 Europe/Berlin", expectedZonedDateTime },
            { "01.08.2017 01:23 Europe/Berlin", expectedZonedDateTime } });
    }
    public String input;
    public ZonedDateTime expected;

    @MethodSource("data")
    @ParameterizedTest(name = "{index}: parseToZonedDateTime({0}) => {1}")
    public void parseToZonedDateTime(String input, ZonedDateTime expected) {
        initInFormatParseToZonedDateTimeTest(input, expected);
        assertEquals(expected, InFormat.parseToZonedDateTime(input));
    }

    public void initInFormatParseToZonedDateTimeTest(String input, ZonedDateTime expected) {
        this.input = input;
        this.expected = expected;
    }
}
