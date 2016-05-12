package br.com.sisnunes.myzkx;

import java.net.URLClassLoader;

/**
 * Created by higor on 13/03/15.
 */
public interface ModuleLoader
{
	public URLClassLoader getLoader();
	public Class loadClass(String className) throws ClassNotFoundException;
	public Class loadClassPrefixed(String className) throws ClassNotFoundException;
	public String[] getModulesName();
}
