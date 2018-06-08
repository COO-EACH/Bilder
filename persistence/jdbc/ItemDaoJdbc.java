package persistence.jdbc;

import java.sql.SQLException;
import java.util.LinkedList;

import persistence.PersistenceException;
import util.Log;
import dto.Item;

public abstract class ItemDaoJdbc extends JdbcConnector {

	public ItemDaoJdbc() throws PersistenceException {
		super();
	}
	
	protected LinkedList<Item> listaItens() throws PersistenceException {
		LinkedList<Item> itens = new LinkedList<Item>();
		openConnection();
		prepareSqlCommand("select codigo, " + "qtdExemplaresDisponiveis, "
				+ "qtdExemplaresEmprestados, " + "qtdTotalExemplares "
				+ "from Item");

		try {
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int codigo = rs.getInt(1);
				int qtdExemplaresDisponiveis = rs.getInt(2);
				int qtdExemplaresEmprestados = rs.getInt(3);
				int qtdTotalExemplares = rs.getInt(4);
				Item item = new Item(qtdTotalExemplares,
						qtdExemplaresDisponiveis, qtdExemplaresEmprestados,
						codigo);
				itens.add(item);
			}
		} catch (SQLException e) {
			Log.gravaLog(e);
			throw new PersistenceException(
					"Problemas ao ler o resultado da consulta.");
		}

		closeConnection();
		return itens;
	}
	
	protected void insereItem(Item item) throws PersistenceException {
		openConnection();
		prepareSqlCommand("insert into Item (qtdTotalExemplares, qtdExemplaresDisponiveis, qtdExemplaresEmprestados, codigo) values (?, ?, ?, ?)");

		try {
			pstmt.setInt(1, item.getQtdTotalExemplares());
			pstmt.setInt(2, item.getQtdExemplaresDisponiveis());
			pstmt.setInt(3, item.getQtdExemplaresEmprestados());
			pstmt.setInt(4, item.getId());
			pstmt.execute();
		} catch (SQLException e) {
			Log.gravaLog(e);
			throw new PersistenceException(
					"Erro ao setar os parâmetros da consulta.");
		}

		closeConnection();
	}
}
