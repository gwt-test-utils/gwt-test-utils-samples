package com.googlecode.gwt.test.sample.client;

import org.junit.Before;

import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.csv.CsvDirectory;
import com.googlecode.gwt.test.csv.CsvMethod;
import com.googlecode.gwt.test.csv.GwtCsvTest;
import com.googlecode.gwt.test.finder.GwtFinder;
import com.googlecode.gwt.test.finder.Node;
import com.googlecode.gwt.test.finder.NodeObjectFinder;

@GwtModule("com.googlecode.gwt.test.sample.CsvSample")
@CsvDirectory(value = "functional-tests", extension = ".csv")
public class CsvSampleTest extends GwtCsvTest {

   private CsvSample app;

   @CsvMethod
   public void initApp() {
      app = new CsvSample();
      app.onModuleLoad();
   }

   @Before
   public void setup() {
      GwtFinder.registerNodeFinder("app", new NodeObjectFinder() {
         public Object find(Node node) {
            return csvRunner.getNodeValue(app, node);
         }
      });

      GwtFinder.registerNodeFinder("sampleView", new NodeObjectFinder() {
         public Object find(Node node) {
            return csvRunner.getNodeValue(RootPanel.get().getWidget(0), node);
         }
      });
   }
}
