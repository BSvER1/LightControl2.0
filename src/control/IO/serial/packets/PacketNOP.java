package control.IO.serial.packets;

public class PacketNOP extends Packet {
	
	public PacketNOP() {
		PacketHeader h = new PacketHeader(PacketHeader.commandPacket);
		PacketData data = new PacketData();
		data.setDataForAck();
		PacketPayload payload = new PacketPayload(PacketCommand.NOP, data);
		
		init(h, payload);
	}
}
