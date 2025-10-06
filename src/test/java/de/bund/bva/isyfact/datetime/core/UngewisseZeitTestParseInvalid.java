package de.bund.bva.isyfact.datetime.core;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class UngewisseZeitTestParseInvalid {

    public static Collection<Object> data() {
        return Arrays.asList(
            new Object[] { "", "xxx", "01:20", "10:aa:20", "10:-5:20", "10:xx:20", "xx:10:xx", "xx:xx:10",
                " 10:08:20", });
    }
    public String input;

    @MethodSource("data")
    @ParameterizedTest(name = "{index}: parse({0})")
    public void parse(String input) throws Exception {
        initUngewisseZeitTestParseInvalid(input);
        assertThrows(DateTimeParseException.class, () ->
            UngewisseZeit.parse(input));
    }

    public void initUngewisseZeitTestParseInvalid(String input) {
        this.input = input;
    }
}