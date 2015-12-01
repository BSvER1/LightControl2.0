package control.IO.serial.packets;

public class PacketMaxChannels extends Packet {

	
	public PacketMaxChannels(int numConfigs) {
		PacketHeader h = new PacketHeader(PacketHeader.commandPacket);
		PacketData data = new PacketData();
		data.setDataForAck();
		data.setData(1, (byte) numConfigs);
		PacketPayload payload = new PacketPayload(PacketCommand.MAX_CHANNEL, data);
		
		init(h, payload);
	}
}
