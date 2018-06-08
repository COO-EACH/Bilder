package persistence.jdbc;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import persistence.BookDao;
import persistence.PersistenceException;
import util.Log;
import dto.Book;
import dto.BookSearchParms;
import dto.Item;

public class BookDaoJdbc extends ItemDaoJdbc implements BookDao {

	public BookDaoJdbc() throws PersistenceException {
		super();
	}

	@Override
	public void insert(Book book) throws PersistenceException {
		insereItem(book);
		openConnection();
		prepareSqlCommand("insert into Livro (autores, titulo, codigo) values (?, ?, ?)");

		try {
			pstmt.setString(1, book.getAuthors());
			pstmt.setString(2, book.getTitle());
			pstmt.setInt(3, book.getId());
			pstmt.execute();
		} catch (SQLException e) {
			Log.gravaLog(e);
			throw new PersistenceException(
					"Erro ao setar os parâmetros da consulta.");
		}

		closeConnection();
	}

	@Override
	public List<Book> listAll() throws PersistenceException {
		LinkedList<Book> books = new LinkedList<Book>();
		LinkedList<Item> items = listaItens();
		openConnection();
		prepareSqlCommand("select autores, titulo from Livro where codigo = ?");

		for (Item item : items) {
			try {
				pstmt.setInt(1, item.getId());
				rs = pstmt.executeQuery();

				if (rs.next()) {
					String autores = rs.getString(1);
					String titulo = rs.getString(2);
					Book livro = new Book(autores, titulo, item);
					books.add(livro);
				}
			} catch (SQLException e) {
				Log.gravaLog(e);
				throw new PersistenceException(
						"Problemas ao ler o resultado da consulta.");
			}

		}

		closeConnection();
		return books;
	}

	@Override
	public Book search(int id) throws PersistenceException {
		throw new PersistenceException("Método não implementado");
	}

	@Override
	public void update(Book t) throws PersistenceException {
		throw new PersistenceException("Método não implementado");
	}

	@Override
	public List<Book> search(BookSearchParms parms) throws PersistenceException {
		throw new PersistenceException("Método não implementado");
	}
}
