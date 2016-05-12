package br.com.sisnunes.myzkx.ui;

import org.zkoss.zk.ui.Component;

/**
 * Created by higor on 25/03/15.
 */
public class Datebox extends org.zkoss.zul.Datebox implements InputField
{
	public Component getComponent()
	{
		return this;
	}

	public Object getFieldValue()
	{
		return getValue();
	}

	public void setFieldValue(Object obj)
	{
		setRawValue(obj);
	}

	public void setFieldType(String type)
	{

	}

	public void setRows(int rows)
	{
	}
}
