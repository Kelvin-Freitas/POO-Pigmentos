package entidades;

public abstract class Pigmento {
	private String id;
	private String nome;
	private double litros;
	private double preco;
	
	public Pigmento() {
		super();
	}

	public Pigmento(String id, String nome, double litros, double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.litros = litros;
		this.preco = preco;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getLitros() {
		return litros;
	}
	public void setLitros(double litros) {
		this.litros = litros;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public void atualizarLitros(double litros) {
		this.litros = this.litros-litros;
	}
	
}