package controller;

import java.util.List;

import model.BusinessException;
import model.GerenciadorRegrasNegocio;
import controller.Dispatcher.Request;
import dto.User;

public class UserController {
	private GerenciadorRegrasNegocio gerenciadorRegrasNegocio;
	private Dispatcher dispatcher;
	private FrontController controller;

	public UserController(FrontController controller, Dispatcher dispatcher,
			GerenciadorRegrasNegocio gerenciadorRegrasNegocio) {
		this.gerenciadorRegrasNegocio = gerenciadorRegrasNegocio;
		this.dispatcher = dispatcher;
		this.controller = controller;
	}

	public void listAll() throws BusinessException {
		List<User> users = gerenciadorRegrasNegocio.listaUsuarios();
		dispatcher.userListView.montaTabelaUsuarios(users);
		dispatcher.dispach(Request.USER_LIST);
	}

	public void register(String name, String titulo, String autor, int id) throws BusinessException {
		gerenciadorRegrasNegocio.cadastraUsuario(name, id);
		listAll();
		//ou:
		//controller.dispatchRequest(Request.USER_LIST, null);
	}
}
