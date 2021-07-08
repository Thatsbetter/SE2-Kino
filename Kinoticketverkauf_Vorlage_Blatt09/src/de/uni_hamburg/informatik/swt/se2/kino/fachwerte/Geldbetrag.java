package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.HashMap;
import java.util.Map;

public class Geldbetrag
{
    private final int _euroAnteil;
    private final int _centAnteil;
    private static final int MAXCENTANTEIL = 99;
    private static Map<String, Geldbetrag> werteMenge = new HashMap<String, Geldbetrag>();
    
    /**
	*erzeugt einen neuen Geldbetrag 
	*
	*@param euro
	*@param cent
	*
	*@require eurocent muss großer gleich 0 sein
	*
	*/
    private Geldbetrag(int euro, int cent)
    {   
    	assert istGueltigerEuroAnteil(euro) : "Vorbedingung verletzt: istGueltigerEuroAnteil(euro)!";
    	assert istGueltigerCentAnteil(cent) : "Vorbedingung verletzt: istGueltigerCentAnteil(cent)!";

        _euroAnteil = euro;
        _centAnteil = cent;
    }
    
    /**
	*erzeugt einen neuen Geldbetrag 
	*
	*@param eurocent 
	*
	*@require eurocent muss großer gleich 0 sein
	*
	*/
	private Geldbetrag(int eurocent)
    {       
        assert eurocent >= 0:"EuroCent muss großer gleich 0 sein";
        _euroAnteil = eurocent / 100;
        _centAnteil = eurocent % 100;
    }
    
    /**
	*addiert zwei Geldbetraege
	*
	*@param summand ein zu addierender Geldbetrag
	*
	*@require istAddierenMoeglich ist true
	*
	*@return Geldbetrag die Summe der zwei Geldbetraege
	*/
	
	public Geldbetrag addiere(Geldbetrag summand)
	{
        assert istAddierenMoeglich(summand);
       	int euroCentResultat = getEurocent() + summand.getEurocent();
        return selectGeldbetrag(euroCentResultat);
        
	}

	/**
	*subtrahiert ein Geldbetrag von einem anderen
	*
	*@param subtrahend der Geldbetrag, der abgezogen wird
	*
	*@return Geldbetrag die Differenz der zwei Geldbetraege
	*/
	public Geldbetrag subtrahiere(Geldbetrag subtrahend)
	{
		
		assert istSubtrahierenMoeglich(subtrahend);
        int eurocent = getEurocent() - subtrahend.getEurocent();
        return selectGeldbetrag(eurocent);
	}


	/**
	*multipliziert unseren Betrag mit einem Faktor
	*
	*@param faktor ein integer um den man den Betrag vervielfachen will
	*
	*@return Geldbetrag
	*/
	public Geldbetrag multipliziere(int faktor)
	{
        assert istMultiplizierenMoeglich(faktor);
        int eurocent = getEurocent() * faktor;
        return selectGeldbetrag(eurocent);
	}

	
	/**
	*überprüft ob ein Geldbetrag großer gleich als anderer Geldbetrag ist
	*
	*@param geldbetrag ein integer um den man den Betrag vervielfachen will
	*@require
	*
	*@return boolean 
	*/
	public boolean istGroesserGleich(Geldbetrag geldbetrag)
	{
	    return this.getEurocent() >= geldbetrag.getEurocent();
	}
	
	/**
	*überprüft ob ein Geldbetrag mit einem anderen Geldbetrag addiert werden kann
	*
	*@param faktor ein integer um den man den Betrag vervielfachen will
	*@require
	*
	*@return Geldbetrag
	*/
	public boolean istAddierenMoeglich(Geldbetrag summand)
	{
		int differenz = Integer.MAX_VALUE  - summand.getEurocent();
		return differenz >= getEurocent();
	}
	
	/**
	*überprüft ob ein Geldbetrag von einem Geldbetrag von einem anderen Geldbetrag subtrahiert werden kann
	*
	*@param subtrahend ein integer um den man den Betrag verringern will
	*
	*
	*@return boolean
	*/
	public boolean istSubtrahierenMoeglich(Geldbetrag subtrahend)
	{
		return this.istGroesserGleich(subtrahend);
	}
	
	/**
	*überprüft ob ein Geldbetrag mit einem Faktor multipliziert werden kann
	*
	*@param faktor ein integer um den man den Betrag vervielfachen will
	*@require
	*
	*@return boolean
	*/
	public boolean istMultiplizierenMoeglich(float faktor)
	{
        assert faktor >= 1 : "Vorbedingung verletzt: faktor >= 1!";
        
		int quotient = (int) (Integer.MAX_VALUE / faktor);
		return quotient >= getEurocent();
	}
	
	
	/**
	*gibt Geldbetrag als EuroCentformat zurück
	*
	*@return int
	*/
	
