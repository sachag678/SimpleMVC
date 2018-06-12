package simpleMVC;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SheepView extends JFrame implements Observer {
	JPanel panel;
	JButton decrement;
	JButton increment;
	private JTextField currentTF; 
	private JTextField totalTF;
	private JLabel status;
	
	public SheepView(SheepController c) {
		super("Sheep Counter");
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,2));
		
		decrement = new JButton("Decrement");
		increment = new JButton("Increment");
		currentTF = new JTextField("0");
		totalTF = new JTextField("0");
		status = new JLabel("Start");
		decrement.setEnabled(false);
		increment.setEnabled(false);
		
		decrement.addActionListener(c);
		increment.addActionListener(c);
		totalTF.addActionListener(c);
		totalTF.setActionCommand("total");
		
		panel.add(new JLabel("Total"));
		panel.add(totalTF);
		panel.add(new JLabel("Current"));
		panel.add(currentTF);
		panel.add(increment);
		panel.add(decrement);
		panel.add(status);
		
		this.setPreferredSize(new Dimension(300,200));
		this.add(panel);
		this.pack();
		this.setVisible(true);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1.toString().equals("Increment")) {
			setCurrentText(Integer.toString((Integer.parseInt(currentTF.getText())+1)));
		}else if(arg1.toString().equals("Decrement")) {
			setCurrentText(Integer.toString((Integer.parseInt(currentTF.getText())-1)));
		}else if(arg1.toString().equals("SetTotal")) {
			setTotalText(Integer.toString(((SheepModel)arg0).getTotal()));
			increment.setEnabled(true);
			decrement.setEnabled(true);
		}
		updateStatus();
	}
	
	private void updateStatus() {
		if(currentTF.getText().equals(totalTF.getText())){
			status.setText("Complete");
			status.setForeground(Color.GREEN);
		}else if(Integer.parseInt(currentTF.getText())>Integer.parseInt(totalTF.getText())) {
			status.setText("Inconsistent");
			status.setForeground(Color.YELLOW);
		}else {
			status.setText("Incomplete");
			status.setForeground(Color.RED);
		}
	}
	
	private void setCurrentText(String current) {
		currentTF.setText(current);
	}
	
	private void setTotalText(String current) {
		totalTF.setText(current);
	}
	
	public static void main(String [] args) {
		SheepModel model = new SheepModel(0,0);
		SheepController controller = new SheepController(model);
		SheepView view = new SheepView(controller);
		model.addObserver(view);
		controller.setView(view);
	}

	public void inputException(String infoMessage) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: Incorrect Input", JOptionPane.INFORMATION_MESSAGE);
	}
}
