package com.googlecode.gwt.test.sample.client;

import static com.googlecode.gwt.test.assertions.GwtAssertions.assertThat;

import org.junit.Test;

import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;
import com.googlecode.gwt.test.utils.events.Browser;

@GwtModule("com.googlecode.gwt.test.sample.Sample")
public class SampleViewTest extends GwtTest {

   @Test
   public void clickOnButtonShouldDisplayMessageInLabel() {
      // Arrange
      SampleView view = new SampleView();
      view.textBox.setText("Ben Linus");
      view.button.setEnabled(true);
      assertThat(view.label).isNotVisible();

      // Act
      Browser.click(view.button);

      // Assert: label should be visible and filled
      assertThat(view.label).isVisible().textEquals("Hello Ben Linus");
   }

   @Test
   public void fillTextShouldEnableButton() {
      // Arrange
      SampleView view = new SampleView();
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
