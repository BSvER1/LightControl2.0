package control.lighting.layout;

public class Location2D {

	private double x, y;
	
	public Location2D() {
		x = 0;
		y = 0;
	}
	
	public Location2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public String toString() {
		return new String("["+x+", "+y+"]");
	}
	
	public Location2D copyOf() {
		return new Location2D(x, y);
	}
	
}
