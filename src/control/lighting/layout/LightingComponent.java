package control.lighting.layout;

import java.awt.Color;

public class LightingComponent {

	private LightingType type;
	private Color col;
	private Location2D start, end;
	private String identifier;
	
	public LightingComponent(LightingType type, String identifier, Location2D start, Location2D end) {
		this.type = type;
		this.identifier = identifier;
		this.start = start;
		this.end = end;
	}

	public LightingType getType() {
		return type;
	}

	public void setType(LightingType type) {
		this.type = type;
	}

	public Color getCol() {
		return col;
	}

	public void setCol(Color col) {
		this.col = col;
	}

	public Location2D getStart() {
		return start;
	}

	public void setStart(Location2D start) {
		this.start = start;
	}

	public Location2D getEnd() {
		return end;
	}

	public void setEnd(Location2D end) {
		this.end = end;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
}
