package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class GeldbetragTest
{
    Geldbetrag _einEuroZwanzigCent = Geldbetrag.selectGeldbetrag(1, 20);
    Geldbetrag _nullEuroNullCent = Geldbetrag.selectGeldbetrag(0, 0);
    Geldbetrag _vierEuroEinCent = Geldbetrag.selectGeldbetrag(4, 1);
    Geldbetrag _maxEuroMaxCent = Geldbetrag.selectGeldbetrag(Integer.MAX_VALUE/100 - 99, 99);
    Map<String, Geldbetrag> werteMenge = new HashMap<String, Geldbetrag>();

    @Test
	public void testAddiere()
	{
	    assertEquals(Geldbetrag.selectGeldbetrag(5, 21),
	            _einEuroZwanzigCent.addiere(_vierEuroEinCent));
	    assertEquals(Geldbetrag.selectGeldbetrag(0, 0),
	            _nullEuroNullCent.addiere(_nullEuroNullCent));
	    assertEquals(Geldbetrag.selectGeldbetrag(1, 20),
	            _nullEuroNullCent.addiere(_einEuroZwanzigCent));
	}

    @Test
    public void testKonstruktor()
    {
    	
    }
    
	@Test
	public void testSubtrahiere()
	{
	    assertEquals(Geldbetrag.selectGeldbetrag(5, 21),
	            _einEuroZwanzigCent.subtrahiere(_vierEuroEinCent));
	    assertEquals(Geldbetrag.selectGeldbetrag(0, 0),
	            _nullEuroNullCent.subtrahiere(_nullEuroNullCent));
	    assertEquals(Geldbetrag.selectGeldbetrag(1, 20),
	            _nullEuroNullCent.subtrahiere(_einEuroZwanzigCent));
	}

	@Test
	public void testMultipliziere()
	{
	    assertEquals(Geldbetrag.selectGeldbetrag(2, 40),
	            _einEuroZwanzigCent.multipliziere(2));
	    assertEquals(Geldbetrag.selectGeldbetrag(0, 0), _nullEuroNullCent.multipliziere(2));
	}

	@Test
	public void testIstGroesserGleich()
	{
	    assertTrue(_einEuroZwanzigCent.istGroesserGleich(_nullEuroNullCent));
	    assertTrue(_einEuroZwanzigCent.istGroesserGleich(_einEuroZwanzigCent));
	    assertTrue(_nullEuroNullCent.istGroesserGleich(_einEuroZwanzigCent));
	}

	@Test
	public void testIstAddierenMoeglich()
	{
		
	}
	
	@Test
	public void testIstSubtrahierenMoeglich()
	{
		
	}
	
	@Test
	public void testIstMultiplizierenMoeglich()
	{
		
	}
	
	@Test
	public void testGetFormatiertenString()
	{
	    assertEquals("1,20 Euro", _einEuroZwanzigCent.toString());
	    assertEquals("0,00 Euro", _nullEuroNullCent.toString());
	}

	@Test
	public void testToString()
	{
	    assertEquals("1,20 Euro", _einEuroZwanzigCent.toString());
	    assertEquals("0,00 Euro", _nullEuroNullCent.toString());
	}

	@Test
    public void testIstGueltigerString()
    {

        assertTrue(Geldbetrag.istGueltigerString("1,20"));
        assertFalse(Geldbetrag.istGueltigerString("500a"));
        assertTrue(Geldbetrag.istGueltigerString("00,04"));

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
        assertEquals(false, werteMenge.containsKey("1,20"));
        Geldbetrag.selectGeldbetrag(1, 20);
        assertTrue(werteMenge.containsKey("1,20"));

        //int
        assertEquals(false, werteMenge.containsKey("2,20"));
        Geldbetrag.selectGeldbetrag(220);
        assertTrue(werteMenge.containsKey("1,20"));

        //String
        assertEquals(false, werteMenge.containsKey("3,20"));
        Geldbetrag.selectGeldbetrag("3,20");
        assertTrue(werteMenge.containsKey("1,20"));
    }

}