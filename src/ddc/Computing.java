package ddc;

import java.util.ArrayList;

import general.CommonObject;
import request.VirtualMachine;

public class Computing extends CommonObject {
	private int capacity = 0;
	private int load = 0;
	private ArrayList<VirtualMachine> occupiedVMs = new ArrayList<>();
	private double reliability = 0;

	public Computing(String name, int index, String comments, int capacity, double reliability) {
		super(name, index, comments);
		this.capacity = capacity;
		this.reliability = reliability;
	}

	public int getLoad() {
		return load;
	}

	public void setLoad(int load) {
		this.load = load;
	}

	public ArrayList<VirtualMachine> getOccupiedVMs() {
		return occupiedVMs;
	}

	public void setOccupiedVMs(ArrayList<VirtualMachine> occupiedVMs) {
		this.occupiedVMs = occupiedVMs;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getReliability() {
		return reliability;
	}

	public void setReliability(double reliability) {
		this.reliability = reliability;
	}

}
