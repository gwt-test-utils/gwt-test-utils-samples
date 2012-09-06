package com.googlecode.gwt.test.sample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class CsvSample implements EntryPoint {

   private RpcSampleView rpcSampleView;

   public void onModuleLoad() {
      rpcSampleView = new RpcSampleView();
      RootPanel.get().add(rpcSampleView);
   }
}
