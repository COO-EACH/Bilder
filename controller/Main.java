package controller;

import java.awt.EventQueue;

import controller.Dispatcher.Request;

public class Main {

	public static void main(String args[]) throws Exception {
		// mostra a tela em uma outra thread
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FrontController controller = new FrontController();
				controller.dispatchRequest(Request.BACK_GROUND, null);
			}
		});
	}
}
