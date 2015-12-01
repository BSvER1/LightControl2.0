package control.IO.serial.packets;

public class PacketSwitch extends Packet {

	private final int MAX_CONFIGS = 4; //TODO this has to be moved someplace more central.
	
	public PacketSwitch(int config) {
		if (config < 0 || config > MAX_CONFIGS) {
			throw new IllegalArgumentException("config must be between 0 and "+MAX_CONFIGS);
		}
		
		PacketHeader h = new PacketHeader(PacketHeader.commandPacket);
		PacketData data = new PacketData();
		//byte configBytes[]
		data.setDataForAck();
		data.setData(0, (byte) config);
		PacketPayload payload = new PacketPayload(PacketCommand.SWITCH, data);
		
		init(h, payload);
	}
	
	public void setConfigData(int config) {
		payload.getData().setData(0, (byte) config);
		
		reverifyPacket();
	}
}
