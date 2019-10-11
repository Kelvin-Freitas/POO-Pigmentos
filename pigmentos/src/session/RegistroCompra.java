package session;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import conversor.cores.ConversorCoresApp;
import conversor.cores.ServicoConversorCores;
import entidades.Cmyk;
import entidades.HexadecimalColor;
import entidades.Pigmento;
import entidades.Rgb;
import exceptions.PigmentoException;
import sql.PigmentoDAO;
import sql.PigmentoSQLDAO;

public class RegistroCompra implements RegistroCompraApp{
	private PigmentoDAO pigmentoDAO;
	
	public RegistroCompra() throws ClassNotFoundException, IOException {
		  this.pigmentoDAO = new PigmentoSQLDAO();
	  }

	@Override
	public String buscarCor(String corUser, double litrosUser) throws PigmentoException {
		ServicoConversorCores conversor = new ConversorCoresApp();
		
		Rgb corUserRGB = conversor.htmlToRgb(new HexadecimalColor(corUser));
		
		Collection<Pigmento> pigmentos = this.pigmentoDAO.findAllCondition(litrosUser);
		
		Pigmento menor = null;
		if(!pigmentos.isEmpty()) {
			Pigmento pigmento;
			double menorDistancia = Double.MAX_VALUE;
			Iterator<Pigmento> itr = pigmentos.iterator();
			
			while(itr.hasNext()){
				pigmento = itr.next();
				if(pigmento instanceof Rgb) {
					double valor = corUserRGB.calcularDistancia((Rgb)pigmento);
					if(valor < menorDistancia) {
						menorDistancia = valor;
						menor = pigmento;
					}
				}else if(pigmento instanceof Cmyk) {
					Rgb novo = conversor.cmykToRgb((Cmyk)pigmento);
					double valor = corUserRGB.calcularDistancia(novo);
					if(valor < menorDistancia) {
						menorDistancia = valor;
						menor = pigmento;
					}
				}
			}
			
		}
		
		
		return menor.getNome();
	}

	@Override
	public void registrarCompra(String corUser, double litrosUser) throws PigmentoException {
		Pigmento pigmento = this.pigmentoDAO.findPigmentoByNome(corUser);
		if(pigmento.getLitros() >= litrosUser) {
			pigmento.atualizarLitros(litrosUser);
			this.pigmentoDAO.update(pigmento);
		}else {
			throw new PigmentoException();
		}
	}
	
	@Override
	public double getValor(String corUser, double litrosUser) throws PigmentoException {
		Pigmento pigmento = this.pigmentoDAO.findPigmentoByNome(corUser);
		double valor;
		valor= litrosUser*pigmento.getPreco();
		return valor;
	}
	
	public void asm() throws PigmentoException{
		//o banco já deve ir com cores cadastradas
		//exemplo de como criar as cores para inserir no banco
		//cada NOME, ID são únicos na tabela, sendo ID a chave primaria
		Rgb p1 = new Rgb("Alpha42B","Especiaria antiga",32,7.50,180,108,114);
		Rgb p2 = new Rgb("Beta10A","DeepSkyBlue",40,9.50,0,191,255);
		Rgb p3 = new Rgb("Omega50C","LightCoral",78,6.70,240,128,128);
		this.pigmentoDAO.save(p1);
		this.pigmentoDAO.save(p2);
		this.pigmentoDAO.save(p3);
	}
}
