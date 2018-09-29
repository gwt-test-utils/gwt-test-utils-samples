package com.googlecode.gwt.test.sample.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTestWithMockito;
import com.googlecode.gwt.test.utils.events.Browser;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static com.googlecode.gwt.test.assertions.GwtAssertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

@GwtModule("com.googlecode.gwt.test.sample.MockSample")
public class RpcSampleViewWithMockitoTest extends GwtTestWithMockito {

    @Mock
    private GreetingServiceAsync greetingServiceAsync;

    @Test
    public void clickOnButtonShouldDisplayErrorInLabelWhenRpcFailure() {
        // Arrange
        RpcSampleView view = new RpcSampleView();
        view.textBox.setText("Ben Linus");
        view.button.setEnabled(true);
        assertThat(view.label).isNotVisible();

        // mock service failed invocation
        doFailureCallback(new RuntimeException("expected mocked runtime exception")).when(greetingServiceAsync).greetServer(eq("Ben Linus"), any());

        // Act
        Browser.click(view.button);

        // Assert
        Mockito.verify(greetingServiceAsync).greetServer(eq("Ben Linus"), any());
        assertThat(view.label).isVisible().textEquals("Server error: expected mocked runtime exception");
    }

    @Test
    public void clickOnButtonShouldDisplayMessageInLabelWhenRpcSuccess() {
        // Arrange
        RpcSampleView view = new RpcSampleView();
        view.textBox.setText("Ben Linus");
        view.button.setEnabled(true);
        assertThat(view.label).isNotVisible();

        // mock service succeed invocation
        doSuccessCallback("Mocked hello message").when(greetingServiceAsync).greetServer(eq("Ben Linus"), any());

        // Act
        Browser.click(view.button);

        // Assert
        Mockito.verify(greetingServiceAsync).greetServer(eq("Ben Linus"), any());
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
