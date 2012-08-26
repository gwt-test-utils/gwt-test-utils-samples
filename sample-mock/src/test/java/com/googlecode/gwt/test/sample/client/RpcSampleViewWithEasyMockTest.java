package com.googlecode.gwt.test.sample.client;

import static com.googlecode.gwt.test.assertions.GwtAssertions.assertThat;

import org.easymock.EasyMock;
import org.junit.Test;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTestWithEasyMock;
import com.googlecode.gwt.test.Mock;
import com.googlecode.gwt.test.utils.events.Browser;

@GwtModule("com.googlecode.gwt.test.sample.MockSample")
public class RpcSampleViewWithEasyMockTest extends GwtTestWithEasyMock {

   @Mock
   private GreetingServiceAsync greetingServiceAsync;

   @Test
   public void clickOnButtonShouldDisplayErrorInLabelWhenRpcFailure() {
      // Arrange
      RpcSampleView view = new RpcSampleView();
      view.textBox.setText("Ben Linus");
      view.button.setEnabled(true);
      assertThat(view.label).isNotVisible();

      // expect service invocation
      greetingServiceAsync.greetServer(EasyMock.eq("Ben Linus"), EasyMock.isA(AsyncCallback.class));
      expectServiceAndCallbackOnFailure(new RuntimeException("expected mocked runtime exception"));

      // EasyMock.replay(..) for every @Mock objects
      replay();

      // Act
      Browser.click(view.button);

      // Assert
      // EasyMock.verify(..) for every @Mock objects
      verify();
      assertThat(view.label).isVisible().textEquals(
               "Server error: expected mocked runtime exception");
   }

   @Test
   public void clickOnButtonShouldDisplayMessageInLabelWhenRpcSuccess() {
      // Arrange
      RpcSampleView view = new RpcSampleView();
      view.textBox.setText("Ben Linus");
      view.button.setEnabled(true);
      assertThat(view.label).isNotVisible();

      // expect service invocation
      greetingServiceAsync.greetServer(EasyMock.eq("Ben Linus"), EasyMock.isA(AsyncCallback.class));
      expectServiceAndCallbackOnSuccess("Mocked hello message");

      // EasyMock.replay(..) for every @Mock objects
      replay();

      // Act
      Browser.click(view.button);

      // Assert
      // EasyMock.verify(..) for every @Mock objects
      verify();
      assertThat(view.label).isVisible().textEquals("Mocked hello message");
   }

   @Test
   public void fillTextShouldEnableButton() {
      // Arrange
      RpcSampleView view = new RpcSampleView();
      // ensure the widgets state at init
      assertThat(view.label).isNotVisible();
      assertThat(view.button).isVisible().isNotEnabled();

      // Act
      Browser.fillText(view.textBox, "John Locke");

      // Assert
      assertThat(view.button).isVisible().isEnabled();
      assertThat(view.label).isNotVisible();
   }

}
