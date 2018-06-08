package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Dispatcher.Request;
import controller.FrontController;
import controller.ValidacaoException;

public class BookRegisterView extends AbstractView {

	private JPanel painelCadastrarLivro = new JPanel();
	private JTextField fieldTituloLivro = new JTextField();
	private JTextField fieldCodigoLivro = new JTextField();
	private JTextField fieldAutoresLivro = new JTextField();

	public BookRegisterView(BackgroundView backgroundView, FrontController controller) {
		super(backgroundView, controller);
	}

	@Override
	public void initComponents() {
		insereItemNoMenu(this, new JMenuItem(),
				backgroundView.getMenuInserir(), "Livro",
				"mostraPainel");

		adicionaPainel(painelCadastrarLivro, "Cadastrar Livro", new Color(255,
				154, 255));
		adicionaCamposComRotulos(painelCadastrarLivro, new String[] {
				"Título: ", "Autores: ", "Código: " }, new JTextField[] {
				fieldTituloLivro, fieldAutoresLivro, fieldCodigoLivro });
		adicionaBotao(this, painelCadastrarLivro, "Cadastrar", 4,
				"trataCliqueBotaoCadastrar");
	}
	
	@Override
	public void show() {
		backgroundView.mostraPainel(painelCadastrarLivro);
	}

	public void mostraPainel(ActionEvent evt) {
		show();
	}

	public void trataCliqueBotaoCadastrar(ActionEvent evt) {
		int id;
		String name;
		String autores;
		

		try {
			id = validador.validaFormataInteiro(fieldCodigoLivro.getText(),
					"Código");
			titulo = validador.validaFormataCaixaAlta(fieldTituloLivro.getText(),
					20, "Nome");
			autores = validador.validaFormataCaixaAlta(fieldAutoresLivro.getText(),
					200, "Autores");
			fieldCodigoLivro.setText("");
			fieldTituloLivro.setText("");
			fieldAutoresLivro.setText("");

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("titulo", titulo);
			params.put("id", id);
			params.put("autores", autores);
			controller.dispatchRequest(Request.BOOK_REQUEST, params);

		} catch (ValidacaoException e) {
			JOptionPane
					.showMessageDialog(
							backgroundView.getFrame(),
							"Não foi possível cadastrar o usuário. \n"
									+ e.getMessage());
		}
	}
	}
}
