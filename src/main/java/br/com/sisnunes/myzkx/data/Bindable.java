package br.com.sisnunes.myzkx.data;

import br.com.sisnunes.myzkx.ui.InputField;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Constraint;

/**
 * Created by higor on 25/03/15.
 */
public interface Bindable extends Component
{
	public Object getVal();
	public void setVal(Object val);
	public boolean isValid();
	public InputField getField();
	public void setConstraint(Constraint constraint);
	public Constraint getConstraint();
}
