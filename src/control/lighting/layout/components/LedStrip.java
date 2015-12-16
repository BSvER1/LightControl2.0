package control.lighting.layout.components;

import control.lighting.layout.Location2D;

public class LedStrip extends LightingComponent {

	private static final long serialVersionUID = -4339036994318040982L;

	public LedStrip(String identifier, Location2D start, Location2D end, int zone) {
		super(LightingType.LedStrip, false, identifier, start, end, zone);
	}

}
