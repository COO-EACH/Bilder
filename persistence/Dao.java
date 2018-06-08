package persistence;

import java.util.List;

public interface Dao<T> {

	public void insert(T t) throws PersistenceException;

	public List<T> listAll() throws PersistenceException;

	public T search(int id) throws PersistenceException;

	public void update(T t) throws PersistenceException;

}
