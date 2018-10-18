package game;

public class Winner {

	public Winner(Color color, int numDisks) {
		super();
		this.color = color;
		this.numDisks = numDisks;
	}
	private Color color;
	private int numDisks;
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getNumDisks() {
		return numDisks;
	}
	public void setNumDisks(int numDisks) {
		this.numDisks = numDisks;
	}
}
