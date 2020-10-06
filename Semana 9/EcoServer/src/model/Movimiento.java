package model;

public class Movimiento {
	
	//Metadata
	private String type = "Movimiento";
	
	private int x,y;
	
	
	
	public Movimiento() {}

	public Movimiento(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	
	

}
