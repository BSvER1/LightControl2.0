package control.lighting.layout;

import java.awt.Color;

import control.lighting.layout.components.LightingComponent;
import control.lighting.layout.components.LightingType;

public interface LayoutEnum {
	
	default Integer getRedChannel() {
		return getChannelStart();
	}
	
	default Integer getGreenChannel() {
		return getChannelStart()+2;
	}
	
	default Integer getBlueChannel() {
		return getChannelStart()+1;
	}
	
	default int getRedValue() {
		return getComponent().getCol().getRed();
	}
	
	default int getGreenValue() {
		return getComponent().getCol().getGreen();
	}

	default int getBlueValue() {
		return getComponent().getCol().getBlue();
	}
	
	default void setCol(Color col) {
		getComponent().setCol(col);
	}

	default String getIdentifier() {
		return getComponent().getIdentifier();
	}

	default void setIdentifier(String identifier) {
		getComponent().setIdentifier(identifier);
	}
	
	default LightingType getType() {
		return getComponent().getType();
	}

	default void setType(LightingType type) {
		getComponent().setType(type);
	}

	default Location2D getStart() {
		return getComponent().getStart();
	}

	default void setStart(Location2D start) {
		getComponent().setStart(start);
	}

	default Location2D getEnd() {
		return getComponent().getEnd();
	}

	default void setEnd(Location2D end) {
		getComponent().setEnd(end);
	}

	//public void setChannelStart(Integer channelStart);

	public int getChannelStart();
	
	public LightingComponent getComponent();
	
	public int getNumComponents(); 
}
