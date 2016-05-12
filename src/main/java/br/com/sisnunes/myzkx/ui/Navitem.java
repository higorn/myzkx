package br.com.sisnunes.myzkx.ui;

import java.io.IOException;
import org.zkoss.lang.Objects;
import org.zkoss.zhtml.Li;
import org.zkoss.zk.au.DeferredValue;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.ext.Disable;
import org.zkoss.zk.ui.sys.ContentRenderer;
import org.zkoss.zul.A;
import org.zkoss.zul.impl.LabelImageElement;

/**
 * Created by higor on 12/03/15.
 */
public class Navitem extends Li
{
	private A 			_a;

	public Navitem()
	{
//		setSclass("z-navitem");
		_a = new A();
//		_a.setSclass("z-navitem-content");
		appendChild(_a);
	}

	public void setLabel(String label)
	{
		_a.setLabel(label);
	}

	public void setIconSclass(String iconName)
	{
		_a.setIconSclass(iconName);
	}
}
/*
public class Navitem extends LabelImageElement implements Disable {
    private String _href;
    private String _target;
    private String _content = "";
    private boolean _disabled;
    private boolean _selected;

    public Navitem() {
    }

    public String getHref() {
        return this._href;
    }

    public void setHref(String href) throws WrongValueException {
        if(href != null && href.trim().length() == 0) {
            href = null;
        }

        if(!Objects.equals(this._href, href)) {
            this._href = href;
            this.smartUpdate("href", new Navitem.EncodedHref());
        }

    }

    public String getContent() {
        return this._content;
    }

    public void setContent(String content) {
        if(content == null) {
            content = "";
        }

        if(!Objects.equals(this._content, content)) {
            this._content = content;
            this.smartUpdate("content", this.getContent());
        }

    }

    public String getTarget() {
        return this._target;
    }

    public void setTarget(String target) {
        if(target != null && target.trim().length() == 0) {
            target = null;
        }

        if(!Objects.equals(this._target, target)) {
            this._target = target;
            this.smartUpdate("target", this._target);
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

    public boolean isSelected() {
        return this._selected;
    }

    public void setSelected(boolean selected) {
        if(this._selected != selected) {
            Navbar navbar = this.getNavbar();
            if(navbar != null) {
                navbar.selectItem(this);
            } else {
                this._selected = selected;
            }
        }

    }

    void setSelectedDirectly(boolean selected) {
        this._selected = selected;
    }

    public void setDisabled(boolean disabled) {
        if(this._disabled != disabled) {
            this._disabled = disabled;
            this.smartUpdate("disabled", this._disabled);
        }

    }

    public boolean isDisabled() {
        return this._disabled;
    }

    public String getZclass() {
        return this._zclass == null?"z-navitem":this._zclass;
    }

    public boolean isTopmost() {
        return !(this.getParent() instanceof Nav);
    }

    public void beforeParentChanged(Component parent) {
        if(parent != null && !(parent instanceof Nav) && !(parent instanceof Navbar)) {
            throw new UiException("Unsupported parent for navitem: " + parent);
        } else {
            super.beforeParentChanged(parent);
        }
    }

    protected void renderProperties(ContentRenderer renderer) throws IOException {
        super.renderProperties(renderer);
        this.render(renderer, "selected", this.isSelected());
        this.render(renderer, "disabled", this.isDisabled());
        this.render(renderer, "content", this.getContent());
        this.render(renderer, "href", this._href);
        this.render(renderer, "target", this._target);
    }

    private String getEncodedHref() {
        Desktop dt = this.getDesktop();
        return this._href != null && dt != null?dt.getExecution().encodeURL(this._href):null;
    }

    private class EncodedHref implements DeferredValue {
        private EncodedHref() {
        }

        public Object getValue() {
            return Navitem.this.getEncodedHref();
        }
    }
}
*/

