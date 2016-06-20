package com.googlecode.gwt.test.sample.client;

import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTestWithEasyMock;
import com.googlecode.gwt.test.Mock;
import com.googlecode.gwt.test.utils.events.Browser;
import org.junit.Test;

import static com.googlecode.gwt.test.assertions.GwtAssertions.assertThat;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;

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
        expect(clientGreeting.greet(eq("Ben Linus"))).andReturn("Mocked hello message");

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
        Browser.fillText("John Locke", view.textBox);

        // Assert
        assertThat(view.button).isVisible().isEnabled();
        assertThat(view.label).isNotVisible();
    }

}
