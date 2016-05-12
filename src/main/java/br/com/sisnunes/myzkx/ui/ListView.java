package br.com.sisnunes.myzkx.ui;

import br.com.sisnunes.myzkx.control.Controle;
import br.com.sisnunes.myzkx.services.BasicService;
import br.com.sisnunes.myzkx.ui.annotation.FieldProps;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by higor on 26/10/14.
 */
public class ListView<T> extends Listbox
{
	private ListModelList<T> 		_listModel;
  private ListModelList<T> 		_listModelFilter;
  private String              _filterInput;
  private List<T>             _list;
  private Controle            _control;

	public ListView(Controle control) throws Exception
  {
    _control = control;
		Listhead listhead = new Listhead();
		listhead.setSizable(true);
		BasicService service = control.getModule().getService();
		if (service != null)
		{
			List<Listheader> listHeader = service.getColumnHeaders();
			for (Listheader h: listHeader)
			{
				h.setSort("auto");
				listhead.appendChild(h);
			}

			Listheader delHeader = new Listheader();
			delHeader.setHflex("min");
			listhead.appendChild(delHeader);

			setItemRenderer(service.getItemRenderer(control));
			appendChild(listhead);
      load();
		}

		setVflex("1");
		addEventListener(Events.ON_SELECT, control);
    setCheckmark(true);
	}

  private void load() throws Exception
  {
    _list = _control.getModule().getService().getAll();
    _listModel = new ListModelList<T>(_list);
    _listModel.setMultiple(true);
    setModel(_listModel);
    if (_filterInput != null && !_filterInput.isEmpty())
    {
      filter(_filterInput);
    }
  }

  public void reload() throws Exception
  {
    load();
  }

  public void filter(String input) throws Exception
  {
    _filterInput = input;
    List<T> newList = new ArrayList<T>();
    for (T obj: _list)
    {
      StringBuilder values = new StringBuilder("");
      Field[] fields = obj.getClass().getDeclaredFields();
      for (Field f: fields)
      {
        if (!f.isAnnotationPresent(FieldProps.class))
          continue;
        String fName = f.getName();
        String methodName = "get"+ fName.replaceFirst(fName.substring(0,1), fName.substring(0,1).toUpperCase());
        Object val = obj.getClass().getMethod(methodName).invoke(obj);
        values.append(val);
      }
      if (values.toString().toUpperCase().contains(input.toUpperCase()))
      {
        newList.add(obj);
      }
    }
    if (!newList.isEmpty())
    {
      _listModelFilter = new ListModelList<T>(newList);
      _listModelFilter.setMultiple(true);
      setModel(_listModelFilter);
    }
  }

/*
  public void set(T item)
  {
    int i = _listModel.indexOf(item);
    _listModel.set(i, item);
    ListModelList<T> model = (ListModelList<T>) getModel();
    i = model.indexOf(item);
    model.set(i, item);
  }
*/

	public ListModelList<T> getListModel()
	{
		return _listModel;
	}
}
