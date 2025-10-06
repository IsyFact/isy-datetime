package de.bund.bva.isyfact.datetime.format;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class InFormatParseToPeriodInvalidTest {

    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { "", 0 }, { "xxx", 0 }, { "xxx 7a 6M", 0 }, { "7a 6M xxx", 6 },
            { "7a 5d xh", 6 }, { "7a -5d 4h", 3 }, { "4a 3M 2D", 6 }, { "4a3M2d", 0 }, { "7h 5a 6s", 0 } });
    }
    public String input;
    public long errorIndex;

    @MethodSource("data")
    @ParameterizedTest(name = "{index}: parseToPeriod({0})")
    public void parseToPeriod(String input, long errorIndex) {
        initInFormatParseToPeriodInvalidTest(input, errorIndex);
        try {
            InFormat.parseToPeriod(input);
            fail();
        } catch (DateTimeParseException e) {
            assertEquals(input, e.getParsedString());
            assertEquals(errorIndex, e.getErrorIndex());
        }
    }

    public void initInFormatParseToPeriodInvalidTest(String input, long errorIndex) {
        this.input = input;
        this.errorIndex = errorIndex;
    }
}
