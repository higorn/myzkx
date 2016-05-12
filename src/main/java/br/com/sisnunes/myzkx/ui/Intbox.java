/*
 * File:   Intbox.java
 *
 * Created on 18/02/16, 21:44
 */
package br.com.sisnunes.myzkx.ui;

import org.zkoss.zk.ui.Component;

/**
 * @author higor
 */
public class Intbox extends org.zkoss.zul.Intbox implements InputField
{
  Class<?>	_valType;

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
    if (obj == null)
      return;

    _valType = obj.getClass();
    setValue((Integer)obj);
  }

  public void setFieldType(String type)
  {

  }

  public void setRows(int rows)
  {

  }
}
