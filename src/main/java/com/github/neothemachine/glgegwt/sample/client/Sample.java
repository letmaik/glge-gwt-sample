package com.github.neothemachine.glgegwt.sample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Sample implements EntryPoint {

	@Override
	public void onModuleLoad() {
		Test test = new Test();
		RootPanel.get().add(test);
	}

}
