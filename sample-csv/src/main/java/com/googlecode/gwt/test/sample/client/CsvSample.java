package com.googlecode.gwt.test.sample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class CsvSample implements EntryPoint {

   private SampleView sampleView;

   public void onModuleLoad() {
      sampleView = new SampleView();
      RootPanel.get().add(sampleView);
   }
}
