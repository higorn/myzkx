package br.com.sisnunes.myzkx.ui;

import br.com.sisnunes.myzkx.data.Binder;
import br.com.sisnunes.myzkx.services.DefaultFieldFactory;
import br.com.sisnunes.myzkx.ui.annotation.FieldProps;
import org.zkoss.zul.*;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * Created by higor on 27/10/14.
 */
public class FormView<T> extends Grid
{
	private Binder<T> _binder;
	private DefaultFieldFactory _fieldFactory;
	private Collection<Object>		_visibleItems;

	public FormView()
	{
		this(null);
	}

	public FormView(DefaultFieldFactory fieldFactory)
	{
		_fieldFactory = fieldFactory;
		Column label = new Column();
		label.setAlign("right");
		label.setHflex("min");
		Column field = new Column();
		Columns columns = new Columns();
		columns.appendChild(label);
		columns.appendChild(field);
		appendChild(columns);
		Rows rows = new Rows();
		appendChild(rows);
		setVflex("1");
	}

	public void setItemDataSource(T bean)
	{
		_binder = new Binder<T>(bean);
		getRows().getChildren().clear();
		for (Field fld: bean.getClass().getDeclaredFields())
		{
			if (fld.isAnnotationPresent(FieldProps.class))
			{
				FormRow f = _fieldFactory.createField(fld);
				getRows().appendChild(f);
				_binder.bind(f, fld.getName());
			}
		}
	}

	public void commit() throws Binder.CommitException
	{
		_binder.commit();
	}

	public DefaultFieldFactory getFieldFactory()
	{
		return _fieldFactory;
	}

	public void setFieldFactory(DefaultFieldFactory _fieldFactory)
	{
		this._fieldFactory = _fieldFactory;
	}

	public void setVisibleItems(Collection<Object> visibleItems)
	{
		this._visibleItems = visibleItems;
	}
}
