package control.lighting.layout.ems;

import control.lighting.layout.LayoutEnum;
import control.lighting.layout.components.LedStrip;
import control.lighting.layout.components.LightingComponent;
import control.lighting.layout.ems.Locations;

public enum EmsSymbol implements LayoutEnum {
	
	E_TOP(new LedStrip("E top", Locations.E_TL, Locations.E_TR, 0), 1),
	E_LEFT_TOP(new LedStrip("E top brace", Locations.E_TL, Locations.E_ML, 0), 4),
	E_MID(new LedStrip("E mid", Locations.E_ML, Locations.E_MR, 0), 7),
	E_LEFT_BOTTOM(new LedStrip("E bottom brace", Locations.E_ML, Locations.E_BL, 0), 10),
	E_BOTTOM(new LedStrip("E bot", Locations.E_BL, Locations.E_BR, 0), 13),
	
	M_LEFT(new LedStrip("M left", Locations.M_BL, Locations.M_TL, 1), 16),
	M_MID_LEFT(new LedStrip("M mid left", Locations.M_TL, Locations.M_BM, 1), 19),
	M_MID_RIGHT(new LedStrip("M mid right", Locations.M_BM, Locations.M_TR, 1), 22),
	M_RIGHT(new LedStrip("M right", Locations.M_TR, Locations.M_BR, 1), 25),
	
	S_TOP_RIGHT(new LedStrip("S top right", Locations.S_TR, Locations.S_TT, 2), 28),
	S_TOP_LEFT(new LedStrip("S top left", Locations.S_TT, Locations.S_TL, 2), 31),
	S_MID_LEFT(new LedStrip("S mid left", Locations.S_TL, Locations.S_M, 2), 34),
	S_MID_RIGHT(new LedStrip("S mis right", Locations.S_M, Locations.S_BR, 2), 37),
	S_BOTTOM_RIGHT(new LedStrip("S bottom right", Locations.S_BR, Locations.S_BM, 2), 40),
	S_BOTTOM_LEFT(new LedStrip("S bottom left", Locations.S_BM, Locations.S_BL, 2), 43),
	
	BORDER_TOP(new LedStrip("border top", Locations.borderTL, Locations.borderTR, 3), 46),
	BORDER_LEFT(new LedStrip("border left", Locations.borderTL, Locations.borderBL, 3), 49),
	BORDER_RIGHT(new LedStrip("border right", Locations.borderTR, Locations.borderBR, 3), 52),
	BORDER_BOTTOM(new LedStrip("border bottom", Locations.borderBL, Locations.borderBR, 3), 55);
	
	
	private Integer channelStart;
	private LightingComponent component;

	private EmsSymbol(LightingComponent component, Integer channelStart) {
		this.component = component;
		this.channelStart = channelStart;
	}
	
	private EmsSymbol(EmsSymbol fixture) {
		this.component = fixture.component;
		this.channelStart = fixture.channelStart;
	}
	
	public int getChannelStart() {
		return channelStart;
	}

	public LightingComponent getComponent() {
		return component;
	}

	public int getNumComponents() {
		return values().length;
	}

}
