package com.googlecode.gwt.test.sample.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.sample.server.FooBeanFactory;
import com.googlecode.gwt.test.sample.server.FooBeanFactorySimple;
import com.googlecode.gwt.test.spring.GwtSpringTest;
import com.googlecode.gwt.test.spring.GwtTestContextLoader;
import com.googlecode.gwt.test.utils.events.Browser;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"}, loader = GwtTestContextLoader.class)
@GwtModule("com.googlecode.gwt.test.sample.SpringSample")
public class DemoSpringTest extends GwtSpringTest {

    @Autowired
    private FooBeanFactory fooBeanFactory;

    @Before
    public void beforeDemoSpringTest() throws Exception {
        // Assert this test has been injected object it needs
        assertNotNull(fooBeanFactory);
        assertEquals(FooBeanFactorySimple.class, fooBeanFactory.getClass());
    }

    @Test
    public void click_button() {
        SpringSampleView view = new SpringSampleView();
        view.textBox.setText("Ben Linus");
        view.button.setEnabled(true);
//        assertThat(view.label).isNotVisible();


        assertEquals("", view.label.getText());

        // Act
        Browser.click(view.button);

        // Assert
        assertEquals("Bean \"OCTO\" has been created", view.label.getText());
    }
}
