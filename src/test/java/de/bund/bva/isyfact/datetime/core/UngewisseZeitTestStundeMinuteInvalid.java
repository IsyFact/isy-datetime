package de.bund.bva.isyfact.datetime.core;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DateTimeException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class UngewisseZeitTestStundeMinuteInvalid {

    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            // hour, minute
            { 1, 60 }, { 24, 1 }, { 24, 60 } });
    }
    public int stunde;
    public int minute;

    @MethodSource("data")
    @ParameterizedTest(name = "{index}: of(Stunde={0}, Minute={1})")
    public void ofStundeMinute(int stunde, int minute) {
        initUngewisseZeitTestStundeMinuteInvalid(stunde, minute);
        assertThrows(DateTimeException.class, () ->
            UngewisseZeit.of(stunde, minute));
    }

    public void initUngewisseZeitTestStundeMinuteInvalid(int stunde, int minute) {
        this.stunde = stunde;
        this.minute = minute;
    }
}