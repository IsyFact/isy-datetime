package de.bund.bva.isyfact.datetime.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class UngewissesDatumTestToString {

    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { UngewissesDatum.leer(), "xx.xx.xxxx" },
            { UngewissesDatum.of(2017, 8, 10), "10.08.2017" }, { UngewissesDatum.of(2017), "xx.xx.2017" },
            { UngewissesDatum.of(2017, 8), "xx.08.2017" },
            { UngewissesDatum.of(LocalDate.of(2017, 8, 10), LocalDate.of(2017, 8, 31)),
                "10.08.2017 - 31.08.2017" } });
    }
    public UngewissesDatum input;
    public String expected;

    @MethodSource("data")
    @ParameterizedTest(name = "{index}: toString({0}) => {1}")
    public void parse(UngewissesDatum input, String expected) throws Exception {
        initUngewissesDatumTestToString(input, expected);
        assertEquals(expected, input.toString());
    }

    public void initUngewissesDatumTestToString(UngewissesDatum input, String expected) {
        this.input = input;
        this.expected = expected;
    }
}