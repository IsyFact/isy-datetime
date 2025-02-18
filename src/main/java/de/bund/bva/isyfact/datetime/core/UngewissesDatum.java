package de.bund.bva.isyfact.datetime.core;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;
import java.util.Optional;

/**
 * Representation of an uncertain date. A date is uncertain, if parts of the date are unknown.
 * <p>
 * The class is meant for use in the application core.
 */
public class UngewissesDatum {

    private static final DateTimeFormatter format =
        new DateTimeFormatterBuilder()
            .appendPattern("[00.00.0000][00.00.yyyy][00.MM.yyyy]['xx.xx.xxxx']['xx.xx.'yyyy]['xx.'MM.yyyy][dd.MM.yyyy]")
            .appendPattern("[yyyy-MM-dd][yyyy-MM'-xx'][yyyy'-xx-xx']['xxxx-xx-xx']")
            .parseStrict()
            .toFormatter();

    private static final int minMonth = (int) ChronoField.MONTH_OF_YEAR.range().getMinimum();

    private static final int minDayOfMonth = (int) ChronoField.DAY_OF_MONTH.range().getMinimum();

    private LocalDate anfang;

    private LocalDate ende;

    private UngewissesDatum() {
    }

    private UngewissesDatum(int jahr) {
        this.anfang = LocalDate.of(jahr, minMonth, minDayOfMonth);
        this.ende = anfang.plusYears(1).minusDays(1);
    }

    private UngewissesDatum(int jahr, int monat) {
        this.anfang = LocalDate.of(jahr, monat, minDayOfMonth);
        this.ende = anfang.plusMonths(1).minusDays(1);
    }

    private UngewissesDatum(int jahr, int monat, int tag) {
        this.anfang = LocalDate.of(jahr, monat, tag);
        this.ende = LocalDate.of(jahr, monat, tag);
    }

    private UngewissesDatum(LocalDate anfang, LocalDate ende) {
        this.anfang = anfang;
        this.ende = ende;
    }

    /**
     * Creates a {@link UngewissesDatum} with empty values.
     *
     * @return a {@link UngewissesDatum} without year, month or day values
     */
    public static UngewissesDatum leer() {
        return new UngewissesDatum();
    }

    /**
     * Returns true, if all values in this {@link UngewissesDatum} are unknown.
     *
     * @return true if all values are unknown
     */
    public boolean isLeer() {
        return anfang == null && ende == null;
    }

    /**
     * Returns true if at least one value in this {@link UngewissesDatum} is unknown.
     *
     * @return true if at least one value is unknown
     */
    public boolean isUngewiss() {
        return anfang == null || !anfang.isEqual(ende);
    }

    /**
     * Creates a {@link UngewissesDatum}, where only the year is known.
     *
     * @param jahr
     *     the year
     * @return a {@link UngewissesDatum} with a set year
     * @throws DateTimeException
     *     if the year value is invalid
     */
    public static UngewissesDatum of(int jahr) {
        return new UngewissesDatum(jahr);
    }

    /**
     * Creates a {@link UngewissesDatum}, where only the year and month are known.
     *
     * @param jahr
     *     the year
     * @param monat
     *     the month
     * @return a {@link UngewissesDatum} with a set year and month
     * @throws DateTimeException
     *     if the year or month value is invalid
     */
    public static UngewissesDatum of(int jahr, int monat) {
        return new UngewissesDatum(jahr, monat);
    }

    /**
     * Creates a {@link UngewissesDatum}, where the year, month and day are known.
     * With these values, the date is not uncertain anymore and can be converted into a {@link LocalDate}
     * using {@link UngewissesDatum#toLocalDate()}.
     *
     * @param jahr
     *     the year
     * @param monat
     *     the month
     * @param tag
     *     the day
     * @return a {@link UngewissesDatum} with a set year, month and day
     * @throws DateTimeException
     *     if the year, month or day value is invalid
     */
    public static UngewissesDatum of(int jahr, int monat, int tag) {
        return new UngewissesDatum(jahr, monat, tag);
    }

