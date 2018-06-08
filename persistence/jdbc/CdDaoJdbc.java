package persistence.jdbc;

import java.sql.SQLException;
import java.util.List;

import persistence.CdDao;
import persistence.PersistenceException;
import util.Log;
import dto.Cd;

public class CdDaoJdbc extends ItemDaoJdbc implements CdDao {

	public CdDaoJdbc() throws PersistenceException {
		super();
	}

	@Override
	public void insert(Cd cd) throws PersistenceException {
		insereItem(cd);
		openConnection();
		prepareSqlCommand("insert into CD (artista, album, codigo) values (?, ?, ?)");

		try {
			pstmt.setString(1, cd.getArtist());
			pstmt.setString(2, cd.getAlbum());
			pstmt.setInt(3, cd.getId());
			pstmt.execute();
		} catch (SQLException e) {
			Log.gravaLog(e);
			throw new PersistenceException(
					"Erro ao setar os parâmetros da consulta.");
		}

		closeConnection();
	}

	@Override
	public Cd search(int id) throws PersistenceException {
		throw new PersistenceException("Método não implementado");
	}

	@Override
	public List<Cd> listAll() throws PersistenceException {
		throw new PersistenceException("Método não implementado");
	}

	@Override
	public void update(Cd t) throws PersistenceException {
		throw new PersistenceException("Método não implementado");
	}

}
