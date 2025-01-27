package de.bund.bva.isyfact.datetime.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class UngewissesDatumTest {

    @Test
    public void leer() {
        UngewissesDatum leer = UngewissesDatum.leer();

        assertTrue(leer.isLeer());
    }

    @Test
    public void ofJahr() {
        UngewissesDatum nurJahr = UngewissesDatum.of(2017);

        assertFalse(nurJahr.isLeer());
        assertEquals(LocalDate.of(2017, 1, 1), nurJahr.getAnfang());
        assertEquals(LocalDate.of(2017, 12, 31), nurJahr.getEnde());
    }

    @Test
    public void ofJahrMonat() {
        UngewissesDatum jahrMonat = UngewissesDatum.of(2016, 2);

        assertFalse(jahrMonat.isLeer());
        assertEquals(LocalDate.of(2016, 2, 1), jahrMonat.getAnfang());
        assertEquals(LocalDate.of(2016, 2, 29), jahrMonat.getEnde());
    }

    @Test
    public void ofJahrMonatTag() {
        UngewissesDatum jahrMontagTag = UngewissesDatum.of(2017, 8, 1);

        assertFalse(jahrMontagTag.isLeer());
        assertEquals(LocalDate.of(2017, 8, 1), jahrMontagTag.getAnfang());
        assertEquals(LocalDate.of(2017, 8, 1), jahrMontagTag.getEnde());
    }

    @Test
    public void ofLocalDate() {
        UngewissesDatum ofLocalDate = UngewissesDatum.of(LocalDate.of(2017, 8, 1), LocalDate.of(2017, 8, 31));

        assertFalse(ofLocalDate.isLeer());
        assertEquals(LocalDate.of(2017, 8, 1), ofLocalDate.getAnfang());
        assertEquals(LocalDate.of(2017, 8, 31), ofLocalDate.getEnde());

        ofLocalDate = UngewissesDatum.of(LocalDate.of(2017, 8, 1), LocalDate.of(2017, 8, 1));

        assertFalse(ofLocalDate.isLeer());
        assertEquals(UngewissesDatum.of(2017, 8, 1), ofLocalDate);
    }

    @Test //(expected = NullPointerException.class)
    public void ofLocalDateNullArguments() {
        assertThatThrownBy(() -> UngewissesDatum.of(null, null)).isInstanceOf(NullPointerException.class);
    }

    @Test //(expected = DateTimeException.class)
    public void ofLocalDateAnfageNachEnde() {
        assertThatThrownBy(() ->
            UngewissesDatum.of(LocalDate.of(2017, 8, 1), LocalDate.of(2017, 7, 1)))
            .isInstanceOf(DateTimeException.class);
    }

    @Test //(expected = DateTimeException.class)
    public void ofLocalDateJahreVerschieden() {
        assertThatThrownBy(() -> UngewissesDatum.of(LocalDate.of(2017, 1, 1), LocalDate.of(2018, 8, 1)))
            .isInstanceOf(DateTimeException.class);
    }

    @Test //(expected = NullPointerException.class)
    public void parseNullArgument() {
        assertThatThrownBy(() -> UngewissesDatum.parse(null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void getJahr() {
        UngewissesDatum datum = UngewissesDatum.leer();
        assertEquals(Optional.empty(), datum.getJahr());

        datum = UngewissesDatum.of(2017);
        assertEquals(Optional.of(2017), datum.getJahr());

        datum = UngewissesDatum.of(LocalDate.of(2017, 8, 1), LocalDate.of(2017, 8, 10));
        assertEquals(Optional.of(2017), datum.getJahr());
    }

    @Test
    public void getMonat() {
        UngewissesDatum datum = UngewissesDatum.leer();
        assertEquals(Optional.empty(), datum.getMonat());

        datum = UngewissesDatum.of(2017, 8);
        assertEquals(Optional.of(8), datum.getMonat());

        datum = UngewissesDatum.of(LocalDate.of(2017, 8, 1), LocalDate.of(2017, 8, 10));
        assertEquals(Optional.of(8), datum.getMonat());

        datum = UngewissesDatum.of(LocalDate.of(2017, 8, 1), LocalDate.of(2017, 9, 10));
        assertEquals(Optional.empty(), datum.getMonat());
    }

    @Test
    public void getTag() {
        UngewissesDatum datum = UngewissesDatum.leer();
        assertEquals(Optional.empty(), datum.getTag());

        datum = UngewissesDatum.of(2017, 8, 1);
        assertEquals(Optional.of(1), datum.getTag());

        datum = UngewissesDatum.of(LocalDate.of(2017, 8, 1), LocalDate.of(2017, 8, 10));
        assertEquals(Optional.empty(), datum.getTag());
    }

    @Test
    public void toLocalDate() {
        UngewissesDatum datum = UngewissesDatum.leer();
        assertEquals(Optional.empty(), datum.toLocalDate());

        datum = UngewissesDatum.of(2017, 8, 1);
        assertEquals(Optional.of(LocalDate.of(2017, 8, 1)), datum.toLocalDate());
    }

    @Test
    public void equalsWithEqualObjects() {
        UngewissesDatum date1 = UngewissesDatum.of(2023, 1, 1);
        UngewissesDatum date2 = UngewissesDatum.of(2023, 1, 1);

        assertEquals(date1, date2);
        // Wenn Referenzen identisch sind, wird restliche Logik von equals übersprungen
        assertEquals(date1, date1);
        assertEquals(date1.hashCode(), date2.hashCode());
    }

    @Test
    public void equalsWithDifferentObjects() {
        UngewissesDatum date1 = UngewissesDatum.of(2023, 1, 1);
        UngewissesDatum date2 = UngewissesDatum.of(2023, 1, 2);

        assertNotEquals(date1, date2);
    }

    @Test
    public void equalsWithNull() {
        UngewissesDatum date1 = UngewissesDatum.of(2023, 1, 1);
        assertNotEquals(null, date1);
    }

    @Test
    public void equalsWithDifferentTypes() {
        UngewissesDatum date1 = UngewissesDatum.of(2023, 1, 1);
        String otherObject = "2023-01-01";
        assertNotEquals(date1, otherObject);
    }

    @Test
    public void testHashCodeWithEqualObjects() {
        UngewissesDatum date1 = UngewissesDatum.of(2023, 1, 1);
        UngewissesDatum date2 = UngewissesDatum.of(2023, 1, 1);

        assertEquals(date1.hashCode(), date2.hashCode());
    }

    @Test
    public void testHashCodeWithDifferentObjects() {
        UngewissesDatum date1 = UngewissesDatum.of(2023, 1, 1);
        UngewissesDatum date2 = UngewissesDatum.of(2023, 1, 2);

        assertNotEquals(date1.hashCode(), date2.hashCode());
    }

    @ParameterizedTest
    @CsvSource({
        "15.08.2016, 2016-08-15",
        "xx.08.2016, 2016-08-xx",
        "xx.xx.2016, 2016-xx-xx",
        "xx.xx.xxxx, xxxx-xx-xx"})
    public void test_IFS_1168(String din5008Input, String iso8601Input) {
        UngewissesDatum parsedDin5008 = UngewissesDatum.parse(din5008Input);
        UngewissesDatum parsedIso8601 = UngewissesDatum.parse(iso8601Input);

        assertThat(parsedDin5008).isEqualTo(parsedIso8601);
    }
}
