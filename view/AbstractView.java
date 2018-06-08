package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.FrontController;
import controller.Validador;

public abstract class AbstractView {
	protected Validador validador = new Validador();
	protected BackgroundView backgroundView;
	protected FrontController controller;
	
	protected AbstractView(BackgroundView backgroundView, FrontController controller) {
		this.backgroundView = backgroundView;
		this.controller = controller;
	}

	protected void adicionaNoCentroDaLinha(Container painel,
			Component componente, int descolamentoTopo) {
		Insets insetsPainel = painel.getInsets();
		painel.add(componente);
		Dimension d = componente.getPreferredSize();
		int deslocamento = (painel.getWidth() - d.width) / 2;
		componente.setBounds(insetsPainel.left + deslocamento,
				insetsPainel.right + descolamentoTopo, d.width, d.height);
	}

	protected int calcRowPosition(int row) {
		return 50 + 30 * row;
	}

	protected void adicionaCampoComRotulo(JPanel painel, String lblText,
			Component field, int row) {
		JLabel lbl = new JLabel();
		lbl.setFont(new Font("Microsoft Sans Serif", 0, 13));
		lbl.setText(lblText);
		Insets insetsPainel = painel.getInsets();
		painel.add(lbl);
		painel.add(field);
		int fimLabel = 150;
		Dimension dimLbl = lbl.getPreferredSize();
		int deslocamentoEsq = fimLabel - dimLbl.width;
		int descolamentoTopo = calcRowPosition(row);
		int tamanhoField = 230;
		lbl.setBounds(insetsPainel.left + deslocamentoEsq, insetsPainel.top
				+ descolamentoTopo, dimLbl.width, dimLbl.height);

		field.setBounds(insetsPainel.left + fimLabel + 5, insetsPainel.top
				+ descolamentoTopo - 4, tamanhoField, dimLbl.height + 10);
	}

	protected void adicionaPainel(JPanel painel, String titulo, Color cor) {
		painel.setLayout(null);
		painel.setBounds(0, 90, 500, 410);
		painel.setBackground(cor);
		backgroundView.addView(painel);
		JLabel lbl = new JLabel();
		lbl.setFont(new Font("Microsoft Sans Serif", 2, 18));
		lbl.setText(titulo);
		adicionaNoCentroDaLinha(painel, lbl, 30);
	}

	protected void adicionaCamposComRotulos(JPanel painel, String[] lblText,
			Component[] field) {
		for (int row = 0; row < lblText.length; row++) {
			adicionaCampoComRotulo(painel, lblText[row], field[row], row + 1);
		}
	}

	protected void adicionaBotao(AbstractView refObjEvent, JPanel painel, String btnText, int row,
			String actionListenerName) {
		JButton btn = new JButton();
		painel.add(btn);
		btn.setText(btnText);
		adicionaNoCentroDaLinha(painel, btn, calcRowPosition(row));
		addActionListener(refObjEvent, btn, actionListenerName);
	}

	protected void insereItemNoMenu(AbstractView refObjEvent, JMenuItem item, JMenu menu, String texto,
			String actionListenerName) {
		menu.add(item);
		item.setText(texto);
		addActionListener(refObjEvent, item, actionListenerName);
	}

	protected void addActionListener(AbstractView refObjEvent, AbstractButton btn,
			String actionListenerName) {
		
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					Method method = refObjEvent.getClass().getMethod(
							actionListenerName, ActionEvent.class);
					method.invoke(refObjEvent, evt);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected void adicionaTabela(JPanel painel, JTable tabela) {
		JScrollPane scrollPane = new JScrollPane(tabela);
		tabela.setBounds(0, 90, 400, 0);
		scrollPane.setBounds(50, 90, 400, 200);
		painel.add(scrollPane);
	}

	public abstract void initComponents();
	
	public abstract void show();
}
