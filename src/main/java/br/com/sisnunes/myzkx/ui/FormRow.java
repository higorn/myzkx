package br.com.sisnunes.myzkx.ui;

import br.com.sisnunes.myzkx.data.Bindable;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;

/**
 * Created by higor on 25/03/15.
 */
public class FormRow extends Row implements Bindable
{
	private Label _label;
	private InputField		_field;

	public FormRow()
	{

	}

	public FormRow(String caption, InputField field)
	{
		this(new Label(caption), field);
	}

	public FormRow(Label label, InputField field)
	{
		_label = label;
		_field = field;
		appendChild(label);
		appendChild(_field.getComponent());
	}

	public Component getBindComponent()
	{
		return _field.getComponent();
	}

	public Label getLabel()
	{
		return _label;
	}

	public void setLabel(Label label)
	{
		_label = label;
	}

	public InputField getField()
	{
		return _field;
	}

	public void setField(InputField field)
	{
		_field = field;
	}

/*
	public Object getFieldVal()
	{
		return _field.getFieldValue();
	}

	public void setFieldVal(Object val)
	{
		_field.setFieldValue(val);
	}
*/

	public Object getVal()
	{
		return _field.getFieldValue();
	}

	public void setVal(Object val)
	{
		_field.setFieldValue(val);
	}

	public boolean isValid()
	{
		return _field.isValid();
	}

	public void setConstraint(Constraint constraint)
	{
		_field.setConstraint(constraint);
	}

	public Constraint getConstraint()
	{
		return _field.getConstraint();
	}
}
