package control.lighting.sequences;

import java.awt.Color;
import java.io.Serializable;
import java.util.concurrent.ConcurrentSkipListMap;

import control.lighting.layout.components.LightingComponent;
import control.main.Driver;

public abstract class Sequence implements Serializable{

	private int zone;
	
	private int maxTime = -1;
	
	private ConcurrentSkipListMap<ComponentTimePair<LightingComponent, Integer>, Color> sequence;
	
	public Sequence(int zone) {
		sequence = new ConcurrentSkipListMap<ComponentTimePair<LightingComponent, Integer>, Color>();
		this.zone = zone;
	}
	
	public int getSequenceLength() {
		return maxTime;
	}

	public ConcurrentSkipListMap<ComponentTimePair<LightingComponent, Integer>, Color> getSequence() {
		return sequence;
	}
	
	public void clearSequence() {
		sequence.clear();
		
		
		maxTime = -1;
	}
	
	public Color getColor(LightingComponent component, Integer time) {
		if (component.isGroup()) {
			Driver.trace("cant get the colour of grouped components");
			return Color.BLACK;
		}
		ComponentTimePair<LightingComponent, Integer> key = getKey(component, time);
		
		return sequence.get(sequence.floorKey(getKey(component, time)));
	}
	
	public void setColor(LightingComponent component, Integer time, Color col) {
		if (time > maxTime) maxTime = time;
		if (sequence.get(getKey(component, time)) != null) 
			Driver.trace("Replacing "+sequence.get(getKey(component, time))+"@["+component.getIdentifier()+", "+time+"] with "+col.toString());
		sequence.put(getKey(component, time), col); 
	}
	
	private ComponentTimePair<LightingComponent, Integer> getKey(LightingComponent component, Integer time) {
		return new ComponentTimePair<LightingComponent, Integer>(component, time);
	}
	
	public int getZone() {
		return zone;
	}

	public void setZone(int zone) {
		this.zone = zone;
	}
}
