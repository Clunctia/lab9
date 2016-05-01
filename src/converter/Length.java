package converter;
/**
 * 
 * @author Clunctia
 *
 */

public enum Length implements Unit{
	METER( "Meter", 1.00 ), 
	KILOMETER( "Kilometer", 1000.00 ), 
	CENTIMETER( "Centimeter", 0.01 ),
	MILIMETER("Milimeter",0.001),
	MILE( "Mile", 1609.344 ),
	FOOT( "Foot", 0.30480 ), 
	WA( "Wa", 2.0 );
	
	
	public final String name;
	public final double value;
	
	/**
	 * 
	 * @param name
	 * @param value
	 */
	private Length( String name, double value) {
		this.name = name;
		this.value = value;
	}
	
	public String toString(){ return name; }

	public double getValue(){ return value;	}
	
}
