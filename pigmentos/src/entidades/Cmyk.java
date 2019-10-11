package entidades;

public class Cmyk extends Pigmento {
	private int cyan;
	private int magenta;
	private int yellow;
	private int keyBlack;
	
	public Cmyk() {
		super();
	}
	
	public Cmyk (int c, int m, int y, int k) {
		this.cyan = c;
		this.magenta = m;
		this.yellow = y;
		this.keyBlack = k;
	}
	
	public Cmyk(String id, String nome, double litros, double preco, int c, int m, int y, int k) {
		super(id, nome, litros, preco);
		this.cyan = c;
		this.magenta = m;
		this.yellow = y;
		this.keyBlack = k;
	}

	public int getCyan() {
		return cyan;
	}
	public int getMagenta() {
		return magenta;
	}
	public int getYellow() {
		return yellow;
	}
	public int getKeyBlack() {
		return keyBlack;
	}
	
}
