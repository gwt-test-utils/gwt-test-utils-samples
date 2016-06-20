package com.googlecode.gwt.test.sample.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.server.rpc.AbstractRemoteServiceServlet;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.csv.CsvDirectory;
import com.googlecode.gwt.test.csv.CsvMacros;
import com.googlecode.gwt.test.csv.CsvMethod;
import com.googlecode.gwt.test.csv.GwtCsvTest;
import com.googlecode.gwt.test.finder.GwtFinder;
import com.googlecode.gwt.test.finder.Node;
import com.googlecode.gwt.test.finder.NodeObjectFinder;
import com.googlecode.gwt.test.rpc.ServletMockProviderAdapter;
import org.junit.Before;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@GwtModule("com.googlecode.gwt.test.sample.CsvSample")
@CsvDirectory(value = "functional-tests", extension = ".csv")
@CsvMacros("functional-tests/macros")
public class CsvSampleTest extends GwtCsvTest {

    private CsvSample app;

    /**
     * Servlet API mock helpers from gwt-test-utils
     **/
    private MockServletConfig mockConfig;
    private MockHttpServletRequest mockRequest;

    @Before
    public void before() {
        // create the ServletConfig object using gwt-test-utils web mock helper
        MockServletContext context = new MockServletContext();
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
            public HttpServletRequest getMockedRequest(AbstractRemoteServiceServlet rpcService, Method rpcMethod) {
                return mockRequest;
            }
        });

        GwtFinder.registerNodeFinder("app", new NodeObjectFinder() {
            public Object find(Node node) {
                return csvRunner.getNodeValue(app, node);
            }
        });

        GwtFinder.registerNodeFinder("rpcSampleView", new NodeObjectFinder() {
            public Object find(Node node) {
                return csvRunner.getNodeValue(RootPanel.get().getWidget(0), node);
            }
        });
    }

    @Override
    public boolean ensureDebugId() {
        // return true to turn debug ids on in gwt-test-utils
        return true;
    }

    @CsvMethod
    public void initApp() {
        app = new CsvSample();
        app.onModuleLoad();
    }

}
