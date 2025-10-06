package de.bund.bva.isyfact.datetime.format;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class InFormatParseToDurationInvalidTest {

    public static Collection<Object[]> data() {
        return Arrays.asList(
            new Object[][] { { "", 0 }, { "xxx", 0 }, { "xxx 7h 6min", 0 }, { "7h 6min xxx", 8 },
                { "7h 5min xs", 8 }, { "7h -5min 4s", 3 }, { "4h 3min 2S", 8 }, { "4h3min2s", 0 },
                { "7a 5h 6min", 0 } });
    }
    public String input;
    public long errorIndex;

    @MethodSource("data")
    @ParameterizedTest(name = "{index}: parseToDuration({0})")
    public void parseToDuration(String input, long errorIndex) {
        initInFormatParseToDurationInvalidTest(input, errorIndex);
        try {
            InFormat.parseToDuration(input);
            fail();
        } catch (DateTimeParseException e) {
            assertEquals(errorIndex, e.getErrorIndex());
        }
    }

    public void initInFormatParseToDurationInvalidTest(String input, long errorIndex) {
        this.input = input;
        this.errorIndex = errorIndex;
    }
}
