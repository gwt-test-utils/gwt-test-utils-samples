package com.googlecode.gwt.test.sample.client;

import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTestWithMockito;
import com.googlecode.gwt.test.utils.events.Browser;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static com.googlecode.gwt.test.assertions.GwtAssertions.assertThat;
import static org.mockito.Matchers.eq;

@GwtModule("com.googlecode.gwt.test.sample.MockSample")
public class MockSampleViewWithMockitoTest extends GwtTestWithMockito {

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
        Mockito.when(clientGreeting.greet(eq("Ben Linus"))).thenReturn("Mocked hello message");

        // Act
        Browser.click(view.button);

        // Assert
        Mockito.verify(clientGreeting).greet(eq("Ben Linus"));
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
        Browser.fillText("John Locke", view.textBox);

        // Assert
        assertThat(view.button).isVisible().isEnabled();
        assertThat(view.label).isNotVisible();
    }

}
