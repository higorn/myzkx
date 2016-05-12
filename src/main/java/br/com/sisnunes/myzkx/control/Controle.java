package br.com.sisnunes.myzkx.control;

import br.com.sisnunes.myzkx.Module;
import br.com.sisnunes.myzkx.data.Binder;
import br.com.sisnunes.myzkx.ui.*;
import br.com.sisnunes.myzkx.ui.Toolbar;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.*;
import org.zkoss.zul.Textbox;

import java.util.ResourceBundle;
import java.util.Set;

/**
 * Created by higor on 12/03/15.
 */
public class Controle<T> extends SelectorComposer<Component>
	implements EventListener<Event>
{
  private static final String		I18N = "i18n/txt";
  private ResourceBundle _txt = ResourceBundle.getBundle(I18N);
	private Module<T>         _module;
	private ViewCreator			  _viewCreator;
	private Component			    _rootComp;
  private Toolbar           _toolbarList;
  private Toolbar           _toolbarForm;
  private ListView<T>       _listView;
  private FormView<T>       _formView;
  private DefaultView       _view;
  private T   					    _selectedObj;
  private Integer           _selectedIndex;

	public Controle()
	{
		super();
	}

	public Controle(Module module, ViewCreator viewCreator, Component rootComp)
	{
		_module = module;
		_viewCreator = viewCreator;
		_rootComp = rootComp;
    _view = new DefaultView();
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception
	{
		super.doAfterCompose(comp);
		_rootComp = comp;
    _viewCreator = new MainView();
	}

  public void show()
  {
    try
    {
      _toolbarList = new Toolbar(this);
      _toolbarForm = new Toolbar(this);
      _toolbarForm.setEdit();
      _listView = new ListView<T>(this);
      _formView = new FormView<T>(_module.getService().getFieldFactory());
      _listView.addEventListener(Events.ON_CHANGE, _module.getService());
      _view.getLayoutCenter().appendChild(_toolbarList);
      _view.getLayoutCenter().appendChild(_listView);
      _view.getLayoutEast().appendChild(_toolbarForm);
      _view.getLayoutEast().appendChild(_formView);

      Tabpanel pane = new Tabpanel();
      pane.setId(_module.getCaption());
      pane.appendChild(_view);
      pane.setStyle("overflow:auto");

      _viewCreator.addView(pane);
    } catch (Exception e)
    {
      e.printStackTrace();
      Clients.showNotification("Erro ao mostrar tabela: "+e.getMessage());
    }
  }

  @Listen("onClick = tree#_tree > treechildren > treeitem")
  public void onClick(MouseEvent event)
  {
    Component target = event.getTarget();

    if (target instanceof Treeitem)
    {
      Treeitem item = (Treeitem)target;
      Module mod = item.getValue();
      mod.init(_viewCreator);
    }
  }

  protected void doAdd()
  {
    Textbox input = _toolbarList.getInput();
    T entity = null;
    try
    {
      entity = (T)_module.getService().getType().newInstance();
      _listView.getListModel().add(entity);
      _listView.getListModel().addToSelection(entity);
      _selectedObj = entity;
      _formView.setItemDataSource(entity);

      refreshFormView();
      input.setValue("");
    } catch (Exception e)
    {
      e.printStackTrace();
      if (e.getMessage().contains("duplicate key"))
      {
        Clients.showNotification("Objeto não adicionado: Um objeto com os mesmo valores já existe");
      }
      else
        Clients.showNotification("Erro ao adicionar objeto: "+e.getMessage());
    }
  }

  protected void doSave()
  {
    try
    {
      _formView.commit();
      if (_selectedObj != null)
      {
        _module.getService().save(_selectedObj);
        _listView.reload();
      }
    }
    catch (Binder.CommitException e)
    {
      e.printStackTrace();
      Clients.showNotification("ERRO: " + e.getMessage());
    } catch (Exception e)
    {
      e.printStackTrace();
      Clients.showNotification("ERRO: " + e.getMessage());
    }
  }

  protected void doDel(Object entity)
  {
    if (entity == null)
      entity = _selectedObj;
    System.out.println("doDel: "+entity);
    _module.getService().del(entity);
    _listView.getListModel().remove(entity);
    if (entity.equals(_selectedObj))
    {
      _selectedObj = null;
      refreshFormView();
    }
  }

  protected void doDelMulti()
  {
    ListModelList<T> listModel = _listView.getListModel();
    Set<T> itemSet = listModel.getSelection();
    T[] itemArray = (T[]) itemSet.toArray(new Object[itemSet.size()]);
    for (T item: itemArray)
    {
      System.out.println("o: "+item);
      doDel(item);
    }
  }

  private void showConfirmation(final Button btn)
  {
    Messagebox.show(_txt.getString("list.delwarn"), _txt.getString("confirma.title"),
        Messagebox.OK|Messagebox.CANCEL, Messagebox.QUESTION, new EventListener<Event>()
        {
          public void onEvent(Event event) throws Exception
          {
            if (event.getName().equals("onOK"))
              doDel(btn.getAttribute("entity"));
          }
        });
  }

  private void showConfirmation()
  {
    Messagebox.show(_txt.getString("toolbar.delwarn"), _txt.getString("confirma.title"),
        Messagebox.OK|Messagebox.CANCEL, Messagebox.QUESTION, new EventListener<Event>()
        {
          public void onEvent(Event event) throws Exception
          {
            if (event.getName().equals("onOK"))
              doDelMulti();
          }
        });
  }

	public void onEvent(Event event)
	{
    Component target = event.getTarget();
    if (target instanceof Button)
    {
      final Button btn = (Button)target;
      if (btn.getSclass().contains("list-btn-edit"))
      {
        T bean = (T)btn.getAttribute("entity");
        _selectedIndex = (Integer)btn.getAttribute("index");
        _selectedObj = bean;
        _formView.setItemDataSource(bean);
        refreshFormView();
      }
      else if (btn.getSclass().contains("toolbar-btn-add"))
      {
        doAdd();
      }
      else if (btn.getSclass().contains("toolbar-btn-save"))
      {
        doSave();
      }
      else if (btn.getSclass().contains("toolbar-btn-del "))
      {
        showConfirmation(btn);
      }
      else if (btn.getSclass().contains("toolbar-btn-delm "))
      {
        ListModelList<T> listModel = _listView.getListModel();
        if (listModel == null || listModel.isSelectionEmpty())
        {
          Clients.showNotification(_txt.getString("toolbar.delNoItem"));
          return;
        }

        showConfirmation();
      }
    }
    else if (target instanceof Textbox)
    {
      Textbox input = (Textbox)target;
      if (input.getSclass().contains("toolbar-input"))
      {
        try
        {
          _listView.filter(input.getText());
        } catch (Exception e)
        {
          e.printStackTrace();
          Clients.showNotification("ERRO: " + e.getMessage());
        }
      }
    }
/*    else if (target instanceof Listbox)
    {
      ListModelList<T> listModel = _listView.getListModel();
      if (listModel == null || listModel.isSelectionEmpty())
        _selectedObj = null;
      else
      {
        T bean = listModel.getSelection().iterator().next();
        _formView.setItemDataSource(bean);
        _selectedObj = bean;
      }
      refreshFormView();
    }*/
	}

  public void refreshFormView()
  {
    if (_selectedObj == null)
    {
      _view.getEast().setVisible(false);
    }
    else
    {
      _view.getEast().setVisible(true);
    }
  }

  public ViewCreator getViewCreator()
  {
    return _viewCreator;
  }

/*	protected void doDel(Object entity)
	{
		if (entity == null)
			entity = _selectedObj;
		_module.getService().del(entity);
		_listView.getListModel().remove(entity);
		if (entity.equals(_selectedObj))
		{
			_selectedObj = null;
			refreshFormView();
		}
	}

	private void showConfirmation(final Button btn)
	{
		Messagebox.show(_txt.getString("list.delwarn"), _txt.getString("confirma.title"),
			Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener<Event>()
			{
				public void onEvent(Event event) throws Exception
				{
					if (event.getName().equals("onOK")) doDel(btn.getAttribute("entity"));
				}
			});
	}*/

  public Module getModule()
  {
    return _module;
  }

	public ResourceBundle getTxt()
	{
		return _txt;
	}

	public void setTxt(ResourceBundle _txt)
	{
		this._txt = _txt;
	}
}
