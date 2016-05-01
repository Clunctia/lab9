package converter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *@author Clunctia
 *
 * 
 */

public class ConverterUI extends JFrame{

	private JButton convertButton;
	private JButton clearButton;
	private UnitConverter unitconverter;
	private JLabel equalsSign;
	private JTextField leftInputField;
	private JTextField rightInputField;
	private JComboBox<Unit> fromUnit;
	private JComboBox<Unit> toUnit;
	private JRadioButton leftToRightOption;
	private JRadioButton rightToLeftOption;
	private ButtonGroup buttonGroup;
	private JMenu menu;
	private JMenuBar menuBar;

	/**
	 * Constractor ConverterUI set the title of this ui and start the app.
	 * @param UnitConverter uc this param for initialize unitconverter.
	 */
	public ConverterUI ( UnitConverter uc ){
		this.unitconverter = uc;

		this.setTitle("Length Converter");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponent();

	}

	/**
	 * initComponent create and add add button, actionListener, and GUI.
	 */
	private void initComponent(){
		JPanel windows = new JPanel();
		windows.setLayout(new BorderLayout());

		JPanel contens = new JPanel();
		contens.setLayout(new FlowLayout());

		JPanel contens2 = new JPanel();
		contens2.setLayout(new FlowLayout());

		windows.add(contens, BorderLayout.NORTH);
		windows.add(contens2, BorderLayout.CENTER);

		unitconverter = new UnitConverter();
		Unit[] unitList = unitconverter.getUnits();
		int length = 10;

		fromUnit = new JComboBox<Unit>(unitList);
		toUnit = new JComboBox<Unit>(unitList);

		leftInputField = new JTextField(length);
		rightInputField = new JTextField(length);

		convertButton = new JButton("Convert");
		clearButton = new JButton("Clear");
		equalsSign = new JLabel("=");
		leftToRightOption = new JRadioButton("Left->Right");
		rightToLeftOption = new JRadioButton("Right->Left");
		buttonGroup = new ButtonGroup();

		buttonGroup.add(leftToRightOption);
		buttonGroup.add(rightToLeftOption);

		contens.add(leftInputField);
		contens.add(fromUnit);
		contens.add(equalsSign);
		contens.add(rightInputField);
		contens.add(toUnit);
		contens.add( convertButton );
		contens.add(clearButton);
		contens2.add(leftToRightOption);
		contens2.add(rightToLeftOption);

		leftToRightOption.setSelected(true);
		rightInputField.setEditable(false);

		ActionListener listener = new ConvertButtonListener();
		convertButton.addActionListener(listener);
		leftInputField.addActionListener(listener);

		ActionListener clear = new ClearButtonListener();
		clearButton.addActionListener(clear);

		ActionListener chooseLeft = new CheckRadioButtonListenerLeft();
		leftToRightOption.addActionListener( chooseLeft );
		
		ActionListener chooseRight = new CheckRadioButtonListenerRight();
		rightToLeftOption.addActionListener( chooseRight );

		this.add(windows);


	}

	/**
	 * Run GUI.
	 */
	public void run(){
		this.pack();
		this.setVisible(true);
	}

	/**
	 * for Convert button this class is ActionListerer to action when click the Convert button.
	 * It will convert from inputField1 to inputField2 or inputField2 to inputField1 depend on which radioButton is selected.
	 * If left radioButton is selected it will convert from inputField1 to inputField2.
	 * And if right radioButton is selected it will convert from inputField2 to inputField1 respectively.
	 */
	class ConvertButtonListener implements ActionListener {
		public void actionPerformed( ActionEvent evt ){
			String s = "";
			if( leftToRightOption.isSelected() ){
				s = leftInputField.getText().trim();
			} else if ( rightToLeftOption.isSelected() ){
				s = rightInputField.getText().trim();
			}
			if( s.length() > 0 ){
				try{
					double value = Double.valueOf( s );
					Unit formUnitA = (Unit) fromUnit.getSelectedItem();
					Unit toUnitB = (Unit)toUnit.getSelectedItem();
					double converted ;
					if( leftToRightOption.isSelected() ){
						converted = unitconverter.convert( value, formUnitA , toUnitB );
						rightInputField.setText(String.format("%f", converted));
					} else if( rightToLeftOption.isSelected() ){
						converted = unitconverter.convert( value, toUnitB , formUnitA );
						leftInputField.setText(String.format("%f", converted));
					}

				}catch (NumberFormatException e){

				}
			}
		}
	}

	/**
	 * When click on clear button both inputField will change to empty String.
	 */
	class ClearButtonListener implements ActionListener {
		public void actionPerformed( ActionEvent evt ){
			leftInputField.setText("");
			rightInputField.setText("");
		}
	}

	/**
	 * Action when which radioButton is selected.
	 * If left button is selected inputField1 will editable but inputField2 will not editable.
	 * And if right button is selected inputField2 will editable but inputField1 will not editable.
	 */
	class CheckRadioButtonListenerLeft implements ActionListener {
		public void actionPerformed( ActionEvent evt ){
			leftInputField.setEditable(true);
			rightInputField.setEditable(false);
		}
	}

	class CheckRadioButtonListenerRight implements ActionListener {
		public void actionPerformed ( ActionEvent evt ){
			leftInputField.setEditable(false);
			rightInputField.setEditable(true);
		}
	}

}
