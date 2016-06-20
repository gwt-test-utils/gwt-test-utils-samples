package com.googlecode.gwt.test.sample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SpringSample implements EntryPoint {

    private final Messages messages = GWT.create(Messages.class);

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        RootPanel.get("spring-simple-container").add(new SpringSampleView());
    }
}
