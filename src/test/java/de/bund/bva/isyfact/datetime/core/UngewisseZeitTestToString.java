package de.bund.bva.isyfact.datetime.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class UngewisseZeitTestToString {

    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { UngewisseZeit.leer(), "xx:xx:xx" },
            { UngewisseZeit.of(17, 30, 01), "17:30:01" }, { UngewisseZeit.of(17), "17:xx:xx" },
            { UngewisseZeit.of(17, 30), "17:30:xx" },
            { UngewisseZeit.of(LocalTime.of(17, 30, 1), LocalTime.of(17, 45, 45)), "17:30:01 - 17:45:45" } });
    }
    public UngewisseZeit input;
    public String expected;

    @MethodSource("data")
    @ParameterizedTest(name = "{index}: toString({0}) => {1}")
    public void parse(UngewisseZeit input, String expected) throws Exception {
        initUngewisseZeitTestToString(input, expected);
        assertEquals(expected, input.toString());
    }

    public void initUngewisseZeitTestToString(UngewisseZeit input, String expected) {
        this.input = input;
        this.expected = expected;
    }
}