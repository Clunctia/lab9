import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


 
public class ConverterUI extends JFrame{

	private JButton convertButton;
	private JButton clearButton;
	private UnitConverter unitconverter;
	private JLabel equalsSign;
	private JTextField inputField1;
	private JTextField inputField2;
	private JComboBox<Unit> unit1;
	private JComboBox<Unit> unit2;
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private ButtonGroup buttonGroup;

	/*
	 * Constractor ConverterUI set the title of this ui and start the app.
	 * @param UnitConverter uc this param for initialize unitconverter.
	 */
	public ConverterUI ( UnitConverter uc ){
		this.unitconverter = uc;

		this.setTitle("Length Converter");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponent();

	}

	/*
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

		unit1 = new JComboBox<Unit>(unitList);
		unit2 = new JComboBox<Unit>(unitList);

		inputField1 = new JTextField(length);
		inputField2 = new JTextField(length);

		convertButton = new JButton("Convert");
		clearButton = new JButton("Clear");
		equalsSign = new JLabel("=");
		radioButton1 = new JRadioButton("Left->Right");
		radioButton2 = new JRadioButton("Right->Left");
		buttonGroup = new ButtonGroup();

		buttonGroup.add(radioButton1);
		buttonGroup.add(radioButton2);

		contens.add(inputField1);
		contens.add(unit1);
		contens.add(equalsSign);
		contens.add(inputField2);
		contens.add(unit2);
		contens.add( convertButton );
		contens.add(clearButton);
		contens2.add(radioButton1);
		contens2.add(radioButton2);

		radioButton1.setSelected(true);
		inputField2.setEditable(false);

		ActionListener listener = new ConvertButtonListener();
		convertButton.addActionListener(listener);
		inputField1.addActionListener(listener);

		ActionListener clear = new ClearButtonListener();
		clearButton.addActionListener(clear);

		ActionListener choose = new CheckRadioButtonListener();
		radioButton1.addActionListener(choose);

		radioButton2.addActionListener(choose);

		this.add(windows);
		this.pack();

	}
	
	/*
	 * Show the GUI.
	 */
	public void run(){
		this.pack();
		this.setVisible(true);
	}
	
	/*
	 * for Convert button this class is ActionListerer to action when click the Convert button.
	 * It will convert from inputField1 to inputField2 or inputField2 to inputField1 depend on which radioButton is selected.
	 * If left radioButton is selected it will convert from inputField1 to inputField2.
	 * And if right radioButton is selected it will convert from inputField2 to inputField1 respectively.
	 */
	class ConvertButtonListener implements ActionListener {
		public void actionPerformed( ActionEvent evt ){
			String s = "";
			if( radioButton1.isSelected() ){
				s = inputField1.getText().trim();
			} else if ( radioButton2.isSelected() ){
				s = inputField2.getText().trim();
			}
			if( s.length() > 0 ){
				try{
					double value = Double.valueOf( s );
					Unit formUnit = (Unit) unit1.getSelectedItem();
					Unit toUnit = (Unit)unit2.getSelectedItem();
					double converted ;
					if( radioButton1.isSelected() ){
						converted = unitconverter.convert( value, formUnit , toUnit );
						inputField2.setText(String.format("%f", converted));
					} else if( radioButton2.isSelected() ){
						converted = unitconverter.convert( value, toUnit , formUnit );
						inputField1.setText(String.format("%f", converted));
					}

				}catch (NumberFormatException e){
					
				}
			}
		}
	}
	
	/*
	 * When click on clear button both inputField will change to empty String.
	 */
	class ClearButtonListener implements ActionListener {
		public void actionPerformed( ActionEvent evt ){
			inputField1.setText("");
			inputField2.setText("");
		}
	}
	
	/*
	 * Action when which radioButton is selected.
	 * If left button is selected inputField1 will editable but inputField2 will not editable.
	 * And if right button is selected inputField2 will editable but inputField1 will not editable.
	 */
	class CheckRadioButtonListener implements ActionListener {
		public void actionPerformed( ActionEvent evt ){
			if( radioButton1.isSelected() ){
				inputField1.setEditable(true);
				inputField2.setEditable(false);
			} else if ( radioButton2.isSelected() ){
				inputField1.setEditable(false);
				inputField2.setEditable(true);
			}
		}
	}

}
