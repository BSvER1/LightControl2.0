package control.IO.serial;

import control.IO.serial.packets.Packet;
import control.main.Driver;
import control.main.system.OsCheck;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class SerialManager {

	private SerialPort serialPort;
	
	private boolean canSend = true;
	
	public SerialManager() {
		
		OsCheck.OSType ostype = OsCheck.getOperatingSystemType();
		switch (ostype) {
		    case Windows: 
		    	Driver.trace("found serial ports:");
		    	String[] portNames = SerialPortList.getPortNames();
		        for(int i = 0; i < portNames.length; i++){
		            Driver.trace("\t"+portNames[i]);
		        }
		        serialPort = new SerialPort("COM7");
		    	break;
		    case MacOS: 
		    	serialPort = new SerialPort("/dev/tty.SLAB_USBtoUART");
		    	break;
		    case Linux: 
		    	Driver.trace("Sorry, this operating system has not yet been implemented.");
		    	System.exit(0);
		    	break;
		    case Other: 
		    	Driver.trace("The program cannot identify your OS. Please contact the author for help.");
		    	System.exit(0);
		    	break; 
		}
		initPort();
		
		addListener();
		
	}
	
	public void initPort() {
		try {
			serialPort.openPort();
			serialPort.setParams(SerialPort.BAUDRATE_115200, 
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			Driver.trace("Opened port successfully");

		} catch (SerialPortException e) {
			canSend = false;
		}
	}
	
	private void addListener() {
		try {
			serialPort.addEventListener(new SerialPortEventListener() {
				@Override
				public void serialEvent(SerialPortEvent e) {
					try {
						if (e.getEventType() == SerialPortEvent.RXCHAR && e.getEventValue() >=1 ) {
							byte[] received_bytes = serialPort.readBytes(e.getEventValue());
							for (int i = 0; i < received_bytes.length; i++) {
								if (received_bytes[i] == (byte) 0x02) {
									//System.out.print("ACK Received: ");
								}
								if (received_bytes[i] == (byte) 0x03) { // command wrong, crc fail, header incorrect, data fucked, etc
									//System.out.print("NAK received ");
								}
								//System.out.printf("0x%02X ", received_bytes[i]);
							}
							//System.out.println();
						}
						
					} catch (SerialPortException e1) {
						//e1.printStackTrace();
					}
				}
			});
		} catch (SerialPortException e) {
			System.out.println("Could not connect to serial controller.");
			canSend = false;
			//e.printStackTrace();
		}
	}
	
	public void writePacket(Packet packet) {
		if (canSend) {
			try {
				serialPort.writeBytes(packet.getFinishedPacket());
			} catch (SerialPortException e) {
				System.err.println("serial port exception");
			}
		} else {
			//Driver.trace("not sending a packet...");
		}
	}
	
	public void closePort() {
		try {
			serialPort.purgePort(SerialPort.PURGE_RXCLEAR + SerialPort.PURGE_TXCLEAR);
			serialPort.closePort(); 
		} catch (SerialPortException e) {
			//do nothing on failure
		}
       
	}
	
}
