package br.com.sisnunes.myzkx.services;

import br.com.sisnunes.myzkx.data.Bindable;
import br.com.sisnunes.myzkx.ui.annotation.FieldProps;

import java.lang.reflect.Field;

/**
 * Created by higor on 31/03/15.
 */
public interface FieldFactory
{
	public Bindable createField(Field field);
	public Bindable createField(String name, Class<?> type, FieldProps props, Field field);
}
