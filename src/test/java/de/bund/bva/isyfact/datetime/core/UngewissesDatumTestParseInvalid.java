package de.bund.bva.isyfact.datetime.core;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class UngewissesDatumTestParseInvalid {

    @Parameterized.Parameters(name = "{index}: parse({0})")
    public static Collection<Object> data() {
        return Arrays.asList(new Object[] {
            "", "xxx", "01.2017", "10.aa.2017", "10.-5.2017", "10.00.2017", "10.00.0000", "2000.01.01",
            "00.10.0000", "10.xx.2017", "10.xx.xxxx", "xx.10.xxxx", "10.08.xxxx", " 10.08.2017",
            "2017-01", "2017-aa-10", "2017--5-10", "2017-00-10", "01-01-2000",
            "2017-xx-10", "xxxx-xx-10", "xxxx-10-xx", "xxxx-08-10", " 2017-08-10", "2017-01-00",
        });
    }

    @Parameterized.Parameter
    public String input;

    @Test
    public void parse() {
        assertThatThrownBy(() -> UngewissesDatum.parse(input)).isInstanceOf(DateTimeParseException.class);
    }
}