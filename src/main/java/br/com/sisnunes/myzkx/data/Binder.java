package br.com.sisnunes.myzkx.data;

import org.zkoss.zk.ui.WrongValueException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by higor on 25/03/15.
 */
public class Binder<T>
{
	private T							_bean;
	private Map<String, Bindable> 		_fields;

	public Binder(T bean)
	{
		_bean = bean;
		_fields = new HashMap<String, Bindable>();
	}

	public void setBean(T bean)
	{
		_bean = bean;
	}

	public void bind(Bindable component, String propId)
	{
		String methodName = "get"+toUpperFist(propId);
		try
		{
			Object val = _bean.getClass().getMethod(methodName).invoke(_bean);
			component.setVal(val);
			_fields.put(propId, component);
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}
	}

	public void commit(String propId, Object val) throws CommitException
	{
		try
		{
			String methodName = "set"+toUpperFist(propId);
			_bean.getClass().getMethod(methodName, _bean.getClass()
					.getDeclaredField(propId).getType()).invoke(_bean, val);
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
			throw new CommitException(e);
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
			throw new CommitException(e);
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
			throw new CommitException(e);
		}
		catch (NoSuchFieldException e)
		{
			e.printStackTrace();
			throw new CommitException(e);
		}
	}

	public void commit() throws CommitException
	{
		try
		{
			for (String k: _fields.keySet())
			{
				System.out.println("k: "+k);
				Bindable comp = _fields.get(k);
				comp.isValid();
/*			if (!comp.isValid())
			{
				throw new CommitException("erro: compo: "+k+": "+comp.getErrorMessage());
			}*/
				commit(k, comp.getVal());
			}
		}
		catch (WrongValueException e)
		{
			throw new CommitException(e.getMessage());
		}
	}

	private String toUpperFist(String str)
	{
		String result = str.replaceFirst(str.substring(0,1), str.substring(0,1).toUpperCase());
		return result;
	}

	public static class CommitException extends Exception
	{
		public CommitException()
		{
		}

		public CommitException(String message)
		{
			super(message);
		}

		public CommitException(String message, Throwable cause)
		{
			super(message, cause);
		}

		public CommitException(Throwable cause)
		{
			super(cause);
		}
	}
}
