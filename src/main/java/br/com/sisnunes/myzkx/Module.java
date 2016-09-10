package br.com.sisnunes.myzkx;

import br.com.sisnunes.myzkx.control.Controle;
import br.com.sisnunes.myzkx.services.BasicService;
import br.com.sisnunes.myzkx.ui.ViewCreator;
import org.zkoss.zk.ui.Component;

/**
 * Created by higor on 12/03/15.
 */
public class Module<T>
{
	private String			_id;
//	private Dao				_dao;
	private Component 		_rootComp;
  private BasicService  _service;

	public Module()
	{
		this(null, null);
	}

	public Module(String id, Component rootComp)
	{
    this(id, null, rootComp);
	}

  public Module(String id, BasicService service, Component rootComp)
  {
    _id = id;
    _rootComp = rootComp;
    _service = service;
  }

	public void init(ViewCreator vc)
	{
		getControl(vc).show();
	}

	public BasicService<T> getService()
  {
    return _service;
  }

//	public abstract Dao<T> getDao();

	public Controle<T> getControl(ViewCreator vc)
	{
		return new Controle<T>(this, vc, _rootComp);
	}

	public Component getRootComp()
	{
		return _rootComp;
	}

	public void setRootComp(Component rootComp)
	{
		_rootComp = rootComp;
	}

	public String getId()
	{
		return _id;
	}

	public String getCaption()
  {
    return _id;
  }
/*
	public String getCaption()
	{
		return Controle.getI18n().getString("tree."+_id);
	}
*/

	public void setId(String id)
	{
		_id = id;
	}

/*	public Dao getDao()
	{
		return _dao;
	}*/
}
