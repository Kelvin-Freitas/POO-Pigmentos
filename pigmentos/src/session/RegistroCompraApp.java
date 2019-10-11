package session;

import exceptions.PigmentoException;

public interface RegistroCompraApp {
	public void registrarCompra(String corUser, double litrosUser) throws PigmentoException;
	public String buscarCor(String corUser, double litrosUser) throws PigmentoException;
	public double getValor(String corUser, double litrosUser) throws PigmentoException;
}
