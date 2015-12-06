package control.lighting.layout;

import java.awt.Color;

import control.lighting.layout.theWheelK11.Locations;
import control.main.Driver;

public enum TheWheelK11 {

	S1_2(new LightingComponent(LightingType.LedStrip, "1-2", Locations.One, Locations.Two), 1),
	S2_1(S1_2),
	S1_3(new LightingComponent(LightingType.LedStrip, "1-3", Locations.One, Locations.Three), 4),
	S3_1(S1_3),
	S1_4(new LightingComponent(LightingType.LedStrip, "1-4", Locations.One, Locations.Four), 7),
	S4_1(S1_4),
	S1_5(new LightingComponent(LightingType.LedStrip, "1-5", Locations.One, Locations.Five), 10),
	S5_1(S1_5),
	S1_6(new LightingComponent(LightingType.LedStrip, "1-6", Locations.One, Locations.Six), 13),
	S6_1(S1_6),
	S1_7(new LightingComponent(LightingType.LedStrip, "1-7", Locations.One, Locations.Seven), 16),
	S7_1(S1_7),
	S1_8(new LightingComponent(LightingType.LedStrip, "1-8", Locations.One, Locations.Eight), 19),
	S8_1(S1_8),
	S1_9(new LightingComponent(LightingType.LedStrip, "1-9", Locations.One, Locations.Nine), 22),
	S9_1(S1_9),
	S1_10(new LightingComponent(LightingType.LedStrip, "1-10", Locations.One, Locations.Ten), 25),
	S10_1(S1_10),
	S1_11(new LightingComponent(LightingType.LedStrip, "1-11", Locations.One, Locations.Eleven), 28),
	S11_1(S1_11),
	S2_3(new LightingComponent(LightingType.LedStrip, "2-3", Locations.Two, Locations.Three), 31),
	S3_2(S2_3),
	S2_4(new LightingComponent(LightingType.LedStrip, "2-4", Locations.Two, Locations.Four), 34),
	S4_2(S2_4),
	S2_5(new LightingComponent(LightingType.LedStrip, "2-5", Locations.Two, Locations.Five), 37),
	S5_2(S2_5),
	S2_6(new LightingComponent(LightingType.LedStrip, "2-6", Locations.Two, Locations.Six), 40),
	S6_2(S2_6),
	S2_7(new LightingComponent(LightingType.LedStrip, "2-7", Locations.Two, Locations.Seven), 43),
	S7_2(S2_7),
	S2_8(new LightingComponent(LightingType.LedStrip, "2-8", Locations.Two, Locations.Eight), 46),
	S8_2(S2_8),
	S2_9(new LightingComponent(LightingType.LedStrip, "2-9", Locations.Two, Locations.Nine), 49),
	S9_2(S2_9),
	S2_10(new LightingComponent(LightingType.LedStrip, "2-10", Locations.Two, Locations.Ten), 52),
	S10_2(S2_10),
	S2_11(new LightingComponent(LightingType.LedStrip, "2-11", Locations.Two, Locations.Eleven), 55),
	S11_2(S2_11),
	S3_4(new LightingComponent(LightingType.LedStrip, "3-4", Locations.Three, Locations.Four), 58),
	S4_3(S3_4),
	S3_5(new LightingComponent(LightingType.LedStrip, "3-5", Locations.Three, Locations.Five), 61),
	S5_3(S3_5),
	S3_6(new LightingComponent(LightingType.LedStrip, "3-6", Locations.Three, Locations.Six), 64),
	S6_3(S3_6),
	S3_7(new LightingComponent(LightingType.LedStrip, "3-7", Locations.Three, Locations.Seven), 67),
	S7_3(S3_7),
	S3_8(new LightingComponent(LightingType.LedStrip, "3-8", Locations.Three, Locations.Eight), 70),
	S8_3(S3_8),
	S3_9(new LightingComponent(LightingType.LedStrip, "3-9", Locations.Three, Locations.Nine), 73),
	S9_3(S3_9),
	S3_10(new LightingComponent(LightingType.LedStrip, "3-10", Locations.Three, Locations.Ten), 76),
	S10_3(S3_10),
	S3_11(new LightingComponent(LightingType.LedStrip, "3-11", Locations.Three, Locations.Eleven), 79),
	S11_3(S3_11),
	S4_5(new LightingComponent(LightingType.LedStrip, "4-5", Locations.Four, Locations.Five), 82),
	S5_4(S4_5),
	S4_6(new LightingComponent(LightingType.LedStrip, "4-6", Locations.Four, Locations.Six), 85),
	S6_4(S4_6),
	S4_7(new LightingComponent(LightingType.LedStrip, "4-7", Locations.Four, Locations.Seven), 88),
	S7_4(S4_7),
	S4_8(new LightingComponent(LightingType.LedStrip, "4-8", Locations.Four, Locations.Eight), 91),
	S8_4(S4_8),
	S4_9(new LightingComponent(LightingType.LedStrip, "4-9", Locations.Four, Locations.Nine), 94),
	S9_4(S4_9),
	S4_10(new LightingComponent(LightingType.LedStrip, "4-10", Locations.Four, Locations.Ten), 97),
	S10_4(S4_10),
	S4_11(new LightingComponent(LightingType.LedStrip, "4-11", Locations.Four, Locations.Eleven), 100),
	S11_4(S4_11),
	S5_6(new LightingComponent(LightingType.LedStrip, "5-6", Locations.Five, Locations.Six), 103),
	S6_5(S5_6),
	S5_7(new LightingComponent(LightingType.LedStrip, "5-7", Locations.Five, Locations.Seven), 106),
	S7_5(S5_7),
	S5_8(new LightingComponent(LightingType.LedStrip, "5-8", Locations.Five, Locations.Eight), 109),
	S8_5(S5_8),
	S5_9(new LightingComponent(LightingType.LedStrip, "5-9", Locations.Five, Locations.Nine), 112),
	S9_5(S5_9),
	S5_10(new LightingComponent(LightingType.LedStrip, "5-10", Locations.Five, Locations.Ten), 115),
	S10_5(S5_10),
	S5_11(new LightingComponent(LightingType.LedStrip, "5-11", Locations.Five, Locations.Eleven), 118),
	S11_5(S5_11),
	S6_7(new LightingComponent(LightingType.LedStrip, "6-7", Locations.Six, Locations.Seven), 121),
	S7_6(S6_7),
	S6_8(new LightingComponent(LightingType.LedStrip, "6-8", Locations.Six, Locations.Eight), 124),
	S8_6(S6_8),
	S6_9(new LightingComponent(LightingType.LedStrip, "6-9", Locations.Six, Locations.Nine), 127),
	S9_6(S6_9),
	S6_10(new LightingComponent(LightingType.LedStrip, "6-10", Locations.Six, Locations.Ten), 130),
	S10_6(S6_10),
	S6_11(new LightingComponent(LightingType.LedStrip, "6-11", Locations.Six, Locations.Eleven), 133),
	S11_6(S6_11),
	S7_8(new LightingComponent(LightingType.LedStrip, "7-8", Locations.Seven, Locations.Eight), 136),
	S8_7(S7_8),
	S7_9(new LightingComponent(LightingType.LedStrip, "7-9", Locations.Seven, Locations.Nine), 139),
	S9_7(S7_9),
	S7_10(new LightingComponent(LightingType.LedStrip, "7-10", Locations.Seven, Locations.Ten), 142),
	S10_7(S7_10),
	S7_11(new LightingComponent(LightingType.LedStrip, "7-11", Locations.Seven, Locations.Eleven), 145),
	S11_7(S7_11),
	S8_9(new LightingComponent(LightingType.LedStrip, "8-9", Locations.Eight, Locations.Nine), 148),
	S9_8(S8_9),
	S8_10(new LightingComponent(LightingType.LedStrip, "8-10", Locations.Eight, Locations.Ten), 151),
	S10_8(S8_10),
	S8_11(new LightingComponent(LightingType.LedStrip, "8-11", Locations.Nine, Locations.Eleven), 154),
	S11_8(S8_11),
	S9_10(new LightingComponent(LightingType.LedStrip, "9-10", Locations.Nine, Locations.Ten), 157),
	S10_9(S9_10),
	S9_11(new LightingComponent(LightingType.LedStrip, "9-11", Locations.Nine, Locations.Eleven), 160),
	S11_9(S9_11),
	S10_11(new LightingComponent(LightingType.LedStrip, "10-11", Locations.Ten, Locations.Eleven), 163),
	S11_10(S10_11);
	
	
	private Integer channelStart;
	private LightingComponent component;
	
	
	private TheWheelK11(LightingComponent component, Integer channelStart) {
		this.component = component;
		this.channelStart = channelStart;
	}
	
