package control.IO.serial.packets;

import java.util.Arrays;

public class PacketData {

	final int MAX_NUM_BYTES = 4;
	
	byte[] data; 
	
	public PacketData() {
		data = new byte[MAX_NUM_BYTES];
	}
	
	public void setData(byte[] data){
		if (data.length!=MAX_NUM_BYTES) { 
			throw new IllegalArgumentException("packet data must consist of an array of "+MAX_NUM_BYTES+" bytes");
		}
		
		this.data = data;
	}
	
	/**
	 * fills all data slots with 0xFF for production of an ACK packet.
	 */
	public void setDataForAck() {
		data = new byte[] {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
	}
	
	public void setData(int pos, byte data) {
		this.data[pos] = data;
	}
	
	public byte[] toBytes() {
		return data;
	}
	
	public boolean equals(PacketData other) {
		return (Arrays.equals(data, other.data));
	}
}
