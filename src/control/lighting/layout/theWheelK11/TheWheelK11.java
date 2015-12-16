package control.lighting.layout.theWheelK11;

import control.lighting.layout.LayoutEnum;
import control.lighting.layout.components.LedStrip;
import control.lighting.layout.components.LightingComponent;

public enum TheWheelK11 implements LayoutEnum {
	
	S1_2(new LedStrip("1-2", Locations.One, Locations.Two, 0), 1),
	S2_1(S1_2),
	S1_3(new LedStrip("1-3", Locations.One, Locations.Three, 0), 4),
	S3_1(S1_3),
	S1_4(new LedStrip("1-4", Locations.One, Locations.Four, 0), 7),
	S4_1(S1_4),
	S1_5(new LedStrip("1-5", Locations.One, Locations.Five, 0), 10),
	S5_1(S1_5),
	S1_6(new LedStrip("1-6", Locations.One, Locations.Six, 0), 13),
	S6_1(S1_6),
	S1_7(new LedStrip("1-7", Locations.One, Locations.Seven, 0), 16),
	S7_1(S1_7),
	S1_8(new LedStrip("1-8", Locations.One, Locations.Eight, 0), 19),
	S8_1(S1_8),
	S1_9(new LedStrip("1-9", Locations.One, Locations.Nine, 0), 22),
	S9_1(S1_9),
	S1_10(new LedStrip("1-10", Locations.One, Locations.Ten, 0), 25),
	S10_1(S1_10),
	S1_11(new LedStrip("1-11", Locations.One, Locations.Eleven, 0), 28),
	S11_1(S1_11),
	S2_3(new LedStrip("2-3", Locations.Two, Locations.Three, 0), 31),
	S3_2(S2_3),
	S2_4(new LedStrip("2-4", Locations.Two, Locations.Four, 0), 34),
	S4_2(S2_4),
	S2_5(new LedStrip("2-5", Locations.Two, Locations.Five, 0), 37),
	S5_2(S2_5),
	S2_6(new LedStrip("2-6", Locations.Two, Locations.Six, 0), 40),
	S6_2(S2_6),
	S2_7(new LedStrip("2-7", Locations.Two, Locations.Seven, 0), 43),
	S7_2(S2_7),
	S2_8(new LedStrip("2-8", Locations.Two, Locations.Eight, 0), 46),
	S8_2(S2_8),
	S2_9(new LedStrip("2-9", Locations.Two, Locations.Nine, 0), 49),
	S9_2(S2_9),
	S2_10(new LedStrip("2-10", Locations.Two, Locations.Ten, 0), 52),
	S10_2(S2_10),
	S2_11(new LedStrip("2-11", Locations.Two, Locations.Eleven, 0), 55),
	S11_2(S2_11),
	S3_4(new LedStrip("3-4", Locations.Three, Locations.Four, 0), 58),
	S4_3(S3_4),
	S3_5(new LedStrip("3-5", Locations.Three, Locations.Five, 0), 61),
	S5_3(S3_5),
	S3_6(new LedStrip("3-6", Locations.Three, Locations.Six, 0), 64),
	S6_3(S3_6),
	S3_7(new LedStrip("3-7", Locations.Three, Locations.Seven, 0), 67),
	S7_3(S3_7),
	S3_8(new LedStrip("3-8", Locations.Three, Locations.Eight, 0), 70),
	S8_3(S3_8),
	S3_9(new LedStrip("3-9", Locations.Three, Locations.Nine, 0), 73),
	S9_3(S3_9),
	S3_10(new LedStrip("3-10", Locations.Three, Locations.Ten, 0), 76),
	S10_3(S3_10),
	S3_11(new LedStrip("3-11", Locations.Three, Locations.Eleven, 0), 79),
	S11_3(S3_11),
	S4_5(new LedStrip("4-5", Locations.Four, Locations.Five, 0), 82),
	S5_4(S4_5),
	S4_6(new LedStrip("4-6", Locations.Four, Locations.Six, 0), 85),
	S6_4(S4_6),
	S4_7(new LedStrip("4-7", Locations.Four, Locations.Seven, 0), 88),
	S7_4(S4_7),
	S4_8(new LedStrip("4-8", Locations.Four, Locations.Eight, 0), 91),
	S8_4(S4_8),
	S4_9(new LedStrip("4-9", Locations.Four, Locations.Nine, 0), 94),
	S9_4(S4_9),
	S4_10(new LedStrip("4-10", Locations.Four, Locations.Ten, 0), 97),
	S10_4(S4_10),
	S4_11(new LedStrip("4-11", Locations.Four, Locations.Eleven, 0), 100),
	S11_4(S4_11),
	S5_6(new LedStrip("5-6", Locations.Five, Locations.Six, 0), 103),
	S6_5(S5_6),
	S5_7(new LedStrip("5-7", Locations.Five, Locations.Seven, 0), 106),
	S7_5(S5_7),
	S5_8(new LedStrip("5-8", Locations.Five, Locations.Eight, 0), 109),
	S8_5(S5_8),
	S5_9(new LedStrip("5-9", Locations.Five, Locations.Nine, 0), 112),
	S9_5(S5_9),
	S5_10(new LedStrip("5-10", Locations.Five, Locations.Ten, 0), 115),
	S10_5(S5_10),
	S5_11(new LedStrip("5-11", Locations.Five, Locations.Eleven, 0), 118),
	S11_5(S5_11),
	S6_7(new LedStrip("6-7", Locations.Six, Locations.Seven, 0), 121),
	S7_6(S6_7),
	S6_8(new LedStrip("6-8", Locations.Six, Locations.Eight, 0), 124),
	S8_6(S6_8),
	S6_9(new LedStrip("6-9", Locations.Six, Locations.Nine, 0), 127),
	S9_6(S6_9),
	S6_10(new LedStrip("6-10", Locations.Six, Locations.Ten, 0), 130),
	S10_6(S6_10),
	S6_11(new LedStrip("6-11", Locations.Six, Locations.Eleven, 0), 133),
	S11_6(S6_11),
	S7_8(new LedStrip("7-8", Locations.Seven, Locations.Eight, 0), 136),
	S8_7(S7_8),
	S7_9(new LedStrip("7-9", Locations.Seven, Locations.Nine, 0), 139),
	S9_7(S7_9),
	S7_10(new LedStrip("7-10", Locations.Seven, Locations.Ten, 0), 142),
	S10_7(S7_10),
	S7_11(new LedStrip("7-11", Locations.Seven, Locations.Eleven, 0), 145),
	S11_7(S7_11),
	S8_9(new LedStrip("8-9", Locations.Eight, Locations.Nine, 0), 148),
	S9_8(S8_9),
	S8_10(new LedStrip("8-10", Locations.Eight, Locations.Ten, 0), 151),
	S10_8(S8_10),
	S8_11(new LedStrip("8-11", Locations.Nine, Locations.Eleven, 0), 154),
	S11_8(S8_11),
	S9_10(new LedStrip("9-10", Locations.Nine, Locations.Ten, 0), 157),
	S10_9(S9_10),
	S9_11(new LedStrip("9-11", Locations.Nine, Locations.Eleven, 0), 160),
	S11_9(S9_11),
	S10_11(new LedStrip("10-11", Locations.Ten, Locations.Eleven, 0), 163),
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

	public LightingComponent getComponent() {
		return component;
	}

	public int getChannelStart() {
		return channelStart;
	}
}
