package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class GeldbetragTest
{
    Geldbetrag _einEuroZwanzigCent = new Geldbetrag(1, 20);
    Geldbetrag _nullEuroNullCent = new Geldbetrag(0, 0);
    Geldbetrag _vierEuroEinCent = new Geldbetrag(4, 1);
    Geldbetrag _maxEuroMaxCent = new Geldbetrag(Integer.MAX_VALUE, 99);
    Map<String, Geldbetrag> werteMenge = new HashMap<String, Geldbetrag>();

    @Test
    public void testIstGueltigerString()
    {

        assertTrue(Geldbetrag.istGueltigerString("1,20 Euro"));
        assertFalse(Geldbetrag.istGueltigerString("500a"));
        assertTrue(Geldbetrag.istGueltigerString("01,20 Euro"));

    }

    @Test
    public void testIstGueltigerEuroAnteil()
    {
        assertEquals(false, Geldbetrag.istGueltigerEuroAnteil(-1));
        assertTrue(Geldbetrag.istGueltigerEuroAnteil(0));
        assertTrue(Geldbetrag.istGueltigerEuroAnteil(1));
        assertEquals(false,
                Geldbetrag.istGueltigerEuroAnteil(Integer.MAX_VALUE + 1));
    }

    @Test
    public void testIstGueltigerCentAnteil()
    {
        assertEquals(false, Geldbetrag.istGueltigerCentAnteil(-1));
        assertTrue(Geldbetrag.istGueltigerCentAnteil(0));
        assertTrue(Geldbetrag.istGueltigerCentAnteil(1));
        assertEquals(false, Geldbetrag.istGueltigerCentAnteil(100));
    }

    @Test
    public void testSelectGeldbetrag()
    {
        //int, int
        assertEquals(false, werteMenge.containsKey("1,20 Euro"));
        Geldbetrag.selectGeldbetrag(1, 20);
        assertTrue(werteMenge.containsKey("1,20 Euro"));

        //int
        assertEquals(false, werteMenge.containsKey("2,20 Euro"));
        Geldbetrag.selectGeldbetrag(220);
        assertTrue(werteMenge.containsKey("1,20 Euro"));

        //String
        assertEquals(false, werteMenge.containsKey("3,20 Euro"));
        Geldbetrag.selectGeldbetrag("3,20 Euro");
        assertTrue(werteMenge.containsKey("1,20 Euro"));
    }

    @Test
    public void testIstGroesserGleich()
    {
        assertTrue(_einEuroZwanzigCent.istGroesserGleich(_nullEuroNullCent));
        assertTrue(_einEuroZwanzigCent.istGroesserGleich(_einEuroZwanzigCent));
        assertTrue(_nullEuroNullCent.istGroesserGleich(_einEuroZwanzigCent));
    }

    @Test
    public void testSummiere()
    {
        assertEquals(new Geldbetrag(5, 21),
                _einEuroZwanzigCent.summiere(_vierEuroEinCent));
        assertEquals(new Geldbetrag(0, 0),
                _nullEuroNullCent.summiere(_nullEuroNullCent));
        assertEquals(new Geldbetrag(1, 20),
                _nullEuroNullCent.summiere(_einEuroZwanzigCent));
    }

    @Test
    public void testSubtrahiere()
    {
        assertEquals(new Geldbetrag(5, 21),
                _einEuroZwanzigCent.subtrahiere(_vierEuroEinCent));
        assertEquals(new Geldbetrag(0, 0),
                _nullEuroNullCent.subtrahiere(_nullEuroNullCent));
        assertEquals(new Geldbetrag(1, 20),
                _nullEuroNullCent.subtrahiere(_einEuroZwanzigCent));
    }

    @Test
    public void testMultipliziere()
    {
        assertEquals(new Geldbetrag(2, 40),
                _einEuroZwanzigCent.multiplizere(2));
        assertEquals(new Geldbetrag(0, 0), _nullEuroNullCent.multiplizere(2));
    }

    @Test
    public void testToString()
    {
        assertEquals("1,20 Euro", _einEuroZwanzigCent.toString());
        assertEquals("0,00 Euro", _nullEuroNullCent.toString());
    }

    @Test
    public void testFormatiertenString()
    {
        assertEquals("1,20 Euro", _einEuroZwanzigCent.toString());
        assertEquals("0,00 Euro", _nullEuroNullCent.toString());
    }

}