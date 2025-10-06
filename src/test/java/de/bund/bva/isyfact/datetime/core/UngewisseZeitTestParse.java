package de.bund.bva.isyfact.datetime.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class UngewisseZeitTestParse {

    public static Collection<Object[]> data() {
        return Arrays.asList(
            new Object[][] { { "xx:xx:xx", UngewisseZeit.leer() }, { "09:xx:xx", UngewisseZeit.of(9) },
                { "17:xx:xx", UngewisseZeit.of(17) }, { "09:09:xx", UngewisseZeit.of(9, 9) },
                { "09:30:xx", UngewisseZeit.of(9, 30) }, { "17:09:xx", UngewisseZeit.of(17, 9) },
                { "17:30:xx", UngewisseZeit.of(17, 30) }, { "17:30:45", UngewisseZeit.of(17, 30, 45) } });
    }
    public String input;
    public UngewisseZeit expected;

    @MethodSource("data")
    @ParameterizedTest(name = "{index}: parse({0}) => {1}")
    public void parse(String input, UngewisseZeit expected) throws Exception {
        initUngewisseZeitTestParse(input, expected);
        assertEquals(expected, UngewisseZeit.parse(input));
    }

    public void initUngewisseZeitTestParse(String input, UngewisseZeit expected) {
        this.input = input;
        this.expected = expected;
    }
}