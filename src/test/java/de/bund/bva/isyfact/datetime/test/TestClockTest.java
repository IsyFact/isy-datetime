package de.bund.bva.isyfact.datetime.test;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

public class TestClockTest {

    private static final ZoneId UTC = ZoneId.of("UTC");

    private static final ZoneId BERLIN = ZoneId.of("Europe/Berlin");

    private static final int JAHR = 2017;

    private static final int MONAT = 8;

    private static final int TAG = 20;

    private static final int STUNDE = 15;

    private static final int MINUTE = 23;

    private static final int SEKUNDE = 45;

    private static final int NANOSEK = 1234;

    private static final LocalDateTime localDateTime =
        LocalDateTime.of(JAHR, MONAT, TAG, STUNDE, MINUTE, SEKUNDE, NANOSEK);

    private static final Instant instant = ZonedDateTime.of(localDateTime, UTC).toInstant();

    private static final ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, UTC);

    @Test
    public void atInstant() {
        TestClock clock = TestClock.at(instant, UTC);

        assertEquals(instant, clock.instant());
        assertEquals(UTC, clock.getZone());
    }

    @Test
    public void atLocalDateTime() {
        TestClock testClock = TestClock.at(localDateTime);

        assertEquals(ZonedDateTime.of(localDateTime, ZoneId.systemDefault()).toInstant(),
            testClock.instant());
        assertEquals(ZoneId.systemDefault(), testClock.getZone());
    }

    @Test
    public void atLocalDateTimeWithZone() {
        TestClock clock = TestClock.at(localDateTime, BERLIN);

        assertEquals(ZonedDateTime.of(localDateTime, BERLIN).toInstant(), clock.instant());
        assertEquals(BERLIN, clock.getZone());
    }

    @Test
    public void atOffsetDateTime() {
        TestClock clock = TestClock.at(zonedDateTime.toOffsetDateTime());

        assertEquals(zonedDateTime.toInstant(), clock.instant());
        assertEquals(ZoneId.of("Z"), clock.getZone());
    }

    @Test
    public void atZonedDateTime() {
        TestClock clock = TestClock.at(zonedDateTime);

        assertEquals(zonedDateTime.toInstant(), clock.instant());
        assertEquals(UTC, clock.getZone());
    }

    @Test
    public void nowWithZone() {
        Instant nowNanosNull = ZonedDateTime.ofInstant(Instant.now(), BERLIN).withNano(0).toInstant();
        TestClock clock = TestClock.now(BERLIN);

        assertEquals(nowNanosNull, ZonedDateTime.ofInstant(clock.instant(), BERLIN).withNano(0).toInstant());
        assertEquals(BERLIN, clock.getZone());
    }

    @Test
    public void now() {
        Instant nowNanosNull =
            ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).withNano(0).toInstant();
        TestClock clock = TestClock.now();

        assertEquals(nowNanosNull,
            ZonedDateTime.ofInstant(clock.instant(), ZoneId.systemDefault()).withNano(0).toInstant());
        assertEquals(ZoneId.systemDefault(), clock.getZone());
    }

    @Test
    public void advanceByDuration() {
        Duration duration = Duration.ofSeconds(50);
        TestClock clock = TestClock.at(zonedDateTime);

        clock.advanceBy(duration);

        assertEquals(zonedDateTime.plus(duration).toInstant(), clock.instant());
    }

    @Test
    public void advanceByPeriod() {
        Period period = Period.ofDays(5);
        TestClock clock = TestClock.at(zonedDateTime);

        clock.advanceBy(period);

        assertEquals(zonedDateTime.plus(period).toInstant(), clock.instant());
    }

    @Test
    public void toStringTest() {
        TestClock clock = TestClock.at(zonedDateTime);

        assertEquals("TestClock: " + zonedDateTime.toString(), clock.toString());
    }

    @Test
    public void withZone() {
        TestClock clock = TestClock.at(instant, UTC);
        TestClock clockBerlin = (TestClock) clock.withZone(BERLIN);

        assertEquals(BERLIN, clockBerlin.getZone());
        assertEquals(instant, clockBerlin.instant());
    }
}