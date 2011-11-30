/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2011 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package ti.gigya.listeners;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;

import ti.gigya.Constants;
import ti.gigya.Util;

import com.gigya.socialize.GSObject;
import com.gigya.socialize.android.event.GSEventListener;

public class GlobalEventListener implements GSEventListener 
{
	private final KrollProxy _proxy;
	
	public GlobalEventListener(final KrollProxy proxy)
	{
		_proxy = proxy;
	}
	
	public void onLogin(String provider, GSObject user, Object context)
	{
		if (_proxy.hasListeners(Constants.kDidLogin)) {
			KrollDict event = new KrollDict();
			event.put(Constants.kProvider, provider);
			event.put(Constants.kData, Util.dataFromGSObject(user));
			
			_proxy.fireEvent (Constants.kDidLogin, event);
		}
	}
	
	public void onLogout(Object context)
	{
		if (_proxy.hasListeners(Constants.kDidLogout)) {
			KrollDict event = new KrollDict();
			
			_proxy.fireEvent (Constants.kDidLogout, event);
		}
	}
	
	public void onConnectionAdded(String provider, GSObject user, Object context) 
	{
		if (_proxy.hasListeners(Constants.kDidAddConnection)) {
			KrollDict event = new KrollDict();
			event.put(Constants.kProvider, provider);
			event.put(Constants.kData, Util.dataFromGSObject(user));
			
			_proxy.fireEvent (Constants.kDidAddConnection, event);
		}
	}
	
	public void onConnectionRemoved(String provider, Object context)
	{
		if (_proxy.hasListeners(Constants.kDidRemoveConnection)) {
			KrollDict event = new KrollDict();
			event.put(Constants.kProvider, provider);
			
			_proxy.fireEvent (Constants.kDidRemoveConnection, event);
		}
	}
}