    /**
     * Creates a {@link UngewissesDatum}, where the start and end of a date span are passed over.
     *
     * @param vonInklusive
     *     the start of the date span, inclusive and not null
     * @param bisInklusive
     *     the end of the date span, inclusive and not null
     * @return a {@link UngewissesDatum} with the set timespan
     * @throws DateTimeException
     *     if the start date occurs after the end date
     */
    public static UngewissesDatum of(LocalDate vonInklusive, LocalDate bisInklusive) {
        Objects.requireNonNull(vonInklusive);
        Objects.requireNonNull(bisInklusive);

        if (bisInklusive.isBefore(vonInklusive)) {
            throw new DateTimeException("Der Anfang " + vonInklusive + " liegt nach dem Ende " + bisInklusive + ".");
        } else if (vonInklusive.getYear() != bisInklusive.getYear()) {
            throw new DateTimeException("Der Anfang " + vonInklusive + " und das Ende " + bisInklusive + " müssen innerhalb des gleiche Jahres sein.");
        }

        return new UngewissesDatum(vonInklusive, bisInklusive);
    }

    /**
     * Parses an uncertain date.
     * <p>
     * The following formats are supported:
     * <p>
     * <table summary="Supported formats" border="1">
     * <tr><th>Case</th><th>Input with 0</th><th>Input with x</th><th>Internal timespan</th></tr>
     * <tr><td>Day unknown</td><td>00.05.1966</td><td>xx.05.1966</td><td>1.5.1966 – 31.5.1966</td></tr>
     * <tr><td>Day and month unknown</td><td>00.00.1966</td><td>xx.xx.1966</td><td>1.1.1966 – 31.12.1966</td></tr>
     * <tr><td>Date completely unknown</td><td>00.00.0000</td><td>xx.xx.xxxx</td><td>not set (null)</td></tr>
     * </table>
     *
     * @param text
     *     the text to parse
     * @return the parsed {@link UngewissesDatum}
     * @throws DateTimeParseException
     *     if the text cannot be parsed
     */
    public static UngewissesDatum parse(String text) {
        Objects.requireNonNull(text);

        if (text.isEmpty()) {
            throw new DateTimeParseException("Der String war leer.", text, 0);
        }

        TemporalAccessor ta = format.parse(text);

        if (ta.isSupported(ChronoField.DAY_OF_MONTH)) {
            return UngewissesDatum.of(ta.get(ChronoField.YEAR), ta.get(ChronoField.MONTH_OF_YEAR),
                ta.get(ChronoField.DAY_OF_MONTH));
        } else if (ta.isSupported(ChronoField.MONTH_OF_YEAR)) {
            return UngewissesDatum.of(ta.get(ChronoField.YEAR), ta.get(ChronoField.MONTH_OF_YEAR));
        } else if (ta.isSupported(ChronoField.YEAR)) {
            return UngewissesDatum.of(ta.get(ChronoField.YEAR));
        } else {
            return UngewissesDatum.leer();
        }
    }

    /**
     * Returns an {@link java.util.Optional}, which contains the year of this date.
     *
     * @return an {@link java.util.Optional} with a year, if it is set, otherwise an empty {@link java.util.Optional}
     */
    public Optional<Integer> getJahr() {
        if (anfang != null || nurJahrBekannt()) {
            return Optional.of(anfang.getYear());
        } else {
            return Optional.empty();
        }
    }

    /**
     * Returns an {@link java.util.Optional}, which contains the month of this date.
     * If start and end of this date are not in the same month, an empty {@link java.util.Optional} is returned.
     *
     * @return an {@link java.util.Optional} with the month, if it is set and certain, otherwise and empty {@link java.util.Optional}
     */
    public Optional<Integer> getMonat() {
        if ((anfang != null && anfang.getMonth() == ende.getMonth()) || nurMonatUndJahrBekannt()) {
            return Optional.of(anfang.getMonthValue());
        } else {
            return Optional.empty();
        }
    }

    /**
     * Returns an {@link java.util.Optional}, which contains the day of this date.
     * If start and end of this date are not on the same day, an empty
     * {@link java.util.Optional} is returned.
     *
     * @return an {@link java.util.Optional} with the day, if it is set and certain, otherwise and empty {@link java.util.Optional}
     */
    public Optional<Integer> getTag() {
        if (isUngewiss()) {
            return Optional.empty();
        } else {
            return Optional.of(anfang.getDayOfMonth());
        }
    }

