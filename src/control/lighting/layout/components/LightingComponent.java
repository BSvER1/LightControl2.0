package control.lighting.layout.components;

import java.awt.Color;
import java.io.Serializable;

import control.lighting.layout.Location2D;
import control.main.Driver;

public abstract class LightingComponent implements Serializable {

	private static final long serialVersionUID = 8060160539881045255L;
	
	private LightingType type;
	private Color col;
	private Location2D start, end;
	private String identifier;
	private boolean isGroup;
	private int zone;
	
	public LightingComponent(LightingType type, boolean isGroup, String identifier, Location2D start, Location2D end, int zone) {
		this.type = type;
		this.identifier = identifier;
		this.start = start;
		this.end = end;
		this.isGroup = isGroup;
		this.zone = zone;
	}

	public LightingType getType() {
		return type;
	}

	public void setType(LightingType type) {
		this.type = type;
	}

	public boolean isGroup() {
		return isGroup;
	}

	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
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
		if (getType() != LightingType.LedStrip) {
			Driver.trace("asked for the end of a "+ getType().toString()+". Returning the position instead.");
			return start;
		}
		
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

	public int getZone() {
		return zone;
	}

	public void setZone(int zone) {
		this.zone = zone;
	}
	
	
}
