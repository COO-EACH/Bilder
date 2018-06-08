package dto;
public class Lend {

	private Item item;
	private User usuario;
	private boolean finalizado;
	private int codigo;
	
	public Lend(Item item, User usuario, boolean finalizado) {
		this.item = item;
		this.usuario = usuario;
		this.finalizado = finalizado;
	}

	public Lend(Item item, User usuario) {
		this.item = item;
		this.usuario = usuario;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
