package br.com.sisnunes.myzkx.ui;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;

/**
 * Created by higor on 24/10/14.
 */
public class Toolbar extends org.zkoss.zul.Toolbar
{
	public static final String			BT_NOVO = "novo";
	public static final String			BT_DELETAR = "deletar";
	public static final String			BT_SALVAR = "salvar";

	private Textbox				_input;
	private Toolbarbutton		_novo;
	private Toolbarbutton		_deletar;
  private Toolbarbutton		_deletarMulti;
	private Toolbarbutton		_salvar;

	public Toolbar()
	{
		this(null);
	}

	public Toolbar(EventListener<Event> listener)
	{
		_input = new Textbox();
		_input.setSclass("toolbar-input");
		_input.setPlaceholder("Buscar");
		_input.setWidth("200");
		_input.setHflex("1");
    _novo = new Toolbarbutton("");
		_novo.setSclass("toolbar-btn-add fa fa-plus-circle fa-lg");
		_novo.setWidth("36px");
    _deletarMulti = new Toolbarbutton();
    _deletarMulti.setSclass("toolbar-btn-delm fa fa-trash fa-lg");
    _deletarMulti.setWidth("36px");
		_salvar = new Toolbarbutton();
		_salvar.setSclass("toolbar-btn-save fa fa-floppy-o fa-lg");
		_salvar.setVisible(false);
		_deletar = new Toolbarbutton();
		_deletar.setSclass("toolbar-btn-del fa fa-trash fa-lg");
		_deletar.setVisible(false);

		if (listener != null)
		{
			_input.addEventListener(Events.ON_OK, listener);
			_novo.addEventListener(Events.ON_CLICK, listener);
			_salvar.addEventListener(Events.ON_CLICK, listener);
			_deletar.addEventListener(Events.ON_CLICK, listener);
      _deletarMulti.addEventListener(Events.ON_CLICK, listener);
		}

		appendChild(_novo);
		appendChild(_deletarMulti);
		appendChild(_input);
		appendChild(_salvar);
		appendChild(_deletar);

//		setWidth("100%");
		setClass("mda-toolbar");
		setHflex("1");
		setAlign("center");
	}

	public void addEventListener(EventListener<Event> listener)
	{
		_novo.addEventListener(Events.ON_CLICK, listener);
		_salvar.addEventListener(Events.ON_CLICK, listener);
		_deletar.addEventListener(Events.ON_CLICK, listener);
    _deletarMulti.addEventListener(Events.ON_CLICK, listener);
	}

	public void setEdit()
	{
		_salvar.setVisible(true);
		_deletar.setVisible(true);
		_input.setVisible(false);
		_novo.setVisible(false);
    _deletarMulti.setVisible(false);
		setAlign("start");
	}

	public Toolbarbutton getButton(String id)
	{
		if (id.equals(BT_NOVO))
			return _novo;
//		else if (id.equals(BT_EDITAR))
//			return _editar;
		else if (id.equals(BT_DELETAR))
			return _deletar;
		else if (id.equals(BT_SALVAR))
			return _salvar;
//		else if (id.equals(BT_CANCELAR))
//			return _cancelar;
		else
			return null;
	}

	public Textbox getInput()
	{
		return _input;
	}
}
