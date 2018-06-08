package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import controller.FrontController;

public class BackgroundView extends AbstractView {

	private List<JPanel> paineis = new LinkedList<JPanel>();

	private JFrame frame;
	private JMenu menuInserir;
	private JMenu menuListar;
	private JMenuBar menuRaiz = new JMenuBar();
	
	// painel fundo
	protected JLabel lblBibliotecaCOO2017 = new JLabel();
	protected Container painelFundo;

	public JFrame getFrame() {
		return this.frame;
	}

	public JMenu getMenuInserir() {
		return menuInserir;
	}

	public JMenu getMenuListar() {
		return menuListar;
	}

	public BackgroundView(FrontController controller) {
		super(null, controller);
	}

	@Override
	public void initComponents() {
		// configura look-and-feel
		for (UIManager.LookAndFeelInfo info : UIManager
				.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				break;
			}
		}

		frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 500, 500);
		frame.setResizable(false);
		frame.setTitle("Biblioteca COO 2017");

		painelFundo = frame.getContentPane();
		painelFundo.setBounds(0, 0, 500, 500);
		painelFundo.setLayout(null);
		painelFundo.setBackground(new Color(234, 255, 255));

		lblBibliotecaCOO2017.setFont(new Font("Luminari", 3, 36));
		lblBibliotecaCOO2017.setForeground(new Color(0, 153, 153));
		lblBibliotecaCOO2017.setText(" Biblioteca COO 2017 ");
		adicionaNoCentroDaLinha(painelFundo, lblBibliotecaCOO2017, 20);

		criaMenu();
		mostraPainel(null);
		frame.setVisible(true);
	}

	public void addView(JPanel panel) {
		painelFundo.add(panel);
		paineis.add(panel);
	}

	/*
	 * public void inserirCDActionPerformed(ActionEvent evt) {
	 * System.out.println("inserir CD"); }
	 * 
	 * public void listarCDActionPerformed(ActionEvent evt) {
	 * System.out.println("listar CD"); }
	 */
	void mostraPainel(JPanel painel) {
		for (JPanel p : paineis) {
			if (p == painel) {
				p.setVisible(true);
			} else {
				p.setVisible(false);
			}
		}
	}

	private void criaMenu() {
		menuListar = new JMenu();
		menuInserir = new JMenu();
		menuRaiz = new JMenuBar();
		frame.setJMenuBar(menuRaiz);
		menuInserir.setText("Cadastrar");
		menuListar.setText("Listar");
		menuRaiz.add(menuInserir);
		menuRaiz.add(menuListar);
	}

	@Override
	public void show() {

		mostraPainel(null);
	}
}
