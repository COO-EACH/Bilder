package controller;

import model.BusinessException;
import model.GerenciadorRegrasNegocio;

public class BookController {
	private GerenciadorRegrasNegocio gerenciadorRegrasNegocio;
	private Dispatcher dispatcher;
	private FrontController controller;
	
	public BookController(FrontController controller, Dispatcher dispatcher, GerenciadorRegrasNegocio gerenciadorRegrasNegocio) {
		this.gerenciadorRegrasNegocio = gerenciadorRegrasNegocio;
		this.dispatcher = dispatcher;
		this.controller = controller;
	}
	
	public void listAll() throws BusinessException {
		List<Book> books = gerenciadorRegrasNegocio.listaLivros();
		dispatcher.bookListView.montaTabelaLivros(books);
		dispatcher.dispach(Request.BOOK_LIST);
	}
	
	public void register(String name, int id) throws BusinessException {
		gerenciadorRegrasNegocio.cadastraLivro(titulo, autores, codigo, 1);
		dispatcher.dispach(Request.USER_LIST, null);
	}
}
