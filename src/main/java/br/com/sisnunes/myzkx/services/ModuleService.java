/*
 * File:   ModuleService.java
 *
 * Created on 10/02/16, 21:34
 */
package br.com.sisnunes.myzkx.services;

import br.com.sisnunes.myzkx.control.Controle;
import br.com.sisnunes.myzkx.data.Entity;
import br.com.sisnunes.myzkx.ui.annotation.FieldProps;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author higor
 */
public class ModuleService<T extends Entity> implements BasicService<T>
{
  private Dao   _dao;
  private Class _class;

  public ModuleService(Dao dao, Class klass)
  {
    _dao = dao;
    _class = klass;
  }

  public List<Listheader> getColumnHeaders()
  {
    List<Listheader> listheaders = new ArrayList<Listheader>();
    for (Field fld: _class.getDeclaredFields())
    {
      if (fld.isAnnotationPresent(FieldProps.class))
      {
        listheaders.add(new Listheader(fld.getName().toUpperCase()));
      }
    }
    return listheaders;
  }

  public List<T> getAll()
  {
    return _dao.queryAll(_class);
  }

  public List getAll(Class klass)
  {
    return _dao.queryAll(klass);
  }

  public T add(Object o)
  {
    return (T) _dao.add((T)o);
  }

  public T save(Object o)
  {
    if (!contains(o))
    {
      return add(o);
    }
    return (T)_dao.save(o);
  }

  public void del(Object o)
  {
    _dao.delete(o);
  }

  public ListitemRenderer<T> getItemRenderer(Controle ctrl)
  {
    return new DefaultItemRenderer<T>(ctrl);
  }

  public DefaultFieldFactory getFieldFactory()
  {
    return new DefaultFieldFactory(this);
  }

  public Dao<T> getDao()
  {
    return _dao;
  }

  public void setDao(Dao dao) {
    _dao = dao;
  }

  public boolean contains(Object o)
  {
    Object pk = ((T)o).getPk();
    if (pk == null)
      return false;
    Object obj = _dao.get(_class, ((T)o).getPk());
    return obj != null;
  }

  public Class getType()
  {
    return _class;
  }

  public void onEvent(Event event) throws Exception
  {

  }

  class DefaultItemRenderer<T> implements ListitemRenderer<T>
  {
    Controle<T>     _ctrl;

    public DefaultItemRenderer(Controle<T> ctrl)
    {
      _ctrl = ctrl;
    }

    private String toUpperFist(String str)
    {
      String result = str.replaceFirst(str.substring(0,1), str.substring(0,1).toUpperCase());
      return result;
    }

    public void render(Listitem listitem, T t, int i) throws Exception
    {
      for (Field fld: t.getClass().getDeclaredFields())
      {
        if (fld.isAnnotationPresent(FieldProps.class))
        {
          String methodName = "get"+toUpperFist(fld.getName());
          Object val = t.getClass().getMethod(methodName).invoke(t);
          if (val == null)
            val = "";
          listitem.appendChild(new Listcell(val.toString()));
        }
      }
//      Button del = new Button("", "/static/img/cross.png");
//      Button del = new Button();
      Toolbarbutton del = new Toolbarbutton();
      del.setSclass("list-btn-edit fa fa-pencil fa-lg");
      del.addEventListener(Events.ON_CLICK, _ctrl);
      del.setAttribute("entity", t);
      del.setAttribute("index", i);
      Listcell cell = new Listcell();
      cell.appendChild(del);
      listitem.appendChild(cell);
    }
  }
}
