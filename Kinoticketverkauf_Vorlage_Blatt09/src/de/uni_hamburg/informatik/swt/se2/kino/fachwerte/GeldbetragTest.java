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
    	//TODO
    }
    
	@Test
	public void testSubtrahiere()
	{
	    assertEquals(Geldbetrag.selectGeldbetrag(2, 81),
	    		_vierEuroEinCent.subtrahiere(_einEuroZwanzigCent));
	    assertEquals(Geldbetrag.selectGeldbetrag(0, 0),
	            _nullEuroNullCent.subtrahiere(_nullEuroNullCent));
	    assertEquals(Geldbetrag.selectGeldbetrag(1, 20),
	    		_einEuroZwanzigCent.subtrahiere(_nullEuroNullCent));
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
	    assertFalse(_nullEuroNullCent.istGroesserGleich(_einEuroZwanzigCent));
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
	    assertEquals("1,20", _einEuroZwanzigCent.toString());
	    assertEquals("0,00", _nullEuroNullCent.toString());
	}

	@Test
	public void testToString()
	{
	    assertEquals("1,20", _einEuroZwanzigCent.toString());
	    assertEquals("0,00", _nullEuroNullCent.toString());
	}

	@Test
    public void testIstGueltigerString()
    {

        assertTrue(Geldbetrag.istGueltigerString("1,20"));
        assertFalse(Geldbetrag.istGueltigerString("500a"));
        assertTrue(Geldbetrag.istGueltigerString("0,04"));

    }

    @Test
    public void testIstGueltigerEuroAnteil()
    {
        assertFalse(Geldbetrag.istGueltigerEuroAnteil(-1));
        assertTrue(Geldbetrag.istGueltigerEuroAnteil(0));
        assertTrue(Geldbetrag.istGueltigerEuroAnteil(1));
        assertFalse(Geldbetrag.istGueltigerEuroAnteil(Integer.MAX_VALUE + 1));
    }

    @Test
    public void testIstGueltigerCentAnteil()
    {
        assertFalse(Geldbetrag.istGueltigerCentAnteil(-1));
        assertTrue(Geldbetrag.istGueltigerCentAnteil(0));
        assertTrue(Geldbetrag.istGueltigerCentAnteil(1));
        assertFalse(Geldbetrag.istGueltigerCentAnteil(100));
    }

    @Test
    public void testSelectGeldbetrag()
    {
        //int, int
        assertFalse(Geldbetrag.werteMenge.containsKey("1,23"));
        Geldbetrag.selectGeldbetrag(1, 23);
        assertTrue(Geldbetrag.werteMenge.containsKey("1,23"));

        //int
        assertFalse(Geldbetrag.werteMenge.containsKey("2,22"));
        Geldbetrag.selectGeldbetrag(222);
        assertTrue(Geldbetrag.werteMenge.containsKey("2,22"));

        //String
        assertFalse(Geldbetrag.werteMenge.containsKey("10,10"));
        Geldbetrag.selectGeldbetrag("10,1");
        assertTrue(Geldbetrag.werteMenge.containsKey("10,10"));
        
        //String
        assertFalse(Geldbetrag.werteMenge.containsKey("20,00"));
        Geldbetrag.selectGeldbetrag("20,");
        assertTrue(Geldbetrag.werteMenge.containsKey("20,00"));
        
        //String
        assertFalse(Geldbetrag.werteMenge.containsKey("30,00"));
        Geldbetrag.selectGeldbetrag("30");
        assertTrue(Geldbetrag.werteMenge.containsKey("30,00"));
    }

}