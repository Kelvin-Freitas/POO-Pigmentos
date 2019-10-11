package entidades;
import java.lang.String;

public class HexadecimalColor {
	private String cor;
	
	public HexadecimalColor (String cor) {
		this.cor = cor;
	}	

	public String getRed () {
		return this.cor.substring(0,1);
	}
	
	public String getGreen () {
		return this.cor.substring(2,3);
	}
	
	public String getBlue () {
		return this.cor.substring(4,5);
	}
	
	public String getTodos () {
		return this.cor;
	}
}