    /**
     * Returns an {@link java.util.Optional}, which contains this date as a {@link LocalDate}.
     *
     * @return an {@link java.util.Optional} with the {@link LocalDate}, if all values are certain, otherwise an empty {@link java.util.Optional}
     */
    public Optional<LocalDate> toLocalDate() {
        if (isUngewiss()) {
            return Optional.empty();
        } else {
            return Optional.of(LocalDate.of(anfang.getYear(), anfang.getMonth(), anfang.getDayOfMonth()));
        }
    }

    /**
     * Returns the {@link LocalDate}, which represents the start (inclusive) of the timespan depicted by this {@link UngewissesDatum}.
     *
     * @return the start (inclusive) of the timespan as a {@link LocalDate}
     */
    public LocalDate getAnfang() {
        return anfang;
    }

    /**
     * Returns the {@link LocalDate}, which represents the end (inclusive) of the timespan depicted by this {@link UngewissesDatum}.
     *
     * @return the end (inclusive) of the timespan as a {@link LocalDate}
     */
    public LocalDate getEnde() {
        return ende;
    }

    /**
     * Returns this {@link UngewissesDatum} as a string in the format {@code dd.MM.uuuu}.
     * Uncertain values are represented with {@code xx}, like {@code xx.08.2017}. If the date cannot be displayed
     * like this, it is represented as a timespan, for like {@code 10.08.2017 - 31.08.2017}.
     *
     * @return Representation of this {@link UngewissesDatum} as a {@link String}
     */
    @Override
    public String toString() {
        String toString;

        DateTimeFormatter ddMMuuuu = DateTimeFormatter.ofPattern("dd.MM.uuuu");

        if (isLeer()) {
            toString = "xx.xx.xxxx";
        } else if (anfang.equals(ende)) {
            toString = anfang.format(ddMMuuuu);
        } else if (nurJahrBekannt()) {
            toString = String.format("xx.xx.%04d", anfang.getYear());
        } else if (nurMonatUndJahrBekannt()) {
            toString = String.format("xx.%02d.%04d", anfang.getMonthValue(), anfang.getYear());
        } else {
            toString = String.format("%s - %s", anfang.format(ddMMuuuu), ende.format(ddMMuuuu));
        }

        return toString;
    }

    /**
     * Returns this {@link UngewissesDatum} as a string in the format {@code uuuu-MM-dd}.
     * Uncertain values are represented with {@code xx}, like {@code 2017-08-xx}. If the date cannot be displayed
     * like this, it is represented as a timespan, for like {@code 2017-08-10 - 2017-08-31}.
     *
     *  @return Representation of this {@link UngewissesDatum} as a {@link String} in ISO-8601 format
     */
    public String toIsoString() {
        if (isLeer()) {
            return "xxxx-xx-xx";
        } else if (anfang.equals(ende)) {
            return anfang.format(DateTimeFormatter.ISO_LOCAL_DATE);
        } else if (nurJahrBekannt()) {
            return String.format("%04d-xx-xx", anfang.getYear());
        } else if (nurMonatUndJahrBekannt()) {
            return String.format("%04d-%02d-xx", anfang.getYear(), anfang.getMonthValue());
        } else {
            return String.format("%s - %s", anfang.format(DateTimeFormatter.ISO_LOCAL_DATE), ende.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
    }

    private boolean nurJahrBekannt() {
        return anfang != null && anfang.getDayOfMonth() == 1 && anfang.getMonthValue() == 1 && anfang
            .plusYears(1).minusDays(1).isEqual(ende);
    }

    private boolean nurMonatUndJahrBekannt() {
        return anfang != null && anfang.getDayOfMonth() == 1 && anfang.plusMonths(1).minusDays(1)
            .isEqual(ende);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UngewissesDatum)) {
            return false;
        }
        UngewissesDatum that = (UngewissesDatum) o;
        return Objects.equals(anfang, that.anfang) && Objects.equals(ende, that.ende);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anfang, ende);
    }
}