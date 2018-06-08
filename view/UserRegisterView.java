package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.FrontController;
import controller.ValidacaoException;
import controller.Dispatcher.Request;

public class UserRegisterView extends AbstractView {

	private JPanel painelCadastrarUsuario = new JPanel();
	private JTextField fieldNomeUsuario = new JTextField();
	private JTextField fieldCodigoUsuario = new JTextField();

	public UserRegisterView(BackgroundView backgroundView,
			FrontController controller) {
		super(backgroundView, controller);
	}

	@Override
	public void initComponents() {
		insereItemNoMenu(this, new JMenuItem(),
				backgroundView.getMenuInserir(), "Usuário", "mostraPainel");
		adicionaPainel(painelCadastrarUsuario, "Cadastrar Usuário", new Color(
				154, 255, 255));
		adicionaCamposComRotulos(painelCadastrarUsuario, new String[] {
				"Nome: ", "Código: " }, new JTextField[] { fieldNomeUsuario,
				fieldCodigoUsuario });
		adicionaBotao(this, painelCadastrarUsuario, "Cadastrar", 3,
				"trataCliqueBotaoCadastrar");
	}

	@Override
	public void show() {
		backgroundView.mostraPainel(painelCadastrarUsuario);
	}

	public void mostraPainel(ActionEvent evt) {
		show();
	}

	public void trataCliqueBotaoCadastrar(ActionEvent evt) {
		int id;
		String name;

		try {
			id = validador.validaFormataInteiro(fieldCodigoUsuario.getText(),
					"Código");
			name = validador.validaFormataCaixaAlta(fieldNomeUsuario.getText(),
					20, "Nome");
			fieldCodigoUsuario.setText("");
			fieldNomeUsuario.setText("");

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("name", name);
			params.put("id", id);
			controller.dispatchRequest(Request.USER_REGISTER, params);

		} catch (ValidacaoException e) {
			JOptionPane
					.showMessageDialog(
							backgroundView.getFrame(),
							"Não foi possível cadastrar o usuário. \n"
									+ e.getMessage());
		}
	}
}
