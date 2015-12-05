package control.IO.serial;

public class HardwareDisplayOutput implements Runnable {

	
	private boolean running = true;
	
	public HardwareDisplayOutput() {
		
	}
	
	@Override
	public void run() {
		
		while (running) {
			
		}
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
}
