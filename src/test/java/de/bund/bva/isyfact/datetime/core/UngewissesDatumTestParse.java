package de.bund.bva.isyfact.datetime.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class UngewissesDatumTestParse {

    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { "00.00.0000", UngewissesDatum.leer() },
            { "00.00.2017", UngewissesDatum.of(2017) }, { "00.01.2017", UngewissesDatum.of(2017, 1) },
            { "00.10.2017", UngewissesDatum.of(2017, 10) }, { "xx.xx.xxxx", UngewissesDatum.leer() },
            { "xx.xx.2017", UngewissesDatum.of(2017) }, { "xx.01.2017", UngewissesDatum.of(2017, 1) },
            { "xx.10.2017", UngewissesDatum.of(2017, 10) }, { "01.01.2017", UngewissesDatum.of(2017, 1, 1) },
            { "01.10.2017", UngewissesDatum.of(2017, 10, 1) },
            { "10.10.2017", UngewissesDatum.of(2017, 10, 10) },
            { "xxxx-xx-xx", UngewissesDatum.leer() },
            { "2017-xx-xx", UngewissesDatum.of(2017) }, { "2017-01-xx", UngewissesDatum.of(2017, 1) },
            { "2017-10-xx", UngewissesDatum.of(2017, 10) }, { "2017-01-01", UngewissesDatum.of(2017, 1, 1) },
            { "2017-10-01", UngewissesDatum.of(2017, 10, 1) },
            { "2017-10-10", UngewissesDatum.of(2017, 10, 10) },
        });
    }
    public String input;
    public UngewissesDatum expected;

    @MethodSource("data")
    @ParameterizedTest(name = "{index}: parse({0}) => {1}")
    public void parse(String input, UngewissesDatum expected) throws Exception {
        initUngewissesDatumTestParse(input, expected);
        assertEquals(expected, UngewissesDatum.parse(input));
    }

    public void initUngewissesDatumTestParse(String input, UngewissesDatum expected) {
        this.input = input;
        this.expected = expected;
    }
}