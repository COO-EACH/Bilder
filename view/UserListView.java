package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.FrontController;
import controller.Dispatcher.Request;
import dto.User;

public class UserListView extends AbstractView {

	private JPanel painelListaUsuarios = new JPanel();
	private JTable tabelaUsuarios = new JTable(new DefaultTableModel(
			new Object[] { "Código", "Nome" }, 0));

	public UserListView(BackgroundView backgroundView,
			FrontController controller) {
		super(backgroundView, controller);
	}

	@Override
	public void initComponents() {
		insereItemNoMenu(this, new JMenuItem(), backgroundView.getMenuListar(),
				"Usuário", "mostraPainel");

		adicionaPainel(painelListaUsuarios, "Usuários Cadastrados", new Color(
				255, 154, 154));
		adicionaTabela(painelListaUsuarios, tabelaUsuarios);
	}

	@Override
	public void show() {
		backgroundView.mostraPainel(painelListaUsuarios);
	}

	public void montaTabelaUsuarios(List<User> usuarios) {
		DefaultTableModel model = (DefaultTableModel) tabelaUsuarios.getModel();

		while (model.getRowCount() > 0)
			model.removeRow(0);
		
		for (User usuario : usuarios) {
			model.addRow(new Object[] { usuario.getCodigo(), usuario.getNome() });
		}
	}

	public void mostraPainel(ActionEvent evt) {
		controller.dispatchRequest(Request.USER_LIST, null);
	}
}
