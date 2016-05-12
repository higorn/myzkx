/*
 * File:   Decimalbox.java
 *
 * Created on 18/02/16, 20:25
 */
package br.com.sisnunes.myzkx.ui;

import org.zkoss.zk.ui.Component;

/**
 * @author higor
 */
public class Decimalbox extends org.zkoss.zul.Decimalbox implements InputField
{
  Class<?>	_valType;

  public Component getComponent()
  {
    return this;
  }

  public Object getFieldValue()
  {
/*
    if (_valType != null && _valType.isAssignableFrom(Integer.class))
      return Integer.valueOf(getValue());
    else if (_valType != null && _valType.isAssignableFrom(Double.class))
      return Double.valueOf(getValue().doubleValue());
*/

    return getValue().doubleValue();
  }

  public void setFieldValue(Object obj)
  {
    if (obj == null)
      return;

    _valType = obj.getClass();
    setValue(obj.toString());
  }

  public void setFieldType(String type)
  {
  }

  public void setRows(int rows)
  {

  }
}
