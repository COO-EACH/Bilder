package controller;

import view.BackgroundView;
import view.BookListView;
import view.BookRegisterView;
import view.UserListView;
import view.UserRegisterView;

public class Dispatcher {
	BackgroundView backgroundView;
	BookListView bookListView;
	BookRegisterView bookRegisterView;
	UserListView userListView;
	UserRegisterView userRegisterView;

	public enum Request {
		BOOK_LIST, BOOK_REGISTER, USER_LIST, USER_REGISTER, BACK_GROUND;
	}

	public Dispatcher(FrontController controller) {
		backgroundView = new BackgroundView(controller);
		backgroundView.initComponents();

		bookListView = new BookListView(backgroundView, controller);
		bookRegisterView = new BookRegisterView(backgroundView, controller);
		userListView = new UserListView(backgroundView, controller);
		userRegisterView = new UserRegisterView(backgroundView, controller);

		bookListView.initComponents();
		bookRegisterView.initComponents();
		userListView.initComponents();
		userRegisterView.initComponents();
	}

	public void dispach(Request request) {
		switch (request) {
		case BOOK_LIST:
			bookListView.show();
			break;

		case BOOK_REGISTER:
			bookRegisterView.show();
			break;

		case USER_LIST:
			userListView.show();
			break;

		case USER_REGISTER:
			userRegisterView.show();
			break;

		case BACK_GROUND:
			backgroundView.show();
			break;

		}
	}
}
