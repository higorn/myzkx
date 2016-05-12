package br.com.sisnunes.myzkx.ui;

import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.East;
import org.zkoss.zul.Vlayout;

/**
 * Created by higor on 24/10/14.
 */
public class DefaultView extends Borderlayout
{
	private Vlayout			_layoutCenter;
	private Vlayout			_layoutEast;

	public DefaultView()
	{
		_layoutCenter = new Vlayout();
		_layoutCenter.setSpacing("0");
		_layoutCenter.setVflex("1");
		_layoutCenter.setHflex("1");
		_layoutEast = new Vlayout();
		_layoutEast.setSpacing("0");
		_layoutEast.setVflex("1");
		_layoutEast.setHflex("1");
//		_layoutEast.setHeight("600px");

		Center center = new Center();
		center.setAutoscroll(true);
		center.appendChild(_layoutCenter);
		appendChild(center);

		East east = new East();
		east.setVisible(false);
		east.setWidth("400px");
		east.setCollapsible(true);
		east.setSplittable(true);
		east.setMinsize(300);
		east.setAutoscroll(true);
		east.appendChild(_layoutEast);
		appendChild(east);
	}

	public Vlayout getLayoutCenter()
	{
		return _layoutCenter;
	}

	public Vlayout getLayoutEast()
	{
		return _layoutEast;
	}
}
