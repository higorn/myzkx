package br.com.sisnunes.myzkx.ui;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Constraint;

/**
 * Created by higor on 25/03/15.
 */
public class Checkbox extends org.zkoss.zul.Checkbox implements InputField
{
	public Component getComponent()
	{
		return this;
	}

	public Object getFieldValue()
	{
		return isChecked();
	}

	public void setFieldValue(Object obj)
	{
		setChecked((Boolean)obj);
	}

	public void setConstraint(String constraint)
	{
	}

	public void setConstraint(Constraint constraint)
	{
	}

	public Constraint getConstraint()
	{
		return null;
	}

	public void setFormat(String format)
	{
	}

	public void setFieldType(String type)
	{
	}

	public void setRows(int rows)
	{
	}

	public void setPlaceholder(String placeHolder)
	{
	}

	public boolean isValid()
	{
		return false;
	}

	public String getErrorMessage()
	{
		return null;
	}

	public void setErrorMessage(String msg)
	{
	}
}
