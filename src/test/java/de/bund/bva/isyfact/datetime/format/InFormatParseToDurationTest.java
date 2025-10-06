package de.bund.bva.isyfact.datetime.format;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class InFormatParseToDurationTest {

    private static final Duration D4H3M2S1MS =
        Duration.ofHours(4).plusMinutes(3).plusSeconds(2).plusMillis(1);

    public static Collection<Object[]> data() {
        return Arrays.asList(
            new Object[][] { { "1ms", Duration.ofMillis(1) }, { "2s", Duration.ofSeconds(2) },
                { "3min", Duration.ofMinutes(3) }, { "4h", Duration.ofHours(4) },
                { "2s 1ms", Duration.ofSeconds(2).plusMillis(1) },
                { "3min 2s 1ms", Duration.ofMinutes(3).plusSeconds(2).plusMillis(1) },
                { "4h 3min 2s 1ms", D4H3M2S1MS }, { "  4h   3min  2s 1ms ", D4H3M2S1MS },
                { "0h 0min 0s 0ms", Duration.ZERO }, });
    }
    public String input;
    public Duration expected;

    @MethodSource("data")
    @ParameterizedTest(name = "{index}: parseToDuration({0}) => {1}")
    public void parseToDuration(String input, Duration expected) {
        initInFormatParseToDurationTest(input, expected);
        assertEquals(expected, InFormat.parseToDuration(input));
    }

    public void initInFormatParseToDurationTest(String input, Duration expected) {
        this.input = input;
        this.expected = expected;
    }
}
