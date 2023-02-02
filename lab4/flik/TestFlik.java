package flik;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestFlik {

    @Test
    public void testsmallnums() {


        Integer a = 12;
        Integer b = 12;

        assertTrue(Flik.isSameNumber(a, b));
    }


    @Test
    public void testnums() {


        Integer a = 128;
        Integer b = 128;

        assertTrue(Flik.isSameNumber(a, b));
    }

}
