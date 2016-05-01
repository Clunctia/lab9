package converter;
/**
 * 
 * @author Clunctia
 *
 */

public class ConverterApp {
	/**
	 * Run ConverterUI
	 * @param args
	 */
	public static void main(String[]args){
		UnitConverter uc = new UnitConverter();
		ConverterUI app = new ConverterUI(uc);
		app.run();
	}
}
