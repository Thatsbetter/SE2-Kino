package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.Map;

public class Geldbetrag
{
    private final int _euroAnteil;
    private final int _centAnteil;
    private static final int MAXCENTANTEIL = 99; //vorher 100
    private static Map<String, Geldbetrag> werteMenge;

    public Geldbetrag(int euro, int cent)
    {
        assert cent < MAXCENTANTEIL;

        _euroAnteil = euro;
        _centAnteil = cent;
    }

    /**
	*addiert zwei Geldbetraege
	*
	*@param summand ein zu addierender Geldbetrag
	*
	*@return Geldbetrag die Summe der zwei Geldbetraege
	*/
	public Geldbetrag addiere(Geldbetrag summand)
	{
	    return summand;
	}

	public Geldbetrag subtrahiere(Geldbetrag subtrahend)
	{
	
	    return subtrahend;
	}

	public Geldbetrag multiplizere(int faktor)
	{
	    return new Geldbetrag(0, 0);
	}

	/*
	*
	
	* checkt ob es groesser oder kleiner ist
	
	*@return
	*/
	public boolean istGroesserGleich(Geldbetrag g)
	{
	    return true;
	}

	public boolean istAddierenMoeglich(Geldbetrag summand)
	{
		return true;
	}

	public boolean istSubtrahierenMoeglich(int faktor)
	{
		return true;
	}

	public boolean istMultiplizierenMoeglich(int faktor)
	{
		return true;
	}
	
	public String getFormatiertenString()
	{
	    return "";
	}

	@Override
	public String toString()
	{
	    return getFormatiertenString();
	}

	public static boolean istGueltigerString(String s)
    {
        return true;
    }

    public static boolean istGueltigerEuroAnteil(int euro)
    {
        return true;
    }

    public static boolean istGueltigerCentAnteil(int cent)
    {
        return true;
    }

    public static Geldbetrag selectGeldbetrag(int euroAnteil, int centAnteil)
    {
        return null;
    }

    public static Geldbetrag selectGeldbetrag(int euro)
    {
        return null;
    }

    public static Geldbetrag selectGeldbetrag(String geldBetrag)
    {
        return null;
    }
        
}
