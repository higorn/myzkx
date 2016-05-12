package br.com.sisnunes.myzkx.ui;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Constraint;

/**
 * Created by higor on 25/03/15.
 */
public interface InputField
{
	public Component getComponent();
	public Object getFieldValue();
	public void setFieldValue(Object obj);
	public void setConstraint(String constraint);
	public void setConstraint(Constraint constraint);
	public Constraint getConstraint();
	public void setFormat(String format);
	public void setFieldType(String type);
	public void setWidth(String width);
	public void setRows(int rows);
	public void setSclass(String sclass);
	public void setPlaceholder(String placeHolder);
	public boolean isValid();
}
