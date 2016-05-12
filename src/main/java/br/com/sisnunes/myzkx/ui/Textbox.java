package br.com.sisnunes.myzkx.ui;

import org.zkoss.zk.ui.Component;

/**
 * Created by higor on 25/03/15.
 */
public class Textbox extends org.zkoss.zul.Textbox implements InputField
{
	Class<?>	_valType;

	public Component getComponent()
	{
		return this;
	}

	public Object getFieldValue()
	{
		if (_valType != null && _valType.isAssignableFrom(Integer.class))
			return Integer.valueOf(getValue());
		else if (_valType != null && _valType.isAssignableFrom(Double.class))
			return Double.valueOf(getValue());

		return getValue();
	}

	public void setFieldValue(Object obj)
	{
		if (obj == null)
			return;

		_valType = obj.getClass();
		setRawValue(obj.toString());
	}

	public void setFormat(String format)
	{
	}

	public void setFieldType(String type)
	{
		setType(type);
	}
}
