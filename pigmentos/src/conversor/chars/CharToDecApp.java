package conversor.chars;

public class CharToDecApp {
	//Servico de Conversao de Char para Decimal
	
	private int resultado;
	
	public CharToDecApp () {
	}
	
	public CharToDecApp (char carac) {
		this.resultado = this.converter(carac);
	}
	
	public int getResultado () {
		return this.resultado;
	}

	public int converter(char carac) {
		if (carac >= 48 && carac <= 57) {
			this.resultado = (int) carac - 48;
		}
		else if (carac >= 97 && carac <= 102) {
			this.resultado = (int) carac - 87;
		}
		else if (carac >= 65 && carac <= 70) {
			this.resultado = (int) carac - 55;
		}
		/*
		else {
			//retorne um erro aqui
			return 0;
		}
		*/

		return this.resultado;
	}
}
