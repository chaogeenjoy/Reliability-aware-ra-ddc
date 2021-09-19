package algorithm_design;

import java.util.ArrayList;
import java.util.Collections;

import ddc.DDC;
import request.VMGenerator;
import request.VirtualMachine;

public class DDCThread extends Thread {
	private int shuffleTime = 0;
	private boolean nonFateSharing = false;
	private int vmNum = 0;
	private double lowerReq = 0;
	private double upperReq = 0;
	private int accetance = 0;
	private int accptWithBackups = 0;

	public int getShuffleTime() {
		return shuffleTime;
	}

	public void setShuffleTime(int shuffleTime) {
		this.shuffleTime = shuffleTime;
	}

	public boolean isNonFateSharing() {
		return nonFateSharing;
	}

	public void setNonFateSharing(boolean nonFateSharing) {
		this.nonFateSharing = nonFateSharing;
	}

	public int getVmNum() {
		return vmNum;
	}

	public void setVmNum(int vmNum) {
		this.vmNum = vmNum;
	}

	public double getLowerReq() {
		return lowerReq;
	}

	public void setLowerReq(double lowerReq) {
		this.lowerReq = lowerReq;
	}

	public double getUpperReq() {
		return upperReq;
	}

	public void setUpperReq(double upperReq) {
		this.upperReq = upperReq;
	}

	public int getAccetance() {
		return accetance;
	}

	public void setAccetance(int accetance) {
		this.accetance = accetance;
	}

	public int getAccptWithBackups() {
		return accptWithBackups;
	}

	public void setAccptWithBackups(int accptWithBackups) {
		this.accptWithBackups = accptWithBackups;
	}

	public DDCThread(int shuffleTime, boolean nonFateSharing, int vmNum, double lowerReq, double upperReq,
			String threadName) {
		super(threadName);
		this.shuffleTime = shuffleTime;
		this.nonFateSharing = nonFateSharing;
		this.vmNum = vmNum;
		this.lowerReq = lowerReq;
		this.upperReq = upperReq;
	}

	@Override
	public void run() {
		int maxAcceptance = 0;
		int maxNumofAccWithBackup = 0;
		double obj = 0.0;
		long begin = System.currentTimeMillis();
		for (int i = 1; i < this.getShuffleTime() + 1; i++) {
			DDC ddc = new DDC();
			ddc.createDDC();

			VMGenerator g = new VMGenerator();
			ArrayList<VirtualMachine> vms = g.generatingVMs(this.getVmNum(), this.getLowerReq(), this.getUpperReq());
			DDC_Algorithm_Reliability_First da = new DDC_Algorithm_Reliability_First();
			da.setAlpha(0.000);
			Collections.shuffle(vms);

			da.mapVMBatches(ddc, vms, this.isNonFateSharing());
			double temp = da.getAccept() - 0.001 * da.getBackup();
			if (temp > obj) {
				maxAcceptance = da.getAccept();
				maxNumofAccWithBackup = da.getBackup();
				obj = temp;
			}
			if (this.getShuffleTime() > 10 && i % (this.getShuffleTime() / 10) == 0)
				System.out.println("\t" + currentThread().getName() + ":\t" + i + ":\t" + obj + "\t"
						+ ((double) (System.currentTimeMillis() - begin) / 1000.0) + "s");
		}

		System.out.println("\n___________________________________" + currentThread().getName() + "Running time:\t"
				+ ((double) (System.currentTimeMillis() - begin) / 1000.0) + "s\ntotal acceptance\t\t" + maxAcceptance
				+ "\r\ntotal accepted with backups\t" + maxNumofAccWithBackup
				+ "\n_________________________________\n");
		
		this.setAccetance(maxAcceptance);
		this.setAccptWithBackups(maxNumofAccWithBackup);

	}
}