	private TheWheelK11(TheWheelK11 fixture) {
		this.component = fixture.component;
		this.channelStart = fixture.channelStart;
	}
	
	public static TheWheelK11 getStrip(int start, int stop) {
		String temp;

		if (start < stop)
			temp = "" + start + "-" + stop;
		else
			temp = "" + stop + "-" + start;
		
		for (TheWheelK11 tempStrip : TheWheelK11.values()) {
			if (tempStrip.getComponent().getIdentifier().equals(temp))
				return tempStrip;
		}
		return null;
	}
	
	public int getNumComponents() {
		return TheWheelK11.values().length/2;
	}
	
	public static TheWheelK11[] getStrip(String value) {
		for (TheWheelK11 str : TheWheelK11.values()) {
			if (str.toString().equals(value)) {
				return new TheWheelK11[] {str};
			}
		}
		return null;
		
	}
	
	public Integer getRedChannel() {
		return channelStart;
	}
	
	public Integer getGreenChannel() {
		return channelStart+2;
	}
	
	public Integer getBlueChannel() {
		return channelStart+1;
	}
	
	public int getRedValue() {
		return component.getCol().getRed();
	}
	
	public int getGreenValue() {
		return component.getCol().getGreen();
	}

	public int getBlueValue() {
		return component.getCol().getBlue();
	}
	
	public void setCol(Color col) {
		this.component.setCol(col);
	}

	public String getIdentifier() {
		return component.getIdentifier();
	}

	public void setIdentifier(String identifier) {
		this.component.setIdentifier(identifier);
	}

	/**
	 * using this method is not recommended. Use get(Color)Channel methods instead.
	 * @return
	 */
	@Deprecated
	public Integer getChannelStart() {
		Driver.trace("using this method is not recommended. Use get(Color)Channel methods instead.");
		return channelStart;
	}

	public void setChannelStart(Integer channelStart) {
		this.channelStart = channelStart;
	}

	public LightingType getType() {
		return component.getType();
	}

	public void setType(LightingType type) {
		component.setType(type);
	}

	public Location2D getStart() {
		return component.getStart();
	}

	public void setStart(Location2D start) {
		component.setStart(start);
	}

	public Location2D getEnd() {
		return component.getEnd();
	}

	public void setEnd(Location2D end) {
		component.setEnd(end);
	}
	
	private LightingComponent getComponent() {
		return component;
	}
}
