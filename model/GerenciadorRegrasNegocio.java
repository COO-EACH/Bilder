package model;

import java.util.List;

import persistence.PersistenceException;
import persistence.PersistenceFacade;
import util.Log;
import dto.Book;
import dto.Cd;
import dto.Lend;
import dto.User;

public class GerenciadorRegrasNegocio {

	private PersistenceFacade persistenceFacade;

	public GerenciadorRegrasNegocio() throws BusinessException {
		try {
			persistenceFacade = PersistenceFacade.getInstance();
		} catch (PersistenceException e) {
			throw new BusinessException(e);
		}
	}

	public void cadastraUsuario(String nome, int codigo)
			throws BusinessException {
		User usuario = new User(nome, codigo);
		try {
			persistenceFacade.insert(usuario);
		} catch (PersistenceException e) {
			Log.gravaLog(e);
			throw new BusinessException(
					"Problemas no acesso ao banco de dados.");
		}
	}

	public void cadastraLivro(String titulo, String autores, int codigo,
			int qtdExemplares) throws BusinessException {
		Book livro = new Book(autores, titulo, qtdExemplares, qtdExemplares, 0,
				codigo);
		try {
			persistenceFacade.insert(livro);
		} catch (PersistenceException e) {
			Log.gravaLog(e);
			throw new BusinessException(
					"Problemas no acesso ao banco de dados.");
		}
	}

	public void cadastraCD(String album, String artista, int codigo,
			int qtdExemplares) throws BusinessException {
		Cd cd = new Cd(album, artista, qtdExemplares, codigo);
		try {
			persistenceFacade.insert(cd);
		} catch (PersistenceException e) {
			Log.gravaLog(e);
			throw new BusinessException(
					"Problemas no acesso ao banco de dados.");
		}
	}

	public void devolveLivro(int codigoEmprestimo) throws BusinessException {
		try {
			Lend emprestimo = persistenceFacade.searchLend(codigoEmprestimo);
			emprestimo.setFinalizado(true);
			persistenceFacade.update(emprestimo);
		} catch (PersistenceException e) {
			Log.gravaLog(e);
			throw new BusinessException(
					"Problemas no acesso ao banco de dados.");
		}
	}

	public List<User> listaUsuarios() throws BusinessException {
		try {
			return persistenceFacade.listAllUsers();
		} catch (PersistenceException e) {
			Log.gravaLog(e);
			throw new BusinessException(
					"Problemas no acesso ao banco de dados.");
		}
	}

	public List<Book> listaLivros() throws BusinessException {
		try {
			return persistenceFacade.listAllBooks();
		} catch (PersistenceException e) {
			Log.gravaLog(e);
			throw new BusinessException(
					"Problemas no acesso ao banco de dados.");
		}
	}

	public List<Lend> listaEmprestimosEmAbertoDoUsuario(User usuario)
			throws BusinessException {
		return null;
	}
}
