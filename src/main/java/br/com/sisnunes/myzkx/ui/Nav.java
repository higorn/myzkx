package br.com.sisnunes.myzkx.ui;

import org.zkoss.zhtml.Li;
import org.zkoss.zhtml.Ul;
import org.zkoss.zul.A;
import org.zkoss.zul.impl.LabelImageElement;
import java.io.IOException;
import org.zkoss.lang.Objects;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zk.ui.sys.ContentRenderer;
import org.zkoss.zul.impl.LabelImageElement;

/**
 * Created by higor on 12/03/15.
 */
public class Nav extends Li
{
	private A 		_a;
	private Ul 		_ul;

	public Nav(String label)
	{
		_a = new A(label);
		_ul = new Ul();
		super.appendChild(_a);
		super.appendChild(_ul);
//		setSclass("z-nav");
//		_a.setSclass("z-nav-content");
	}

	@Override
	public boolean appendChild(Component child)
	{
		return _ul.appendChild(child);
	}

	public void setIconSclass(String iconName)
	{
		_a.setIconSclass(iconName);
	}
}
/*
public class Nav extends LabelImageElement
{
    private boolean _open = false;
    private String _badgeText = null;

    public Nav() {
    }

    public Nav(String label) {
        super(label);
    }

    public String getBadgeText() {
        return this._badgeText;
    }

    public void setBadgeText(String badgeText) {
        if(!Objects.equals(this._badgeText, badgeText)) {
            this._badgeText = badgeText;
            this.smartUpdate("badgeText", this._badgeText);
        }

    }

    public boolean isOpen() {
        return this._open;
    }

    public void setOpen(boolean opened) {
        if(this._open != opened) {
            this._open = opened;
            this.smartUpdate("open", this._open);
        }

    }

    public Navbar getNavbar() {
        Object p = this;

        while(true) {
            Component q = ((Component)p).getParent();
            if(q == null) {
                return null;
            }

            if(q instanceof Navbar) {
                return (Navbar)q;
            }

            p = q;
        }
    }

    public String getZclass() {
        return this._zclass == null?"z-nav":this._zclass;
    }

    public void beforeParentChanged(Component parent) {
        if(parent != null && !(parent instanceof Nav) && !(parent instanceof Navbar)) {
            throw new UiException("Unsupported parent for nav: " + parent);
        } else {
            super.beforeParentChanged(parent);
        }
    }

    public void beforeChildAdded(Component child, Component refChild) {
        if(!(child instanceof Nav) && !(child instanceof Navitem) && !(child instanceof Navseparator)) {
            throw new UiException("Unsupported child for nav: " + child);
        } else {
            super.beforeChildAdded(child, refChild);
        }
    }

    protected void renderProperties(ContentRenderer renderer) throws IOException {
        super.renderProperties(renderer);
        if(this._badgeText != null) {
            this.render(renderer, "badgeText", this._badgeText);
        }

        if(this.isOpen()) {
            renderer.render("open", true);
        }

    }

    public void service(AuRequest request, boolean everError) {
        String cmd = request.getCommand();
        if(cmd.equals("onOpen")) {
            OpenEvent evt = OpenEvent.getOpenEvent(request);
            this._open = evt.isOpen();
            Events.postEvent(evt);
        } else {
            super.service(request, everError);
        }

    }

    static {
        addClientEvent(Nav.class, "onOpen", 1);
    }
}
*/

