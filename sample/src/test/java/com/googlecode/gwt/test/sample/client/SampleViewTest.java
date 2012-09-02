package com.googlecode.gwt.test.sample.client;

import static com.googlecode.gwt.test.assertions.GwtAssertions.assertThat;
import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;
import com.googlecode.gwt.test.assertions.GwtAssertions;
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
   public void deferredCommandShouldNotBeTriggerSynchronously() {
      // Arrange
      final StringBuilder sb = new StringBuilder();
      ScheduledCommand cmd = new ScheduledCommand() {

         public void execute() {
            sb.append("triggered!");
         }
      };

      // Assert the cmd is not yet triggered
      assertThat(sb.toString()).isEmpty();

      // Act
      Scheduler.get().scheduleDeferred(cmd);

      // simulate an event loop end
      getBrowserSimulator().fireLoopEnd();

      // Assert
      assertThat(sb.toString()).isEqualTo("triggered!");
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
      GwtAssertions.assertThat(view.button).isVisible().isEnabled();
      assertThat(view.label).isNotVisible();
   }
}
