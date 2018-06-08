package dto;
public class Book extends Item {
	private String authors;
	private String title;

	public Book(String autores, String titulo, int qtdTotalExemplares,
			int qtdExemplaresDisponiveis, int qtdExemplaresEmprestados,
			int codigo) {
		super(qtdTotalExemplares, qtdExemplaresDisponiveis,
				qtdExemplaresEmprestados, codigo);
		this.authors = autores;
		this.title = titulo;
	}

	public Book(String autores, String titulo, Item item) {
		this(autores, titulo, item.getQtdTotalExemplares(), item
				.getQtdExemplaresDisponiveis(), item
				.getQtdExemplaresEmprestados(), item.getId());
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
