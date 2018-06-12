package simpleMVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class SheepController implements ActionListener {
	SheepModel model;
	SheepView view;
	
	public SheepController(SheepModel model) {
		this.model = model;
	}
	
	public void setView(SheepView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Increment")) {
			model.increment();}
		else if(e.getActionCommand().equals("Decrement")) {
			model.decrement();}
		else if(e.getActionCommand().equals("total")){
			setTotal(e);
		}
	}
	
	/**
	 * Checks for bad inputs - negative number and characters other than numbers
	 * */
	private void setTotal(ActionEvent e) {
		String text = ((JTextField)e.getSource()).getText();
		int total;
		try {
			total = Integer.parseInt(text);
			if(total>=0) {
				model.setTotal(total);
			}else{
				view.inputException("You must only enter positive numbers into total!");
			}
		}catch(Exception exp) {
			view.inputException("You must only enter numbers into total!");
		}
	}

}
