package dto;

public class Cd extends Item {
	private String album;
	private String artist;

	public Cd(String album, String artista, int qtdTotalExemplares, int codigo) {
		super(qtdTotalExemplares, qtdTotalExemplares, 0, codigo);
		this.album = album;
		this.artist = artista;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
}
