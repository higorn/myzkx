package br.com.sisnunes.myzkx.services;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by higor on 12/03/15.
 */
public interface Dao<T>
{
	public List<T> queryAll(String queryName);
	public List<T> queryAll(Class type);
	public T getObjectBy(Class type, String paramName, Object param);
	public List getObjectByNamedQuery(Class type, String queryName, String paramName, Object param);
	public T get(Class type, Object id);
	public T add(Object obj);
	public T save(Object obj);
	public void delete(Object obj);
	public boolean contains(Object obj);
	public EntityManager getEntityManager();
}
