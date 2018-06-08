package controller;

import java.util.HashMap;

import model.BusinessException;
import model.GerenciadorRegrasNegocio;
import controller.Dispatcher.Request;

public class FrontController {

	private GerenciadorRegrasNegocio gerenciadorRegrasNegocio;
	private Dispatcher dispatcher = new Dispatcher(this);
	private UserController userController;
	private BookController bookController;

	public FrontController() {
		try {
			gerenciadorRegrasNegocio = new GerenciadorRegrasNegocio();
			userController = new UserController(this, dispatcher,
					gerenciadorRegrasNegocio);
			bookController = new BookController(this, dispatcher,
					gerenciadorRegrasNegocio);
		} catch (BusinessException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void dispatchRequest(Request request, HashMap<String, Object> params) {
		try {
			switch (request) {
			case USER_LIST:
				userController.listAll();
				break;

			case USER_REGISTER:
				userController.register(
					(String) params.get("name"),
					(String) params.get("titulo"),
					(String) params.get("autor"),
					(Integer) params.get("id")
				);
				break;

			case BACK_GROUND:
				dispatcher.dispach(request);
				break;

			case BOOK_LIST:
				bookController.listAll();
				break;

			case BOOK_REGISTER:
				bookController.register(
					(String) params.get("name"),
					(Integer) params.get("id")
				);
				break;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
