package control.lighting.sequences;

import java.io.Serializable;

public final class ComponentTimePair<L, T extends Comparable<T>> implements Serializable{

	private final L component;
	private final T time;
	private final int hashCode;
	
	public ComponentTimePair(L component, T time) {
		this.component = component;
		this.time = time;
		this.hashCode = hashCode(component, time);
	}

	private int hashCode(L component, T time)
    {
        return 31 * component.hashCode() + 79 * time.hashCode();
    }
	
	@Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ComponentTimePair<?,?>)) return false;
        ComponentTimePair<?,?> other = (ComponentTimePair<?,?>) obj;
        if (!time.equals(other.time)) return false;
        if (!component.equals(other.component)) return false;
        return true;
    }
	
	public T getTime() {
		return time;
	}

	public L getComponent() {
		return component;
	}
	
}
