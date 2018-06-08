package persistence.jdbc;

import persistence.BookDao;
import persistence.CdDao;
import persistence.DaoFactory;
import persistence.LendDao;
import persistence.PersistenceException;
import persistence.UserDao;

public class JdbcDaoFactory extends DaoFactory {

	private static JdbcDaoFactory jdbcDaoFactory;

	private JdbcDaoFactory() {
	}

	public static JdbcDaoFactory getInstance() {
		if (jdbcDaoFactory == null) {
			jdbcDaoFactory = new JdbcDaoFactory();
		}
		return jdbcDaoFactory;
	}

	@Override
	public BookDao createBookDao() throws PersistenceException {
		return new BookDaoJdbc();
	}

	@Override
	public CdDao createCdDao() throws PersistenceException {
		return new CdDaoJdbc();
	}

	@Override
	public LendDao createLendDao() throws PersistenceException {
		return new LendDaoJdbc();
	}

	@Override
	public UserDao createUserDao() throws PersistenceException {
		return new UserDaoJdbc();
	}
}
