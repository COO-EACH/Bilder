package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.FrontController;
import dto.Book;

public class BookListView extends AbstractView {

	private JPanel painelListaLivros = new JPanel();
	private JTable tabelaLivros = new JTable(new DefaultTableModel(
			new Object[] { "Código", "Autores", "Título", }, 0));

	public BookListView(BackgroundView backgroundView, FrontController controller) {
		super(backgroundView, controller);
	}

	@Override
	public void initComponents() {
		insereItemNoMenu(this, new JMenuItem(), backgroundView.getMenuListar(),
				"Livro", "mostraPainel");
		adicionaPainel(painelListaLivros, "Livros Cadastrados", new Color(255,
				255, 154));
		adicionaTabela(painelListaLivros, tabelaLivros);
	}

	@Override
	public void show() {
		backgroundView.mostraPainel(painelListaLivros);
	}

	public void montaTabelaLivros(List<Book> livros) {
		DefaultTableModel model = (DefaultTableModel) tabelaLivros.getModel();

		for (Book livro : livros) {
			model.addRow(new Object[] { livro.getId(), livro.getAuthors(),
					livro.getTitle() });
		}
	}

	public void mostraPainel(ActionEvent evt) {
		/**********************/
		/** FALTA COMPLETAR ***/
		/**********************/
	}
}
