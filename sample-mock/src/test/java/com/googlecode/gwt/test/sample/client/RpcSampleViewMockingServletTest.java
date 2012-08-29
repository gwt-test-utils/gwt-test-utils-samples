package com.googlecode.gwt.test.sample.client;

import static com.googlecode.gwt.test.assertions.GwtAssertions.assertThat;

import java.lang.reflect.Method;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;

import com.google.gwt.user.server.rpc.AbstractRemoteServiceServlet;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;
import com.googlecode.gwt.test.rpc.ServletMockProviderAdapter;
import com.googlecode.gwt.test.utils.events.Browser;
import com.googlecode.gwt.test.web.MockHttpServletRequest;
import com.googlecode.gwt.test.web.MockServletConfig;
import com.googlecode.gwt.test.web.MockServletContext;

@GwtModule("com.googlecode.gwt.test.sample.MockSample")
public class RpcSampleViewMockingServletTest extends GwtTest {

   /** Servlet API mock helpers from gwt-test-utils **/
   private MockServletConfig mockConfig;
   private MockHttpServletRequest mockRequest;

   @Before
   public void before() {
      // create the ServletConfig object using gwt-test-utils web mock helper
      MockServletContext context = new MockServletContext();
      context.setServerInfo("mocked-server-info");
      this.mockConfig = new MockServletConfig(context);

      // same thing for HttpServletRequest
      this.mockRequest = new MockHttpServletRequest();
      this.mockRequest.addHeader("User-Agent", "mocked-user-agent");

      // use the provided adapter to implement only the methods you need for your test
      setServletMockProvider(new ServletMockProviderAdapter() {

         @Override
         public ServletConfig getMockedConfig(AbstractRemoteServiceServlet remoteService) {
            return mockConfig;
         }

         @Override
         public HttpServletRequest getMockedRequest(AbstractRemoteServiceServlet rpcService,
                  Method rpcMethod) {
            return mockRequest;
         }
      });

   }

   @Test
   public void clickOnButtonShouldDisplayMessageInLabel() {
      // Arrange
      RpcSampleView view = new RpcSampleView();
      view.textBox.setText("Ben Linus");
      view.button.setEnabled(true);
      assertThat(view.label).isNotVisible();

      // Act
      Browser.click(view.button);

      // Assert
      assertThat(view.label).isVisible().textEquals(
               "Hello, Ben Linus!<br><br>I am running mocked-server-info.<br><br>It looks like you are using:<br>mocked-user-agent");
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
