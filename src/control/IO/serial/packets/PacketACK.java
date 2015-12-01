package control.IO.serial.packets;

public class PacketACK extends Packet {

	public PacketACK() {
		PacketHeader h = new PacketHeader(PacketHeader.ackPacket);
		PacketData data = new PacketData();
		data.setDataForAck();
		PacketPayload payload = new PacketPayload(PacketCommand.ACK, data);
		
		init(h, payload);
	}

	
}
