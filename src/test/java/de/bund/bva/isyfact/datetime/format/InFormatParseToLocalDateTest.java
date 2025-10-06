package de.bund.bva.isyfact.datetime.format;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class InFormatParseToLocalDateTest {

    public static Collection<Object[]> data() {
        return Arrays.asList(
            new Object[][] { { "1.1.1", LocalDate.of(1, 1, 1) }, { "1.1.11", LocalDate.of(11, 1, 1) },
                { "1.1.111", LocalDate.of(111, 1, 1) }, { "1.1.1111", LocalDate.of(1111, 1, 1) },
                { "01.01.1", LocalDate.of(1, 1, 1) }, { "01.01.11", LocalDate.of(11, 1, 1) },
                { "01.01.111", LocalDate.of(111, 1, 1) }, { "01.01.1111", LocalDate.of(1111, 1, 1) } });
    }
    public String input;
    public LocalDate expected;

    @MethodSource("data")
    @ParameterizedTest(name = "{index}: parseToLocalDate({0}) => {1}")
    public void parseToLocalDate(String input, LocalDate expected) {
        initInFormatParseToLocalDateTest(input, expected);
        assertEquals(expected, InFormat.parseToLocalDate(input));
    }

    public void initInFormatParseToLocalDateTest(String input, LocalDate expected) {
        this.input = input;
        this.expected = expected;
    }
}
