package br.com.sisnunes.myzkx.ui;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.ListModelList;

/**
 * Created by higor on 25/03/15.
 */
public class Combobox<T> extends org.zkoss.zul.Combobox implements InputField
{
	private ListModelList<T> _modelList;

	public Component getComponent()
	{
		return this;
	}

	public void setModel(ListModelList<T> modelList)
	{
		_modelList = modelList;
		super.setModel(modelList);
	}

	public void setFieldValue(Object obj)
	{
		if (obj == null)
			return;

		_modelList.addToSelection((T)obj);
		setValue(obj.toString());
	}

	public Object getFieldValue()
	{
		if (_modelList.isSelectionEmpty())
			return null;

		return _modelList.getSelection().iterator().next();
	}

	public void setFormat(String format)
	{
	}

	public void setFieldType(String type)
	{
		setType(type);
	}
}
