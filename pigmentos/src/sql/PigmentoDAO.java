package sql;

import java.util.Collection;

import entidades.Pigmento;
import exceptions.PigmentoException;

public interface PigmentoDAO {
	public void save(Pigmento p) throws PigmentoException;
	public Pigmento findPigmentoByNome(String nome) throws PigmentoException;
	public void update(Pigmento c) throws PigmentoException;
	public Collection<Pigmento> findAll() throws PigmentoException;
	public Collection<Pigmento> findAllCondition(double litrosUser) throws PigmentoException;
}