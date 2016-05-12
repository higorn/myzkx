package br.com.sisnunes.myzkx.services;

import br.com.sisnunes.myzkx.Module;
import br.com.sisnunes.myzkx.ModuleLoader;
import br.com.sisnunes.myzkx.control.Controle;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Tabbox;

import java.util.List;

/**
 * Created by higor on 12/03/15.
 */
public interface BasicService<T> extends EventListener<Event>
{
	public List<Listheader> getColumnHeaders();
//		public List<Object> getVisibleitems();
	public List<T> getAll();
  public List getAll(Class klass);
	public T add(Object o) throws Exception;
	public T save(Object o) throws Exception;
	public void del(Object o);
	public ListitemRenderer<T> getItemRenderer(Controle ctrl);
	public DefaultFieldFactory getFieldFactory();
	public Dao<T> getDao();
	public boolean contains(Object o);
	public Class getType();
}
