package br.com.sisnunes.myzkx.ui;

import org.zkoss.zul.impl.XulElement;

/**
 * Created by higor on 12/03/15.
 */
public class Navseparator extends XulElement
{
    public Navseparator() {
    }

    public String getZclass() {
        return this._zclass == null?"z-navseparator":this._zclass;
    }

    protected boolean isChildable() {
        return false;
    }
}
