package persistence.jdbc;

import java.util.List;

import persistence.LendDao;
import persistence.PersistenceException;
import dto.Lend;

public class  LendDaoJdbc extends JdbcConnector implements LendDao {
	
	public LendDaoJdbc() throws PersistenceException {
		super();
	}

	@Override
	public void insert(Lend land) throws PersistenceException {
		throw new PersistenceException("Método não implementado");
	}

	@Override
	public void update(Lend land) throws PersistenceException {
		throw new PersistenceException("Método não implementado");
	}

	@Override
	public Lend search(int id) throws PersistenceException {
		throw new PersistenceException("Método não implementado");
	}

	@Override
	public List<Lend> listAll() throws PersistenceException {
		throw new PersistenceException("Método não implementado");
	}
}
