package control.lighting.layout.components;

import control.lighting.layout.Location2D;

public class LedStrip extends LightingComponent {

	public LedStrip(String identifier, Location2D start, Location2D end) {
		super(LightingType.LedStrip, false, identifier, start, end);
	}

}
