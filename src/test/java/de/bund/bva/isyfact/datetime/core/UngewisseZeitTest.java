package de.bund.bva.isyfact.datetime.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;


public class UngewisseZeitTest {
    private UngewisseZeit time1 = UngewisseZeit.of(14, 30, 45);
    private UngewisseZeit time2 = UngewisseZeit.of(14, 30, 45);
    @Test
    public void leer() {
        UngewisseZeit leer = UngewisseZeit.leer();

        assertTrue(leer.isLeer());
    }

    @Test
    public void ofStunde() {
        UngewisseZeit nurStunde = UngewisseZeit.of(17);

        assertFalse(nurStunde.isLeer());
        assertEquals(LocalTime.of(17, 0, 0), nurStunde.getAnfang());
        assertEquals(LocalTime.of(17, 59, 59), nurStunde.getEnde());
    }

    @Test
    public void ofStundeNegativ() {
        assertThrows(DateTimeException.class, () ->
            UngewisseZeit.of(-1));
    }

    @Test
    public void ofStundeZuGross() {
        assertThrows(DateTimeException.class, () ->
            UngewisseZeit.of(24));
    }

    @Test
    public void ofStundeMinute() {
        UngewisseZeit stundeMinute = UngewisseZeit.of(17, 30);

        assertFalse(stundeMinute.isLeer());
        assertEquals(LocalTime.of(17, 30, 0), stundeMinute.getAnfang());
        assertEquals(LocalTime.of(17, 30, 59), stundeMinute.getEnde());
    }

    @Test
    public void ofStundeMinuteSekunde() {
        UngewisseZeit stundeMinuteSekunde = UngewisseZeit.of(17, 30, 30);

        assertFalse(stundeMinuteSekunde.isLeer());
        assertEquals(LocalTime.of(17, 30, 30), stundeMinuteSekunde.getAnfang());
        assertEquals(LocalTime.of(17, 30, 30), stundeMinuteSekunde.getEnde());
    }

    @Test
    public void ofLocalTime() {
        UngewisseZeit ofLocalTime = UngewisseZeit.of(LocalTime.of(17, 30, 30), LocalTime.of(17, 45, 45));

        assertFalse(ofLocalTime.isLeer());
        assertEquals(LocalTime.of(17, 30, 30), ofLocalTime.getAnfang());
        assertEquals(LocalTime.of(17, 45, 45), ofLocalTime.getEnde());

        ofLocalTime = UngewisseZeit.of(LocalTime.of(17, 30, 0), LocalTime.of(17, 30, 0));

        assertFalse(ofLocalTime.isLeer());
        assertEquals(UngewisseZeit.of(17, 30, 0), ofLocalTime);
    }

    @Test
    public void ofLocalDateNullArguments() {
        assertThrows(NullPointerException.class, () ->
            UngewisseZeit.of(null, null));
    }

    @Test
    public void ofLocalDateAnfageNachEnde() {
        assertThrows(DateTimeException.class, () ->
            UngewisseZeit.of(LocalTime.of(17, 45), LocalTime.of(17, 30)));
    }

    @Test
    public void parseNullArgument() {
        assertThrows(NullPointerException.class, () ->
            UngewisseZeit.parse(null));
    }

    @Test
    public void getStunde() {
        UngewisseZeit zeit = UngewisseZeit.leer();
        assertEquals(Optional.empty(), zeit.getStunde());

        zeit = UngewisseZeit.of(17);
        assertEquals(Optional.of(17), zeit.getStunde());

        zeit = UngewisseZeit.of(LocalTime.of(15, 0), LocalTime.of(17, 0));
        assertEquals(Optional.empty(), zeit.getStunde());
    }

    @Test
    public void getMinute() {
        UngewisseZeit zeit = UngewisseZeit.leer();
        assertEquals(Optional.empty(), zeit.getMinute());

        zeit = UngewisseZeit.of(17, 30);
        assertEquals(Optional.of(30), zeit.getMinute());

        zeit = UngewisseZeit.of(LocalTime.of(15, 30), LocalTime.of(17, 31));
        assertEquals(Optional.empty(), zeit.getMinute());
    }

    @Test
    public void getSekunde() {
        UngewisseZeit zeit = UngewisseZeit.leer();
        assertEquals(Optional.empty(), zeit.getSekunde());

        zeit = UngewisseZeit.of(17, 30, 45);
        assertEquals(Optional.of(45), zeit.getSekunde());

        zeit = UngewisseZeit.of(LocalTime.of(15, 0, 20), LocalTime.of(17, 0, 30));
        assertEquals(Optional.empty(), zeit.getSekunde());
    }

    @Test
    public void toLocalTime() {
        UngewisseZeit zeit = UngewisseZeit.leer();
        assertEquals(Optional.empty(), zeit.toLocalTime());

        zeit = UngewisseZeit.of(17, 30, 45);
        assertEquals(Optional.of(LocalTime.of(17, 30, 45)), zeit.toLocalTime());

        zeit = UngewisseZeit.of(LocalTime.of(15, 0), LocalTime.of(17, 0));
        assertEquals(Optional.empty(), zeit.toLocalTime());
    }
    
    @Test
    public void equalsWithEqualObjects() {
        assertEquals(time1, time2);
        assertEquals(time2, time1);
        assertEquals(time1.hashCode(), time2.hashCode());
    }

    @Test
    public void equalsWithDifferentObjects() {
        UngewisseZeit differentTime = UngewisseZeit.of(12, 30, 0);

        assertNotEquals(time1, differentTime);
    }

    @Test
    public void equalsWithNull() {
        assertNotEquals(null, time1);
    }

    @Test
    public void equalsWithDifferentTypes() {
        String otherObject = "14:30:45";
        assertNotEquals(time1, otherObject);
    }

    @Test
    public void testHashCodeWithEqualObjects() {
        assertEquals(time1.hashCode(), time2.hashCode());
    }

    @Test
    public void testHashCodeWithDifferentObjects() {
        UngewisseZeit differentTime = UngewisseZeit.of(12, 30, 0);

        assertNotEquals(time1.hashCode(), differentTime.hashCode());
    }
}
