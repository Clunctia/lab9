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


	public ConverterUI ( UnitConverter uc ){
		this.unitconverter = uc;

		this.setTitle("Length Converter");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponent();

	}


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

	public void run(){
		this.pack();
		this.setVisible(true);
	}

	class ConvertButtonListener implements ActionListener {
		public void actionPerformed( ActionEvent evt ){
			String s = "";
			if( radioButton1.isSelected() ){
				s = inputField1.getText().trim();
			} else if ( radioButton2.isSelected() ){
				s = inputField2.getText().trim();
			}

			System.out.println("actionPerformed: input=" + s);
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
					System.out.println("Please input number");
				}
			}
		}
	}

	class ClearButtonListener implements ActionListener {
		public void actionPerformed( ActionEvent evt ){
			inputField1.setText("");
			inputField2.setText("");
		}
	}

	class CheckRadioButtonListener implements ActionListener {
		public void actionPerformed( ActionEvent evt ){
			if(radioButton1.isSelected()){
				inputField1.setEditable(true);
				inputField2.setEditable(false);
			} else if (radioButton2.isSelected()){
				inputField1.setEditable(false);
				inputField2.setEditable(true);
			}
		}
	}

}
