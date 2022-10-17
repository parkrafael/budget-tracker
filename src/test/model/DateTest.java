package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTest {

    Date d;

    @BeforeEach
    public void setup() {
        d = new Date(3,12, 2022);
    }

    @Test
    public void getterTests() {
        assertEquals(d.getYear(), 2022);
        assertEquals(d.getMonth(), 12);
        assertEquals(d.getDay(), 3);
    }

}
