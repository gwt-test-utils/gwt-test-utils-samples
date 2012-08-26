package com.googlecode.gwt.test.sample.client;

import static com.googlecode.gwt.test.assertions.GwtAssertions.assertThat;

import org.easymock.EasyMock;
import org.junit.Test;

import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTestWithEasyMock;
import com.googlecode.gwt.test.Mock;
import com.googlecode.gwt.test.utils.events.Browser;

@GwtModule("com.googlecode.gwt.test.sample.MockSample")
public class MockSampleViewWithEasyMockTest extends GwtTestWithEasyMock {

   @Mock
   private ClientGreeting clientGreeting;

   @Test
   public void clickOnButtonShouldDisplayMessageInLabel() {
      // Arrange
      MockSampleView view = new MockSampleView(clientGreeting);
      view.textBox.setText("Ben Linus");
      view.button.setEnabled(true);
      assertThat(view.label).isNotVisible();

      // expect object invocation
      EasyMock.expect(clientGreeting.greet(EasyMock.eq("Ben Linus"))).andReturn(
               "Mocked hello message");

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
