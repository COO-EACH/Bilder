package persistence;

import java.util.List;

import dto.Book;
import dto.BookSearchParms;

public interface BookDao extends Dao<Book> {
	public List<Book> search(BookSearchParms parms) throws PersistenceException;
}
