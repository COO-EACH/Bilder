package installation;

import persistence.PersistenceException;
import persistence.jdbc.JdbcConnector;

public class ScriptCriacaoBD extends JdbcConnector {

	public ScriptCriacaoBD() throws PersistenceException {
		super(DB.MYSQL);
	}

	public static void main(String[] args) throws Exception {
		new ScriptCriacaoBD().criaTabelasBD();
	}

	public void criaTabelasBD() throws Exception {
		JdbcConnector.DB_NAME = "";
		openConnection();
		prepareSqlCommand("create database if not exists coo2018_outro");
		pstmt.execute();
		closeConnection();

		JdbcConnector.DB_NAME = "coo2018_outro";

		openConnection();
		prepareSqlCommand("create table Item ("
				+ "codigo  int unsigned not null primary key,"
				+ "qtdTotalExemplares int unsigned not null,"
				+ "qtdExemplaresDisponiveis int unsigned not null,"
				+ "qtdExemplaresEmprestados int unsigned not null" + ");");
		pstmt.execute();
		closeConnection();

		openConnection();
		prepareSqlCommand("create table Livro ("
				+ "autores varchar(100) not null,"
				+ "titulo varchar(50) not null,"
				+ "codigo int unsigned not null,"
				+ "constraint fk_Livro_Item FOREIGN KEY (codigo) REFERENCES Item (codigo)"
				+ ");");
		pstmt.execute();
		closeConnection();

		openConnection();
		prepareSqlCommand("create table CD ("
				+ "artista varchar(100) not null,"
				+ "album varchar(50) not null,"
				+ "codigo int unsigned not null,"
				+ "constraint fk_CD_Item FOREIGN KEY (codigo) REFERENCES Item (codigo)"
				+ ");");
		pstmt.execute();
		closeConnection();

		openConnection();
		prepareSqlCommand("create table Usuario ("
				+ "codigo int unsigned not null primary key,"
				+ "nome varchar(50) not null" + ");");
		pstmt.execute();
		closeConnection();

		openConnection();
		prepareSqlCommand("create table Emprestimo ("
				+ "codigoEmprestimo  int unsigned not null auto_increment primary key,"
				+ "codigoItem int unsigned not null,"
				+ "codigoUsuario int unsigned not null,"
				+ "finalizado boolean not null,"
				+ "constraint fk_Emprestimo_Item FOREIGN KEY (codigoItem) REFERENCES Item (codigo),"
				+ "constraint fk_Emprestimo_Usuario FOREIGN KEY (codigoUsuario) REFERENCES Usuario (codigo)"
				+ ");");
		pstmt.execute();
		closeConnection();
	}
}
