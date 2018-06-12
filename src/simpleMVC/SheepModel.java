package simpleMVC;

import java.util.Observable;

public class SheepModel extends Observable {
	private int total;
	private int current;
	
	public SheepModel(int total, int current) {
		this.setTotal(total);
		this.current = current;
	}
	
	public void increment() {
		this.setChanged();
		this.notifyObservers("Increment");
		current++;
	}
	
	public void decrement() {
		if(current>0) {
		this.setChanged();
		this.notifyObservers("Decrement");
		current--;
		}
	}

	public void setTotal(int total) {
		this.total = total;
		this.setChanged();
		this.notifyObservers("SetTotal");
	}

	public int getTotal() {
		return total;
	}
	
	
	
}
