package br.com.sisnunes.myzkx.ui;

import org.zkoss.zul.*;

import java.util.List;

/**
 * Created by higor on 23/10/14.
 */
public class MainView extends Tabbox implements ViewCreator
{
	public MainView()
	{
		Tabs tabs = new Tabs();
		appendChild(tabs);
		Tabpanels panels = new Tabpanels();
		appendChild(panels);
		setWidth("100%");
//		setClass("mda-tabs");
		setVflex("1");
	}

	public Tab getTab(String label)
	{
		List<Tab> tabList = getTabs().getChildren();
		for (Tab t: tabList)
		{
			if (t.getLabel().equals(label))
				return t;
		}

		return null;
	}

	public void addView(Tabpanel panel)
	{
		Tab t = getTab(panel.getId());
		if (t == null)
		{
			t = new Tab(panel.getId());
			t.setClosable(true);
			getTabs().appendChild(t);
			getTabpanels().appendChild(panel);
		}
		setSelectedTab(t);
	}
}
