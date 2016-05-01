package converter;
/**
 * 
 * @author Clunctia
 *
 */
public class UnitConverter {
	
	/**
	 * 
	 * @param amount
	 * @param fromUnit
	 * @param toUnit
	 * @return
	 */
	public double convert( double amount, Unit fromUnit, Unit toUnit ){
		return amount*fromUnit.getValue()/toUnit.getValue();
	}
	
	/**
	 * 
	 * @return
	 */
	public Unit[] getUnits(){
		return Length.values();
	}
}
