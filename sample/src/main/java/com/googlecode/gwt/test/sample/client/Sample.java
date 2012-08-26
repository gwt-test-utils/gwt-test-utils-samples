package com.googlecode.gwt.test.sample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Sample implements EntryPoint {

   /**
    * This is the entry point method.
    */
   public void onModuleLoad() {
      RootPanel.get("container").add(new SampleView());
   }

}
