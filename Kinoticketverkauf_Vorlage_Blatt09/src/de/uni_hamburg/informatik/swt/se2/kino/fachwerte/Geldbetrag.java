package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.HashMap;
import java.util.Map;

public class Geldbetrag
{
    private final int _euroAnteil;
    private final int _centAnteil;
    private static final int MAXEUROCENT = Integer.MAX_VALUE/100 - 99;
    private static final Map<String, Geldbetrag> werteMenge = new HashMap<String, Geldbetrag>();
    
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
	*erzeugt einen neuen Geldbetrag 
	*
	*@param geldbetrag  
	*
	*@require geldbetrag muss Form E+,CC haben
	*
	*/
	private Geldbetrag(String geldbetrag)
    {   
        String[] geldBetragArray = geldbetrag.split(",");
    	int euro = Integer.parseInt(geldBetragArray[0]);
    	int cent = Integer.parseInt(geldBetragArray[1]);
    	
    	assert istGueltigerEuroAnteil(euro) : "Vorbedingung verletzt: istGueltigerEuroAnteil(euro)!";
    	assert istGueltigerCentAnteil(cent) : "Vorbedingung verletzt: istGueltigerCentAnteil(cent)!";

        _euroAnteil = euro;
        _centAnteil = cent;
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
	*@param faktor ein float, um den man den Betrag vervielfachen will
	*
	*@return Geldbetrag
	*/
	public Geldbetrag multipliziere(float faktor)
	{
        assert istMultiplizierenMoeglich(faktor);
        int eurocent = Math.round(getEurocent() * faktor);
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
	    return getEurocent() >= geldbetrag.getEurocent();
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
		int differenz = MAXEUROCENT  - summand.getEurocent();
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
		return istGroesserGleich(subtrahend);
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
		return faktor >= 0 && (faktor <= 1 || ((int) (Integer.MAX_VALUE / faktor)) >= getEurocent());
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
	*@return : formatierter String 
	*/
	public String getFormatiertenString()
	{
		return toString() + " €";
	}
		
	/**
	* Formatiert ein int in einen String
	*
	*@param cent : der zu formatierende Centbetrag
	*@return ein formatierter String, der den Centbetrag enthält
	*/
	private static String centToString(int cent)
	{
		assert cent<100 : "Vorbedingung verletzt: cent<100!";
		assert cent>=0 : "Vorbedingung verletzt: cent>0!";

		if(cent<10)
		{
			return "0" + cent;
		}
		else
		{
			return Integer.toString(cent);
		}
	}


	/**
	*ein string der die felder unseres objects durch ein komma getrennt wiederspiegelt
	*
	*@return eine string representation unseres Geldbetrag objects
	*/
	@Override
	public String toString()
	{
	    return _euroAnteil + "," + centToString(_centAnteil);
	}
	
	/**
	*Prüft, ob stringGeldbetrag ein gültiger Wert für Geldbetrag ist.
	*
	*@param stringGeldbetrag ein String, den man überprüfen will 
	*@return boolean
	*/
	public static boolean istGueltigerString(String stringGeldbetrag)
    {
        return  stringGeldbetrag.matches("[1-9][0-9]{0,8},[0-9]{0,2}") ||
        		stringGeldbetrag.matches("0,[0-9]{0,2}")|| stringGeldbetrag.matches("[0-9]{0,8}") ;
                
    }
    	
	/**
	*Prüft, ob EuroAnteil ein gültiger Wert für Geldbetrag ist.
	*
	*@param  euro : der int, den man überprüfen will 
	*@return boolean
	*/
    public static boolean istGueltigerEuroAnteil(int euro)
    {
        return euro >= 0 && euro <= Integer.MAX_VALUE/100 - 99;
    }
    
    /**
	*Prüft, ob CentAnteil ein gültiger Wert für Geldbetrag ist.
	*
	*@param  cent : der int, den man überprüfen will 
	*@return boolean
	*/
    public static boolean istGueltigerCentAnteil(int cent)
    {
        return cent >= 0 && cent < 100;
    }
    
    /**
	*Prüft, ob EuroCent ein gültiger Wert für Geldbetrag ist.
	*
	*@param  eurocent : der int, den man überprüfen will  
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
	*@require istGueltigerEuroAnteil(euro)
	*@require istGueltigerCentAnteil(cent)
	*@ensure result != null
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
	*@require  istGueltigerEurocentBetrag(eurocent)
	*@ensure result != null
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
	*@require istGueltigerString(geldbetrag)
	*@ensure result != null
	*@return Geldbetrag 
	*/
    public static Geldbetrag selectGeldbetrag(String geldbetrag)
    {
    	assert istGueltigerString(geldbetrag) : "Vorbedingung verletzt: istGueltigerString(geldbetrag)!";

    	geldbetrag = formatGeldbetragString(geldbetrag);
    	String key = geldbetrag;

    	if(!werteMenge.containsKey(key))
    	{
    		werteMenge.put(key, new Geldbetrag(geldbetrag));
    	}
        return werteMenge.get(key);
    }
    
    /**
     * Formatiert den String, sodass er die Form E+,CC hat
     * nimmt String mit oder ohne komma an, falls es keine Kommas gibt, wird sie hinzugefügt
     *
     * @require key != null
     * @param geldbetrag : der Geldbetrag
     * @return Geldbetrag
     */
    private static String formatGeldbetragString(String geldbetrag)
    {
    	assert geldbetrag != null : "Vorbedingung verletzt: geldbetrag != null!";

    	if(!geldbetrag.contains(","))
    	{
    		geldbetrag = geldbetrag.concat(",00");
    	}
    	else if(geldbetrag.charAt(geldbetrag.length()-2)== ',')
    	{
            geldbetrag = geldbetrag.concat("0");
        }
    	else if(geldbetrag.charAt(geldbetrag.length()-1)== ',')
    	{
            geldbetrag = geldbetrag.concat("00");
        }
    	    	
    	return geldbetrag;
    }
    
    /**
     * Prüft, ob ein Key in der werteMenge existiert.
     * 
     * @require key != null
     * @param key der Key
     * @return boolean (key existiert in wertemenge)
     */
    public static boolean existiertKey(String key)
    {
    	return werteMenge.containsKey(key);      
    }
}
