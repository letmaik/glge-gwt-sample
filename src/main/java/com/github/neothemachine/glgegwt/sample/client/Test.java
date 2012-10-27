package com.github.neothemachine.glgegwt.sample.client;

import com.github.neothemachine.glgegwt.client.Document;
import com.github.neothemachine.glgegwt.client.Document.LoadListener;
import com.github.neothemachine.glgegwt.client.GLGECanvas;
import com.github.neothemachine.glgegwt.client.Renderer;
import com.github.neothemachine.glgegwt.client.Scene;
import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Test extends Composite implements AnimationCallback {

	private static TestUiBinder uiBinder = GWT.create(TestUiBinder.class);

	interface TestUiBinder extends UiBinder<Widget, Test> {
	}
	
	private final Renderer renderer;
	
	@UiField
	GLGECanvas glge;

	public Test() {
		initWidget(uiBinder.createAndBindUi(this));
		
		renderer = glge.getRenderer();
		
		final Document doc = Document.create();
		doc.addLoadListener(new LoadListener() {
			@Override
			public void finishedLoading() {
				Scene scene = doc.getElement("mainscene");
				renderer.setScene(scene);
			}
		});
		
		doc.load("Sample/scene.xml");
		
		final Element glgeCanvas = glge.getCanvas().getCanvasElement();

		Scheduler.get().scheduleFixedPeriod(new RepeatingCommand() {
			@Override
			public boolean execute() {
				AnimationScheduler.get().requestAnimationFrame(Test.this, glgeCanvas);				
				return true;
			}
		}, 15);
	}

	/**
	 * Run a step in our animation loop.
	 */
	@Override
	public void execute(double timestamp) {
		renderer.render();
	}
	
}
