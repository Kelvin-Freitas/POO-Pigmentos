package sql;

import java.util.Collection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidades.Cmyk;
import entidades.Pigmento;
import entidades.Rgb;
import exceptions.PigmentoException;

public class PigmentoSQLDAO extends AbstractSQLDAO implements PigmentoDAO{

	private static final String INSERT_PIGMENTO = 
			"INSERT INTO PIGMENTO(id, nome, estoque, preco, tipo, r, g, b, c, m, y, k) " +
			"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String SELECT_PIGMENTO_NOME = 
			"SELECT * FROM PIGMENTO " +
			" WHERE nome = ?";
	
	private static final String UPDATE_PIGMENTO = 
			"UPDATE PIGMENTO " + 
			"SET estoque = ? " +
			" WHERE nome = ?";
	
	private static final String SELECT_PIGMENTO = 
			"SELECT * FROM PIGMENTO";
	
	private static final String SELECT_PIGMENTO_CONDICAO = 
			"SELECT * FROM PIGMENTO" +
			" WHERE estoque >= ?";
	
	@Override
	public void save(Pigmento p) throws PigmentoException{
		// TODO Auto-generated method stub
		try {
			PreparedStatement stmt = this.getConnection().prepareStatement(PigmentoSQLDAO.INSERT_PIGMENTO);
			stmt.setString(1, p.getId());
			stmt.setString(2, p.getNome());
			stmt.setDouble(3, p.getLitros());
			stmt.setDouble(4, p.getPreco());
			stmt.setInt(5, this.typeToInt(p));
			if(this.typeToInt(p)==1) {
				Rgb rgb = (Rgb)p;
				stmt.setInt(6, rgb.getRed());
				stmt.setInt(7, rgb.getGreen());
				stmt.setInt(8, rgb.getBlue());
				stmt.setNull(9, java.sql.Types.INTEGER);
				stmt.setNull(10, java.sql.Types.INTEGER);
				stmt.setNull(11, java.sql.Types.INTEGER);
				stmt.setNull(12, java.sql.Types.INTEGER);
			}else if(this.typeToInt(p)==2) {
				Cmyk cmyk = (Cmyk)p;
				stmt.setNull(6, java.sql.Types.INTEGER);
				stmt.setNull(7, java.sql.Types.INTEGER);
				stmt.setNull(8, java.sql.Types.INTEGER);
				stmt.setInt(9, cmyk.getCyan());
				stmt.setInt(10, cmyk.getMagenta());
				stmt.setInt(11, cmyk.getYellow());
				stmt.setInt(12, cmyk.getKeyBlack());
			}
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new PigmentoException(e);
		} catch (InstantiationException e) {
			throw new PigmentoException(e);
		} catch (IllegalAccessException e) {
			throw new PigmentoException(e);
		} catch (ClassNotFoundException e) {
			throw new PigmentoException(e);
		}
	}

	@Override
	public Pigmento findPigmentoByNome(String nome) throws PigmentoException {
		Pigmento p = null;
		try {
			PreparedStatement stmt = this.getConnection().prepareStatement(PigmentoSQLDAO.SELECT_PIGMENTO_NOME);
			stmt.setString(1, nome);
			ResultSet rSet = stmt.executeQuery();
			if(rSet.next()) {
				p = this.intToType(rSet.getInt("tipo"));
				p.setId(rSet.getString("id"));
				p.setNome(rSet.getString("nome"));
				p.setLitros(rSet.getDouble("estoque"));
				p.setPreco(rSet.getDouble("preco"));
				if(this.typeToInt(p)==1) {
					
				}
			}
		} catch (SQLException e) {
			throw new PigmentoException(e);
		} catch (InstantiationException e) {
			throw new PigmentoException(e);
		} catch (IllegalAccessException e) {
			throw new PigmentoException(e);
		} catch (ClassNotFoundException e) {
			throw new PigmentoException(e);
		}
		return p;
	}

	@Override
	public void update(Pigmento p) throws PigmentoException {
		// TODO Auto-generated method stub
		try {
			PreparedStatement stmt = this.getConnection().prepareStatement(PigmentoSQLDAO.UPDATE_PIGMENTO);
			stmt.setDouble(1, p.getLitros());
			stmt.setString(2, p.getNome());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new PigmentoException(e);
		} catch (InstantiationException e) {
			throw new PigmentoException(e);
		} catch (IllegalAccessException e) {
			throw new PigmentoException(e);
		} catch (ClassNotFoundException e) {
			throw new PigmentoException(e);
		}
	}

	@Override
	public Collection<Pigmento> findAll() throws PigmentoException {
		// TODO Auto-generated method stub
		List<Pigmento> pigmentos = new ArrayList<Pigmento>();
		try {
			PreparedStatement stmt = this.getConnection().prepareStatement(PigmentoSQLDAO.SELECT_PIGMENTO);
			ResultSet rSet = stmt.executeQuery();
			while(rSet.next()) {
				Pigmento p;
				p = this.intToType(rSet.getInt("tipo"));
				p.setId(rSet.getString("id"));
				p.setNome(rSet.getString("nome"));
				p.setLitros(rSet.getDouble("estoque"));
				p.setPreco(rSet.getDouble("preco"));
				pigmentos.add(p);
			}
		} catch (SQLException e) {
			throw new PigmentoException(e);
		} catch (InstantiationException e) {
			throw new PigmentoException(e);
		} catch (IllegalAccessException e) {
			throw new PigmentoException(e);
		} catch (ClassNotFoundException e) {
			throw new PigmentoException(e);
		}
		return pigmentos;
	}
	
	private int typeToInt(Pigmento p) {
		if(p.getClass() == Rgb.class)
			return 1;
		else if(p.getClass() == Cmyk.class)
			return 2;
		return 0;
	}
	
	private Pigmento intToType(int i) {
		if(i == 1)
			return new Rgb();
		if(i == 2)
			return new Cmyk();
		return null;
	}

	@Override
	public Collection<Pigmento> findAllCondition(double litrosUser) throws PigmentoException {
		List<Pigmento> pigmentos = new ArrayList<Pigmento>();
		try {
			PreparedStatement stmt = this.getConnection().prepareStatement(PigmentoSQLDAO.SELECT_PIGMENTO_CONDICAO);
			stmt.setDouble(1, litrosUser);
			ResultSet rSet = stmt.executeQuery();
			while(rSet.next()) {
				Pigmento p;
				p = this.intToType(rSet.getInt("tipo"));
				if(p.getClass()==Rgb.class) {
					Rgb novo = new Rgb(rSet.getString("id"),rSet.getString("nome"),rSet.getDouble("estoque"),rSet.getDouble("preco"),rSet.getInt("r"),rSet.getInt("g"),rSet.getInt("b"));
					System.out.println(novo.getNome());
					pigmentos.add(novo);
				}else {
					Cmyk novo = new Cmyk(rSet.getString("id"),rSet.getString("nome"),rSet.getDouble("estoque"),rSet.getDouble("preco"),rSet.getInt("c"),rSet.getInt("m"),rSet.getInt("y"),rSet.getInt("k"));
					System.out.println(novo.getNome());
					pigmentos.add(novo);
				}
				
			}
		} catch (SQLException e) {
			throw new PigmentoException(e);
		} catch (InstantiationException e) {
			throw new PigmentoException(e);
		} catch (IllegalAccessException e) {
			throw new PigmentoException(e);
		} catch (ClassNotFoundException e) {
			throw new PigmentoException(e);
		}
		return pigmentos;
	}

}