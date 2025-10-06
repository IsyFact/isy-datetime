package de.bund.bva.isyfact.datetime.core;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DateTimeException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class UngewisseZeitTestStundeMinuteSekundeInvalid {

    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            // hour, minute, second
            { 1, 1, 60 }, { 1, 60, 1 }, { 1, 60, 60 }, { 24, 1, 1 }, { 24, 1, 60 }, { 24, 60, 1 },
            { 24, 60, 60 } });
    }
    public int stunde;
    public int minute;
    public int sekunde;

    @MethodSource("data")
    @ParameterizedTest(name = "{index}: of(Stunde={0}, Minute={1}, Sekunde={2})")
    public void ofStundeMinuteSekunde(int stunde, int minute, int sekunde) {
        initUngewisseZeitTestStundeMinuteSekundeInvalid(stunde, minute, sekunde);
        assertThrows(DateTimeException.class, () ->
            UngewisseZeit.of(stunde, minute, sekunde));
    }

    public void initUngewisseZeitTestStundeMinuteSekundeInvalid(int stunde, int minute, int sekunde) {
        this.stunde = stunde;
        this.minute = minute;
        this.sekunde = sekunde;
    }
}