
public class ConverterApp {
	public static void main(String[]args){
		UnitConverter uc = new UnitConverter();
		ConverterUI app = new ConverterUI(uc);
		app.run();
	}
}
