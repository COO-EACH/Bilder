package persistence.jdbc;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import persistence.PersistenceException;
import persistence.UserDao;
import util.Log;
import dto.User;

public class UserDaoJdbc extends JdbcConnector implements UserDao {

	public UserDaoJdbc() throws PersistenceException {
		super();
	}

	@Override
	public void insert(User usuario) throws PersistenceException {
		openConnection();
		prepareSqlCommand("insert into Usuario (nome, codigo) values (?, ?)");

		try {
			pstmt.setString(1, usuario.getNome());
			pstmt.setInt(2, usuario.getCodigo());
			pstmt.execute();
		} catch (SQLException e) {
			Log.gravaLog(e);
			throw new PersistenceException(
					"Erro ao setar os parâmetros da consulta.");
		}

		closeConnection();
	}

	@Override
	public User search(int codigoUsuario) throws PersistenceException {
		throw new PersistenceException("Método não implementado");
	}

	@Override
	public List<User> listAll() throws PersistenceException {
		LinkedList<User> users = new LinkedList<User>();
		openConnection();
		prepareSqlCommand("select codigo, nome from Usuario");

		try {
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int codigo = rs.getInt(1);
				String nome = rs.getString(2);
				User usuario = new User(nome, codigo);
				users.add(usuario);
			}
		} catch (SQLException e) {
			Log.gravaLog(e);
			throw new PersistenceException(
					"Problemas ao ler o resultado da consulta.");
		}

		closeConnection();
		return users;
	}

	@Override
	public void update(User t) throws PersistenceException {
		throw new PersistenceException("Método não implementado");
	}
}
