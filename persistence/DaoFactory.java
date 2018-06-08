package persistence;

import persistence.jdbc.JdbcDaoFactory;

public abstract class DaoFactory {

	public enum DaoType {
		JDBC, FLAT_FILES, MAIN_FRAME;
	}

	private static BookDao bookDao;
	private static CdDao cdDao;
	private static LendDao lendDao;
	private static UserDao userDao;

	public static DaoFactory getInstance(DaoType daoType)
			throws PersistenceException {
		if (DaoType.JDBC.equals(daoType)) {
			return JdbcDaoFactory.getInstance();
		} else {
			throw new PersistenceException(
					"DAO especificado não está implementado.");
		}
	}

	public BookDao getBookDao() throws PersistenceException {
		if (bookDao == null) {
			bookDao = createBookDao();
		}

		return bookDao;
	}

	public CdDao getCdDao() throws PersistenceException {
		if (cdDao == null) {
			cdDao = createCdDao();
		}

		return cdDao;

	}

	public LendDao getLendDao() throws PersistenceException {
		if (lendDao == null) {
			lendDao = createLendDao();
		}

		return lendDao;
	}

	public UserDao getUserDao() throws PersistenceException {
		if (userDao == null) {
			userDao = createUserDao();
		}

		return userDao;
	}

	protected abstract BookDao createBookDao() throws PersistenceException;

	protected abstract CdDao createCdDao() throws PersistenceException;

	protected abstract LendDao createLendDao() throws PersistenceException;

	protected abstract UserDao createUserDao() throws PersistenceException;
}
