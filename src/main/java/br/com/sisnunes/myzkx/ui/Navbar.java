package br.com.sisnunes.myzkx.ui;

import org.zkoss.zhtml.Ul;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Div;

/**
 * Created by higor on 12/03/15.
 */
public class Navbar extends Div
{
	private Ul 			_ul;
	private boolean		_collapsed;

	public Navbar(String orient)
	{
		_ul = new Ul();
		super.appendChild(_ul);
//		setSclass("z-navbar");
//		if (orient.equals("vertical"))
//			setSclass("z-navbar-vertical");
//		else
//			setSclass("z-navbar-horizontal");
	}

	@Override
	public boolean appendChild(Component child)
	{
		return _ul.appendChild(child);
	}

	public boolean isCollapsed() {
		return this._collapsed;
	}

	public void setCollapsed(boolean collapsed) {
		if(this._collapsed != collapsed) {
			this._collapsed = collapsed;
			this.smartUpdate("collapsed", this._collapsed);
		}

	}
}
//public class Navbar extends XulElement
/*
public class Navbar extends Div
{
    private static final Logger log = LoggerFactory.getLogger(Navbar.class);
    private boolean _collapsed;
    private String _orient;
    private Navitem _sel;

    public Navbar() {
        this._collapsed = false;
        this._orient = "horizontal";
    }

    public Navbar(String orient) {
        this();
        this.setOrient(orient);
    }

    public String getOrient() {
        return this._orient;
    }

    public void setOrient(String orient) throws WrongValueException {
        if(!"horizontal".equals(orient) && !"vertical".equals(orient)) {
            throw new WrongValueException("orient cannot be " + orient);
        } else {
            if(!Objects.equals(this._orient, orient)) {
                this._orient = orient;
                this.smartUpdate("orient", this._orient);
            }

        }
    }

    public boolean isHorizontal() {
        return "horizontal".equals(this.getOrient());
    }

    public boolean isVertical() {
        return "vertical".equals(this.getOrient());
    }

    public boolean isCollapsed() {
        return this._collapsed;
    }

    public void setCollapsed(boolean collapsed) {
        if(this._collapsed != collapsed) {
            this._collapsed = collapsed;
            this.smartUpdate("collapsed", this._collapsed);
        }

    }

    public void setSelectedItem(Navitem item) {
        this.selectItem(item);
    }

    public Navitem getSelectedItem() {
        return this._sel;
    }

    public void clearSelection() {
        if(this._sel != null) {
            this._sel.setSelectedDirectly(false);
            this._sel = null;
            this.smartUpdate("selectedItem", (Object)null);
        }

    }

    public void selectItem(Navitem item) {
        if(item == null) {
            this.clearSelection();
        } else {
            if(item.getNavbar() != this) {
                throw new UiException("Not a child: " + item);
            }

            if(this._sel == null) {
                this._sel = item;
                item.setSelectedDirectly(true);
                this.smartUpdate("selectedItem", item);
            } else if(!this._sel.equals(item)) {
                this._sel.setSelectedDirectly(false);
                this._sel = item;
                item.setSelectedDirectly(true);
                this.smartUpdate("selectedItem", item);
            }
        }

    }

    public String getZclass() {
        return this._zclass == null?"z-navbar":this._zclass;
    }

    public void beforeChildAdded(Component child, Component refChild) {
        if(!(child instanceof Navitem) && !(child instanceof Nav) && !(child instanceof Navseparator)) {
            throw new UiException("Unsupported child for navbar: " + child);
        } else {
            super.beforeChildAdded(child, refChild);
        }
    }

    protected void renderProperties(ContentRenderer renderer) throws IOException {
        super.renderProperties(renderer);
        if(!"horizontal".equals(this.getOrient())) {
            this.render(renderer, "orient", this._orient);
        }

        if(this.isCollapsed()) {
            this.render(renderer, "collapsed", this._collapsed);
        }

        if(this._sel != null) {
            this.render(renderer, "selectedItem", this._sel);
        }

    }

    public void service(AuRequest request, boolean everError) {
        String cmd = request.getCommand();
        if(cmd.equals("onSelect")) {
            SelectEvent evt = SelectEvent.getSelectEvent(request);
            Set items = evt.getSelectedItems();
            if(!items.contains(this._sel)) {
                try {
                    Events.postEvent(evt);
                    this.disableClientUpdate(true);
                    this.setSelectedItem((Navitem)items.iterator().next());
                } finally {
                    this.disableClientUpdate(false);
                }
            }
        } else {
            super.service(request, everError);
        }

    }

    static {
        addClientEvent(Navbar.class, "onSelect", 8193);
    }
}
*/

