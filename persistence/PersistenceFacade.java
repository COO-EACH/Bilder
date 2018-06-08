package persistence;

import java.util.List;

import persistence.DaoFactory.DaoType;
import dto.Book;
import dto.BookSearchParms;
import dto.Cd;
import dto.Lend;
import dto.User;

public class PersistenceFacade {

	private BookDao bookDao;
	private CdDao cdDao;
	private LendDao lendDao;
	private UserDao userDao;
	private DaoFactory daoFactory;
	private static PersistenceFacade instance;

	private PersistenceFacade() throws PersistenceException {
		daoFactory = DaoFactory.getInstance(DaoType.JDBC);
		bookDao = daoFactory.getBookDao();
		cdDao = daoFactory.getCdDao();
		lendDao = daoFactory.getLendDao();
		userDao = daoFactory.getUserDao();
	}

	public static PersistenceFacade getInstance() throws PersistenceException {
		return instance == null ? instance = new PersistenceFacade() : instance;
	}

	// ----- BookDAO -----
	public void insert(Book book) throws PersistenceException {
		bookDao.insert(book);
	}

	public List<Book> listAllBooks() throws PersistenceException {
		return bookDao.listAll();
	}

	public Book searchBook(int id) throws PersistenceException {
		return bookDao.search(id);
	}

	public List<Book> searchBook(BookSearchParms parms)
			throws PersistenceException {
		return bookDao.search(parms);
	}

	public void update(Book book) throws PersistenceException {
		bookDao.update(book);
	}

	// ------------------

	// ----- CdDAO -----
	public void insert(Cd cd) throws PersistenceException {
		cdDao.insert(cd);
	}

	public List<Cd> listAllCds() throws PersistenceException {
		return cdDao.listAll();
	}

	public Cd searchCd(int id) throws PersistenceException {
		return cdDao.search(id);
	}

	public void update(Cd cd) throws PersistenceException {
		cdDao.update(cd);
	}

	// ------------------

	// ----- LendDAO -----
	public void insert(Lend lend) throws PersistenceException {
		lendDao.insert(lend);
	}

	public List<Lend> listAllLends() throws PersistenceException {
		return lendDao.listAll();
	}

	public Lend searchLend(int id) throws PersistenceException {
		return lendDao.search(id);
	}

	public void update(Lend lend) throws PersistenceException {
		lendDao.update(lend);
	}

	// ------------------

	// ----- UserDAO -----
	public void insert(User user) throws PersistenceException {
		userDao.insert(user);
	}

	public List<User> listAllUsers() throws PersistenceException {
		return userDao.listAll();
	}

	public User searchUser(int id) throws PersistenceException {
		return userDao.search(id);
	}

	public void update(User user) throws PersistenceException {
		userDao.update(user);
	}
	// ------------------

}
