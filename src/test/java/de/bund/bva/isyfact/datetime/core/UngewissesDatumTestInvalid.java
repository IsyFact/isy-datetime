package de.bund.bva.isyfact.datetime.core;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DateTimeException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class UngewissesDatumTestInvalid {

    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            // year, month, day
            { 0, 0, -1 },
            { 0, -1, 0 },
            { 0, -1, -1 },
            { -1, 0, -1 },
            { -1, -1, 0 },
            { -1, -1, -1 },
            { 0, 0, 100 },
            { 0, 100, 0 },
            { 0, 100, 100 },
            { 100, 0, 100 },
            { 100, 100, 0 },
            { 100, 100, 100 }
        });
    }
    public int jahr;
    public int monat;
    public int tag;

    @MethodSource("data")
    @ParameterizedTest(name = "{index}: of(Jahr={0}, Monat={1}, Tag={2})")
    public void ofJahrMonatTag(int jahr, int monat, int tag) {
        initUngewissesDatumTestInvalid(jahr, monat, tag);
        assertThrows(DateTimeException.class, () ->
            UngewissesDatum.of(jahr, monat, tag));
    }

    public void initUngewissesDatumTestInvalid(int jahr, int monat, int tag) {
        this.jahr = jahr;
        this.monat = monat;
        this.tag = tag;
    }
}