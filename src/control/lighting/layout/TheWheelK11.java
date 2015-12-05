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
	S1_9(LightingType.LedStrip, "1-9", 22, Locations.One, Locations.Nine),
	S9_1(S1_9),
	S1_10(LightingType.LedStrip, "1-10", 25, Locations.One, Locations.Ten),
	S10_1(S1_10),
	S1_11(LightingType.LedStrip, "1-11", 28, Locations.One, Locations.Eleven),
	S11_1(S1_11),
	S2_3(LightingType.LedStrip, "2-3", 31, Locations.Two, Locations.Three),
	S3_2(S2_3),
	S2_4(LightingType.LedStrip, "2-4", 34, Locations.Two, Locations.Four),
	S4_2(S2_4),
	S2_5(LightingType.LedStrip, "2-5", 37, Locations.Two, Locations.Five),
	S5_2(S2_5),
	S2_6(LightingType.LedStrip, "2-6", 40, Locations.Two, Locations.Six),
	S6_2(S2_6),
	S2_7(LightingType.LedStrip, "2-7", 43, Locations.Two, Locations.Seven),
	S7_2(S2_7),
	S2_8(LightingType.LedStrip, "2-8", 46, Locations.Two, Locations.Eight),
	S8_2(S2_8),
	S2_9(LightingType.LedStrip, "2-9", 49, Locations.Two, Locations.Nine),
	S9_2(S2_9),
	S2_10(LightingType.LedStrip, "2-10", 52, Locations.Two, Locations.Ten),
	S10_2(S2_10),
	S2_11(LightingType.LedStrip, "2-11", 55, Locations.Two, Locations.Eleven),
	S11_2(S2_11),
	S3_4(LightingType.LedStrip, "3-4", 58, Locations.Three, Locations.Four),
	S4_3(S3_4),
	S3_5(LightingType.LedStrip, "3-5", 61, Locations.Three, Locations.Five),
	S5_3(S3_5),
	S3_6(LightingType.LedStrip, "3-6", 64, Locations.Three, Locations.Six),
	S6_3(S3_6),
	S3_7(LightingType.LedStrip, "3-7", 67, Locations.Three, Locations.Seven),
	S7_3(S3_7),
	S3_8(LightingType.LedStrip, "3-8", 70, Locations.Three, Locations.Eight),
	S8_3(S3_8),
	S3_9(LightingType.LedStrip, "3-9", 73, Locations.Three, Locations.Nine),
	S9_3(S3_9),
	S3_10(LightingType.LedStrip, "3-10", 76, Locations.Three, Locations.Ten),
	S10_3(S3_10),
	S3_11(LightingType.LedStrip, "3-11", 79, Locations.Three, Locations.Eleven),
	S11_3(S3_11),
	S4_5(LightingType.LedStrip, "4-5", 82, Locations.Four, Locations.Five),
	S5_4(S4_5),
	S4_6(LightingType.LedStrip, "4-6", 85, Locations.Four, Locations.Six),
	S6_4(S4_6),
	S4_7(LightingType.LedStrip, "4-7", 88, Locations.Four, Locations.Seven),
	S7_4(S4_7),
	S4_8(LightingType.LedStrip, "4-8", 91, Locations.Four, Locations.Eight),
	S8_4(S4_8),
	S4_9(LightingType.LedStrip, "4-9", 94, Locations.Four, Locations.Nine),
	S9_4(S4_9),
	S4_10(LightingType.LedStrip, "4-10", 97, Locations.Four, Locations.Ten),
	S10_4(S4_10),
	S4_11(LightingType.LedStrip, "4-11", 100, Locations.Four, Locations.Eleven),
	S11_4(S4_11),
	S5_6(LightingType.LedStrip, "5-6", 103, Locations.Five, Locations.Six),
	S6_5(S5_6),
	S5_7(LightingType.LedStrip, "5-7", 106, Locations.Five, Locations.Seven),
	S7_5(S5_7),
	S5_8(LightingType.LedStrip, "5-8", 109, Locations.Five, Locations.Eight),
	S8_5(S5_8),
	S5_9(LightingType.LedStrip, "5-9", 112, Locations.Five, Locations.Nine),
	S9_5(S5_9),
	S5_10(LightingType.LedStrip, "5-10", 115, Locations.Five, Locations.Ten),
	S10_5(S5_10),
	S5_11(LightingType.LedStrip, "5-11", 118, Locations.Five, Locations.Eleven),
	S11_5(S5_11),
	S6_7(LightingType.LedStrip, "6-7", 121, Locations.Six, Locations.Seven),
	S7_6(S6_7),
	S6_8(LightingType.LedStrip, "6-8", 124, Locations.Six, Locations.Eight),
	S8_6(S6_8),
	S6_9(LightingType.LedStrip, "6-9", 127, Locations.Six, Locations.Nine),
	S9_6(S6_9),
	S6_10(LightingType.LedStrip, "6-10", 130, Locations.Six, Locations.Ten),
	S10_6(S6_10),
	S6_11(LightingType.LedStrip, "6-11", 133, Locations.Six, Locations.Eleven),
	S11_6(S6_11),
	S7_8(LightingType.LedStrip, "7-8", 136, Locations.Seven, Locations.Eight),
	S8_7(S7_8),
	S7_9(LightingType.LedStrip, "7-9", 139, Locations.Seven, Locations.Nine),
	S9_7(S7_9),
	S7_10(LightingType.LedStrip, "7-10", 142, Locations.Seven, Locations.Ten),
	S10_7(S7_10),
	S7_11(LightingType.LedStrip, "7-11", 145, Locations.Seven, Locations.Eleven),
	S11_7(S7_11),
	S8_9(LightingType.LedStrip, "8-9", 148, Locations.Eight, Locations.Nine),
	S9_8(S8_9),
	S8_10(LightingType.LedStrip, "8-10", 151, Locations.Eight, Locations.Ten),
	S10_8(S8_10),
	S8_11(LightingType.LedStrip, "8-11", 154, Locations.Nine, Locations.Eleven),
	S11_8(S8_11),
	S9_10(LightingType.LedStrip, "9-10", 157, Locations.Nine, Locations.Ten),
	S10_9(S9_10),
	S9_11(LightingType.LedStrip, "9-11", 160, Locations.Nine, Locations.Eleven),
	S11_9(S9_11),
	S10_11(LightingType.LedStrip, "10-11", 163, Locations.Ten, Locations.Eleven),
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
		component.getCol().getGreen();
	}

	public int getBlueValue() {
		component.getCol().getBlue();
	}
	
	public void setCol(Color col) {
		component.setCol(col);
	}

	public String getIdentifier() {
		return component.getIdentifier();
	}

	public void setIdentifier(String identifier) {
		component.setIdentifier(identifier);
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
