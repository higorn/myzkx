package br.com.sisnunes.myzkx.services;

import br.com.sisnunes.myzkx.data.Entity;
import br.com.sisnunes.myzkx.ui.*;
import br.com.sisnunes.myzkx.ui.annotation.FieldProps;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by higor on 25/03/15.
 */
public class DefaultFieldFactory<T extends Entity> implements FieldFactory
{
	private static final String		I18N = "i18n/txt";
	private ResourceBundle				_txt = ResourceBundle.getBundle(I18N);
	private BasicService<T>			_service;

	public DefaultFieldFactory(BasicService<T> service)
	{
		_service = service;
	}

	public FormRow createField(Field field)
	{
		FieldProps props = field.getAnnotation(FieldProps.class);
		Class<?> type = props.type();
		String name = field.getName();
		String caption = name;
		if (_txt != null)
			caption = _txt.getString("formfield."+field.getName());
		if (!type.equals(Object.class))
		{
			try
			{
				Object obj = type.newInstance();
				if (obj instanceof InputField)
				{
					FormRow fld = new FormRow(new Label(caption), (InputField)obj);
					fld.getField().setConstraint(parseConstraint(props.constraint()));
					fld.getField().setFormat(props.format());
					fld.getField().setWidth(props.width());
					fld.getField().setRows(props.rows());
					fld.getField().setSclass(props.sclass());
//					fld.getField().setPlaceholder(_txt.getString(props.placeholder()));

					return fld;
				}
			} catch (InstantiationException e)
			{
				e.printStackTrace();
			} catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		type = field.getType();

		return createField(caption, type, props, field);
	}

	public FormRow createField(String name, Class<?> type, FieldProps props, Field field)
	{
		FormRow formRow;
		if (Enum.class.isAssignableFrom(type))
			formRow = createEnumField(name, type);
		else if (Date.class.isAssignableFrom(type))
			formRow = createDateField(name, type);
		else if (Boolean.class.isAssignableFrom(type)
				|| boolean.class.isAssignableFrom(type))
			formRow = createBooleanField(name, type);
		else if (Entity.class.isAssignableFrom(type))
			formRow = createComboboxField(name, type, props, field);
		else
			formRow = new FormRow(new Label(name), new Textbox());

		formRow.getField().setConstraint(parseConstraint(props.constraint()));
		formRow.getField().setFormat(props.format());
		formRow.getField().setWidth(props.width());
		formRow.getField().setRows(props.rows());
		formRow.getField().setSclass(props.sclass());
//		field.getField().setPlaceholder(_txt.getString(props.placeholder()));

		return formRow;
	}

	public FormRow createEnumField(String name, Class<?> type)
	{
		return new FormRow(new Label(name), new Combobox());
	}

	public FormRow createDateField(String name, Class<?> type)
	{
		return new FormRow(new Label(name), new Datebox());
	}

	public FormRow createBooleanField(String name, Class<?> type)
	{
		return new FormRow(new Label(name), new Checkbox());
	}

	public FormRow createComboboxField(String name, Class<?> type, FieldProps props, Field field)
	{
		final Combobox<T> combobox = new Combobox<T>();
		combobox.setConstraint(parseConstraint(props.constraint()));
		combobox.setWidth(props.width());

		ListModelList<T> listModel = new ListModelList<T>(_service.getAll(type));
		combobox.setModel(listModel);
		String label = getTxt("formfield."+field.getName());
		FormRow formRow = new FormRow(new Label(label), combobox);
		return formRow;
	}

	public String parseConstraint(String constraint)
	{
		if (!constraint.contains(":_") || _txt == null)
			return constraint;

		String[] strArray = constraint.split("_");
		String cons = strArray[0];
		String msg = _txt.getString(strArray[1]);

		return cons+msg;
	}

	public String getTxt(String key)
	{
		return _txt.getString(key);
	}
}