	public int getEurocent()
	{
	    return 100*_euroAnteil + _centAnteil;
	}
	
	/**
	*gibt Geldbetrag als formatierten String zurück
	*
	*@return String 
	*/
	
	
	public String getFormatiertenString()
	{
		return _euroAnteil + "," + centToString(_centAnteil);
	}
		
	/**
	*gibt Geldbetrag als EuroCentformat zurück
	*
	*@return int
	*/
	
	private static String centToString(int cent)
	{
		if(cent<10)
		{
			return "0" + cent;
		}
		else
		{
			return Integer.toString(cent);
		}
	}

	@Override
	public String toString()
	{
	    return getFormatiertenString();
	}
	
	/**
	*prüft, ob String gültig ist
	*@param ein String ,das man überprüfen will 
	*@return boolean
	*/
	public static boolean istGueltigerString(String stringGeldbetrag)
    {
        return  stringGeldbetrag.matches("[1-9][0-9]{0,8},[0-9]{1,2}") ||
        		stringGeldbetrag.matches("0,[0-9]{1,2}");
                
    }
    	
    	
	/**
	*prüft, ob EuroAnteil gültig ist
	*@param  int als euro 
	*@return boolean
	*/
    public static boolean istGueltigerEuroAnteil(int euro)
    {
        return euro >= 0 && euro <= Integer.MAX_VALUE/100 - 99;
    }
    
    /**
	*prüft, ob CentAnteil gültig ist
	*@param  int als cent 
	*@return boolean
	*/
    public static boolean istGueltigerCentAnteil(int cent)
    {
        return cent >= 0 && cent < 100;
    }
    
    /**
	*prüft, ob EuroCent gültig ist
	*@param  int als EuroCent 
	*@return boolean
	*/
    public static boolean istGueltigerEurocentBetrag(int eurocent)
    {
        return eurocent >= 0 && eurocent <= Integer.MAX_VALUE;
    }
    
    
    /**
	*Gibt den entsprechenden Geldbetrag zurück
	*
	*@param euro : der Euroanteil
	*@param cent : der Centanteil
	*
	*@require istGueltigerEuroAnteil(euro)
	*@require istGueltigerCentAnteil(cent)
	*
	*@ensure result != null
	*
	*@return Geldbetrag 
	*/
    public static Geldbetrag selectGeldbetrag(int euro, int cent)
    {
    	assert istGueltigerEuroAnteil(euro) : "Vorbedingung verletzt: istGueltigerEuroAnteil(euro)!";
    	assert istGueltigerCentAnteil(cent) : "Vorbedingung verletzt: istGueltigerCentAnteil(cent)!";

    	String key = euro + "," + centToString(cent);
    	if(!werteMenge.containsKey(key))
    	{
    		werteMenge.put(key, new Geldbetrag(euro, cent));
    	}
        return werteMenge.get(key);
    }
        
    /**
	*Gibt den entsprechenden Geldbetrag zurück
	*
	*@param eurocent : EuroCent
	*
	*@require  istGueltigerEurocentBetrag(eurocent)
	*
	*@ensure result != null
	*
	*@return Geldbetrag 
	*/
    public static Geldbetrag selectGeldbetrag(int eurocent)
    {
    	assert istGueltigerEurocentBetrag(eurocent) : "Vorbedingung verletzt: istGueltigerEuroBetrag(eurocent)!";

    	String key = eurocent/100 + "," + centToString(eurocent%100);
    	if(!werteMenge.containsKey(key))
    	{
    		werteMenge.put(key, new Geldbetrag(eurocent));
    	}
        return werteMenge.get(key);    
    }
    
    /**
	*Gibt den entsprechenden Geldbetrag zurück
	*
	*@param geldbetrag : der Geldbetrag
	*
	*@require istGueltigerString(geldbetrag)
	*
	*@ensure result != null
	*
	*@return Geldbetrag 
	*/
    public static Geldbetrag selectGeldbetrag(String geldbetrag)
    {
    	assert istGueltigerString(geldbetrag) : "Vorbedingung verletzt: istGueltigerString(geldbetrag)!";

    	String key = geldbetrag;
    	int eurocent = Integer.parseInt(geldbetrag.replaceFirst("," , ""));
    	
 
    	if(!werteMenge.containsKey(key))
    	{
    		werteMenge.put(key, new Geldbetrag(eurocent));
    	}
        return werteMenge.get(key);
    }
        
}
